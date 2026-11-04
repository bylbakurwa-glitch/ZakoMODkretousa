/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_7833;
/*    */ import net.minecraft.class_989;
/*    */ import org.joml.Quaternionf;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.Turns;
/*    */ 
/*    */ 
/*    */ @Mixin({class_989.class})
/*    */ public class HeldItemTurnsMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_4192"}, require = 0, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_7833;rotationDegrees(F)Lorg/joml/Quaternionf;")})
/*    */   private Quaternionf zakoopt$turn(class_7833 axis, float degrees, Operation<Quaternionf> original) {
/* 18 */     Quaternionf turn = Turns.degrees(axis, degrees);
/* 19 */     return (turn != null) ? turn : (Quaternionf)original.call(new Object[] { axis, Float.valueOf(degrees) });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\HeldItemTurnsMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */