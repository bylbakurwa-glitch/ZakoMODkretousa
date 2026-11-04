/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import net.minecraft.class_11228;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.hud.HudCache;
/*    */ 
/*    */ @Mixin({class_11228.class})
/*    */ public class GuiRendererPipMixin {
/*    */   @Inject(method = {"method_70893"}, at = {@At("HEAD")})
/*    */   private void zakoopt$realTarget(CallbackInfo ci) {
/* 14 */     HudCache.pipPrepare = true;
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_70893"}, at = {@At("RETURN")})
/*    */   private void zakoopt$hudTarget(CallbackInfo ci) {
/* 19 */     HudCache.pipPrepare = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\GuiRendererPipMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */