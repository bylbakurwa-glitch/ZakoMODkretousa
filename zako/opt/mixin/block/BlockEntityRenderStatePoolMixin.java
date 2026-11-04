/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.minecraft.class_11954;
/*    */ import net.minecraft.class_11973;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_824;
/*    */ import net.minecraft.class_826;
/*    */ import net.minecraft.class_827;
/*    */ import net.minecraft.class_835;
/*    */ import net.minecraft.class_839;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.PooledState;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_824.class})
/*    */ public class BlockEntityRenderStatePoolMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74348"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_827;method_74335()Lnet/minecraft/class_11954;")})
/*    */   private class_11954 zakoopt$pooled(class_827<?, ?> renderer, Operation<class_11954> original, @Local(argsOnly = true) class_2586 blockEntity) {
/* 29 */     Class<?> type = renderer.getClass();
/* 30 */     if (!Features.FEWER_ALLOCATIONS.on || (type != class_826.class && type != class_839.class && type != class_835.class)) {
/* 31 */       return (class_11954)original.call(new Object[] { renderer });
/*    */     }
/* 33 */     PooledState pooled = (PooledState)blockEntity;
/* 34 */     if (pooled.zakoopt$stateOwner() == renderer) {
/* 35 */       class_11954 class_11954 = (class_11954)pooled.zakoopt$state();
/* 36 */       if (class_11954 instanceof class_11973) { class_11973 spawner = (class_11973)class_11954;
/* 37 */         spawner.field_62750 = null; }
/*    */       
/* 39 */       return class_11954;
/*    */     } 
/* 41 */     class_11954 state = (class_11954)original.call(new Object[] { renderer });
/* 42 */     pooled.zakoopt$setState(state, renderer);
/* 43 */     return state;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityRenderStatePoolMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */