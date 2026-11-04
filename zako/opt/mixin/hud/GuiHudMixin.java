/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import net.minecraft.class_329;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.hud.HudCache;
/*    */ 
/*    */ @Mixin({class_329.class})
/*    */ public class GuiHudMixin
/*    */ {
/*    */   @Inject(method = {"method_55798", "method_1736"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$live(CallbackInfo ci) {
/* 15 */     if (HudCache.suppressLiveParts)
/* 16 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\GuiHudMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */