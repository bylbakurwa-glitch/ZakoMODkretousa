/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyReturnValue;
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_897;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.StateEntity;
/*    */ 
/*    */ @Mixin({class_897.class})
/*    */ public class EntityRendererStateMixin {
/*    */   @ModifyReturnValue(method = {"method_62425(Lnet/minecraft/class_1297;F)Lnet/minecraft/class_10017;"}, at = {@At("RETURN")})
/*    */   private class_10017 zakoopt$rememberEntity(class_10017 state, @Local(argsOnly = true) class_1297 entity) {
/* 16 */     ((StateEntity)state).zakoopt$entity(entity);
/* 17 */     return state;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityRendererStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */