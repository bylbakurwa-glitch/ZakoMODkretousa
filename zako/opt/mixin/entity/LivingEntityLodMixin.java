/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*    */ import net.minecraft.class_10042;
/*    */ import net.minecraft.class_922;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.EntityLod;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_922.class})
/*    */ public class LivingEntityLodMixin
/*    */ {
/*    */   @ModifyExpressionValue(method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_922;method_62483(Lnet/minecraft/class_10042;)Z")})
/*    */   private boolean zakoopt$farLayers(boolean render, class_10042 state) {
/* 17 */     if (!render || ((state instanceof net.minecraft.class_10055) ? EntityLod.farPlayer(state.field_53332) : EntityLod.far(state.field_53332))) return false; 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\LivingEntityLodMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */