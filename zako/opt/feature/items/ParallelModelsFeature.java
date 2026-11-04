/*    */ package zako.opt.feature.items;
/*    */ 
/*    */ import zako.opt.ZakoOptConfig;
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
/*    */ @FeatureData(key = "parallel_models", group = FeatureGroup.ITEMS, mixins = {"entity.ModelParallelMixin", "entity.ModelFeatureRendererMixin"}, bench = BenchView.CROWD)
/*    */ public class ParallelModelsFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 20 */     return (ZakoOptConfig.CORES < 4) ? "zakoopt.blocked.cpu" : (Features.shaders() ? "Iris" : null);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\items\ParallelModelsFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */