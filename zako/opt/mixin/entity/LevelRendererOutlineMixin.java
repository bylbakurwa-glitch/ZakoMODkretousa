/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.mojang.blaze3d.systems.CommandEncoder;
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_11658;
/*    */ import net.minecraft.class_761;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_761.class})
/*    */ public class LevelRendererOutlineMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_11658 field_61737;
/*    */   @Unique
/*    */   private boolean zakoopt$outlineDirty = true;
/*    */   
/*    */   @WrapOperation(method = {"method_62214"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/CommandEncoder;clearColorAndDepthTextures(Lcom/mojang/blaze3d/textures/GpuTexture;ILcom/mojang/blaze3d/textures/GpuTexture;D)V")})
/*    */   private void zakoopt$clearOutline(CommandEncoder encoder, GpuTexture color, int clearColor, GpuTexture depth, double clearDepth, Operation<Void> original) {
/* 30 */     boolean glowing = this.field_61737.field_61736;
/* 31 */     if (glowing || this.zakoopt$outlineDirty || !Features.OUTLINE_SKIP.on) {
/* 32 */       original.call(new Object[] { encoder, color, Integer.valueOf(clearColor), depth, Double.valueOf(clearDepth) });
/*    */     }
/* 34 */     this.zakoopt$outlineDirty = glowing;
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_3254"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$skipEmptyOutline(CallbackInfo ci) {
/* 40 */     if (!this.field_61737.field_61736 && Features.OUTLINE_SKIP.on)
/* 41 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\LevelRendererOutlineMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */