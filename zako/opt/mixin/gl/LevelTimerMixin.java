/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import net.minecraft.class_761;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_761.class})
/*    */ public class LevelTimerMixin {
/*    */   @Inject(method = {"method_22710"}, at = {@At("HEAD")})
/*    */   private void zakoopt$markSetup(CallbackInfo ci) {
/* 14 */     GpuTimers.mark("terrain setup, chunk uploads");
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\LevelTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */