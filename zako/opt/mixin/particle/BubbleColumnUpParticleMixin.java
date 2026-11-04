/*    */ package zako.opt.mixin.particle;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*    */ import java.lang.ref.WeakReference;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_3610;
/*    */ import net.minecraft.class_638;
/*    */ import net.minecraft.class_653;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_653.class})
/*    */ public class BubbleColumnUpParticleMixin
/*    */ {
/*    */   @Unique
/* 21 */   private static final Long2ObjectOpenHashMap<class_3610> zakoopt$fluids = new Long2ObjectOpenHashMap();
/*    */   @Unique
/* 23 */   private static long zakoopt$tick = Long.MIN_VALUE;
/*    */   @Unique
/* 25 */   private static WeakReference<class_638> zakoopt$level = new WeakReference<>(null);
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_3070"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_638;method_8316(Lnet/minecraft/class_2338;)Lnet/minecraft/class_3610;")})
/*    */   private class_3610 zakoopt$cachedFluid(class_638 level, class_2338 pos, Operation<class_3610> original) {
/* 30 */     if (!Features.MICRO_OPTS.on) {
/* 31 */       return (class_3610)original.call(new Object[] { level, pos });
/*    */     }
/* 33 */     long tick = level.method_75260();
/* 34 */     if (tick != zakoopt$tick || level != zakoopt$level.get()) {
/* 35 */       zakoopt$fluids.clear();
/* 36 */       zakoopt$tick = tick;
/* 37 */       zakoopt$level = new WeakReference<>(level);
/*    */     } 
/* 39 */     long key = pos.method_10063();
/* 40 */     class_3610 fluid = (class_3610)zakoopt$fluids.get(key);
/* 41 */     if (fluid == null) {
/* 42 */       fluid = (class_3610)original.call(new Object[] { level, pos });
/* 43 */       zakoopt$fluids.put(key, fluid);
/*    */     } 
/* 45 */     return fluid;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\BubbleColumnUpParticleMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */