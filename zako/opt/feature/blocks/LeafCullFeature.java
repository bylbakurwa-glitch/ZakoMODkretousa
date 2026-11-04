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
/*    */ @FeatureData(key = "leaf_cull", group = FeatureGroup.BLOCKS, mixins = {"block.SodiumLeafCullMixin", "block.SodiumLeafCullOldMixin"})
/*    */ public class LeafCullFeature
/*    */   extends Feature
/*    */ {
/*    */   public void refresh() {
/* 17 */     boolean was = this.on;
/* 18 */     super.refresh();
/* 19 */     if (was != this.on)
/* 20 */       Features.terrainChanged = true; 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\LeafCullFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */