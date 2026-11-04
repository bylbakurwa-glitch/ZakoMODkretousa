/*    */ package zako.opt.feature.blocks;
/*    */ 
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "micro_opts", group = FeatureGroup.BLOCKS, bench = BenchView.OVERVIEW)
/*    */ public class MicroOptsFeature
/*    */   extends Feature
/*    */ {
/*    */   public boolean stableResults;
/*    */   public boolean fewerChecks;
/*    */   public boolean texBufferOnce;
/*    */   public boolean sharedItemBounds;
/*    */   public boolean fastCubes;
/*    */   public boolean spawnerMobMesh;
/*    */   
/*    */   public void refresh() {
/* 31 */     super.refresh();
/* 32 */     this.stableResults = flag("micro_opts.stable_results", this.value);
/* 33 */     this.fewerChecks = flag("micro_opts.fewer_checks", this.value);
/* 34 */     this.texBufferOnce = flag("micro_opts.tex_buffer_once", this.value);
/* 35 */     this.sharedItemBounds = flag("micro_opts.shared_item_bounds", this.value);
/* 36 */     this.fastCubes = flag("micro_opts.fast_cubes", true);
/* 37 */     this.spawnerMobMesh = flag("micro_opts.spawner_mob_mesh", true);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\MicroOptsFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */