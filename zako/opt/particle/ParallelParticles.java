/*     */ package zako.opt.particle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_11944;
/*     */ import net.minecraft.class_128;
/*     */ import net.minecraft.class_148;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_3940;
/*     */ import net.minecraft.class_4184;
/*     */ import net.minecraft.class_4604;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.mixin.particle.ParticleAccessor;
/*     */ import zako.opt.mixin.particle.QuadParticleRenderStateAccessor;
/*     */ import zako.opt.mixin.particle.QuadParticleStorageInvoker;
/*     */ 
/*     */ public final class ParallelParticles {
/*     */   private static final int MIN_PARTICLES = 1024;
/*     */   
/*     */   @Generated
/*     */   private ParallelParticles() {
/*  24 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static final int BLOCK = 256;
/*  27 */   private static final List<class_11944> LOCAL = new ArrayList<>();
/*  28 */   private static class_3940[] scratch = new class_3940[0];
/*     */   
/*     */   private static boolean enabled() {
/*  31 */     return Features.PARALLEL_PARTICLES.on;
/*     */   }
/*     */   
/*     */   public static boolean extract(Queue<class_3940> particles, class_11944 dest, class_4604 frustum, class_4184 camera, float partialTick) {
/*     */     Throwable error;
/*  36 */     int n = particles.size();
/*  37 */     if (n < 1024 || !enabled()) {
/*  38 */       return false;
/*     */     }
/*  40 */     class_3940[] arr = (class_3940[])particles.toArray((Object[])scratch);
/*  41 */     scratch = arr;
/*  42 */     int blocks = (n + 256 - 1) / 256;
/*  43 */     while (LOCAL.size() < blocks) {
/*  44 */       LOCAL.add(new class_11944());
/*     */     }
/*  46 */     class_243 cam = camera.method_71156();
/*     */     
/*     */     try {
/*  49 */       error = Workers.run(blocks, b -> extractRange(arr, b * 256, Math.min(n, (b + 1) * 256), LOCAL.get(b), frustum, camera, cam, partialTick));
/*     */     } finally {
/*  51 */       Arrays.fill((Object[])arr, 0, n, (Object)null);
/*     */     } 
/*  53 */     if (error != null) {
/*  54 */       throw new class_148(class_128.method_560(error, "Rendering Particle (zakoopt parallel)"));
/*     */     }
/*  56 */     for (int b = 0; b < blocks; b++) {
/*  57 */       class_11944 local = LOCAL.get(b);
/*  58 */       merge(local, dest);
/*  59 */       local.method_74316();
/*     */     } 
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void extractRange(class_3940[] arr, int from, int to, class_11944 out, class_4604 frustum, class_4184 camera, class_243 cam, float partialTick) {
/*  66 */     for (int i = from; i < to; i++) {
/*  67 */       class_3940 p = arr[i];
/*  68 */       ParticleAccessor pos = (ParticleAccessor)p;
/*  69 */       double x = pos.zakoopt$x(), y = pos.zakoopt$y(), z = pos.zakoopt$z();
/*  70 */       if (frustum.method_74404(x, y, z) && ParticleLod.keep(p, x, y, z, cam)) {
/*  71 */         p.method_3074(out, camera, partialTick);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void merge(class_11944 from, class_11944 to) {
/*  77 */     QuadParticleRenderStateAccessor toAcc = (QuadParticleRenderStateAccessor)to;
/*  78 */     for (Map.Entry<class_3940.class_11941, Object> e : (Iterable<Map.Entry<class_3940.class_11941, Object>>)((QuadParticleRenderStateAccessor)from).zakoopt$particles().entrySet()) {
/*  79 */       QuadParticleStorageInvoker src = (QuadParticleStorageInvoker)e.getValue();
/*  80 */       int n = src.zakoopt$count();
/*  81 */       if (n == 0) {
/*     */         continue;
/*     */       }
/*  84 */       Object existing = toAcc.zakoopt$particles().get(e.getKey());
/*  85 */       if (existing == null) {
/*     */         
/*  87 */         float[] f = src.zakoopt$floats();
/*  88 */         int[] in = src.zakoopt$ints();
/*  89 */         to.method_74323(e.getKey(), f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], f[8], f[9], f[10], f[11], in[0], in[1]);
/*  90 */         copy(src, 1, n - 1, (QuadParticleStorageInvoker)toAcc.zakoopt$particles().get(e.getKey()));
/*     */       } else {
/*  92 */         copy(src, 0, n, (QuadParticleStorageInvoker)existing);
/*     */       } 
/*  94 */       toAcc.zakoopt$setParticleCount(toAcc.zakoopt$particleCount() + n - ((existing == null) ? 1 : 0));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void copy(QuadParticleStorageInvoker src, int from, int n, QuadParticleStorageInvoker dst) {
/*  99 */     if (n <= 0) {
/*     */       return;
/*     */     }
/* 102 */     int at = dst.zakoopt$count();
/* 103 */     while (dst.zakoopt$capacity() < at + n) {
/* 104 */       dst.zakoopt$grow();
/*     */     }
/* 106 */     System.arraycopy(src.zakoopt$floats(), from * 12, dst.zakoopt$floats(), at * 12, n * 12);
/* 107 */     System.arraycopy(src.zakoopt$ints(), from * 2, dst.zakoopt$ints(), at * 2, n * 2);
/* 108 */     dst.zakoopt$setCount(at + n);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\ParallelParticles.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */