/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_897;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.PooledState;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_897.class})
/*    */ public abstract class EntityRenderStatePoolMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_62425(Lnet/minecraft/class_1297;F)Lnet/minecraft/class_10017;"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_897;method_55269()Lnet/minecraft/class_10017;")})
/*    */   private class_10017 zakoopt$pooled(class_897<?, ?> self, Operation<class_10017> original, @Local(argsOnly = true) class_1297 entity) {
/* 22 */     if (!Features.STATE_POOL.on) {
/* 23 */       return (class_10017)original.call(new Object[] { self });
/*    */     }
/* 25 */     PooledState pooled = (PooledState)entity;
/* 26 */     if (pooled.zakoopt$stateOwner() == self) {
/* 27 */       return (class_10017)pooled.zakoopt$state();
/*    */     }
/* 29 */     class_10017 state = (class_10017)original.call(new Object[] { self });
/* 30 */     pooled.zakoopt$setState(state, self);
/* 31 */     return state;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityRenderStatePoolMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */