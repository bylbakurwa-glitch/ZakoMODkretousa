/*    */ package zako.opt.feature.render;
/*    */ 
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.feature.Range;
/*    */ import zako.opt.feature.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "render_scale", group = FeatureGroup.RENDER, enabled = false, mixins = {"gl.GameRendererScaleMixin"})
/*    */ public class RenderScaleFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "render_scale_percent")
/*    */   @Range(min = 50, max = 90, unit = "zakoopt.percent")
/* 18 */   public int percent = 75;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String blocked() {
/* 24 */     return Features.shaders() ? "Iris" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\RenderScaleFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */