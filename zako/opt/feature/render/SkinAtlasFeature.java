/*    */ package zako.opt.feature.render;
/*    */ 
/*    */ import zako.opt.MixinPlugin;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "skin_atlas", group = FeatureGroup.RENDER, mixins = {"entity.LivingEntityRendererSkinMixin"})
/*    */ public class SkinAtlasFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 16 */     return MixinPlugin.VULKAN ? "VulkanMod" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\SkinAtlasFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */