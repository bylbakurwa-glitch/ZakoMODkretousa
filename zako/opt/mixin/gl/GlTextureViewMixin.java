/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_10874;
/*    */ import net.minecraft.class_11391;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_11391.class})
/*    */ public class GlTextureViewMixin
/*    */ {
/*    */   @Inject(method = {"method_76232"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$shareTextureFbo(class_10874 dsa, GpuTexture depth, CallbackInfoReturnable<Integer> cir) {
/* 19 */     class_11391 view = (class_11391)this;
/* 20 */     if (view.baseMipLevel() == 0 && Features.FBO_SHARE.on)
/* 21 */       cir.setReturnValue(Integer.valueOf(view.method_71638().method_68426(dsa, depth))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlTextureViewMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */