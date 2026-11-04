/*    */ package zako.opt.bench;
/*    */ import java.util.Arrays;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2561;
/*    */ import zako.opt.Canvas;
/*    */ 
/*    */ public final class FrameGraph {
/*    */   private static final long LABEL_NS = 500000000L;
/*    */   
/*    */   @Generated
/*    */   private FrameGraph() {
/* 12 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static final int SMOOTH = -12940742; public static final int STUTTER = -3110374;
/*    */   public static final int BIG_STUTTER = -5222358;
/* 16 */   private static final long[] frames = new long[1024];
/* 17 */   private static final long[] sorted = new long[1024];
/*    */   private static long lastLabel;
/* 19 */   private static class_2561 fps = (class_2561)class_2561.method_43473();
/* 20 */   private static class_2561 frame = (class_2561)class_2561.method_43473();
/*    */   
/*    */   public static void draw(Canvas c, int x, int bottom, int width, int height) {
/* 23 */     c.fill(x, bottom - height, x + width, bottom, -2569296);
/* 24 */     int n = Bench.lastFrames(frames, Math.min(width, frames.length));
/* 25 */     if (n == 0) {
/*    */       return;
/*    */     }
/* 28 */     System.arraycopy(frames, 0, sorted, 0, n);
/* 29 */     Arrays.sort(sorted, 0, n);
/* 30 */     double median = Math.max(sorted[n / 2], 1L);
/* 31 */     for (int i = 0; i < n; i++) {
/* 32 */       double ratio = frames[i] / median;
/* 33 */       int h = (int)Math.max(1.0D, Math.min(height, ratio / 3.0D * height));
/* 34 */       int column = x + width - n + i;
/* 35 */       c.fill(column, bottom - h, column + 1, bottom, (ratio >= 2.5D) ? -5222358 : ((ratio >= 1.5D) ? -3110374 : -12940742));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class_2561 fps() {
/* 40 */     long now = System.nanoTime();
/* 41 */     if (now - lastLabel >= 500000000L) {
/* 42 */       lastLabel = now;
/* 43 */       double recent = Bench.recentFps();
/* 44 */       long tenths = (recent > 0.0D) ? Math.round(10000.0D / recent) : 0L;
/* 45 */       fps = (class_2561)class_2561.method_43469("zakoopt.bench.fps", new Object[] { Long.valueOf(Math.round(recent)) });
/* 46 */       frame = (class_2561)class_2561.method_43469("zakoopt.bench.frame", new Object[] { Long.valueOf(tenths / 10L), Long.valueOf(tenths % 10L) });
/*    */     } 
/* 48 */     return fps;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class_2561 frame() {
/* 53 */     return frame;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\FrameGraph.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */