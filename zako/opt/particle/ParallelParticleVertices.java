/*     */ package zako.opt.particle;
/*     */ import com.mojang.blaze3d.buffers.GpuBufferSlice;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import com.mojang.blaze3d.vertex.VertexFormatElement;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.class_11944;
/*     */ import net.minecraft.class_11977;
/*     */ import net.minecraft.class_290;
/*     */ import net.minecraft.class_3940;
/*     */ import net.minecraft.class_9799;
/*     */ import net.minecraft.class_9848;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.mixin.particle.QuadParticleRenderStateAccessor;
/*     */ import zako.opt.mixin.particle.QuadParticleStorageInvoker;
/*     */ 
/*     */ public final class ParallelParticleVertices {
/*     */   private static final int BLOCK = 256;
/*     */   
/*     */   @Generated
/*     */   private ParallelParticleVertices() {
/*  27 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*  29 */   private static final VertexFormat FORMAT = class_290.field_1584;
/*  30 */   private static final int STRIDE = FORMAT.getVertexSize();
/*  31 */   private static final int POS = FORMAT.getOffset(VertexFormatElement.POSITION);
/*  32 */   private static final int UV = FORMAT.getOffset(VertexFormatElement.UV0);
/*  33 */   private static final int COLOR = FORMAT.getOffset(VertexFormatElement.COLOR);
/*  34 */   private static final int LIGHT = FORMAT.getOffset(VertexFormatElement.UV2);
/*  35 */   private static final boolean LITTLE_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
/*     */ 
/*     */   
/*     */   public static class_11944.class_12041 prepare(class_11944 state, class_11977.class_12051 cache) {
/*  39 */     QuadParticleRenderStateAccessor acc = (QuadParticleRenderStateAccessor)state;
/*  40 */     int particles = acc.zakoopt$particleCount();
/*  41 */     if (particles < 1024 || !LITTLE_ENDIAN || !Features.PARALLEL_PARTICLES.vertices) {
/*  42 */       return null;
/*     */     }
/*     */     
/*  45 */     Map<class_3940.class_11941, class_11944.class_12042> layers = new HashMap<>();
/*  46 */     List<Block> blocks = new ArrayList<>();
/*  47 */     int vertex = 0;
/*  48 */     for (Map.Entry<class_3940.class_11941, Object> e : (Iterable<Map.Entry<class_3940.class_11941, Object>>)acc.zakoopt$particles().entrySet()) {
/*  49 */       QuadParticleStorageInvoker storage = (QuadParticleStorageInvoker)e.getValue();
/*  50 */       int count = storage.zakoopt$count();
/*  51 */       if (count > 0) {
/*  52 */         layers.put(e.getKey(), new class_11944.class_12042(vertex, count * 6));
/*  53 */         for (int from = 0; from < count; from += 256) {
/*  54 */           blocks.add(new Block(storage.zakoopt$floats(), storage.zakoopt$ints(), from, Math.min(count, from + 256), vertex + from * 4));
/*     */         }
/*     */       } 
/*  57 */       vertex += count * 4;
/*     */     } 
/*  59 */     class_9799 bytes = class_9799.method_72201(vertex * STRIDE); 
/*  60 */     try { long base = bytes.method_60808(vertex * STRIDE);
/*  61 */       Throwable error = Workers.run(blocks.size(), b -> write(blocks.get(b), base));
/*  62 */       if (error != null) {
/*  63 */         throw new RuntimeException("zakoopt parallel particle vertices", error);
/*     */       }
/*  65 */       class_9799.class_9800 result = bytes.method_60807();
/*  66 */       if (result == null)
/*  67 */       { class_11944.class_12041 class_120411 = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  76 */         if (bytes != null) bytes.close();  return class_120411; }  class_9799.class_9800 class_98001 = result; try { cache.method_74835(result.method_60817()); if (class_98001 != null) class_98001.close();  } catch (Throwable throwable) { if (class_98001 != null) try { class_98001.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  int indexCount = particles * 6; RenderSystem.getSequentialBuffer(VertexFormat.class_5596.field_27382).method_68274(indexCount); GpuBufferSlice transforms = ParticleTransforms.write(); class_11944.class_12041 class_12041 = new class_11944.class_12041(indexCount, transforms, layers); if (bytes != null) bytes.close();  return class_12041; } catch (Throwable throwable) { if (bytes != null)
/*     */         try { bytes.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */           throw throwable; }
/*  79 */      } private static final class Block extends Record { private final float[] floats; private final int[] ints; private final int from; private final int to; private final int firstVertex; private Block(float[] floats, int[] ints, int from, int to, int firstVertex) { this.floats = floats; this.ints = ints; this.from = from; this.to = to; this.firstVertex = firstVertex; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/particle/ParallelParticleVertices$Block;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/particle/ParallelParticleVertices$Block; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/particle/ParallelParticleVertices$Block;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/particle/ParallelParticleVertices$Block; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/particle/ParallelParticleVertices$Block;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #79	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/particle/ParallelParticleVertices$Block;
/*  79 */       //   0	8	1	o	Ljava/lang/Object; } public float[] floats() { return this.floats; } public int[] ints() { return this.ints; } public int from() { return this.from; } public int to() { return this.to; } public int firstVertex() { return this.firstVertex; }
/*     */      }
/*     */   
/*     */   private static void write(Block block, long base) {
/*  83 */     float[] f = block.floats;
/*  84 */     int[] in = block.ints;
/*  85 */     long ptr = base + block.firstVertex * STRIDE;
/*  86 */     for (int p = block.from; p < block.to; p++) {
/*  87 */       int o = p * 12;
/*  88 */       float x = f[o], y = f[o + 1], z = f[o + 2];
/*  89 */       float qx = f[o + 3], qy = f[o + 4], qz = f[o + 5], qw = f[o + 6];
/*  90 */       float size = f[o + 7], u0 = f[o + 8], u1 = f[o + 9], v0 = f[o + 10], v1 = f[o + 11];
/*  91 */       int color = class_9848.method_61337(in[p * 2]);
/*  92 */       int light = in[p * 2 + 1];
/*     */       
/*  94 */       ptr = vertex(ptr, qx, qy, qz, qw, 1.0F, -1.0F, size, x, y, z, u1, v1, color, light);
/*  95 */       ptr = vertex(ptr, qx, qy, qz, qw, 1.0F, 1.0F, size, x, y, z, u1, v0, color, light);
/*  96 */       ptr = vertex(ptr, qx, qy, qz, qw, -1.0F, 1.0F, size, x, y, z, u0, v0, color, light);
/*  97 */       ptr = vertex(ptr, qx, qy, qz, qw, -1.0F, -1.0F, size, x, y, z, u0, v1, color, light);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long vertex(long ptr, float qx, float qy, float qz, float qw, float vx, float vy, float size, float x, float y, float z, float u, float v, int color, int light) {
/* 104 */     float tx = 2.0F * -qz * vy;
/* 105 */     float ty = 2.0F * qz * vx;
/* 106 */     float tz = 2.0F * (qx * vy - qy * vx);
/* 107 */     float rx = vx + qw * tx + qy * tz - qz * ty;
/* 108 */     float ry = vy + qw * ty + qz * tx - qx * tz;
/* 109 */     float rz = qw * tz + qx * ty - qy * tx;
/* 110 */     MemoryUtil.memPutFloat(ptr + POS, rx * size + x);
/* 111 */     MemoryUtil.memPutFloat(ptr + POS + 4L, ry * size + y);
/* 112 */     MemoryUtil.memPutFloat(ptr + POS + 8L, rz * size + z);
/* 113 */     MemoryUtil.memPutFloat(ptr + UV, u);
/* 114 */     MemoryUtil.memPutFloat(ptr + UV + 4L, v);
/* 115 */     MemoryUtil.memPutInt(ptr + COLOR, color);
/* 116 */     MemoryUtil.memPutInt(ptr + LIGHT, light);
/* 117 */     return ptr + STRIDE;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\ParallelParticleVertices.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */