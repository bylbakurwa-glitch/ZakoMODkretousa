/*     */ package zako.opt.bench;
/*     */ import com.sun.management.OperatingSystemMXBean;
/*     */ import com.sun.management.ThreadMXBean;
/*     */ import java.lang.management.GarbageCollectorMXBean;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.OperatingSystemMXBean;
/*     */ import java.lang.management.ThreadMXBean;
/*     */ import net.minecraft.class_2561;
/*     */ import zako.opt.Canvas;
/*     */ 
/*     */ public final class MemoryGraph {
/*     */   private static final long SAMPLE_NS = 250000000L;
/*     */   
/*     */   @Generated
/*     */   private MemoryGraph() {
/*  16 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static final long LABEL_NS = 500000000L; private static final long GARBAGE_NS = 1000000000L;
/*     */   private static final int HISTORY = 512;
/*     */   public static final int HEAP = -13997408;
/*     */   public static final int NATIVE = -3900902;
/*     */   public static final int GC = -5728128;
/*  23 */   private static final float[] used = new float[512];
/*  24 */   private static final float[] committed = new float[512];
/*  25 */   private static final float[] process = new float[512];
/*  26 */   private static final boolean[] gc = new boolean[512];
/*  27 */   private static final List<GarbageCollectorMXBean> GCS = ManagementFactory.getGarbageCollectorMXBeans(); static final OperatingSystemMXBean OS; private static int samples; private static long lastSample; private static long lastLabel; private static long lastGarbage;
/*     */   static {
/*  29 */     OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean(); OperatingSystemMXBean os = (OperatingSystemMXBean)operatingSystemMXBean; OS = (operatingSystemMXBean instanceof OperatingSystemMXBean) ? os : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   private static long gcCount = -1L;
/*  35 */   private static long gcAtGarbage = -1L;
/*  36 */   private static long allocated = -1L; private static int garbageFrames; private static final ThreadMXBean THREADS;
/*     */   
/*     */   static {
/*  39 */     ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean(); ThreadMXBean t = (ThreadMXBean)threadMXBean; THREADS = (threadMXBean instanceof ThreadMXBean) ? t : null;
/*  40 */   } private static class_2561 label = (class_2561)class_2561.method_43473();
/*  41 */   private static class_2561 garbage = (class_2561)class_2561.method_43473();
/*     */   
/*     */   public static void reset() {
/*  44 */     samples = 0;
/*  45 */     lastSample = 0L;
/*  46 */     lastGarbage = 0L;
/*  47 */     gcCount = -1L;
/*  48 */     gcAtGarbage = -1L;
/*  49 */     allocated = -1L;
/*  50 */     garbageFrames = 0;
/*  51 */     label = (class_2561)class_2561.method_43473();
/*  52 */     garbage = (class_2561)class_2561.method_43473();
/*     */   }
/*     */ 
/*     */   
/*     */   private static long allocatedBytes() {
/*     */     try {
/*  58 */       return (THREADS == null) ? -1L : THREADS.getCurrentThreadAllocatedBytes();
/*  59 */     } catch (UnsupportedOperationException e) {
/*  60 */       return -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void frame(long now) {
/*  66 */     garbageFrames++;
/*  67 */     if (now - lastSample < 250000000L) {
/*     */       return;
/*     */     }
/*  70 */     lastSample = now;
/*  71 */     Runtime rt = Runtime.getRuntime();
/*  72 */     long count = 0L;
/*  73 */     for (GarbageCollectorMXBean bean : GCS) {
/*  74 */       count += Math.max(0L, bean.getCollectionCount());
/*     */     }
/*  76 */     int i = samples & 0x1FF;
/*  77 */     used[i] = (float)(rt.totalMemory() - rt.freeMemory()) / 1048576.0F;
/*  78 */     committed[i] = (float)rt.totalMemory() / 1048576.0F;
/*     */     
/*  80 */     process[i] = (OS == null) ? 0.0F : ((float)OS.getCommittedVirtualMemorySize() / 1048576.0F);
/*  81 */     gc[i] = (gcCount >= 0L && count != gcCount);
/*  82 */     gcCount = count;
/*  83 */     samples++;
/*  84 */     if (now - lastLabel >= 500000000L) {
/*  85 */       lastLabel = now;
/*  86 */       label = (class_2561)class_2561.method_43469("zakoopt.bench.memory", new Object[] {
/*  87 */             class_2561.method_43470(String.valueOf((int)used[i])).method_54663(-13997408), String.valueOf(rt.maxMemory() >> 20L), 
/*  88 */             (OS == null) ? class_2561.method_43470("?") : size(Math.max(0.0F, process[i] - committed[i])).method_54663(-3900902) });
/*     */     } 
/*  90 */     if (now - lastGarbage >= 1000000000L) {
/*  91 */       long bytes = allocatedBytes();
/*     */       
/*  93 */       String rate = (allocated < 0L || bytes < 0L || garbageFrames == 0) ? "—" : String.valueOf(Math.round((bytes - allocated) / 1024.0D / garbageFrames));
/*  94 */       class_2561 gcs = (gcAtGarbage < 0L) ? (class_2561)class_2561.method_43470("—") : times(count - gcAtGarbage);
/*  95 */       allocated = bytes;
/*  96 */       garbageFrames = 0;
/*  97 */       gcAtGarbage = count;
/*  98 */       lastGarbage = now;
/*  99 */       garbage = (class_2561)class_2561.method_43469("zakoopt.bench.garbage", new Object[] { rate, gcs });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class_2561 label() {
/* 104 */     return label;
/*     */   }
/*     */   
/*     */   public static class_2561 garbage() {
/* 108 */     return garbage;
/*     */   }
/*     */   
/*     */   public static void draw(Canvas c, int x, int bottom, int width, int height) {
/* 112 */     c.fill(x, bottom - height, x + width, bottom, -2569296);
/* 113 */     int n = Math.min(samples, Math.min(width, 512));
/* 114 */     float scale = 1.0F; int k;
/* 115 */     for (k = samples - n; k < samples; k++) {
/* 116 */       int i = k & 0x1FF;
/* 117 */       scale = Math.max(scale, Math.max(committed[i], process[i] - committed[i]));
/*     */     } 
/* 119 */     for (k = samples - n; k < samples; k++) {
/* 120 */       int i = k & 0x1FF, cx = x + width - samples - k;
/* 121 */       if (gc[i]) {
/* 122 */         c.fill(cx, bottom - height, cx + 1, bottom, -5728128);
/*     */       }
/* 124 */       c.fill(cx, y(bottom, height, used[i], scale), cx + 1, bottom, -13997408);
/* 125 */       if (OS != null) {
/* 126 */         int ny = y(bottom, height, process[i] - committed[i], scale);
/* 127 */         c.fill(cx, ny, cx + 1, ny + 1, -3900902);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int y(int bottom, int height, float mb, float scale) {
/* 133 */     return bottom - Math.max(1, Math.min(height, Math.round(mb / scale * height)));
/*     */   }
/*     */   
/*     */   private static class_5250 size(float mb) {
/* 137 */     long tenths = Math.round(mb / 102.4D);
/* 138 */     return (mb < 1024.0F) ? class_2561.method_43469("zakoopt.bench.mb", new Object[] { Integer.valueOf((int)mb) }) : class_2561.method_43469("zakoopt.bench.gb", new Object[] { Long.valueOf(tenths / 10L), Long.valueOf(tenths % 10L) });
/*     */   }
/*     */ 
/*     */   
/*     */   private static class_2561 times(long n) {
/* 143 */     String form = (n == 1L) ? "once" : ((n % 10L == 1L && n % 100L != 11L) ? "one" : ((n % 10L >= 2L && n % 10L <= 4L && (n % 100L < 12L || n % 100L > 14L)) ? "few" : "many"));
/* 144 */     return (class_2561)class_2561.method_43469("zakoopt.bench.times." + form, new Object[] { Long.valueOf(n) });
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\MemoryGraph.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */