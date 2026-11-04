/*    */ package zako.opt.feature.items;
/*    */ 
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "state_pool", group = FeatureGroup.MEMORY, mixins = {"entity.EntityRenderStatePoolMixin"}, bench = BenchView.CROWD)
/*    */ public class StatePoolFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 19 */     return Features.shaders() ? "Iris" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\items\StatePoolFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */