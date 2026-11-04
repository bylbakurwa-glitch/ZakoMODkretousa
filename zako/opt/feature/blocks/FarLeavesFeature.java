/*    */ package zako.opt.feature.blocks;
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
/*    */ 
/*    */ @FeatureData(key = "far_leaves", visual = true, group = FeatureGroup.BLOCKS, enabled = false, mixins = {"block.SodiumFarLeavesMixin"})
/*    */ public class FarLeavesFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "far_leaves_distance")
/*    */   @Range(min = 16, max = 128)
/* 19 */   public int distance = 32;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 25 */     boolean was = this.on;
/* 26 */     super.refresh();
/* 27 */     if (was != this.on)
/* 28 */       Features.terrainChanged = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\FarLeavesFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */