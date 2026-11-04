/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.opengl.GlStateManager;
/*    */ import org.lwjgl.opengl.GL30;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({GlStateManager.class})
/*    */ public class GlStateManagerMixin
/*    */ {
/*    */   @Shadow
/*    */   private static int readFbo;
/*    */   @Shadow
/*    */   private static int writeFbo;
/*    */   
/*    */   @Inject(method = {"_glBindFramebuffer"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void zakoopt$singleBind(int target, int fbo, CallbackInfo ci) {
/* 23 */     if (target == 36160 && readFbo != fbo && writeFbo != fbo && Features.MICRO_OPTS.on) {
/* 24 */       GL30.glBindFramebuffer(36160, fbo);
/* 25 */       readFbo = fbo;
/* 26 */       writeFbo = fbo;
/* 27 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlStateManagerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */