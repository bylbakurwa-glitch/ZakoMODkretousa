/*     */ package zako.opt.gl;
/*     */ 
/*     */ import com.mojang.blaze3d.buffers.GpuBuffer;
/*     */ import com.mojang.blaze3d.buffers.GpuFence;
/*     */ import com.mojang.blaze3d.systems.CommandEncoder;
/*     */ import com.mojang.blaze3d.systems.GpuDevice;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_10865;
/*     */ import net.minecraft.class_10874;
/*     */ import net.minecraft.class_11270;
/*     */ import org.lwjgl.opengl.ARBDirectStateAccess;
/*     */ import org.lwjgl.opengl.GL;
/*     */ import org.lwjgl.opengl.GL11C;
/*     */ import org.lwjgl.opengl.GL15C;
/*     */ import org.lwjgl.opengl.GLCapabilities;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.mixin.gl.GlBufferInvoker;
/*     */ 
/*     */ public final class ImmediateRing
/*     */ {
/*     */   @Generated
/*  31 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); private static final int SEGMENTS = 3; private static final int INITIAL_SEGMENT = 4194304; private static final int MAX_SEGMENT = 134217728; @Generated
/*  32 */   private ImmediateRing() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/*     */ 
/*     */ 
/*     */   
/*  36 */   private static final Map<VertexFormat, Ring> RINGS = new IdentityHashMap<>();
/*  37 */   private static final GpuFence[] FENCES = new GpuFence[3];
/*     */   private static long frame;
/*     */   private static boolean usedThisFrame;
/*     */   private static boolean coherentChecked;
/*     */   private static class_10874 dsa;
/*     */   private static int liveBuilders;
/*     */   
/*     */   private static final class Ring
/*     */   {
/*     */     GpuBuffer buffer;
/*     */     ByteBuffer coherent;
/*     */     int segmentSize;
/*     */     int cursor;
/*     */     int needed;
/*     */   }
/*     */   
/*     */   public static int upload(VertexFormat format, ByteBuffer data) {
/*  54 */     if (!Features.IMMEDIATE_RING.on) {
/*  55 */       return -1;
/*     */     }
/*  57 */     Ring ring = ring(format);
/*  58 */     int stride = format.getVertexSize();
/*  59 */     int length = data.remaining();
/*  60 */     if (ring.coherent != null) {
/*  61 */       long base = MemoryUtil.memAddress0(ring.coherent);
/*  62 */       long address = MemoryUtil.memAddress(data);
/*  63 */       if (address >= base && address + length <= base + ring.coherent.capacity()) {
/*     */         
/*  65 */         usedThisFrame = true;
/*  66 */         return (int)((address - base) / stride);
/*     */       } 
/*     */     } 
/*  69 */     int offset = reserve(ring, stride, length);
/*  70 */     if (offset < 0) {
/*  71 */       return -1;
/*     */     }
/*  73 */     if (ring.coherent != null) {
/*     */       
/*  75 */       MemoryUtil.memCopy(MemoryUtil.memAddress(data), MemoryUtil.memAddress0(ring.coherent) + offset, length);
/*     */     } else {
/*  77 */       CommandEncoder encoder = RenderSystem.getDevice().createCommandEncoder();
/*  78 */       GpuBuffer.MappedView view = encoder.mapBuffer(ring.buffer.slice(offset, length), false, true); 
/*  79 */       try { MemoryUtil.memCopy(MemoryUtil.memAddress(data), MemoryUtil.memAddress(view.data()), length);
/*  80 */         if (view != null) view.close();  } catch (Throwable throwable) { if (view != null)
/*     */           try { view.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; } 
/*  82 */     }  usedThisFrame = true;
/*  83 */     return offset / stride;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long reserveBuilder(VertexFormat format, int bytes) {
/*  88 */     if (!Features.IMMEDIATE_RING.on || !Features.RING_ZERO_COPY.on) {
/*  89 */       return 0L;
/*     */     }
/*  91 */     Ring ring = ring(format);
/*  92 */     if (ring.coherent == null) {
/*  93 */       return 0L;
/*     */     }
/*  95 */     int offset = reserve(ring, format.getVertexSize(), bytes);
/*  96 */     if (offset < 0) {
/*  97 */       return 0L;
/*     */     }
/*  99 */     usedThisFrame = true;
/* 100 */     liveBuilders++;
/* 101 */     return MemoryUtil.memAddress0(ring.coherent) + offset;
/*     */   }
/*     */   
/*     */   public static void builderReleased() {
/* 105 */     liveBuilders--;
/*     */   }
/*     */   
/*     */   private static Ring ring(VertexFormat format) {
/* 109 */     Ring ring = RINGS.computeIfAbsent(format, f -> new Ring());
/* 110 */     if (ring.buffer == null) {
/* 111 */       allocate(ring, format, 4194304);
/* 112 */       ring.cursor = segmentStart(ring);
/*     */     } 
/* 114 */     return ring;
/*     */   }
/*     */   
/*     */   private static int reserve(Ring ring, int stride, int length) {
/* 118 */     int offset = (ring.cursor + stride - 1) / stride * stride;
/* 119 */     if (offset + length > segmentStart(ring) + ring.segmentSize) {
/* 120 */       ring.needed = Math.max(ring.needed, offset - segmentStart(ring) + length);
/* 121 */       return -1;
/*     */     } 
/* 123 */     ring.cursor = offset + length;
/* 124 */     return offset;
/*     */   }
/*     */   
/*     */   public static GpuBuffer buffer(VertexFormat format) {
/* 128 */     return ((Ring)RINGS.get(format)).buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void endFrame() {
/* 134 */     if (!usedThisFrame && RINGS.isEmpty()) {
/*     */       return;
/*     */     }
/* 137 */     int segment = (int)(frame % 3L);
/* 138 */     if (FENCES[segment] != null) {
/* 139 */       FENCES[segment].close();
/*     */     }
/*     */     
/* 142 */     FENCES[segment] = FrameFence.enabled() ? FrameFence.create() : (GpuFence)new class_11270();
/* 143 */     usedThisFrame = false;
/* 144 */     frame++;
/* 145 */     int next = (int)(frame % 3L);
/* 146 */     if (FENCES[next] != null) {
/*     */       
/* 148 */       FENCES[next].awaitCompletion(Long.MAX_VALUE);
/* 149 */       FENCES[next].close();
/* 150 */       FENCES[next] = null;
/*     */     } 
/* 152 */     for (Map.Entry<VertexFormat, Ring> e : RINGS.entrySet()) {
/* 153 */       Ring ring = e.getValue();
/* 154 */       if (ring.needed > 0 && liveBuilders == 0) {
/* 155 */         int size = (int)Math.min(134217728L, Integer.highestOneBit(ring.needed) << 1L);
/*     */         
/* 157 */         if (size > ring.segmentSize) {
/* 158 */           waitAll();
/* 159 */           ring.buffer.close();
/* 160 */           allocate(ring, e.getKey(), size);
/* 161 */           log.info("Immediate ring for {} grown to {} KB per frame", e.getKey(), Integer.valueOf(size >> 10));
/*     */         } 
/* 163 */         ring.needed = 0;
/*     */       } 
/* 165 */       ring.cursor = segmentStart(ring);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int segmentStart(Ring ring) {
/* 170 */     return (int)(frame % 3L) * ring.segmentSize;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean coherentSupported() {
/* 175 */     if (coherentChecked) {
/* 176 */       return (dsa != null);
/*     */     }
/* 178 */     coherentChecked = true;
/* 179 */     GLCapabilities caps = GL.getCapabilities();
/* 180 */     GpuDevice gpuDevice = RenderSystem.getDevice(); if (gpuDevice instanceof class_10865) { class_10865 device = (class_10865)gpuDevice; if ((caps.OpenGL45 || caps.GL_ARB_direct_state_access) && (caps.OpenGL44 || caps.GL_ARB_buffer_storage))
/*     */       {
/* 182 */         dsa = device.method_68401(); }  }
/*     */     
/* 184 */     log.info("Immediate ring: {}", (dsa != null) ? "coherent persistent mapping" : "explicit flush fallback");
/* 185 */     return (dsa != null);
/*     */   }
/*     */   
/*     */   private static void allocate(Ring ring, VertexFormat format, int segmentSize) {
/* 189 */     ring.segmentSize = segmentSize;
/* 190 */     int size = segmentSize * 3;
/* 191 */     Supplier<String> label = () -> "zakoopt immediate ring " + String.valueOf(format);
/* 192 */     ring.coherent = null;
/* 193 */     if (coherentSupported()) {
/* 194 */       int flags = 194;
/* 195 */       int handle = ARBDirectStateAccess.glCreateBuffers();
/* 196 */       ARBDirectStateAccess.glNamedBufferStorage(handle, size, flags);
/* 197 */       ByteBuffer mapped = ARBDirectStateAccess.glMapNamedBufferRange(handle, 0L, size, flags);
/* 198 */       if (mapped != null) {
/* 199 */         ring.coherent = mapped;
/* 200 */         ring.buffer = (GpuBuffer)GlBufferInvoker.zakoopt$create(label, dsa, 34, size, handle, mapped);
/*     */         return;
/*     */       } 
/* 203 */       GL15C.glDeleteBuffers(handle);
/*     */     } 
/* 205 */     ring.buffer = RenderSystem.getDevice().createBuffer(label, 34, size);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void waitAll() {
/* 210 */     GL11C.glFlush();
/* 211 */     for (int i = 0; i < 3; i++) {
/* 212 */       if (FENCES[i] != null) {
/* 213 */         FENCES[i].awaitCompletion(Long.MAX_VALUE);
/* 214 */         FENCES[i].close();
/* 215 */         FENCES[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\ImmediateRing.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */