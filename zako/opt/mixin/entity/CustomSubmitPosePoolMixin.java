/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11682;
/*    */ import net.minecraft.class_4587;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.PosePool;
/*    */ 
/*    */ @Mixin({class_11682.class_12047.class})
/*    */ public class CustomSubmitPosePoolMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74813"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_4587$class_4665;method_56822()Lnet/minecraft/class_4587$class_4665;")})
/*    */   private class_4587.class_4665 zakoopt$pooledPose(class_4587.class_4665 pose, Operation<class_4587.class_4665> original) {
/* 16 */     return PosePool.pooling() ? PosePool.copy(pose) : (class_4587.class_4665)original.call(new Object[] { pose });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\CustomSubmitPosePoolMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */