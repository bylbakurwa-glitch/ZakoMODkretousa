/*     */ package zako.opt.gl;
/*     */ import com.mojang.blaze3d.buffers.GpuBuffer;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.textures.GpuTexture;
/*     */ import com.mojang.blaze3d.textures.GpuTextureView;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_11284;
/*     */ import net.minecraft.class_276;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_6367;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.hud.HudGpu;
/*     */ import zako.opt.mixin.gl.RenderTargetAccessor;
/*     */ 
/*     */ public final class RenderScale {
/*     */   @Generated
/*  19 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/*     */   private RenderScale() {
/*  21 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */ 
/*     */   
/*     */   private static class_6367 low;
/*     */   
/*     */   private static class_11284 lowGlobals;
/*     */   
/*     */   private static GpuBuffer fullGlobals;
/*     */   private static Globals globals;
/*     */   private static boolean scaled;
/*     */   
/*     */   public static void globals(Globals update) {
/*  34 */     globals = update;
/*     */   }
/*     */   
/*     */   public static void begin(class_310 mc) {
/*  38 */     boolean on = Features.RENDER_SCALE.on;
/*     */     
/*  40 */     if (on && !ScaleGpu.ready()) {
/*  41 */       Features.RENDER_SCALE.broken = true;
/*  42 */       log.error("Render scale shader did not compile; turned off until restart");
/*  43 */       on = false;
/*     */     } 
/*  45 */     class_276 main = HudGpu.mainTarget(mc);
/*  46 */     int w = main.field_1482, h = main.field_1481;
/*  47 */     if (on) {
/*  48 */       w = Math.max(1, w * Features.RENDER_SCALE.percent / 100);
/*  49 */       h = Math.max(1, h * Features.RENDER_SCALE.percent / 100);
/*     */     } 
/*     */     
/*  52 */     class_276 outline = ((LevelRendererScaleAccessor)mc.field_1769).zakoopt$entityOutlineTarget();
/*  53 */     if (outline != null) {
/*  54 */       fit(outline, w, h);
/*     */     }
/*  56 */     ScaleGpu.fitAlongside(mc, w, h);
/*  57 */     if (!on) {
/*     */       return;
/*     */     }
/*  60 */     if (low == null) {
/*  61 */       low = ScaleGpu.createTarget(w, h);
/*  62 */       lowGlobals = new class_11284();
/*     */     } else {
/*  64 */       fit((class_276)low, w, h);
/*     */     } 
/*  66 */     fullGlobals = RenderSystem.getGlobalSettingsUniform();
/*  67 */     if (globals != null) {
/*  68 */       globals.update(lowGlobals, w, h);
/*     */     }
/*  70 */     swap(main, (class_276)low);
/*  71 */     scaled = true;
/*     */   }
/*     */   
/*     */   public static void end(class_310 mc) {
/*  75 */     if (!scaled) {
/*     */       return;
/*     */     }
/*  78 */     scaled = false;
/*  79 */     class_276 main = HudGpu.mainTarget(mc);
/*  80 */     swap(main, (class_276)low);
/*  81 */     RenderSystem.setGlobalSettingsUniform(fullGlobals);
/*  82 */     ScaleGpu.upscale(low, main);
/*     */   }
/*     */   
/*     */   public static void fit(class_276 target, int w, int h) {
/*  86 */     if (target.field_1482 != w || target.field_1481 != h) {
/*  87 */       target.method_1234(w, h);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void swap(class_276 a, class_276 b) {
/*  92 */     RenderTargetAccessor x = (RenderTargetAccessor)a, y = (RenderTargetAccessor)b;
/*  93 */     int w = a.field_1482, h = a.field_1481;
/*  94 */     a.field_1482 = b.field_1482;
/*  95 */     a.field_1481 = b.field_1481;
/*  96 */     b.field_1482 = w;
/*  97 */     b.field_1481 = h;
/*  98 */     GpuTexture color = x.zakoopt$colorTexture();
/*  99 */     x.zakoopt$colorTexture(y.zakoopt$colorTexture());
/* 100 */     y.zakoopt$colorTexture(color);
/* 101 */     GpuTextureView colorView = x.zakoopt$colorTextureView();
/* 102 */     x.zakoopt$colorTextureView(y.zakoopt$colorTextureView());
/* 103 */     y.zakoopt$colorTextureView(colorView);
/* 104 */     GpuTexture depth = x.zakoopt$depthTexture();
/* 105 */     x.zakoopt$depthTexture(y.zakoopt$depthTexture());
/* 106 */     y.zakoopt$depthTexture(depth);
/* 107 */     GpuTextureView depthView = x.zakoopt$depthTextureView();
/* 108 */     x.zakoopt$depthTextureView(y.zakoopt$depthTextureView());
/* 109 */     y.zakoopt$depthTextureView(depthView);
/*     */   }
/*     */   
/*     */   public static interface Globals {
/*     */     void update(class_11284 param1class_11284, int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\RenderScale.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */