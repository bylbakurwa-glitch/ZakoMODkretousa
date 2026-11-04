/*    */ package zako.opt.bench;
/*    */ 
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_437;
/*    */ import zako.opt.Canvas;
/*    */ import zako.opt.ZakoKey;
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.update.UpdateUi;
/*    */ 
/*    */ public class MonitorScreen extends ProgressScreen {
/*    */   public MonitorScreen() {
/* 13 */     Bench.resetFrames();
/* 14 */     MemoryGraph.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void pollKey() {
/* 19 */     while (ZakoKey.MONITOR.method_1436()) {
/* 20 */       if ((class_310.method_1551()).field_1687 != null && !Bench.running()) {
/* 21 */         UpdateUi.setScreen((class_437)new MonitorScreen());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected class_2561 stage() {
/* 28 */     return (class_2561)class_2561.method_43471(ZakoOptConfig.enabled ? "zakoopt.bench.compare.on" : "zakoopt.bench.compare.off");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int percent() {
/* 33 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected class_2561 hint() {
/* 38 */     return (class_2561)class_2561.method_43471("zakoopt.monitor.hint");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paint(Canvas c) {
/* 43 */     super.paint(c);
/* 44 */     int w = 240, x0 = this.field_22789 / 2 - w / 2, y0 = this.field_22790 - 8 - 30;
/* 45 */     frame(c, x0, y0, x0 + w, y0 + 30);
/* 46 */     modToggle(c, x0 + 8, y0 + 8, w - 16);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void closed() {
/* 51 */     ZakoOptConfig.save();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\MonitorScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */