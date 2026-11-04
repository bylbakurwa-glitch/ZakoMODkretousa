/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_1959;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2669;
/*    */ import net.minecraft.class_6880;
/*    */ import net.minecraft.class_835;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.TickCached;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_835.class})
/*    */ public class PistonHeadRendererMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74380(Lnet/minecraft/class_2669;Lnet/minecraft/class_11968;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_23753(Lnet/minecraft/class_2338;)Lnet/minecraft/class_6880;")})
/*    */   private class_6880<class_1959> zakoopt$cachedBiome(class_1937 level, class_2338 pos, Operation<class_6880<class_1959>> original, class_2669 blockEntity) {
/* 24 */     if (!Features.LOOKUP_CACHES.on) {
/* 25 */       return (class_6880<class_1959>)original.call(new Object[] { level, pos });
/*    */     }
/* 27 */     TickCached cache = (TickCached)blockEntity;
/* 28 */     if (cache.zakoopt$cached() == null) {
/* 29 */       cache.zakoopt$setCached(original.call(new Object[] { level, pos }, ), 0L);
/*    */     }
/* 31 */     return (class_6880<class_1959>)cache.zakoopt$cached();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\PistonHeadRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */