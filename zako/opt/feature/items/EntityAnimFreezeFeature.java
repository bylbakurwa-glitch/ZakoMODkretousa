/*    */ package zako.opt.feature.items;
/*    */ 
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Range;
/*    */ import zako.opt.feature.Setting;
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "entity_anim_freeze", visual = true, group = FeatureGroup.ITEMS, enabled = false, mixins = {"entity.ModelFeatureRendererMixin", "entity.LivingEntityAnimFreezeMixin"})
/*    */ public class EntityAnimFreezeFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "entity_anim_distance")
/*    */   @Range(min = 4, max = 96)
/* 17 */   public int distance = 16;
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\items\EntityAnimFreezeFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */