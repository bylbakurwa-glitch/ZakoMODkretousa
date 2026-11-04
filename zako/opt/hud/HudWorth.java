/*    */ package zako.opt.hud;
/*    */ import java.util.Objects;
/*    */ import java.util.stream.Stream;
/*    */ import lombok.Generated;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import zako.opt.Mods;
/*    */ 
/*    */ public final class HudWorth {
/*    */   private static final double OFF_BELOW = 1.5D;
/*    */   private static final double ON_ABOVE = 2.0D;
/*    */   @Generated
/* 13 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); private static double frameNs; private static long last; @Generated
/* 14 */   private HudWorth() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/*    */ 
/*    */   
/*    */   private static boolean worth = true; private static boolean mainSwapped;
/*    */   @Generated
/* 19 */   public static boolean isWorth() { return worth; } private static String swappedBy; @Generated
/*    */   public static boolean isMainSwapped() {
/* 21 */     return mainSwapped;
/*    */   } @Generated
/*    */   public static String getSwappedBy() {
/* 24 */     return swappedBy;
/*    */   }
/*    */   
/*    */   public static boolean update(long now, long refreshNs) {
/* 28 */     long dt = now - last;
/* 29 */     last = now;
/* 30 */     if (dt > 0L && dt < 1000000000L) {
/* 31 */       frameNs = (frameNs == 0.0D) ? dt : (frameNs * 0.95D + dt * 0.05D);
/*    */     }
/* 33 */     double framesPerRefresh = (frameNs == 0.0D) ? 2.0D : (refreshNs / frameNs);
/* 34 */     if (worth ? (framesPerRefresh < 1.5D) : (framesPerRefresh > 2.0D)) {
/* 35 */       worth = !worth;
/*    */     }
/* 37 */     return worth;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void mainSwapped(Object target) {
/* 43 */     if (!mainSwapped) {
/* 44 */       mainSwapped = true;
/* 45 */       swappedBy = Mods.owner(target.getClass());
/* 46 */       if (swappedBy != null) {
/* 47 */         log.warn("HUD cache turned off: {} swaps the main render target while the HUD draws", swappedBy);
/*    */       
/*    */       }
/*    */       else {
/*    */         
/* 52 */         String suspect = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).<String>walk(frames -> (String)frames.map(Mods::owner).filter(Objects::nonNull).findFirst().orElse(null));
/* 53 */         log.warn("HUD cache turned off: another mod swaps the main render target while the HUD draws{}", (suspect == null) ? "" : (" (possibly " + suspect + ")"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\hud\HudWorth.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */