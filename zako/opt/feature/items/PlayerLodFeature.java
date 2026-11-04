/*    */ package zako.opt.feature.items;
/*    */ 
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Range;
/*    */ import zako.opt.feature.Setting;
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "player_lod", visual = true, group = FeatureGroup.ITEMS, mixins = {"entity.AvatarLodMixin", "entity.PlayerLodMixin"})
/*    */ public class PlayerLodFeature
/*    */   extends Feature
/*    */ {
/*    */   @Setting(key = "player_lod_distance")
/*    */   @Range(min = 8, max = 96)
/* 16 */   public int distance = 35;
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\items\PlayerLodFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */