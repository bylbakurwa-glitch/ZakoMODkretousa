/*    */ package zako.opt.feature.spawners;
/*    */ 
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Range;
/*    */ import zako.opt.feature.Setting;
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "spawner_cull", visual = true, group = FeatureGroup.SPAWNERS, mixins = {"block.SpawnerRendererMixin"})
/*    */ public class SpawnerCullFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "spawner_distance")
/*    */   @Range(min = 4, max = 64)
/* 16 */   public int distance = 16;
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\spawners\SpawnerCullFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */