/*    */ package zako.opt.feature.render;
/*    */ 
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
/*    */ @FeatureData(key = "occlusion_cull", group = FeatureGroup.RENDER, enabled = false, mixins = {"gl.SodiumOcclusionCullMixin"})
/*    */ public class OcclusionCullFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 18 */     return Features.shaders() ? "Iris" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\OcclusionCullFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */