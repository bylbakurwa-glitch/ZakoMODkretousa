/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.buffers.GpuFence;
/*    */ import net.minecraft.class_10860;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.gl.FrameFence;
/*    */ 
/*    */ @Mixin({class_10860.class})
/*    */ public class GlCommandEncoderMixin {
/*    */   @Inject(method = {"createFence"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$frameFence(CallbackInfoReturnable<GpuFence> cir) {
/* 15 */     if (FrameFence.enabled())
/* 16 */       cir.setReturnValue(FrameFence.create()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlCommandEncoderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */