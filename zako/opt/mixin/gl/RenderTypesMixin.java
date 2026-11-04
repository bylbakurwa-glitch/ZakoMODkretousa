/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraft.class_12249;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_2960;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_12249.class})
/*    */ public class RenderTypesMixin
/*    */ {
/*    */   @Unique
/* 21 */   private static final Map<class_2960, class_1921> zakoopt$cutoutNoCull = new ConcurrentHashMap<>();
/*    */   @Unique
/* 23 */   private static final Map<class_2960, class_1921> zakoopt$translucent = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_75969(Lnet/minecraft/class_2960;Z)Lnet/minecraft/class_1921;"}, at = {@At(value = "INVOKE", target = "Ljava/util/function/BiFunction;apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")})
/*    */   private static Object zakoopt$cutoutNoCull(BiFunction<Object, Object, Object> memo, Object id, Object flag, Operation<Object> original) {
/* 29 */     return zakoopt$lookup(zakoopt$cutoutNoCull, memo, id, flag, original);
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_75980(Lnet/minecraft/class_2960;Z)Lnet/minecraft/class_1921;"}, at = {@At(value = "INVOKE", target = "Ljava/util/function/BiFunction;apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")})
/*    */   private static Object zakoopt$translucent(BiFunction<Object, Object, Object> memo, Object id, Object flag, Operation<Object> original) {
/* 35 */     return zakoopt$lookup(zakoopt$translucent, memo, id, flag, original);
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private static Object zakoopt$lookup(Map<class_2960, class_1921> cache, BiFunction<Object, Object, Object> memo, Object id, Object flag, Operation<Object> original) {
/* 40 */     if (!((Boolean)flag).booleanValue() || !Features.LOOKUP_CACHES.on) {
/* 41 */       return original.call(new Object[] { memo, id, flag });
/*    */     }
/* 43 */     class_1921 type = cache.get(id);
/* 44 */     if (type == null) {
/* 45 */       type = (class_1921)original.call(new Object[] { memo, id, flag });
/* 46 */       cache.put((class_2960)id, type);
/*    */     } 
/* 48 */     return type;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderTypesMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */