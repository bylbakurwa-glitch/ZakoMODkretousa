/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import net.minecraft.class_310;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.entity.PosePool;
/*    */ 
/*    */ @Mixin({class_310.class})
/*    */ public class PosePoolFrameMixin {
/*    */   @Inject(method = {"method_1523"}, at = {@At("HEAD")})
/*    */   private void zakoopt$resetPosePool(boolean advanceGameTime, CallbackInfo ci) {
/* 14 */     PosePool.reset();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\PosePoolFrameMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */