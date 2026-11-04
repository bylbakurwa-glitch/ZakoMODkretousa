/*    */ package zako.opt.feature.ui;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import zako.opt.MixinPlugin;
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.hud.HudWorth;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "hud_cache", group = FeatureGroup.INTERFACE, mixins = {"hud.GuiRendererMixin", "hud.GuiRendererTargetMixin", "hud.GuiHudMixin"}, bench = BenchView.OVERVIEW)
/*    */ public class HudCacheFeature
/*    */   extends Feature
/*    */ {
/*    */   public String blocked() {
/* 22 */     return MixinPlugin.VULKAN ? "VulkanMod" : (ZakoOptConfig.FOREIGN_HUD ? ZakoOptConfig.FOREIGN_HUD_MOD : (
/* 23 */       HudWorth.isMainSwapped() ? Objects.<String>requireNonNullElse(HudWorth.getSwappedBy(), "zakoopt.blocked.other_mod") : null));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\featur\\ui\HudCacheFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */