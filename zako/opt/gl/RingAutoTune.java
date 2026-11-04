/*     */ package zako.opt.gl;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_310;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public final class RingAutoTune
/*     */ {
/*     */   @Generated
/*  12 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/*  13 */   private RingAutoTune() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/*     */   
/*     */   private static final long FIRST_DELAY = 3000000000L;
/*     */   private static final long NOISY_RETRY = 10000000000L;
/*     */   private static final long WINDOW = 250000000L;
/*     */   private static final int WINDOWS = 25;
/*     */   private static final int SKIP = 5;
/*     */   private static final double MIN_GAIN = 0.98D;
/*     */   private static final double MAX_SPREAD = 0.1D;
/*  22 */   private static final double[] MEANS = new double[25];
/*     */   
/*  24 */   private static final boolean BENCH = "second".equals(System.getProperty("zakobench.monitor"));
/*     */   private static boolean testing;
/*     */   private static boolean done;
/*     */   private static boolean keep;
/*     */   private static long result;
/*  29 */   private static long delay = 3000000000L;
/*     */   
/*     */   private static long calmSince;
/*     */   private static Object level;
/*     */   private static long last;
/*     */   private static long windowStart;
/*     */   private static int window;
/*     */   private static int frames;
/*     */   private static long sum;
/*     */   
/*     */   public static boolean ringAllowed() {
/*  40 */     return testing ? ((window % 2 == 1)) : keep;
/*     */   }
/*     */   
/*     */   public static String status() {
/*  44 */     return done ? ((keep ? "on" : "off") + " (" + (keep ? "on" : "off") + "% frame time)") : (testing ? "testing" : "test pending");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void frame() {
/*  49 */     class_310 mc = class_310.method_1551();
/*  50 */     if (mc == null || done) {
/*     */       return;
/*     */     }
/*  53 */     long now = System.nanoTime();
/*  54 */     long dt = now - last;
/*  55 */     last = now;
/*     */     
/*  57 */     if (mc.field_1687 == null || mc.field_1687 != level || (!mc.field_1729.method_1613() && !BENCH)) {
/*  58 */       level = mc.field_1687;
/*  59 */       testing = false;
/*  60 */       calmSince = now;
/*     */       return;
/*     */     } 
/*  63 */     if (!testing) {
/*  64 */       if (now - calmSince >= delay) {
/*  65 */         testing = true;
/*  66 */         window = 0;
/*  67 */         frames = 0;
/*  68 */         sum = 0L;
/*  69 */         windowStart = now;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     if (++frames > 5) {
/*  75 */       sum += dt;
/*     */     }
/*  77 */     if (now - windowStart >= 250000000L) {
/*  78 */       MEANS[window++] = sum / Math.max(1, frames - 5);
/*  79 */       frames = 0;
/*  80 */       sum = 0L;
/*  81 */       windowStart = now;
/*  82 */       if (window == 25) {
/*  83 */         decide();
/*  84 */         testing = false;
/*  85 */         calmSince = now;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void decide() {
/*  92 */     double[] ratios = new double[12];
/*  93 */     for (int i = 1; i < 24; i += 2) {
/*  94 */       ratios[i / 2] = MEANS[i] / (MEANS[i - 1] + MEANS[i + 1]) / 2.0D;
/*     */     }
/*  96 */     Arrays.sort(ratios);
/*  97 */     double median = ratios[ratios.length / 2];
/*  98 */     double spread = ratios[ratios.length * 3 / 4] - ratios[ratios.length / 4];
/*  99 */     if (spread > 0.1D) {
/* 100 */       delay = 10000000000L;
/* 101 */       log.info("Immediate ring test too noisy (spread {}), retrying later", Long.valueOf(Math.round(spread * 100.0D)));
/*     */       return;
/*     */     } 
/* 104 */     keep = (median < 0.98D);
/* 105 */     result = Math.round(median * 100.0D);
/* 106 */     done = true;
/* 107 */     log.info("Immediate ring test: {}% frame time with it -> {}", Long.valueOf(Math.round(median * 100.0D)), keep ? "kept" : "off");
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\RingAutoTune.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */