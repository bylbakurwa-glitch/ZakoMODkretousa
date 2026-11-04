/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_897;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.CachedName;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_897.class})
/*    */ public class EntityRendererNameMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_62426"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_5476()Lnet/minecraft/class_2561;")})
/*    */   private class_2561 zakoopt$cachedName(class_1297 entity, Operation<class_2561> original) {
/* 19 */     if (!Features.MICRO_OPTS.fewerChecks) {
/* 20 */       return (class_2561)original.call(new Object[] { entity });
/*    */     }
/* 22 */     CachedName cache = (CachedName)entity;
/* 23 */     long tick = entity.method_73183().method_75260();
/* 24 */     if (cache.zakoopt$nameTick() != tick) {
/* 25 */       cache.zakoopt$setName((class_2561)original.call(new Object[] { entity }, ), tick);
/*    */     }
/* 27 */     return cache.zakoopt$name();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityRendererNameMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */