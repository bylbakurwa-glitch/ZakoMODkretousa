/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_10860;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.gl.TexBufferCache;
/*    */ 
/*    */ @Mixin({class_10860.class})
/*    */ public class GlCommandEncoderTexBufferMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_68351"}, at = {@At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11C;glBindTexture(II)V")})
/*    */   private void zakoopt$trackTbo(int target, int texture, Operation<Void> original) {
/* 16 */     TexBufferCache.boundTexture = texture;
/* 17 */     original.call(new Object[] { Integer.valueOf(target), Integer.valueOf(texture) });
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_68351"}, at = {@At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL31;glTexBuffer(III)V")})
/*    */   private void zakoopt$texBufferOnce(int target, int format, int buffer, Operation<Void> original) {
/* 23 */     if (Features.MICRO_OPTS.texBufferOnce && TexBufferCache.attached(format, buffer)) {
/*    */       return;
/*    */     }
/* 26 */     original.call(new Object[] { Integer.valueOf(target), Integer.valueOf(format), Integer.valueOf(buffer) });
/* 27 */     TexBufferCache.attach(format, buffer);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlCommandEncoderTexBufferMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */