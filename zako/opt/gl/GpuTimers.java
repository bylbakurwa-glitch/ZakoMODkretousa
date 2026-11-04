/*     */ package zako.opt.gl;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongLinkedOpenHashMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.opengl.GL;
/*     */ import org.lwjgl.opengl.GL15C;
/*     */ import zako.opt.DebugInfo;
/*     */ import zako.opt.bench.ProgressScreen;
/*     */ 
/*     */ public final class GpuTimers {
/*     */   private static final int SLOTS = 4;
/*     */   private static final int MARKS = 64;
/*     */   private static boolean supported;
/*     */   
/*     */   @Generated
/*     */   private GpuTimers() {
/*  19 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static boolean checked;
/*     */   private static boolean active;
/*     */   private static boolean shown;
/*     */   private static int slot;
/*  25 */   private static final int[][] queries = new int[4][];
/*  26 */   private static final String[][] names = new String[4][64];
/*  27 */   private static final int[] counts = new int[4];
/*  28 */   private static final boolean[] pending = new boolean[4];
/*     */   
/*  30 */   private static final Object2LongLinkedOpenHashMap<String> totals = new Object2LongLinkedOpenHashMap(); private static long frameTotal; private static long frames;
/*     */   private static long windowStart;
/*  32 */   private static double utilization = -1.0D; private static long lastFrameNs; private static long cpuFrameNs; private static long cpuFrames;
/*  33 */   private static List<String> lines = List.of();
/*     */   
/*     */   static {
/*  36 */     DebugInfo.EXTRA.add(GpuTimers::lines);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> lines() {
/*  41 */     return shown ? lines : List.<String>of();
/*     */   }
/*     */   
/*     */   public static double utilization() {
/*  45 */     return utilization;
/*     */   }
/*     */   
/*     */   public static void beginFrame() {
/*  49 */     boolean f3 = DebugToggle.visible();
/*  50 */     active = ((f3 || ProgressScreen.open()) && supported());
/*  51 */     shown = (active && f3);
/*  52 */     if (!active) {
/*  53 */       lastFrameNs = 0L;
/*     */       return;
/*     */     } 
/*  56 */     long now = System.nanoTime();
/*  57 */     if (lastFrameNs != 0L) {
/*  58 */       cpuFrameNs += now - lastFrameNs;
/*  59 */       cpuFrames++;
/*     */     } 
/*  61 */     lastFrameNs = now;
/*  62 */     slot = (slot + 1) % 4;
/*  63 */     harvest(slot);
/*  64 */     counts[slot] = 0;
/*  65 */     mark("before world");
/*     */   }
/*     */   
/*     */   public static void mark(String name) {
/*  69 */     if (!active || counts[slot] == (shown ? 64 : 1)) {
/*     */       return;
/*     */     }
/*  72 */     if (counts[slot] > 0) {
/*  73 */       GL15C.glEndQuery(35007);
/*     */     }
/*  75 */     counts[slot] = counts[slot] + 1; int i = counts[slot];
/*  76 */     names[slot][i] = name;
/*  77 */     GL15C.glBeginQuery(35007, queries[slot][i]);
/*     */   }
/*     */   
/*     */   public static void endFrame() {
/*  81 */     if (!active) {
/*     */       return;
/*     */     }
/*  84 */     if (counts[slot] > 0) {
/*  85 */       GL15C.glEndQuery(35007);
/*     */     }
/*  87 */     pending[slot] = true;
/*     */     
/*  89 */     active = false;
/*  90 */     window();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void vanilla(double gpuUtilization) {
/*  95 */     utilization = gpuUtilization;
/*     */   }
/*     */   
/*     */   public static boolean supported() {
/*  99 */     if (!checked) {
/* 100 */       checked = true;
/*     */       try {
/* 102 */         supported = ((GL.getCapabilities()).OpenGL33 || (GL.getCapabilities()).GL_ARB_timer_query);
/* 103 */       } catch (IllegalStateException e) {
/* 104 */         supported = false;
/*     */       } 
/* 106 */       if (supported) {
/* 107 */         for (int s = 0; s < 4; s++) {
/* 108 */           queries[s] = new int[64];
/* 109 */           GL15C.glGenQueries(queries[s]);
/*     */         } 
/*     */       }
/*     */     } 
/* 113 */     return supported;
/*     */   }
/*     */   
/*     */   private static void harvest(int s) {
/* 117 */     if (!pending[s]) {
/*     */       return;
/*     */     }
/* 120 */     pending[s] = false;
/* 121 */     int n = counts[s];
/* 122 */     if (n == 0 || GL15C.glGetQueryObjecti(queries[s][n - 1], 34919) == 0) {
/*     */       return;
/*     */     }
/* 125 */     for (int i = 0; i < n; i++) {
/* 126 */       long ns = Integer.toUnsignedLong(GL15C.glGetQueryObjecti(queries[s][i], 34918));
/* 127 */       totals.addTo(names[s][i], ns);
/* 128 */       frameTotal += ns;
/*     */     } 
/* 130 */     frames++;
/*     */   }
/*     */   
/*     */   private static void window() {
/* 134 */     long now = System.nanoTime();
/* 135 */     if (windowStart == 0L) {
/* 136 */       windowStart = now;
/*     */     }
/* 138 */     if (now - windowStart < 500000000L || frames == 0L) {
/*     */       return;
/*     */     }
/* 141 */     utilization = (cpuFrames == 0L) ? 0.0D : Math.min(100.0D, frameTotal / frames * 100.0D / cpuFrameNs / cpuFrames);
/* 142 */     if (shown) {
/* 143 */       formatLines();
/*     */     }
/* 145 */     totals.clear();
/* 146 */     frameTotal = frames = cpuFrameNs = cpuFrames = 0L;
/* 147 */     windowStart = now;
/*     */   }
/*     */   
/*     */   private static void formatLines() {
/* 151 */     List<Map.Entry<String, Long>> sorted = new ArrayList<>((Collection<? extends Map.Entry<String, Long>>)totals.entrySet());
/* 152 */     sorted.sort(Map.Entry.comparingByValue().reversed());
/* 153 */     List<String> parts = new ArrayList<>();
/* 154 */     for (Map.Entry<String, Long> e : sorted) {
/* 155 */       double ms = ((Long)e.getValue()).longValue() / 1000000.0D / frames;
/* 156 */       if (ms >= 0.05D) {
/* 157 */         parts.add("%s %.2f".formatted(new Object[] { e.getKey(), Double.valueOf(ms) }));
/*     */       }
/*     */     } 
/* 160 */     List<String> out = new ArrayList<>();
/* 161 */     StringBuilder line = new StringBuilder("GPU %.2f ms/frame:".formatted(new Object[] { Double.valueOf(frameTotal / 1000000.0D / frames) }));
/* 162 */     for (String part : parts) {
/* 163 */       if (line.length() + part.length() > 110) {
/* 164 */         out.add(line.toString());
/* 165 */         line = new StringBuilder(" ");
/*     */       } 
/* 167 */       line.append(' ').append(part).append(',');
/*     */     } 
/* 169 */     out.add(line.substring(0, line.length() - 1));
/* 170 */     lines = out;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\GpuTimers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */