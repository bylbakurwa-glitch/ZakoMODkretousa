/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_2591;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_824;
/*    */ import net.minecraft.class_827;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.CachedValidity;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_824.class})
/*    */ public class BlockEntityRenderDispatcherMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74348"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_2591;method_20526(Lnet/minecraft/class_2680;)Z")})
/*    */   private boolean zakoopt$cachedValid(class_2591<?> type, class_2680 state, Operation<Boolean> original, class_2586 blockEntity, float partialTick, class_11683.class_11792 crumbling) {
/* 24 */     if (!Features.SIGN_CACHE.on) {
/* 25 */       return ((Boolean)original.call(new Object[] { type, state })).booleanValue();
/*    */     }
/* 27 */     CachedValidity cache = (CachedValidity)blockEntity;
/* 28 */     if (cache.zakoopt$validState() != state) {
/* 29 */       cache.zakoopt$setValid(state, ((Boolean)original.call(new Object[] { type, state })).booleanValue());
/*    */     }
/* 31 */     return cache.zakoopt$valid();
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_74348"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_827;method_33892(Lnet/minecraft/class_2586;Lnet/minecraft/class_243;)Z")})
/*    */   private boolean zakoopt$cachedDistance(class_827<class_2586, ?> renderer, class_2586 blockEntity, class_243 camera, Operation<Boolean> original) {
/* 36 */     if (!Features.MICRO_OPTS.fewerChecks || blockEntity.method_10997() == null) {
/* 37 */       return ((Boolean)original.call(new Object[] { renderer, blockEntity, camera })).booleanValue();
/*    */     }
/* 39 */     CachedValidity cache = (CachedValidity)blockEntity;
/* 40 */     long tick = blockEntity.method_10997().method_75260();
/* 41 */     if (cache.zakoopt$distanceTick() != tick) {
/* 42 */       cache.zakoopt$setInDistance(((Boolean)original.call(new Object[] { renderer, blockEntity, camera }, )).booleanValue(), tick);
/*    */     }
/* 44 */     return cache.zakoopt$inDistance();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityRenderDispatcherMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */