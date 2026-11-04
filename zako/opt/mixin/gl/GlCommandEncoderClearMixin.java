/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_10860;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_10860.class})
/*    */ public class GlCommandEncoderClearMixin
/*    */ {
/*    */   @WrapOperation(method = {"clearColorAndDepthTextures(Lcom/mojang/blaze3d/textures/GpuTexture;ILcom/mojang/blaze3d/textures/GpuTexture;D)V"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/opengl/GlStateManager;_glBindFramebuffer(II)V")})
/*    */   private void zakoopt$keepBound(int target, int framebuffer, Operation<Void> original) {
/* 17 */     if (framebuffer != 0 || !Features.LAZY_CLEAR.on)
/* 18 */       original.call(new Object[] { Integer.valueOf(target), Integer.valueOf(framebuffer) }); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlCommandEncoderClearMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */