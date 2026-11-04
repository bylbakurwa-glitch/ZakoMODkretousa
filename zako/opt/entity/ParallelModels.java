/*     */ package zako.opt.entity;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_1058;
/*     */ import net.minecraft.class_3532;
/*     */ import net.minecraft.class_3879;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4588;
/*     */ import net.minecraft.class_630;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Quaternionf;
/*     */ import org.joml.Quaternionfc;
/*     */ import org.joml.Vector3f;
/*     */ import org.lwjgl.system.MemoryStack;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.gl.RawVertices;
/*     */ import zako.opt.mixin.entity.ModelPartAccessor;
/*     */ import zako.opt.particle.Workers;
/*     */ 
/*     */ public final class ParallelModels
/*     */ {
/*     */   @Generated
/*     */   private ParallelModels() {
/*  32 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*  33 */   } public static final VertexFormat FORMAT = EntityFormat.FORMAT;
/*     */   public static final int MIN_SUBMITS = 24;
/*  35 */   private static final int STRIDE = FORMAT.getVertexSize();
/*     */   
/*     */   private static final int FIELDS = 9;
/*     */   private static final int PAGE = 1048576;
/*  39 */   private static final Map<class_630, Flat> FLATS = new WeakHashMap<>();
/*  40 */   private static final List<Job> BUILT = new ArrayList<>();
/*     */   
/*  42 */   private static final List<Job> POOL = new ArrayList<>();
/*  43 */   private static final ThreadLocal<Scratch> SCRATCH = ThreadLocal.withInitial(Scratch::new);
/*     */   
/*  45 */   private static final List<Long> PAGES = new ArrayList<>();
/*  46 */   private static final List<Integer> PAGE_SIZES = new ArrayList<>();
/*     */   private static int page;
/*     */   private static int pageUsed;
/*     */   private static Workers.Stream stream;
/*     */   
/*     */   public static boolean enabled() {
/*  52 */     return (Features.PARALLEL_MODELS.on && !Features.ENTITY_ANIM_FREEZE.on);
/*     */   }
/*     */   
/*     */   public static void begin() {
/*  56 */     BUILT.clear();
/*  57 */     page = 0;
/*  58 */     pageUsed = 0;
/*  59 */     stream = Workers.stream(i -> build(BUILT.get(i)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Job add(class_3879 model, Object state, class_4587.class_4665 pose, int light, int overlay, int color, class_1058 sprite) {
/*     */     Job job;
/*  66 */     model.method_2819(state);
/*  67 */     class_630 root = model.method_63512();
/*  68 */     Flat flat = FLATS.computeIfAbsent(root, Flat::new);
/*  69 */     int n = flat.parts.length;
/*     */     
/*  71 */     if (BUILT.size() < POOL.size()) {
/*  72 */       job = POOL.get(BUILT.size());
/*     */     } else {
/*  74 */       job = new Job();
/*  75 */       POOL.add(job);
/*     */     } 
/*  77 */     job.flat = flat;
/*  78 */     job.matrix = pose;
/*  79 */     job.light = light;
/*  80 */     job.overlay = overlay;
/*  81 */     job.color = Integer.reverseBytes(Integer.rotateLeft(color, 8));
/*  82 */     job.sprite = sprite;
/*  83 */     if (job.flags.length < n) {
/*  84 */       job.pose = new float[n * 9];
/*  85 */       job.flags = new byte[n];
/*     */     } 
/*  87 */     int vertices = 0; int i;
/*  88 */     for (i = 0; i < n; i++) {
/*  89 */       class_630 p = (i == 0) ? root : flat.parts[i];
/*  90 */       int o = i * 9;
/*  91 */       job.pose[o] = p.field_3657;
/*  92 */       job.pose[o + 1] = p.field_3656;
/*  93 */       job.pose[o + 2] = p.field_3655;
/*  94 */       job.pose[o + 3] = p.field_3654;
/*  95 */       job.pose[o + 4] = p.field_3675;
/*  96 */       job.pose[o + 5] = p.field_3674;
/*  97 */       job.pose[o + 6] = p.field_37938;
/*  98 */       job.pose[o + 7] = p.field_37939;
/*  99 */       job.pose[o + 8] = p.field_37940;
/* 100 */       job.flags[i] = (byte)((p.field_3665 ? true : false) | (p.field_38456 ? true : false));
/*     */     } 
/* 102 */     for (i = 0; i < n; ) {
/* 103 */       if ((job.flags[i] & 0x1) == 0) {
/* 104 */         i = flat.end[i];
/*     */         continue;
/*     */       } 
/* 107 */       if ((job.flags[i] & 0x2) == 0) {
/* 108 */         vertices += flat.vertices[i];
/*     */       }
/* 110 */       i++;
/*     */     } 
/* 112 */     job.vertices = vertices;
/* 113 */     job.address = reserve(vertices * STRIDE);
/* 114 */     BUILT.add(job);
/* 115 */     stream.publish(BUILT.size());
/* 116 */     return job;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void end() {
/* 121 */     Throwable error = stream.finish(BUILT.size());
/* 122 */     stream = null;
/* 123 */     if (error != null) {
/* 124 */       throw new RuntimeException("zakoopt parallel models", error);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean emit(Job job, class_4588 consumer, MemoryStack stack) {
/* 130 */     if (job.vertices == 0) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (!RawVertices.accepts(consumer, FORMAT)) {
/* 134 */       return false;
/*     */     }
/* 136 */     stack.push();
/*     */     try {
/* 138 */       RawVertices.push(consumer, stack, job.address, job.vertices, FORMAT);
/*     */     } finally {
/* 140 */       stack.pop();
/*     */     } 
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   private static long reserve(int bytes) {
/* 146 */     if (page < PAGES.size() && pageUsed + bytes > ((Integer)PAGE_SIZES.get(page)).intValue()) {
/* 147 */       page++;
/* 148 */       pageUsed = 0;
/*     */     } 
/* 150 */     if (page == PAGES.size()) {
/* 151 */       int size = Math.max(1048576, bytes);
/* 152 */       PAGES.add(Long.valueOf(MemoryUtil.nmemAlloc(size)));
/* 153 */       PAGE_SIZES.add(Integer.valueOf(size));
/* 154 */     } else if (bytes > ((Integer)PAGE_SIZES.get(page)).intValue()) {
/* 155 */       MemoryUtil.nmemFree(((Long)PAGES.get(page)).longValue());
/* 156 */       PAGES.set(page, Long.valueOf(MemoryUtil.nmemAlloc(bytes)));
/* 157 */       PAGE_SIZES.set(page, Integer.valueOf(bytes));
/*     */     } 
/* 159 */     long address = ((Long)PAGES.get(page)).longValue() + pageUsed;
/* 160 */     pageUsed += bytes;
/* 161 */     return address;
/*     */   }
/*     */   
/*     */   private static void build(Job job) {
/* 165 */     Scratch s = SCRATCH.get();
/* 166 */     s.poses.method_23760().method_66521(job.matrix);
/* 167 */     s.at = job.address;
/* 168 */     for (int i = 0; i < job.flat.parts.length;) {
/* 169 */       i = part(job, s, i);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int normal(float c) {
/* 174 */     return (int)(class_3532.method_15363(c, -1.0F, 1.0F) * 127.0F) & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int part(Job job, Scratch s, int i) {
/* 179 */     class_4587 poses = s.poses;
/* 180 */     Flat flat = job.flat;
/* 181 */     int end = flat.end[i];
/* 182 */     if ((job.flags[i] & 0x1) == 0 || flat.empty[i]) {
/* 183 */       return end;
/*     */     }
/* 185 */     float[] p = job.pose;
/* 186 */     int o = i * 9;
/* 187 */     poses.method_22903();
/* 188 */     poses.method_46416(p[o] / 16.0F, p[o + 1] / 16.0F, p[o + 2] / 16.0F);
/* 189 */     if (p[o + 3] != 0.0F || p[o + 4] != 0.0F || p[o + 5] != 0.0F) {
/*     */       
/* 191 */       Quaternionf rotation = s.rotation.rotationZYX(p[o + 5], p[o + 4], p[o + 3]);
/* 192 */       poses.method_23760().method_23761().rotate((Quaternionfc)rotation);
/* 193 */       poses.method_23760().method_23762().rotate((Quaternionfc)rotation);
/*     */     } 
/* 195 */     if (p[o + 6] != 1.0F || p[o + 7] != 1.0F || p[o + 8] != 1.0F) {
/* 196 */       poses.method_22905(p[o + 6], p[o + 7], p[o + 8]);
/*     */     }
/* 198 */     if ((job.flags[i] & 0x2) == 0) {
/* 199 */       class_4587.class_4665 pose = poses.method_23760();
/* 200 */       Matrix4f matrix = pose.method_23761();
/* 201 */       Vector3f v = s.v;
/*     */       
/* 203 */       class_1058 sprite = job.sprite;
/* 204 */       for (Cube cube : flat.cubes[i]) {
/*     */         
/* 206 */         float[] at = s.corners;
/* 207 */         if (at.length < cube.corners.length) {
/* 208 */           at = s.corners = new float[cube.corners.length];
/*     */         }
/* 210 */         for (int j = 0; j < cube.corners.length; j += 3) {
/* 211 */           matrix.transformPosition(cube.corners[j], cube.corners[j + 1], cube.corners[j + 2], v);
/* 212 */           at[j] = v.x();
/* 213 */           at[j + 1] = v.y();
/* 214 */           at[j + 2] = v.z();
/*     */         } 
/* 216 */         int k = 0;
/* 217 */         for (int q = 0; q < cube.sizes.length; q++) {
/* 218 */           Vector3f n = pose.method_56820(cube.normals[q * 3], cube.normals[q * 3 + 1], cube.normals[q * 3 + 2], v);
/* 219 */           int normal = normal(n.x()) | normal(n.y()) << 8 | normal(n.z()) << 16;
/* 220 */           for (int e = k + cube.sizes[q]; k < e; k++) {
/* 221 */             int m = cube.index[k] * 3;
/* 222 */             float u = cube.uv[k * 2], w = cube.uv[k * 2 + 1];
/* 223 */             if (sprite != null) {
/* 224 */               u = sprite.method_4580(u);
/* 225 */               w = sprite.method_4570(w);
/*     */             } 
/* 227 */             long v0 = s.at;
/* 228 */             MemoryUtil.memPutFloat(v0, at[m]);
/* 229 */             MemoryUtil.memPutFloat(v0 + 4L, at[m + 1]);
/* 230 */             MemoryUtil.memPutFloat(v0 + 8L, at[m + 2]);
/* 231 */             MemoryUtil.memPutInt(v0 + 12L, job.color);
/* 232 */             MemoryUtil.memPutFloat(v0 + 16L, u);
/* 233 */             MemoryUtil.memPutFloat(v0 + 20L, w);
/* 234 */             MemoryUtil.memPutInt(v0 + 24L, job.overlay);
/* 235 */             MemoryUtil.memPutInt(v0 + 28L, job.light);
/* 236 */             MemoryUtil.memPutInt(v0 + 32L, normal);
/* 237 */             s.at += STRIDE;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  int c;
/* 242 */     for (c = i + 1; c < end;) {
/* 243 */       c = part(job, s, c);
/*     */     }
/* 245 */     poses.method_22909();
/* 246 */     return end;
/*     */   }
/*     */   
/*     */   public static final class Job {
/*     */     ParallelModels.Flat flat;
/*     */     class_4587.class_4665 matrix;
/*     */     int light;
/*     */     int overlay;
/*     */     int color;
/*     */     class_1058 sprite;
/* 256 */     float[] pose = new float[0];
/* 257 */     byte[] flags = new byte[0];
/*     */     int vertices;
/*     */     long address;
/*     */   }
/*     */   
/*     */   private static final class Scratch
/*     */   {
/* 264 */     final class_4587 poses = new class_4587();
/* 265 */     final Quaternionf rotation = new Quaternionf();
/* 266 */     final Vector3f v = new Vector3f();
/* 267 */     float[] corners = new float[24];
/*     */     long at;
/*     */   }
/*     */   
/*     */   private static final class Cube
/*     */   {
/*     */     final float[] corners;
/*     */     final int[] index;
/*     */     final float[] uv;
/*     */     final float[] normals;
/*     */     final int[] sizes;
/*     */     
/*     */     Cube(class_630.class_628 cube) {
/* 280 */       List<Float> corner = new ArrayList<>();
/* 281 */       List<Integer> idx = new ArrayList<>();
/* 282 */       List<Float> uvs = new ArrayList<>();
/* 283 */       this.normals = new float[cube.field_3649.length * 3];
/* 284 */       this.sizes = new int[cube.field_3649.length];
/* 285 */       for (int q = 0; q < cube.field_3649.length; q++) {
/* 286 */         class_630.class_593 polygon = cube.field_3649[q];
/* 287 */         this.normals[q * 3] = polygon.comp_3185().x();
/* 288 */         this.normals[q * 3 + 1] = polygon.comp_3185().y();
/* 289 */         this.normals[q * 3 + 2] = polygon.comp_3185().z();
/* 290 */         this.sizes[q] = (polygon.comp_3184()).length;
/* 291 */         for (class_630.class_618 vertex : polygon.comp_3184()) {
/* 292 */           int found = -1;
/* 293 */           for (int i = 0; i < corner.size(); i += 3) {
/* 294 */             if (((Float)corner.get(i)).floatValue() == vertex.method_74250() && ((Float)corner.get(i + 1)).floatValue() == vertex.method_74251() && ((Float)corner.get(i + 2)).floatValue() == vertex.method_74252()) {
/* 295 */               found = i / 3;
/*     */               break;
/*     */             } 
/*     */           } 
/* 299 */           if (found < 0) {
/* 300 */             found = corner.size() / 3;
/* 301 */             corner.add(Float.valueOf(vertex.method_74250()));
/* 302 */             corner.add(Float.valueOf(vertex.method_74251()));
/* 303 */             corner.add(Float.valueOf(vertex.method_74252()));
/*     */           } 
/* 305 */           idx.add(Integer.valueOf(found));
/* 306 */           uvs.add(Float.valueOf(vertex.comp_3187()));
/* 307 */           uvs.add(Float.valueOf(vertex.comp_3188()));
/*     */         } 
/*     */       } 
/* 310 */       this.corners = new float[corner.size()]; int c;
/* 311 */       for (c = 0; c < this.corners.length; c++) {
/* 312 */         this.corners[c] = ((Float)corner.get(c)).floatValue();
/*     */       }
/* 314 */       this.index = idx.stream().mapToInt(Integer::intValue).toArray();
/* 315 */       this.uv = new float[uvs.size()];
/* 316 */       for (c = 0; c < this.uv.length; c++) {
/* 317 */         this.uv[c] = ((Float)uvs.get(c)).floatValue();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Flat
/*     */   {
/*     */     final class_630[] parts;
/*     */     final int[] end;
/*     */     final int[] vertices;
/*     */     final boolean[] empty;
/*     */     final ParallelModels.Cube[][] cubes;
/*     */     
/*     */     Flat(class_630 root) {
/* 331 */       List<class_630> list = new ArrayList<>();
/* 332 */       List<Integer> ends = new ArrayList<>();
/* 333 */       add(root, list, ends);
/* 334 */       int n = list.size();
/* 335 */       this.parts = list.<class_630>toArray(new class_630[0]);
/* 336 */       this.end = new int[n];
/* 337 */       this.vertices = new int[n];
/* 338 */       this.empty = new boolean[n];
/* 339 */       this.cubes = new ParallelModels.Cube[n][];
/* 340 */       for (int i = 0; i < n; i++) {
/* 341 */         ModelPartAccessor acc = (ModelPartAccessor)this.parts[i];
/* 342 */         this.end[i] = ((Integer)ends.get(i)).intValue();
/* 343 */         List<class_630.class_628> source = acc.zakoopt$cubes();
/* 344 */         this.cubes[i] = new ParallelModels.Cube[source.size()];
/* 345 */         for (int c = 0; c < (this.cubes[i]).length; c++) {
/* 346 */           this.cubes[i][c] = new ParallelModels.Cube(source.get(c));
/* 347 */           this.vertices[i] = this.vertices[i] + (this.cubes[i][c]).index.length;
/*     */         } 
/* 349 */         this.empty[i] = ((this.cubes[i]).length == 0 && acc.zakoopt$children().isEmpty());
/*     */       } 
/* 351 */       this.parts[0] = null;
/*     */     }
/*     */     
/*     */     private void add(class_630 part, List<class_630> list, List<Integer> ends) {
/* 355 */       int at = list.size();
/* 356 */       list.add(part);
/* 357 */       ends.add(Integer.valueOf(0));
/* 358 */       for (class_630 child : ((ModelPartAccessor)part).zakoopt$children().values()) {
/* 359 */         add(child, list, ends);
/*     */       }
/* 361 */       ends.set(at, Integer.valueOf(list.size()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\ParallelModels.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */