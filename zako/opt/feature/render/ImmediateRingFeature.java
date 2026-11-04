/*    */ package zako.opt.feature.render;
/*    */ 
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.feature.Setting;
/*    */ import zako.opt.gl.RingAutoTune;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "immediate_ring", group = FeatureGroup.RENDER, mixins = {"gl.BatchableBufferSourceMixin", "gl.BufferUploaderMixin"}, bench = BenchView.OVERVIEW)
/*    */ public class ImmediateRingFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "immediate_ring_force")
/*    */   public boolean force = false;
/*    */   
/*    */   public boolean forced() {
/* 24 */     return (System.getProperty(property()) != null || this.force);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 29 */     boolean forced = forced();
/* 30 */     if (Features.active && this.value && !forced && ZakoOptConfig.RING_PRESENT) {
/* 31 */       RingAutoTune.frame();
/*    */     }
/* 33 */     this.on = (flag(this.value) && (forced || RingAutoTune.ringAllowed()));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\ImmediateRingFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */