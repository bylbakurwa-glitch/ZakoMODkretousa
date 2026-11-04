/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.class_10529;
/*    */ import net.minecraft.class_1058;
/*    */ import net.minecraft.class_11701;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_3879;
/*    */ import net.minecraft.class_4719;
/*    */ import net.minecraft.class_4730;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_10529.class})
/*    */ public abstract class AbstractSignRendererMixin
/*    */ {
/*    */   @Unique
/* 28 */   private final Map<class_2680, class_3879.class_9948> zakoopt$models = new IdentityHashMap<>();
/*    */   @Unique
/* 30 */   private final Map<class_4719, class_4730> zakoopt$materials = new IdentityHashMap<>();
/*    */   @Unique
/* 32 */   private final Map<class_4730, class_1921> zakoopt$renderTypes = new IdentityHashMap<>();
/*    */   @Unique
/* 34 */   private final Map<class_4730, class_1058> zakoopt$sprites = new IdentityHashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_65820(Lnet/minecraft/class_11971;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_10529;method_65823(Lnet/minecraft/class_2680;Lnet/minecraft/class_4719;)Lnet/minecraft/class_3879$class_9948;")})
/*    */   private class_3879.class_9948 zakoopt$model(class_10529 renderer, class_2680 state, class_4719 wood, Operation<class_3879.class_9948> original) {
/* 40 */     if (!Features.SIGN_CACHE.on) {
/* 41 */       return (class_3879.class_9948)original.call(new Object[] { renderer, state, wood });
/*    */     }
/* 43 */     return this.zakoopt$models.computeIfAbsent(state, s -> (class_3879.class_9948)original.call(new Object[] { renderer, s, wood }));
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_65825"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_10529;method_45792(Lnet/minecraft/class_4719;)Lnet/minecraft/class_4730;")})
/*    */   private class_4730 zakoopt$material(class_10529 renderer, class_4719 wood, Operation<class_4730> original) {
/* 48 */     if (!Features.SIGN_CACHE.on) {
/* 49 */       return (class_4730)original.call(new Object[] { renderer, wood });
/*    */     }
/* 51 */     return this.zakoopt$materials.computeIfAbsent(wood, w -> (class_4730)original.call(new Object[] { renderer, w }));
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_65825"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_4730;method_24146(Ljava/util/function/Function;)Lnet/minecraft/class_1921;")})
/*    */   private class_1921 zakoopt$renderType(class_4730 material, Function<?, ?> factory, Operation<class_1921> original) {
/* 56 */     if (!Features.SIGN_CACHE.on) {
/* 57 */       return (class_1921)original.call(new Object[] { material, factory });
/*    */     }
/* 59 */     return this.zakoopt$renderTypes.computeIfAbsent(material, m -> (class_1921)original.call(new Object[] { m, factory }));
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_65825"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_11701;method_73030(Lnet/minecraft/class_4730;)Lnet/minecraft/class_1058;")})
/*    */   private class_1058 zakoopt$sprite(class_11701 materials, class_4730 material, Operation<class_1058> original) {
/* 64 */     if (!Features.SIGN_CACHE.on) {
/* 65 */       return (class_1058)original.call(new Object[] { materials, material });
/*    */     }
/* 67 */     return this.zakoopt$sprites.computeIfAbsent(material, m -> (class_1058)original.call(new Object[] { materials, m }));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\AbstractSignRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */