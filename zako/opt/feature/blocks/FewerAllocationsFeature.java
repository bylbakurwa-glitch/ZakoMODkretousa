/*    */ package zako.opt.feature.blocks;
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
/*    */ @FeatureData(key = "fewer_allocations", group = FeatureGroup.MEMORY, mixins = {"block.BlockEntityRenderStatePoolMixin", "entity.SubmitPosePoolMixin", "gl.BatchableBufferSourceSetPoolMixin", "entity.ModelPartChildrenMixin"})
/*    */ public class FewerAllocationsFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 18 */     return Features.shaders() ? "Iris" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\FewerAllocationsFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */