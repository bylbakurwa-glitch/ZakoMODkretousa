/*     */ package zako.opt.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.textures.GpuTexture;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_1011;
/*     */ import net.minecraft.class_1058;
/*     */ import net.minecraft.class_10868;
/*     */ import net.minecraft.class_2960;
/*     */ import net.minecraft.class_310;
/*     */ import org.lwjgl.opengl.GL;
/*     */ import org.lwjgl.opengl.GL43C;
/*     */ import zako.opt.MixinPlugin;
/*     */ 
/*     */ public final class SkinAtlas
/*     */ {
/*     */   @Generated
/*     */   private SkinAtlas() {
/*  22 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*  23 */   } public static final class_2960 ID = class_2960.method_60655("zakoopt", "skin_atlas");
/*     */   
/*     */   private static final int SIZE = 1024;
/*     */   private static final int SKIN = 64;
/*     */   private static final int PER_ROW = 16;
/*  28 */   private static final Map<class_2960, Slot> SLOTS = new LinkedHashMap<>(64, 0.75F, true);
/*  29 */   private static final ArrayDeque<Integer> FREE = new ArrayDeque<>();
/*     */   private static AtlasTexture texture;
/*     */   private static class_1011 placeholder;
/*     */   private static long frame;
/*     */   private static Boolean supported;
/*     */   
/*     */   private static final class Slot {
/*     */     final int index;
/*     */     final class_1058 sprite;
/*     */     GpuTexture source;
/*     */     long lastFrame;
/*     */     
/*     */     Slot(int index, class_1058 sprite) {
/*  42 */       this.index = index;
/*  43 */       this.sprite = sprite;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void endFrame() {
/*  48 */     frame++;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class_1058 sprite(class_2960 skin) {
/*  53 */     GpuTexture source = class_310.method_1551().method_1531().method_4619(skin).method_68004();
/*     */     
/*  55 */     if (source instanceof class_10868) { class_10868 src = (class_10868)source; if (source.getWidth(0) == 64 && source.getHeight(0) == 64 && !source.isClosed()) {
/*     */ 
/*     */         
/*  58 */         if (supported == null)
/*     */         {
/*  60 */           supported = Boolean.valueOf((!MixinPlugin.VULKAN && ((GL.getCapabilities()).OpenGL43 || (GL.getCapabilities()).GL_ARB_copy_image)));
/*     */         }
/*  62 */         if (!supported.booleanValue()) {
/*  63 */           return null;
/*     */         }
/*  65 */         if (texture == null) {
/*  66 */           texture = new AtlasTexture("zakoopt skin atlas", 1024);
/*  67 */           class_310.method_1551().method_1531().method_4616(ID, texture);
/*  68 */           placeholder = new class_1011(1, 1, false);
/*  69 */           for (int i = 0; i < 256; i++) {
/*  70 */             FREE.add(Integer.valueOf(i));
/*     */           }
/*     */         } 
/*  73 */         Slot slot = SLOTS.get(skin);
/*  74 */         if (slot == null) {
/*  75 */           slot = allocate(skin);
/*  76 */           if (slot == null) {
/*  77 */             return null;
/*     */           }
/*     */         } 
/*  80 */         if (slot.source != source) {
/*  81 */           int x = slot.index % 16 * 64, y = slot.index / 16 * 64;
/*     */           
/*  83 */           GL43C.glCopyImageSubData(src.method_68427(), 3553, 0, 0, 0, 0, ((class_10868)texture
/*  84 */               .method_68004()).method_68427(), 3553, 0, x, y, 0, 64, 64, 1);
/*  85 */           slot.source = source;
/*     */         } 
/*  87 */         slot.lastFrame = frame;
/*  88 */         return slot.sprite;
/*     */       }  }
/*     */     
/*     */     return null; } private static Slot allocate(class_2960 skin) {
/*  92 */     Integer index = FREE.poll();
/*  93 */     if (index == null) {
/*  94 */       Iterator<Map.Entry<class_2960, Slot>> eldest = SLOTS.entrySet().iterator();
/*  95 */       Slot victim = (Slot)((Map.Entry)eldest.next()).getValue();
/*  96 */       if (victim.lastFrame == frame)
/*     */       {
/*  98 */         return null;
/*     */       }
/* 100 */       eldest.remove();
/* 101 */       index = Integer.valueOf(victim.index);
/*     */     } 
/* 103 */     int x = index.intValue() % 16 * 64, y = index.intValue() / 16 * 64;
/* 104 */     Slot slot = new Slot(index.intValue(), new AtlasSprite(ID, AtlasSprite.contents(skin, 64, placeholder), 1024, x, y));
/* 105 */     SLOTS.put(skin, slot);
/* 106 */     return slot;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\SkinAtlas.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */