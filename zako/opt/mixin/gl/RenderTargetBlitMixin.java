/*    */ package zako.opt.mixin.gl;
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_10868;
/*    */ import net.minecraft.class_276;
/*    */ import org.lwjgl.opengl.GL11C;
/*    */ import org.lwjgl.opengl.GL30C;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_276.class})
/*    */ public class RenderTargetBlitMixin {
/*    */   @Unique
/*    */   private static int zakoopt$fbo;
/*    */   
/*    */   @Inject(method = {"method_1237"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$blit(CallbackInfo ci) {
/*    */     class_10868 color;
/* 23 */     GpuTimers.mark("blit to window");
/* 24 */     class_276 self = (class_276)this;
/* 25 */     if (Features.FAST_BLIT.on) { GpuTexture gpuTexture = self.method_30277(); if (gpuTexture instanceof class_10868) { color = (class_10868)gpuTexture; } else { return; }
/*    */        }
/*    */     else { return; }
/* 28 */      int prevRead = GL11C.glGetInteger(36010), prevDraw = GL11C.glGetInteger(36006);
/* 29 */     if (zakoopt$fbo == 0) {
/* 30 */       zakoopt$fbo = GL30C.glGenFramebuffers();
/*    */     }
/* 32 */     GL30C.glBindFramebuffer(36008, zakoopt$fbo);
/*    */     
/* 34 */     GL30C.glFramebufferTexture2D(36008, 36064, 3553, color.method_68427(), 0);
/* 35 */     boolean scissor = GL11C.glIsEnabled(3089);
/* 36 */     GL11C.glDisable(3089);
/* 37 */     GL30C.glBindFramebuffer(36009, 0);
/* 38 */     GL30C.glBlitFramebuffer(0, 0, self.field_1482, self.field_1481, 0, 0, self.field_1482, self.field_1481, 16384, 9728);
/* 39 */     GL30C.glBindFramebuffer(36008, prevRead);
/* 40 */     GL30C.glBindFramebuffer(36009, prevDraw);
/* 41 */     if (scissor) {
/* 42 */       GL11C.glEnable(3089);
/*    */     }
/* 44 */     ci.cancel();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderTargetBlitMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */