/*     */ package zako.opt.gl;
/*     */ import net.minecraft.class_2818;
/*     */ import net.minecraft.class_638;
/*     */ import org.joml.Matrix4f;
/*     */ 
/*     */ public final class CpuOcclusion {
/*     */   private static final int GROUP = 4;
/*     */   private static final int RADIUS = 96;
/*     */   private static final int SCAN = 64;
/*     */   private static final long STALE_NS = 4000000000L;
/*     */   private static final int REFRESH_PER_FRAME = 8;
/*     */   
/*     */   @Generated
/*     */   private CpuOcclusion() {
/*  15 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   } private static final class Columns extends Record {
/*     */     private final short[] top; private final byte[] solid; private final long builtNs; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/gl/CpuOcclusion$Columns;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #22	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/gl/CpuOcclusion$Columns;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/gl/CpuOcclusion$Columns;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #22	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/gl/CpuOcclusion$Columns;
/*     */     }
/*  22 */     private Columns(short[] top, byte[] solid, long builtNs) { this.top = top; this.solid = solid; this.builtNs = builtNs; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/gl/CpuOcclusion$Columns;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #22	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/gl/CpuOcclusion$Columns;
/*  22 */       //   0	8	1	o	Ljava/lang/Object; } public short[] top() { return this.top; } public byte[] solid() { return this.solid; } public long builtNs() { return this.builtNs; }
/*     */   
/*     */   }
/*  25 */   private static final Long2ObjectOpenHashMap<Columns> chunks = new Long2ObjectOpenHashMap();
/*  26 */   private static final class_2338.class_2339 pos = new class_2338.class_2339();
/*  27 */   private static final float[] clip = new float[32]; private static final float[] poly = new float[32]; private static final float[] screen = new float[24]; public static int width;
/*     */   public static int height;
/*  29 */   public static float[] depth = new float[0]; private static float far; private static boolean reversed; private static boolean zeroToOne; private static int refreshed;
/*     */   public static int groups;
/*     */   public static int planes;
/*     */   public static int covered;
/*     */   public static int missing;
/*     */   
/*     */   public static void build(Matrix4f m, double camX, double camY, double camZ, boolean reversed, boolean zeroToOne, float aspect) {
/*  36 */     class_638 level = (class_310.method_1551()).field_1687;
/*  37 */     CpuOcclusion.reversed = reversed;
/*  38 */     CpuOcclusion.zeroToOne = zeroToOne;
/*  39 */     width = 256;
/*  40 */     height = Math.max(16, Math.round(256.0F / aspect));
/*  41 */     if (depth.length != width * height) {
/*  42 */       depth = new float[width * height];
/*     */     }
/*  44 */     far = reversed ? 0.0F : 1.0F;
/*  45 */     Arrays.fill(depth, far);
/*  46 */     if (level == null) {
/*     */       return;
/*     */     }
/*  49 */     refreshed = 0;
/*  50 */     groups = planes = covered = missing = 0;
/*  51 */     int bx = (int)Math.floor(camX) & 0xFFFFFFFC, bz = (int)Math.floor(camZ) & 0xFFFFFFFC;
/*  52 */     for (int gx = bx - 96; gx < bx + 96; gx += 4) {
/*  53 */       for (int gz = bz - 96; gz < bz + 96; gz += 4) {
/*  54 */         int plane = plane(level, gx, gz);
/*  55 */         groups++;
/*  56 */         if (plane != Integer.MIN_VALUE) {
/*  57 */           planes++;
/*  58 */           quad(m, (float)(gx - camX), (float)(plane - camY), (float)(gz - camZ), 4.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*  62 */     erode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  67 */   private static float[] scratch = new float[0];
/*     */   
/*     */   private static void erode() {
/*  70 */     if (scratch.length != depth.length) {
/*  71 */       scratch = new float[depth.length];
/*     */     }
/*  73 */     for (int y = 0; y < height; y++) {
/*  74 */       for (int x = 0; x < width; x++) {
/*  75 */         float v = depth[y * width + x];
/*  76 */         for (int dy = -1; dy <= 1; dy++) {
/*  77 */           int yy = y + dy;
/*  78 */           for (int dx = -1; dx <= 1; dx++) {
/*  79 */             int xx = x + dx;
/*  80 */             float n = (xx < 0 || yy < 0 || xx >= width || yy >= height) ? far : depth[yy * width + xx];
/*  81 */             v = reversed ? Math.min(v, n) : Math.max(v, n);
/*     */           } 
/*     */         } 
/*  84 */         scratch[y * width + x] = v;
/*     */       } 
/*     */     } 
/*  87 */     float[] t = depth;
/*  88 */     depth = scratch;
/*  89 */     scratch = t;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int plane(class_638 level, int gx, int gz) {
/*  94 */     Columns c = columns(level, gx >> 4, gz >> 4);
/*  95 */     if (c == null) {
/*  96 */       missing++;
/*  97 */       return Integer.MIN_VALUE;
/*     */     } 
/*  99 */     int lx = gx & 0xF, lz = gz & 0xF, low = Integer.MAX_VALUE, deepest = Integer.MIN_VALUE;
/* 100 */     for (int x = lx; x < lx + 4; x++) {
/* 101 */       for (int z = lz; z < lz + 4; z++) {
/* 102 */         int i = z << 4 | x, top = c.top[i];
/* 103 */         if (top == -32768) {
/* 104 */           return Integer.MIN_VALUE;
/*     */         }
/* 106 */         low = Math.min(low, top);
/* 107 */         deepest = Math.max(deepest, top - c.solid[i] + 1);
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     return (deepest <= low) ? (low + 1) : Integer.MIN_VALUE;
/*     */   }
/*     */   private static Columns columns(class_638 level, int cx, int cz) {
/*     */     class_2818 chunk;
/* 115 */     long key = cx & 0xFFFFFFFFL | (cz & 0xFFFFFFFFL) << 32L;
/* 116 */     Columns c = (Columns)chunks.get(key);
/* 117 */     long now = System.nanoTime();
/* 118 */     if (c != null && (now - c.builtNs < 4000000000L || refreshed >= 8)) {
/* 119 */       return c;
/*     */     }
/* 121 */     class_2818 class_28181 = level.method_2935().method_12126(cx, cz, false); if (class_28181 instanceof class_2818) { chunk = class_28181; }
/* 122 */     else { return null; }
/*     */     
/* 124 */     refreshed++;
/* 125 */     short[] top = new short[256];
/* 126 */     byte[] solid = new byte[256];
/* 127 */     int minY = level.method_31607();
/* 128 */     for (int z = 0; z < 16; z++) {
/* 129 */       for (int x = 0; x < 16; x++) {
/* 130 */         int y = chunk.method_12005(class_2902.class_2903.field_13202, x, z), found = -32768, stop = Math.max(minY, y - 64);
/* 131 */         for (; y >= stop; y--) {
/* 132 */           if (chunk.method_8320((class_2338)pos.method_10103(x, y, z)).method_26216()) {
/* 133 */             found = y;
/*     */             break;
/*     */           } 
/*     */         } 
/* 137 */         int run = 0;
/* 138 */         if (found != -32768) {
/* 139 */           for (int d = found; d > found - 16 && d >= minY && chunk.method_8320((class_2338)pos.method_10103(x, d, z)).method_26216(); d--) {
/* 140 */             run++;
/*     */           }
/*     */         }
/* 143 */         top[z << 4 | x] = (short)found;
/* 144 */         solid[z << 4 | x] = (byte)run;
/*     */       } 
/*     */     } 
/* 147 */     c = new Columns(top, solid, now);
/* 148 */     chunks.put(key, c);
/* 149 */     if (chunks.size() > 4096) {
/* 150 */       chunks.clear();
/*     */     }
/* 152 */     return c;
/*     */   }
/*     */   
/*     */   private static void quad(Matrix4f m, float x, float y, float z, float size) {
/* 156 */     float[] corners = { x, z, x + size, z, x + size, z + size, x, z + size };
/* 157 */     for (int i = 0; i < 4; i++) {
/* 158 */       float px = corners[i * 2], pz = corners[i * 2 + 1];
/* 159 */       clip[i * 4] = m.m00() * px + m.m10() * y + m.m20() * pz + m.m30();
/* 160 */       clip[i * 4 + 1] = m.m01() * px + m.m11() * y + m.m21() * pz + m.m31();
/* 161 */       clip[i * 4 + 2] = m.m02() * px + m.m12() * y + m.m22() * pz + m.m32();
/* 162 */       clip[i * 4 + 3] = m.m03() * px + m.m13() * y + m.m23() * pz + m.m33();
/*     */     } 
/*     */     
/* 165 */     int n = clipNear(4);
/* 166 */     if (n < 3)
/*     */       return; 
/*     */     int j;
/* 169 */     for (j = 0; j < n; j++) {
/* 170 */       float w = poly[j * 4 + 3];
/* 171 */       float nz = poly[j * 4 + 2] / w;
/* 172 */       screen[j * 3] = (poly[j * 4] / w * 0.5F + 0.5F) * width;
/* 173 */       screen[j * 3 + 1] = (poly[j * 4 + 1] / w * 0.5F + 0.5F) * height;
/* 174 */       screen[j * 3 + 2] = zeroToOne ? nz : (nz * 0.5F + 0.5F);
/*     */     } 
/* 176 */     for (j = 1; j + 1 < n; j++) {
/* 177 */       triangle(0, j, j + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int clipNear(int n) {
/* 182 */     float near = 0.05F;
/* 183 */     int out = 0;
/* 184 */     for (int i = 0; i < n; i++) {
/* 185 */       int a = i * 4, b = (i + 1) % n * 4;
/* 186 */       float wa = clip[a + 3], wb = clip[b + 3];
/* 187 */       boolean ina = (wa > 0.05F), inb = (wb > 0.05F);
/* 188 */       if (ina) {
/* 189 */         System.arraycopy(clip, a, poly, out++ * 4, 4);
/*     */       }
/* 191 */       if (ina != inb) {
/* 192 */         float t = (0.05F - wa) / (wb - wa);
/* 193 */         for (int k = 0; k < 4; k++) {
/* 194 */           poly[out * 4 + k] = clip[a + k] + (clip[b + k] - clip[a + k]) * t;
/*     */         }
/* 196 */         out++;
/*     */       } 
/*     */     } 
/* 199 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void triangle(int a, int b, int c) {
/* 204 */     float x0 = screen[a * 3], y0 = screen[a * 3 + 1], z0 = screen[a * 3 + 2];
/* 205 */     float x1 = screen[b * 3], y1 = screen[b * 3 + 1], z1 = screen[b * 3 + 2];
/* 206 */     float x2 = screen[c * 3], y2 = screen[c * 3 + 1], z2 = screen[c * 3 + 2];
/* 207 */     float area = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
/* 208 */     if (Math.abs(area) < 1.0E-4F) {
/*     */       return;
/*     */     }
/* 211 */     if (area < 0.0F) {
/* 212 */       float t = x1; x1 = x2; x2 = t;
/* 213 */       t = y1; y1 = y2; y2 = t;
/* 214 */       t = z1; z1 = z2; z2 = t;
/* 215 */       area = -area;
/*     */     } 
/*     */     
/* 218 */     float dzdx = ((z1 - z0) * (y2 - y0) - (z2 - z0) * (y1 - y0)) / area;
/* 219 */     float dzdy = ((z2 - z0) * (x1 - x0) - (z1 - z0) * (x2 - x0)) / area;
/* 220 */     float slack = (Math.abs(dzdx) + Math.abs(dzdy)) * 0.5F;
/* 221 */     int minX = Math.max(0, (int)Math.floor(Math.min(x0, Math.min(x1, x2))));
/* 222 */     int maxX = Math.min(width - 1, (int)Math.ceil(Math.max(x0, Math.max(x1, x2))));
/* 223 */     int minY = Math.max(0, (int)Math.floor(Math.min(y0, Math.min(y1, y2))));
/* 224 */     int maxY = Math.min(height - 1, (int)Math.ceil(Math.max(y0, Math.max(y1, y2))));
/*     */     
/* 226 */     float a0 = y1 - y2, b0 = x2 - x1, c0 = x1 * y2 - x2 * y1;
/* 227 */     float a1 = y2 - y0, b1 = x0 - x2, c1 = x2 * y0 - x0 * y2;
/* 228 */     float a2 = y0 - y1, b2 = x1 - x0, c2 = x0 * y1 - x1 * y0;
/* 229 */     for (int py = minY; py <= maxY; py++) {
/* 230 */       float cy = py + 0.5F;
/* 231 */       int row = py * width;
/* 232 */       for (int px = minX; px <= maxX; px++) {
/* 233 */         float cx = px + 0.5F;
/* 234 */         if (a0 * cx + b0 * cy + c0 >= 0.0F && a1 * cx + b1 * cy + c1 >= 0.0F && a2 * cx + b2 * cy + c2 >= 0.0F) {
/*     */ 
/*     */           
/* 237 */           float zc = z0 + dzdx * (cx - x0) + dzdy * (cy - y0);
/* 238 */           float d = reversed ? (zc - slack) : (zc + slack);
/* 239 */           int i = row + px;
/* 240 */           covered++;
/* 241 */           if (reversed ? (d > depth[i]) : (d < depth[i]))
/* 242 */             depth[i] = d; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\CpuOcclusion.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */