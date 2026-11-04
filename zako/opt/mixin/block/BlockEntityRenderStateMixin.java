/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_11954;
/*    */ import net.minecraft.class_1920;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2586;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.CachedLight;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_11954.class})
/*    */ public class BlockEntityRenderStateMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74399"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_761;method_23794(Lnet/minecraft/class_1920;Lnet/minecraft/class_2338;)I")})
/*    */   private static int zakoopt$cachedLight(class_1920 level, class_2338 pos, Operation<Integer> original, class_2586 blockEntity, class_11954 state, class_11683.class_11792 crumbling) {
/*    */     class_1937 world;
/* 23 */     if (Features.LOOKUP_CACHES.on && level instanceof class_1937) { world = (class_1937)level; }
/* 24 */     else { return ((Integer)original.call(new Object[] { level, pos })).intValue(); }
/*    */     
/* 26 */     CachedLight cache = (CachedLight)blockEntity;
/* 27 */     long tick = world.method_75260();
/* 28 */     if (cache.zakoopt$lightTick() != tick) {
/* 29 */       cache.zakoopt$setLight(((Integer)original.call(new Object[] { level, pos }, )).intValue(), tick);
/*    */     }
/* 31 */     return cache.zakoopt$light();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityRenderStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */