/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import net.minecraft.class_757;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_757.class})
/*    */ public class GameRendererTimerMixin {
/*    */   @Inject(method = {"method_3192"}, at = {@At("HEAD")})
/*    */   private void zakoopt$beginFrame(CallbackInfo ci) {
/* 14 */     GpuTimers.beginFrame();
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3172"}, at = {@At("HEAD")})
/*    */   private void zakoopt$markHand(CallbackInfo ci) {
/* 19 */     GpuTimers.mark("hand");
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_1753(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V")})
/*    */   private void zakoopt$markHud(CallbackInfo ci) {
/* 24 */     GpuTimers.mark("hud");
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GameRendererTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */