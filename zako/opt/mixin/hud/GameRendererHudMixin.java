/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.mojang.blaze3d.buffers.GpuBufferSlice;
/*    */ import net.minecraft.class_11228;
/*    */ import net.minecraft.class_11246;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_329;
/*    */ import net.minecraft.class_332;
/*    */ import net.minecraft.class_374;
/*    */ import net.minecraft.class_757;
/*    */ import net.minecraft.class_9779;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.hud.HudCache;
/*    */ import zako.opt.hud.HudGraphics;
/*    */ 
/*    */ @Mixin({class_757.class})
/*    */ public class GameRendererHudMixin {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_11246 field_59966;
/*    */   @Shadow
/*    */   @Final
/*    */   private class_310 field_4015;
/*    */   
/*    */   @Inject(method = {"method_3192"}, at = {@At("HEAD")})
/*    */   private void zakoopt$hudFrame(class_9779 deltaTracker, boolean renderLevel, CallbackInfo ci) {
/* 34 */     HudCache.beginFrame(renderLevel);
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_1753(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V")})
/*    */   private void zakoopt$hud(class_329 gui, class_332 graphics, class_9779 deltaTracker, Operation<Void> original) {
/* 39 */     if (!HudCache.active()) {
/* 40 */       original.call(new Object[] { gui, graphics, deltaTracker });
/* 41 */     } else if (!HudCache.skipExtraction()) {
/* 42 */       HudCache.suppressLiveParts = true;
/*    */       try {
/* 44 */         HudCache.extractIntoTexture(() -> original.call(new Object[] { gui, graphics, deltaTracker }));
/*    */       } finally {
/* 46 */         HudCache.suppressLiveParts = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_39192(Lnet/minecraft/class_332;Lnet/minecraft/class_9779;)V")})
/*    */   private void zakoopt$savingIndicator(class_329 gui, class_332 graphics, class_9779 deltaTracker, Operation<Void> original) {
/* 53 */     if (!HudCache.skipExtraction()) {
/* 54 */       original.call(new Object[] { gui, graphics, deltaTracker });
/*    */     }
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_374;method_1996(Lnet/minecraft/class_332;)V")})
/*    */   private void zakoopt$toasts(class_374 toasts, class_332 graphics, Operation<Void> original) {
/* 60 */     if (!HudCache.skipExtraction()) {
/* 61 */       original.call(new Object[] { toasts, graphics });
/*    */     }
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_70838(Lnet/minecraft/class_332;)V")})
/*    */   private void zakoopt$debugOverlay(class_329 gui, class_332 graphics, Operation<Void> original) {
/* 67 */     if (!HudCache.skipExtraction()) {
/* 68 */       original.call(new Object[] { gui, graphics });
/*    */     }
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_329;method_72738()V")})
/*    */   private void zakoopt$subtitles(class_329 gui, Operation<Void> original) {
/* 74 */     if (!HudCache.skipExtraction()) {
/* 75 */       original.call(new Object[] { gui });
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_11228;method_70890(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V")})
/*    */   private void zakoopt$drawHud(class_11228 renderer, GpuBufferSlice fog, Operation<Void> original, class_9779 deltaTracker, boolean renderLevel) {
/* 82 */     if (!HudCache.active()) {
/* 83 */       original.call(new Object[] { renderer, fog });
/*    */       return;
/*    */     } 
/* 86 */     if (!HudCache.skipExtraction()) {
/* 87 */       HudCache.drawIntoTexture(() -> original.call(new Object[] { renderer, fog }));
/*    */     }
/* 89 */     if (!this.field_4015.field_1690.field_1842) {
/* 90 */       class_332 graphics = HudGraphics.create(this.field_4015, this.field_59966);
/* 91 */       GuiInvoker gui = (GuiInvoker)this.field_4015.field_1705;
/* 92 */       gui.zakoopt$renderCameraOverlays(graphics, deltaTracker);
/* 93 */       gui.zakoopt$renderCrosshair(graphics, deltaTracker);
/* 94 */       original.call(new Object[] { renderer, fog });
/*    */     } 
/* 96 */     HudCache.composite();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\GameRendererHudMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */