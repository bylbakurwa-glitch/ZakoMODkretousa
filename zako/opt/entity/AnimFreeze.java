/*     */ package zako.opt.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_10017;
/*     */ import net.minecraft.class_1058;
/*     */ import net.minecraft.class_1297;
/*     */ import net.minecraft.class_1921;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_4588;
/*     */ import net.minecraft.class_9799;
/*     */ import net.minecraft.class_9801;
/*     */ import org.joml.Matrix4f;
/*     */ import org.lwjgl.system.MemoryStack;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.gl.RawVertices;
/*     */ 
/*     */ public final class AnimFreeze
/*     */ {
/*     */   public static long frame;
/*     */   
/*     */   @Generated
/*     */   private AnimFreeze() {
/*  29 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*  31 */   private static final class_9799 SCRATCH = new class_9799(16384);
/*  32 */   private static ByteBuffer emitBuffer = MemoryUtil.memAlloc(16384);
/*     */   
/*     */   public static final class Holder
/*     */   {
/*  36 */     private final List<AnimFreeze.Capture> captures = new ArrayList<>(4);
/*     */   }
/*     */   
/*     */   static final class Capture
/*     */   {
/*     */     private Object model;
/*     */     private class_1921 type;
/*     */     private class_1058 sprite;
/*     */     private int ordinal;
/*  45 */     private long usedFrame = -1L;
/*  46 */     private long tick = Long.MIN_VALUE;
/*     */     private ByteBuffer data;
/*     */     private int count;
/*     */     private float x;
/*     */     private float y;
/*     */     private float z; }
/*     */   
/*     */   public static boolean freezes(Object state, Matrix4f pose) {
/*  54 */     if (Features.ENTITY_ANIM_FREEZE.on && state instanceof class_10017) { class_10017 s = (class_10017)state; if (((StateEntity)s).zakoopt$entity() != null) {
/*     */ 
/*     */         
/*  57 */         float d = Features.ENTITY_ANIM_FREEZE.distance;
/*  58 */         return (pose.m30() * pose.m30() + pose.m31() * pose.m31() + pose.m32() * pose.m32() > d * d);
/*     */       }  }
/*     */     
/*     */     return false;
/*     */   }
/*     */   public static boolean render(class_1297 entity, Object model, class_1921 type, class_1058 sprite, Matrix4f pose, class_4588 target, Consumer<class_4588> draw) {
/*  64 */     float x = pose.m30(), y = pose.m31(), z = pose.m32();
/*  65 */     float d = Features.ENTITY_ANIM_FREEZE.distance;
/*  66 */     if (x * x + y * y + z * z <= d * d || !RawVertices.accepts(target, type.method_23031())) {
/*  67 */       return false;
/*     */     }
/*  69 */     AnimHolder owner = (AnimHolder)entity;
/*  70 */     Holder holder = owner.zakoopt$anim();
/*  71 */     if (holder == null) {
/*  72 */       holder = new Holder();
/*  73 */       owner.zakoopt$anim(holder);
/*     */     } 
/*  75 */     Capture capture = find(holder, model, type, sprite);
/*  76 */     long tick = entity.method_73183().method_75260();
/*  77 */     if (capture.tick != tick) {
/*  78 */       class_287 builder = TypeBuilders.create(SCRATCH, type);
/*  79 */       draw.accept((sprite == null) ? (class_4588)builder : sprite.method_24108((class_4588)builder));
/*  80 */       capture.count = 0;
/*  81 */       class_9801 mesh = builder.method_60794(); 
/*  82 */       try { if (mesh != null) {
/*  83 */           ByteBuffer src = mesh.method_60818();
/*  84 */           int bytes = src.remaining();
/*  85 */           if (capture.data == null || capture.data.capacity() < bytes) {
/*  86 */             capture.data = ByteBuffer.allocateDirect(bytes * 2);
/*     */           }
/*  88 */           MemoryUtil.memCopy(MemoryUtil.memAddress(src), MemoryUtil.memAddress0(capture.data), bytes);
/*  89 */           capture.count = mesh.method_60822().comp_750();
/*     */         } 
/*  91 */         if (mesh != null) mesh.close();  } catch (Throwable throwable) { if (mesh != null)
/*  92 */           try { mesh.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  capture.tick = tick;
/*  93 */       capture.x = x;
/*  94 */       capture.y = y;
/*  95 */       capture.z = z;
/*     */     } 
/*  97 */     if (capture.count > 0) {
/*  98 */       emit(capture, type.method_23031(), x - capture.x, y - capture.y, z - capture.z, target);
/*     */     }
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Capture find(Holder holder, Object model, class_1921 type, class_1058 sprite) {
/* 105 */     int ordinal = 0;
/* 106 */     for (Capture capture : holder.captures) {
/* 107 */       if (capture.model == model && capture.type == type && capture.sprite == sprite && capture.usedFrame == frame) {
/* 108 */         ordinal++;
/*     */       }
/*     */     } 
/* 111 */     for (Capture capture : holder.captures) {
/* 112 */       if (capture.model == model && capture.type == type && capture.sprite == sprite && capture.ordinal == ordinal) {
/* 113 */         capture.usedFrame = frame;
/* 114 */         return capture;
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     holder.captures.removeIf(old -> (old.usedFrame < frame - 1L));
/* 119 */     Capture c = new Capture();
/* 120 */     c.model = model;
/* 121 */     c.type = type;
/* 122 */     c.sprite = sprite;
/* 123 */     c.ordinal = ordinal;
/* 124 */     c.usedFrame = frame;
/* 125 */     holder.captures.add(c);
/* 126 */     return c;
/*     */   }
/*     */   
/*     */   private static void emit(Capture capture, VertexFormat format, float dx, float dy, float dz, class_4588 target) {
/* 130 */     int stride = format.getVertexSize();
/* 131 */     int pos = TypeBuilders.positionOffset(format);
/* 132 */     int size = capture.count * stride;
/* 133 */     if (emitBuffer.capacity() < size) {
/* 134 */       emitBuffer = MemoryUtil.memRealloc(emitBuffer, size * 2);
/*     */     }
/* 136 */     long dst = MemoryUtil.memAddress0(emitBuffer);
/* 137 */     MemoryUtil.memCopy(MemoryUtil.memAddress0(capture.data), dst, size); long v, end;
/* 138 */     for (v = dst + pos, end = dst + size; v < end; v += stride) {
/* 139 */       MemoryUtil.memPutFloat(v, MemoryUtil.memGetFloat(v) + dx);
/* 140 */       MemoryUtil.memPutFloat(v + 4L, MemoryUtil.memGetFloat(v + 4L) + dy);
/* 141 */       MemoryUtil.memPutFloat(v + 8L, MemoryUtil.memGetFloat(v + 8L) + dz);
/*     */     } 
/* 143 */     MemoryStack stack = MemoryStack.stackPush(); try {
/* 144 */       RawVertices.push(target, stack, dst, capture.count, format);
/* 145 */       if (stack != null) stack.close(); 
/*     */     } catch (Throwable throwable) {
/*     */       if (stack != null)
/*     */         try {
/*     */           stack.close();
/*     */         } catch (Throwable throwable1) {
/*     */           throwable.addSuppressed(throwable1);
/*     */         }  
/*     */       throw throwable;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\AnimFreeze.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */