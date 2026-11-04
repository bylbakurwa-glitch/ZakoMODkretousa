/*    */ package zako.opt.hud;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_313;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public final class MonitorRate {
/*    */   @Generated
/* 10 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/* 11 */   private MonitorRate() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/* 12 */    private static long intervalNs = 6944444L;
/*    */   private static long nextCheck;
/*    */   
/*    */   public static long intervalNs(long now) {
/* 16 */     if (now >= nextCheck) {
/*    */       
/* 18 */       class_313 monitor = class_310.method_1551().method_22683().method_20831();
/* 19 */       int hz = (monitor != null) ? monitor.method_1617().method_1671() : 144;
/* 20 */       long interval = 1000000000L / Math.max(30, hz);
/* 21 */       if (interval != intervalNs) {
/* 22 */         log.info("Monitor refresh rate {} Hz", Integer.valueOf(hz));
/*    */       }
/* 24 */       intervalNs = interval;
/* 25 */       nextCheck = now + 1000000000L;
/*    */     } 
/* 27 */     return intervalNs;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\hud\MonitorRate.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */