/*     */ package zako.opt.gl;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lombok.Generated;
/*     */ import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.LocalSectionIndex;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.data.SectionRenderDataStorage;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.lists.ChunkRenderList;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.lists.ChunkRenderListIterable;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.region.RenderRegion;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
/*     */ import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
/*     */ import net.caffeinemc.mods.sodium.client.render.viewport.CameraTransform;
/*     */ import net.caffeinemc.mods.sodium.client.util.iterator.ByteIterator;
/*     */ import net.minecraft.class_276;
/*     */ import net.minecraft.class_4076;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Matrix4fc;
/*     */ import org.lwjgl.opengl.GL;
/*     */ import org.lwjgl.opengl.GL11C;
/*     */ import org.lwjgl.opengl.GL13C;
/*     */ import org.lwjgl.opengl.GL15C;
/*     */ import org.lwjgl.opengl.GL20C;
/*     */ import org.lwjgl.opengl.GL30C;
/*     */ import org.lwjgl.opengl.GL32C;
/*     */ import org.lwjgl.opengl.GL33C;
/*     */ import org.lwjgl.opengl.GLCapabilities;
/*     */ import org.slf4j.Logger;
/*     */ import zako.opt.DebugToggle;
/*     */ import zako.opt.feature.Features;
/*     */ 
/*     */ public final class OcclusionCull {
/*     */   @Generated
/*  38 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); private static final int SLOTS = 3; private static final int BLOCK = 8; private static final double NEAR = 24.0D; private static final double MOVE = 0.5D; @Generated
/*     */   private OcclusionCull() {
/*  40 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */ 
/*     */   
/*     */   private static final int EXPIRE = 6;
/*     */   
/*     */   private static final int MAX_TEXELS = 4096;
/*     */   
/*     */   private static final int TRIAL = 40;
/*     */   
/*     */   private static final long RETRIAL_NS = 20000000000L;
/*     */   
/*     */   private static final double MARGIN = 0.98D;
/*  53 */   private static final boolean FORCED = Boolean.getBoolean("zakoopt.occlusion_cull.force"); private static int program; private static int vao; private static int fbo; private static int texture; private static int uDepth; private static int uBlock; private static int uSize; private static int uReversed; private static int texWidth; private static int texHeight;
/*  54 */   private static final TerrainRenderPass[] PASSES = new TerrainRenderPass[] { DefaultTerrainRenderPasses.SOLID, DefaultTerrainRenderPasses.CUTOUT, DefaultTerrainRenderPasses.TRANSLUCENT }; private static boolean ready;
/*     */   private static boolean failed;
/*     */   private static boolean armed;
/*     */   private static int frame;
/*     */   private static long lastPassNs;
/*     */   private static double camX;
/*     */   private static double camY;
/*     */   private static double camZ;
/*     */   private static boolean worth = true;
/*  63 */   private static int trialFrame = -1; private static long trialEndNs;
/*     */   private static long lastFrameNs;
/*  65 */   private static final long[][] trialTimes = new long[2][80];
/*     */   
/*  67 */   private static final int[] pbos = new int[3];
/*  68 */   private static final long[] fences = new long[3];
/*  69 */   private static final int[] slotFrame = new int[3]; private static final int[] slotWidth = new int[3]; private static final int[] slotHeight = new int[3];
/*  70 */   private static final Matrix4f[] slotMatrix = new Matrix4f[] { new Matrix4f(), new Matrix4f(), new Matrix4f() };
/*     */   
/*  72 */   private static final boolean[] slotReversed = new boolean[3]; private static final boolean[] slotZeroToOne = new boolean[3];
/*  73 */   private static final double[][] slotCamera = new double[3][3];
/*     */ 
/*     */   
/*  76 */   private static float[] depth = new float[0]; private static int depthWidth; private static int depthHeight;
/*  77 */   private static int depthFrame = -1073741824;
/*  78 */   private static final Matrix4f depthMatrix = new Matrix4f(); private static boolean depthReversed;
/*     */   private static boolean depthZeroToOne;
/*     */   private static double depthX;
/*     */   private static double depthY;
/*     */   private static double depthZ;
/*  83 */   private static final Long2IntOpenHashMap verdicts = new Long2IntOpenHashMap();
/*  84 */   private static final Filter filter = new Filter();
/*  85 */   private static final SectionRenderDataStorage[] storages = new SectionRenderDataStorage[PASSES.length];
/*  86 */   private static final float[] corner = new float[4]; private static long sections; private static long culled; private static long weightAll; private static long weightCulled; private static long cpuNs;
/*     */   private static long statFrames;
/*     */   private static long windowStart;
/*  89 */   private static String line = "Occlusion: collecting"; private static boolean cpu;
/*     */   
/*     */   static {
/*  92 */     verdicts.defaultReturnValue(-1);
/*  93 */     DebugInfo.EXTRA.add(() -> Features.OCCLUSION_CULL.on ? List.<String>of(line) : List.of());
/*     */   }
/*     */   
/*     */   public static String line() {
/*  97 */     return line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void beginPass(TerrainRenderPass pass, CameraTransform camera, ChunkRenderMatrices matrices) {
/* 104 */     if (pass != DefaultTerrainRenderPasses.SOLID || failed) {
/*     */       return;
/*     */     }
/* 107 */     cpu = Boolean.getBoolean("zakoopt.occlusion_cull.cpu");
/* 108 */     long now = System.nanoTime();
/* 109 */     if (now - lastPassNs > 1000000000L) {
/* 110 */       depthFrame = -1073741824;
/*     */     }
/* 112 */     trial(now);
/* 113 */     lastPassNs = now;
/* 114 */     frame++;
/* 115 */     camX = camera.x;
/* 116 */     camY = camera.y;
/* 117 */     camZ = camera.z;
/* 118 */     armed = true;
/* 119 */     if (cpu) {
/* 120 */       long start = System.nanoTime();
/* 121 */       if (culling()) {
/* 122 */         class_276 target = MainView.target();
/* 123 */         boolean rev = reversed(matrices), z01 = zeroToOne();
/* 124 */         depthMatrix.set(matrices.projection()).mul(matrices.modelView());
/* 125 */         CpuOcclusion.build(depthMatrix, camX, camY, camZ, rev, z01, target.field_1482 / target.field_1481);
/* 126 */         depth = CpuOcclusion.depth;
/* 127 */         depthWidth = CpuOcclusion.width;
/* 128 */         depthHeight = CpuOcclusion.height;
/* 129 */         depthReversed = rev;
/* 130 */         depthZeroToOne = z01;
/* 131 */         depthX = camX;
/* 132 */         depthY = camY;
/* 133 */         depthZ = camZ;
/* 134 */         depthFrame = frame;
/*     */       } 
/* 136 */       cpuNs += System.nanoTime() - start;
/*     */       return;
/*     */     } 
/* 139 */     if (ready) {
/* 140 */       long start = System.nanoTime();
/* 141 */       collect();
/* 142 */       cpuNs += System.nanoTime() - start;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void endPass(ChunkRenderMatrices matrices, ChunkRenderListIterable lists, TerrainRenderPass pass, CameraTransform camera) {
/* 147 */     if (pass != DefaultTerrainRenderPasses.CUTOUT || !armed || failed) {
/*     */       return;
/*     */     }
/* 150 */     armed = false;
/* 151 */     if (cpu) {
/* 152 */       if (DebugToggle.visible()) {
/* 153 */         count(lists);
/*     */       }
/* 155 */       statFrames++;
/* 156 */       window();
/*     */       return;
/*     */     } 
/* 159 */     if (GlTargets.depth(MainView.target()) < 0) {
/*     */       return;
/*     */     }
/* 162 */     if (!ready && !init()) {
/*     */       return;
/*     */     }
/* 165 */     long start = System.nanoTime();
/* 166 */     if (culling()) {
/* 167 */       capture(frame % 3, matrices, camera);
/*     */     }
/* 169 */     if (DebugToggle.visible()) {
/* 170 */       count(lists);
/*     */     }
/* 172 */     cpuNs += System.nanoTime() - start;
/* 173 */     statFrames++;
/* 174 */     window();
/*     */   }
/*     */ 
/*     */   
/*     */   public static ByteIterator filter(ByteIterator sections, RenderRegion region) {
/* 179 */     if (sections == null || (!ready && !cpu)) {
/* 180 */       return sections;
/*     */     }
/* 182 */     filter.in = sections;
/* 183 */     filter.region = region;
/* 184 */     filter.advance();
/* 185 */     return filter;
/*     */   }
/*     */   
/*     */   private static boolean hidden(long key, int cx, int cy, int cz) {
/* 189 */     int v = verdicts.get(key);
/* 190 */     if (v >= 0 && v >>> 1 == frame) {
/* 191 */       return ((v & 0x1) != 0);
/*     */     }
/* 193 */     boolean hidden = test(cx, cy, cz);
/* 194 */     verdicts.put(key, frame << 1 | (hidden ? 1 : 0));
/* 195 */     return hidden;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean culling() {
/* 200 */     if (FORCED) {
/* 201 */       return true;
/*     */     }
/* 203 */     return (trialFrame < 0) ? worth : ((trialFrame / 40 % 2 == 0));
/*     */   }
/*     */   
/*     */   private static void trial(long now) {
/* 207 */     long dt = now - lastFrameNs;
/* 208 */     lastFrameNs = now;
/* 209 */     if (trialFrame < 0) {
/* 210 */       if (now > trialEndNs + 20000000000L) {
/* 211 */         trialFrame = 0;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 216 */     if (trialFrame % 40 != 0 && dt > 0L && dt < 200000000L) {
/* 217 */       int run = trialFrame / 40;
/* 218 */       trialTimes[run % 2][run / 2 * 40 + trialFrame % 40] = dt;
/*     */     } 
/* 220 */     if (++trialFrame == 160) {
/* 221 */       long on = median(trialTimes[0]), off = median(trialTimes[1]);
/* 222 */       worth = (on < off * 0.98D);
/* 223 */       log.info("Occlusion culling {}: {} us a frame with it, {} us without", new Object[] { worth ? "on" : "off", Long.valueOf(on / 1000L), Long.valueOf(off / 1000L) });
/* 224 */       trialFrame = -1;
/* 225 */       trialEndNs = now;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static long median(long[] times) {
/* 230 */     long[] sorted = Arrays.stream(times).filter(t -> (t > 0L)).sorted().toArray();
/* 231 */     Arrays.fill(times, 0L);
/* 232 */     return (sorted.length == 0) ? Long.MAX_VALUE : sorted[sorted.length / 2];
/*     */   }
/*     */   
/*     */   private static boolean usable() {
/* 236 */     double mx = camX - depthX, my = camY - depthY, mz = camZ - depthZ;
/* 237 */     return (frame - depthFrame <= 6 && mx * mx + my * my + mz * mz <= 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean test(int cx, int cy, int cz) {
/* 242 */     if (cpu && Boolean.getBoolean("zakoopt.occlusion_cull.ceiling")) {
/* 243 */       return (((cy << 4) + 16) < camY - 24.0D);
/*     */     }
/* 245 */     if (!usable()) {
/* 246 */       return false;
/*     */     }
/* 248 */     int x = cx << 4, y = cy << 4, z = cz << 4;
/* 249 */     double dx = Math.max(Math.max(x - camX, camX - x - 16.0D), 0.0D);
/* 250 */     double dy = Math.max(Math.max(y - camY, camY - y - 16.0D), 0.0D);
/* 251 */     double dz = Math.max(Math.max(z - camZ, camZ - z - 16.0D), 0.0D);
/* 252 */     if (dx * dx + dy * dy + dz * dz < 576.0D) {
/* 253 */       return false;
/*     */     }
/* 255 */     float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, maxX = -3.4028235E38F, maxY = -3.4028235E38F, nearest = depthReversed ? -3.4028235E38F : Float.MAX_VALUE;
/* 256 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 258 */       float px = (float)((x - 1 + (((i & 0x1) == 0) ? 0 : 18)) - depthX);
/* 259 */       float py = (float)((y - 1 + (((i & 0x2) == 0) ? 0 : 18)) - depthY);
/* 260 */       float pz = (float)((z - 1 + (((i & 0x4) == 0) ? 0 : 18)) - depthZ);
/* 261 */       Matrix4f m = depthMatrix;
/* 262 */       float w = m.m03() * px + m.m13() * py + m.m23() * pz + m.m33();
/* 263 */       if (w < 0.05F) {
/* 264 */         return false;
/*     */       }
/* 266 */       float nx = (m.m00() * px + m.m10() * py + m.m20() * pz + m.m30()) / w;
/* 267 */       float ny = (m.m01() * px + m.m11() * py + m.m21() * pz + m.m31()) / w;
/* 268 */       float nz = (m.m02() * px + m.m12() * py + m.m22() * pz + m.m32()) / w;
/* 269 */       minX = Math.min(minX, nx);
/* 270 */       maxX = Math.max(maxX, nx);
/* 271 */       minY = Math.min(minY, ny);
/* 272 */       maxY = Math.max(maxY, ny);
/* 273 */       nearest = depthReversed ? Math.max(nearest, nz) : Math.min(nearest, nz);
/*     */     } 
/*     */     
/* 276 */     if (maxX < -1.0F || minX > 1.0F || maxY < -1.0F || minY > 1.0F) {
/* 277 */       return false;
/*     */     }
/* 279 */     minX = Math.max(minX, -1.0F);
/* 280 */     maxX = Math.min(maxX, 1.0F);
/* 281 */     minY = Math.max(minY, -1.0F);
/* 282 */     maxY = Math.min(maxY, 1.0F);
/* 283 */     int x0 = (int)((minX * 0.5F + 0.5F) * depthWidth), x1 = Math.min(depthWidth - 1, (int)((maxX * 0.5F + 0.5F) * depthWidth));
/* 284 */     int y0 = (int)((minY * 0.5F + 0.5F) * depthHeight), y1 = Math.min(depthHeight - 1, (int)((maxY * 0.5F + 0.5F) * depthHeight));
/* 285 */     if ((x1 - x0 + 1) * (y1 - y0 + 1) > (cpu ? (depthWidth * depthHeight) : 4096)) {
/* 286 */       return false;
/*     */     }
/* 288 */     float boxDepth = depthZeroToOne ? nearest : (nearest * 0.5F + 0.5F);
/* 289 */     for (int ty = y0; ty <= y1; ty++) {
/* 290 */       int row = ty * depthWidth;
/* 291 */       for (int tx = x0; tx <= x1; tx++) {
/* 292 */         if (depthReversed ? (depth[row + tx] <= boxDepth) : (depth[row + tx] >= boxDepth)) {
/* 293 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 297 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void collect() {
/* 302 */     int newest = -1; int s;
/* 303 */     for (s = 0; s < 3; s++) {
/* 304 */       if (fences[s] != 0L && GL32C.glClientWaitSync(fences[s], 0, 0L) != 37147 && (newest < 0 || slotFrame[s] > slotFrame[newest])) {
/* 305 */         newest = s;
/*     */       }
/*     */     } 
/* 308 */     if (newest < 0) {
/*     */       return;
/*     */     }
/* 311 */     for (s = 0; s < 3; s++) {
/* 312 */       if (fences[s] != 0L && slotFrame[s] <= slotFrame[newest]) {
/* 313 */         GL32C.glDeleteSync(fences[s]);
/* 314 */         fences[s] = 0L;
/*     */       } 
/*     */     } 
/* 317 */     int w = slotWidth[newest], h = slotHeight[newest];
/* 318 */     if (depth.length < w * h) {
/* 319 */       depth = new float[w * h];
/*     */     }
/* 321 */     int prev = GL11C.glGetInteger(35053);
/* 322 */     GL15C.glBindBuffer(35051, pbos[newest]);
/* 323 */     ByteBuffer mapped = GL30C.glMapBufferRange(35051, 0L, w * h * 4L, 1);
/* 324 */     if (mapped != null) {
/* 325 */       mapped.order(ByteOrder.nativeOrder()).asFloatBuffer().get(depth, 0, w * h);
/* 326 */       GL15C.glUnmapBuffer(35051);
/* 327 */       depthWidth = w;
/* 328 */       depthHeight = h;
/* 329 */       depthFrame = slotFrame[newest];
/* 330 */       depthMatrix.set((Matrix4fc)slotMatrix[newest]);
/* 331 */       depthReversed = slotReversed[newest];
/* 332 */       depthZeroToOne = slotZeroToOne[newest];
/* 333 */       depthX = slotCamera[newest][0];
/* 334 */       depthY = slotCamera[newest][1];
/* 335 */       depthZ = slotCamera[newest][2];
/*     */     } 
/* 337 */     GL15C.glBindBuffer(35051, prev);
/*     */   }
/*     */   
/*     */   private static void capture(int s, ChunkRenderMatrices matrices, CameraTransform camera) {
/* 341 */     if (fences[s] != 0L) {
/*     */       return;
/*     */     }
/* 344 */     GpuTimers.mark("occlusion depth");
/* 345 */     class_276 target = MainView.target();
/* 346 */     int srcW = target.field_1482, srcH = target.field_1481;
/* 347 */     int w = (srcW + 8 - 1) / 8, h = (srcH + 8 - 1) / 8;
/*     */     
/* 349 */     int prevDraw = GL11C.glGetInteger(36006), prevRead = GL11C.glGetInteger(36010);
/* 350 */     int prevProgram = GL11C.glGetInteger(35725), prevVao = GL11C.glGetInteger(34229);
/* 351 */     int prevUnit = GL11C.glGetInteger(34016);
/* 352 */     GL13C.glActiveTexture(33984);
/* 353 */     int prevTexture = GL11C.glGetInteger(32873);
/* 354 */     int prevPack = GL11C.glGetInteger(35053);
/* 355 */     int[] viewport = new int[4];
/* 356 */     GL11C.glGetIntegerv(2978, viewport);
/* 357 */     boolean depthTest = GL11C.glIsEnabled(2929), blend = GL11C.glIsEnabled(3042), cull = GL11C.glIsEnabled(2884);
/* 358 */     boolean scissor = GL11C.glIsEnabled(3089);
/* 359 */     boolean depthMask = GL11C.glGetBoolean(2930);
/*     */     
/* 361 */     if (w != texWidth || h != texHeight) {
/* 362 */       texWidth = w;
/* 363 */       texHeight = h;
/* 364 */       GL11C.glBindTexture(3553, texture);
/* 365 */       GL11C.glTexImage2D(3553, 0, 33326, w, h, 0, 6403, 5126, (ByteBuffer)null);
/* 366 */       for (int p = 0; p < 3; p++) {
/* 367 */         GL15C.glBindBuffer(35051, pbos[p]);
/* 368 */         GL15C.glBufferData(35051, w * h * 4L, 35041);
/*     */       } 
/*     */     } 
/*     */     
/* 372 */     GL30C.glBindFramebuffer(36160, fbo);
/* 373 */     GL11C.glViewport(0, 0, w, h);
/* 374 */     GL11C.glDisable(2929);
/* 375 */     GL11C.glDisable(3042);
/* 376 */     GL11C.glDisable(2884);
/* 377 */     GL11C.glDisable(3089);
/* 378 */     GL11C.glDepthMask(false);
/* 379 */     GL20C.glUseProgram(program);
/* 380 */     GL30C.glBindVertexArray(vao);
/*     */     
/* 382 */     int prevSampler = GL11C.glGetInteger(35097);
/*     */     
/* 384 */     GL33C.glBindSampler(0, 0);
/*     */     
/* 386 */     GL11C.glBindTexture(3553, GlTargets.depth(target));
/* 387 */     GL20C.glUniform1i(uDepth, 0);
/* 388 */     GL20C.glUniform2i(uBlock, 8, 8);
/* 389 */     GL20C.glUniform2i(uSize, srcW, srcH);
/* 390 */     boolean reversed = reversed(matrices);
/* 391 */     GL20C.glUniform1i(uReversed, reversed ? 1 : 0);
/* 392 */     GL11C.glDrawArrays(4, 0, 3);
/*     */     
/* 394 */     GL15C.glBindBuffer(35051, pbos[s]);
/*     */     
/* 396 */     int rowLength = GL11C.glGetInteger(3330), skipRows = GL11C.glGetInteger(3331);
/* 397 */     int skipPixels = GL11C.glGetInteger(3332), alignment = GL11C.glGetInteger(3333);
/* 398 */     GL11C.glPixelStorei(3330, 0);
/* 399 */     GL11C.glPixelStorei(3331, 0);
/* 400 */     GL11C.glPixelStorei(3332, 0);
/* 401 */     GL11C.glPixelStorei(3333, 4);
/* 402 */     GL30C.glBindFramebuffer(36008, fbo);
/* 403 */     GL11C.glReadBuffer(36064);
/* 404 */     GL11C.glReadPixels(0, 0, w, h, 6403, 5126, 0L);
/* 405 */     GL11C.glPixelStorei(3330, rowLength);
/* 406 */     GL11C.glPixelStorei(3331, skipRows);
/* 407 */     GL11C.glPixelStorei(3332, skipPixels);
/* 408 */     GL11C.glPixelStorei(3333, alignment);
/* 409 */     fences[s] = GL32C.glFenceSync(37143, 0);
/* 410 */     slotFrame[s] = frame;
/* 411 */     slotWidth[s] = w;
/* 412 */     slotHeight[s] = h;
/* 413 */     slotMatrix[s].set(matrices.projection()).mul(matrices.modelView());
/* 414 */     slotReversed[s] = reversed;
/* 415 */     slotZeroToOne[s] = zeroToOne();
/* 416 */     slotCamera[s][0] = camera.x;
/* 417 */     slotCamera[s][1] = camera.y;
/* 418 */     slotCamera[s][2] = camera.z;
/*     */     
/* 420 */     GL15C.glBindBuffer(35051, prevPack);
/* 421 */     GL30C.glBindFramebuffer(36009, prevDraw);
/* 422 */     GL30C.glBindFramebuffer(36008, prevRead);
/* 423 */     GL11C.glViewport(viewport[0], viewport[1], viewport[2], viewport[3]);
/* 424 */     GL11C.glBindTexture(3553, prevTexture);
/*     */     
/* 426 */     GL33C.glBindSampler(0, prevSampler);
/*     */     
/* 428 */     GL13C.glActiveTexture(prevUnit);
/* 429 */     GL30C.glBindVertexArray(prevVao);
/* 430 */     GL20C.glUseProgram(prevProgram);
/* 431 */     GL11C.glDepthMask(depthMask);
/* 432 */     if (depthTest) {
/* 433 */       GL11C.glEnable(2929);
/*     */     }
/* 435 */     if (blend) {
/* 436 */       GL11C.glEnable(3042);
/*     */     }
/* 438 */     if (cull) {
/* 439 */       GL11C.glEnable(2884);
/*     */     }
/* 441 */     if (scissor) {
/* 442 */       GL11C.glEnable(3089);
/*     */     }
/* 444 */     GpuTimers.mark("entities, block entities");
/*     */   }
/*     */   
/*     */   private static void count(ChunkRenderListIterable lists) {
/* 448 */     Iterator<ChunkRenderList> it = lists.iterator(false);
/* 449 */     while (it.hasNext()) {
/* 450 */       ChunkRenderList list = it.next();
/* 451 */       RenderRegion region = list.getRegion();
/* 452 */       ByteIterator iterator = list.sectionsWithGeometryIterator(false);
/* 453 */       if (iterator == null) {
/*     */         continue;
/*     */       }
/* 456 */       for (int p = 0; p < PASSES.length; p++) {
/* 457 */         storages[p] = region.getStorage(PASSES[p]);
/*     */       }
/* 459 */       while (iterator.hasNext()) {
/* 460 */         int local = iterator.nextByteAsInt();
/* 461 */         int cx = region.getChunkX() + LocalSectionIndex.unpackX(local);
/* 462 */         int cy = region.getChunkY() + LocalSectionIndex.unpackY(local);
/* 463 */         int cz = region.getChunkZ() + LocalSectionIndex.unpackZ(local);
/* 464 */         long weight = weight(local);
/* 465 */         sections++;
/* 466 */         weightAll += weight;
/* 467 */         if (hidden(class_4076.method_18685(cx, cy, cz), cx, cy, cz)) {
/* 468 */           culled++;
/* 469 */           weightCulled += weight;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static long weight(int local) {
/* 476 */     long elements = 0L;
/* 477 */     for (SectionRenderDataStorage storage : storages) {
/* 478 */       if (storage != null) {
/*     */ 
/*     */         
/* 481 */         long data = storage.getDataPointer(local);
/* 482 */         for (int facing = 0; facing < ModelQuadFacing.COUNT; facing++)
/* 483 */           elements += SodiumSections.count(data, facing); 
/*     */       } 
/*     */     } 
/* 486 */     return elements;
/*     */   }
/*     */   
/*     */   private static void window() {
/* 490 */     long now = System.nanoTime();
/* 491 */     if (windowStart == 0L) {
/* 492 */       windowStart = now;
/*     */       return;
/*     */     } 
/* 495 */     if (now - windowStart < 1000000000L || statFrames == 0L) {
/*     */       return;
/*     */     }
/* 498 */     line = "Occlusion%s%s: %d%% of triangles culled (%d of %d sections); %.2f ms CPU; depth %d frames old".formatted(new Object[] {
/* 499 */           cpu ? " CPU[groups %d planes %d missing %d covered %d]".formatted(new Object[] { Integer.valueOf(CpuOcclusion.groups), Integer.valueOf(CpuOcclusion.planes), Integer.valueOf(CpuOcclusion.missing), Integer.valueOf(CpuOcclusion.covered) }) : "", FORCED ? " (forced)" : ((trialFrame >= 0) ? " (measuring)" : (worth ? "" : " (off: slower here)")), 
/* 500 */           Long.valueOf((weightAll == 0L) ? 0L : (weightCulled * 100L / weightAll)), Long.valueOf(culled / statFrames), Long.valueOf(sections / statFrames), Double.valueOf(cpuNs / 1000000.0D / statFrames), Integer.valueOf(frame - depthFrame) });
/* 501 */     sections = culled = weightAll = weightCulled = cpuNs = statFrames = 0L;
/* 502 */     windowStart = now;
/* 503 */     if (verdicts.size() > 65536) {
/* 504 */       verdicts.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean reversed(ChunkRenderMatrices matrices) {
/* 510 */     Matrix4fc p = matrices.projection();
/* 511 */     float near = (p.m22() * -1.0F + p.m32()) / (p.m23() * -1.0F + p.m33()), far = (p.m22() * -1000.0F + p.m32()) / (p.m23() * -1000.0F + p.m33());
/* 512 */     return (far < near);
/*     */   }
/*     */   
/*     */   private static boolean zeroToOne() {
/*     */     GLCapabilities caps;
/*     */     try {
/* 518 */       caps = GL.getCapabilities();
/* 519 */     } catch (IllegalStateException e) {
/*     */       
/* 521 */       return true;
/*     */     } 
/* 523 */     return ((caps.OpenGL45 || caps.GL_ARB_clip_control) && GL11C.glGetInteger(37725) == 37727);
/*     */   }
/*     */   
/*     */   private static boolean init() {
/*     */     try {
/* 528 */       int vs = shader(35633, "#version 150 core\nvoid main() {\n\tvec2 p = vec2((gl_VertexID << 1) & 2, gl_VertexID & 2);\n\tgl_Position = vec4(p * 2.0 - 1.0, 0.0, 1.0);\n}\n");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 535 */       int fs = shader(35632, "#version 150 core\nuniform sampler2D depth;\nuniform ivec2 block;\nuniform ivec2 size;\nuniform bool reversed;\nout vec4 color;\nvoid main() {\n\tivec2 origin = ivec2(gl_FragCoord.xy) * block;\n\tfloat far = reversed ? 1.0 : 0.0;\n\tfor (int y = 0; y < block.y; y++) {\n\t\tfor (int x = 0; x < block.x; x++) {\n\t\t\tfloat d = texelFetch(depth, min(origin + ivec2(x, y), size - 1), 0).r;\n\t\t\tfar = reversed ? min(far, d) : max(far, d);\n\t\t}\n\t}\n\tcolor = vec4(far);\n}\n");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 554 */       program = GL20C.glCreateProgram();
/* 555 */       GL20C.glAttachShader(program, vs);
/* 556 */       GL20C.glAttachShader(program, fs);
/* 557 */       GL20C.glLinkProgram(program);
/* 558 */       GL20C.glDeleteShader(vs);
/* 559 */       GL20C.glDeleteShader(fs);
/* 560 */       if (GL20C.glGetProgrami(program, 35714) == 0) {
/* 561 */         throw new IllegalStateException(GL20C.glGetProgramInfoLog(program));
/*     */       }
/* 563 */       uDepth = GL20C.glGetUniformLocation(program, "depth");
/* 564 */       uBlock = GL20C.glGetUniformLocation(program, "block");
/* 565 */       uSize = GL20C.glGetUniformLocation(program, "size");
/* 566 */       uReversed = GL20C.glGetUniformLocation(program, "reversed");
/* 567 */       vao = GL30C.glGenVertexArrays();
/*     */       
/* 569 */       int prevTexture = GL11C.glGetInteger(32873);
/* 570 */       int prevFbo = GL11C.glGetInteger(36006);
/* 571 */       texture = GL11C.glGenTextures();
/* 572 */       GL11C.glBindTexture(3553, texture);
/* 573 */       GL11C.glTexParameteri(3553, 10241, 9728);
/* 574 */       GL11C.glTexParameteri(3553, 10240, 9728);
/* 575 */       GL11C.glTexImage2D(3553, 0, 33326, 1, 1, 0, 6403, 5126, (ByteBuffer)null);
/* 576 */       fbo = GL30C.glGenFramebuffers();
/* 577 */       GL30C.glBindFramebuffer(36160, fbo);
/* 578 */       GL30C.glFramebufferTexture2D(36160, 36064, 3553, texture, 0);
/* 579 */       GL30C.glBindFramebuffer(36160, prevFbo);
/* 580 */       GL11C.glBindTexture(3553, prevTexture);
/* 581 */       GL15C.glGenBuffers(pbos);
/* 582 */       texWidth = texHeight = -1;
/* 583 */       ready = true;
/* 584 */       log.info("Occlusion culling ready");
/* 585 */       return true;
/* 586 */     } catch (RuntimeException e) {
/* 587 */       failed = true;
/* 588 */       log.error("Occlusion culling could not start", e);
/* 589 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int shader(int type, String source) {
/* 594 */     int shader = GL20C.glCreateShader(type);
/* 595 */     GL20C.glShaderSource(shader, source);
/* 596 */     GL20C.glCompileShader(shader);
/* 597 */     if (GL20C.glGetShaderi(shader, 35713) == 0) {
/* 598 */       throw new IllegalStateException(GL20C.glGetShaderInfoLog(shader));
/*     */     }
/* 600 */     return shader;
/*     */   }
/*     */   
/*     */   private static final class Filter
/*     */     implements ByteIterator {
/*     */     ByteIterator in;
/*     */     RenderRegion region;
/* 607 */     int next = -1;
/*     */     
/*     */     void advance() {
/* 610 */       this.next = -1;
/* 611 */       while (this.in.hasNext()) {
/* 612 */         int local = this.in.nextByteAsInt();
/* 613 */         int cx = this.region.getChunkX() + LocalSectionIndex.unpackX(local);
/* 614 */         int cy = this.region.getChunkY() + LocalSectionIndex.unpackY(local);
/* 615 */         int cz = this.region.getChunkZ() + LocalSectionIndex.unpackZ(local);
/* 616 */         if (!OcclusionCull.hidden(class_4076.method_18685(cx, cy, cz), cx, cy, cz)) {
/* 617 */           this.next = local;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 625 */       return (this.next >= 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public int nextByteAsInt() {
/* 630 */       int local = this.next;
/* 631 */       advance();
/* 632 */       return local;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\OcclusionCull.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */