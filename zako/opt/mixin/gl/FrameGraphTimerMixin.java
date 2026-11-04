/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_9909;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_9909.class})
/*    */ public class FrameGraphTimerMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_61910(Lnet/minecraft/class_9922;Lnet/minecraft/class_9909$class_9912;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_9909$class_9912;method_61920(Ljava/lang/String;)V")})
/*    */   private void zakoopt$markPass(class_9909.class_9912 inspector, String name, Operation<Void> original) {
/* 15 */     GpuTimers.mark(name);
/* 16 */     original.call(new Object[] { inspector, name });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\FrameGraphTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */