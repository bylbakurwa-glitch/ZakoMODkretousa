/*     */ package zako.opt.mixin.block;
/*     */ 
/*     */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*     */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntFunction;
/*     */ import net.minecraft.class_11683;
/*     */ import net.minecraft.class_11959;
/*     */ import net.minecraft.class_1937;
/*     */ import net.minecraft.class_2281;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2586;
/*     */ import net.minecraft.class_2618;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_4732;
/*     */ import net.minecraft.class_4737;
/*     */ import net.minecraft.class_7833;
/*     */ import net.minecraft.class_826;
/*     */ import org.joml.Quaternionf;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import zako.opt.block.CachedLight;
/*     */ import zako.opt.block.ChestFunctions;
/*     */ import zako.opt.block.TickCached;
/*     */ import zako.opt.feature.Features;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({class_826.class})
/*     */ public class ChestRendererMixin
/*     */ {
/*     */   @Unique
/*     */   private static final String EXTRACT = "extractRenderState(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V";
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_2281;method_24167(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Z)Lnet/minecraft/class_4732$class_4734;")})
/*     */   private class_4732.class_4734<?> zakoopt$cachedCombine(class_2281 block, class_2680 state, class_1937 level, class_2338 pos, boolean ignoreBlocked, Operation<class_4732.class_4734<?>> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/*  41 */     if (!Features.BLOCK_ENTITY_CACHE.on) {
/*  42 */       return (class_4732.class_4734)original.call(new Object[] { block, state, level, pos, Boolean.valueOf(ignoreBlocked) });
/*     */     }
/*     */     
/*  45 */     TickCached cache = (TickCached)blockEntity;
/*  46 */     long tick = level.method_75260();
/*  47 */     if (cache.zakoopt$cachedTick() != tick || cache.zakoopt$cached() == null) {
/*  48 */       cache.zakoopt$setCached(original.call(new Object[] { block, state, level, pos, Boolean.valueOf(ignoreBlocked) }, ), tick);
/*     */     }
/*  50 */     return (class_4732.class_4734)cache.zakoopt$cached();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/ints/Int2IntFunction;applyAsInt(I)I")})
/*     */   private int zakoopt$cachedPairLight(Int2IntFunction combiner, int light, Operation<Integer> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/*  59 */     class_1937 level = blockEntity.method_10997();
/*  60 */     if (!Features.MICRO_OPTS.stableResults || level == null) {
/*  61 */       return ((Integer)original.call(new Object[] { combiner, Integer.valueOf(light) })).intValue();
/*     */     }
/*  63 */     CachedLight cache = (CachedLight)blockEntity;
/*  64 */     long tick = level.method_75260();
/*  65 */     if (cache.zakoopt$pairLightTick() != tick) {
/*  66 */       cache.zakoopt$setPairLight(((Integer)original.call(new Object[] { combiner, Integer.valueOf(light) }, )).intValue(), tick);
/*     */     }
/*  68 */     return cache.zakoopt$pairLight();
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*  73 */   private static final Float ZAKOOPT$SHUT = Float.valueOf(0.0F);
/*     */   
/*     */   @WrapOperation(method = {"method_74367"}, at = {@At(value = "INVOKE", target = "Ljava/lang/Float;valueOf(F)Ljava/lang/Float;")})
/*     */   private Float zakoopt$lid(float value, Operation<Float> original) {
/*  77 */     return (value == 0.0F && Features.FEWER_ALLOCATIONS.on) ? ZAKOOPT$SHUT : (Float)original.call(new Object[] { Float.valueOf(value) });
/*     */   }
/*     */ 
/*     */   
/*     */   @Unique
/*  82 */   private static final Quaternionf ZAKOOPT$TURN = new Quaternionf();
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74367"}, require = 0, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_7833;rotationDegrees(F)Lorg/joml/Quaternionf;")})
/*     */   private Quaternionf zakoopt$turn(class_7833 axis, float degrees, Operation<Quaternionf> original) {
/*  87 */     return (Features.FEWER_ALLOCATIONS.on && axis == class_7833.field_40716) ? ZAKOOPT$TURN.rotationY(degrees * 0.017453292F) : (Quaternionf)original.call(new Object[] { axis, Float.valueOf(degrees) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_2281;method_24166(Lnet/minecraft/class_2618;)Lnet/minecraft/class_4732$class_3923;")})
/*     */   private class_4732.class_3923<?, ?> zakoopt$openCombiner(class_2618 lid, Operation<class_4732.class_3923<?, ?>> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/*  97 */     class_1937 level = blockEntity.method_10997();
/*  98 */     return (Features.FEWER_ALLOCATIONS.on && level != null && ((ChestFunctions)blockEntity).zakoopt$openFn(level.method_75260()) != null) ? null : (class_4732.class_3923<?, ?>)original.call(new Object[] { lid });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "NEW", target = "()Lnet/minecraft/class_4737;")})
/*     */   private class_4737<?> zakoopt$lightCombiner(Operation<class_4737<?>> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/* 105 */     class_1937 level = blockEntity.method_10997();
/* 106 */     return (Features.FEWER_ALLOCATIONS.on && level != null && ((ChestFunctions)blockEntity).zakoopt$lightFn(level.method_75260()) != null) ? null : (class_4737)original.call(new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/class_4732$class_4734;apply(Lnet/minecraft/class_4732$class_3923;)Ljava/lang/Object;")})
/*     */   private Object zakoopt$openFn(class_4732.class_4734<?> result, class_4732.class_3923<?, ?> combiner, Operation<Object> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/* 114 */     ChestFunctions cache = (ChestFunctions)blockEntity;
/* 115 */     class_1937 level = blockEntity.method_10997();
/* 116 */     if (combiner == null) {
/* 117 */       return cache.zakoopt$openFn(level.method_75260());
/*     */     }
/* 119 */     Object fn = original.call(new Object[] { result, combiner });
/* 120 */     if (Features.FEWER_ALLOCATIONS.on && level != null) {
/* 121 */       cache.zakoopt$setOpenFn(fn, level.method_75260());
/*     */     }
/* 123 */     return fn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @WrapOperation(method = {"method_74365(Lnet/minecraft/class_2586;Lnet/minecraft/class_11959;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/class_4732$class_4734;apply(Lnet/minecraft/class_4732$class_3923;)Ljava/lang/Object;")})
/*     */   private Object zakoopt$lightFn(class_4732.class_4734<?> result, class_4732.class_3923<?, ?> combiner, Operation<Object> original, class_2586 blockEntity, class_11959 renderState, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/* 131 */     ChestFunctions cache = (ChestFunctions)blockEntity;
/* 132 */     class_1937 level = blockEntity.method_10997();
/* 133 */     if (combiner == null) {
/* 134 */       return cache.zakoopt$lightFn(level.method_75260());
/*     */     }
/* 136 */     Object fn = original.call(new Object[] { result, combiner });
/* 137 */     if (Features.FEWER_ALLOCATIONS.on && level != null) {
/* 138 */       cache.zakoopt$setLightFn(fn, level.method_75260());
/*     */     }
/* 140 */     return fn;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\ChestRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */