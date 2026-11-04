/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_1309;
/*    */ import net.minecraft.class_1764;
/*    */ import net.minecraft.class_1799;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_1764.class})
/*    */ public class CrossbowChargeMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_7775"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_1890;method_60159(Lnet/minecraft/class_1799;Lnet/minecraft/class_1309;F)F")})
/*    */   private static float zakoopt$chargeTime(class_1799 stack, class_1309 holder, float time, Operation<Float> original) {
/* 19 */     return stack.method_58657().method_57543() ? Math.max(0.0F, time) : ((Float)original.call(new Object[] { stack, holder, Float.valueOf(time) })).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\CrossbowChargeMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */