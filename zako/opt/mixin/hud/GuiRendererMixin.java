/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11228;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_310;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.hud.HudCache;
/*    */ import zako.opt.hud.MonitorRate;
/*    */ 
/*    */ @Mixin({class_11228.class})
/*    */ public class GuiRendererMixin
/*    */ {
/*    */   @Unique
/*    */   private long zakoopt$lastAnimatedRefresh;
/*    */   @Unique
/*    */   private boolean zakoopt$refreshAnimated;
/*    */   
/*    */   @Inject(method = {"method_70892"}, at = {@At("HEAD")})
/*    */   private void zakoopt$decideRefresh(CallbackInfo ci) {
/* 28 */     long now = System.nanoTime();
/*    */     
/* 30 */     this.zakoopt$refreshAnimated = (!Features.GUI_ANIMATED_ITEMS.on || HudCache.active() || now - this.zakoopt$lastAnimatedRefresh >= MonitorRate.intervalNs(now));
/* 31 */     if (this.zakoopt$refreshAnimated) {
/* 32 */       this.zakoopt$lastAnimatedRefresh = now;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @ModifyExpressionValue(method = {"method_71055"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_11540;method_70948()Z", ordinal = 0)})
/*    */   private boolean zakoopt$throttleAnimated(boolean animated) {
/* 39 */     return (animated && this.zakoopt$refreshAnimated);
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_71291"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_310;method_1522()Lnet/minecraft/class_276;")})
/*    */   private class_276 zakoopt$hudTarget(class_310 minecraft, Operation<class_276> original) {
/* 44 */     class_276 requested = (class_276)original.call(new Object[] { minecraft });
/* 45 */     class_276 target = HudCache.drawTarget(requested);
/* 46 */     return (target != null) ? target : requested;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\GuiRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */