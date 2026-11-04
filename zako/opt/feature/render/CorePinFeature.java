/*    */ package zako.opt.feature.render;
/*    */ 
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.cpu.CorePin;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "core_pin", group = FeatureGroup.RENDER, enabled = false, bench = BenchView.CROWD)
/*    */ public class CorePinFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 18 */     return CorePin.available() ? null : "zakoopt.blocked.cores_same";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 24 */     super.refresh();
/* 25 */     CorePin.set(this.on);
/* 26 */     CorePin.check();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\CorePinFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */