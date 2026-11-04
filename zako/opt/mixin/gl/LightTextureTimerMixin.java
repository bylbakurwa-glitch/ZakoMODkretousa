/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import net.minecraft.class_765;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_765.class})
/*    */ public class LightTextureTimerMixin {
/*    */   @Inject(method = {"method_3313"}, at = {@At("HEAD")})
/*    */   private void zakoopt$markLightmap(CallbackInfo ci) {
/* 14 */     GpuTimers.mark("lightmap");
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\LightTextureTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */