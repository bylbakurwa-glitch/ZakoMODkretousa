/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_10860;
/*    */ import net.minecraft.class_10868;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.gl.DepthFbos;
/*    */ 
/*    */ @Mixin({class_10860.class})
/*    */ public class GlCommandEncoderDepthClearMixin
/*    */ {
/*    */   @Inject(method = {"clearDepthTexture"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$clearInPlace(GpuTexture depth, double value, CallbackInfo ci) {
/* 18 */     if (Features.FBO_SHARE.on && depth instanceof class_10868) { class_10868 gl = (class_10868)depth; if (!gl.isClosed() && DepthFbos.clear(gl.method_68427(), value))
/* 19 */         ci.cancel();  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlCommandEncoderDepthClearMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */