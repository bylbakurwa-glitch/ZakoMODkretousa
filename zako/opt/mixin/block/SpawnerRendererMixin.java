/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_11659;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_11973;
/*    */ import net.minecraft.class_12075;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2636;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_3532;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_839;
/*    */ import net.minecraft.class_898;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.SpawnerCache;
/*    */ import zako.opt.block.SpawnerReplay;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_839.class})
/*    */ public class SpawnerRendererMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74388(Lnet/minecraft/class_2636;Lnet/minecraft/class_11973;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_8984;method_74396(Lnet/minecraft/class_11973;FLnet/minecraft/class_1297;Lnet/minecraft/class_898;DD)V")})
/*    */   private void zakoopt$skipFarDisplayEntity(class_11973 state, float partialTick, class_1297 entity, class_898 dispatcher, double oSpin, double spin, Operation<Void> original, class_2636 spawner, class_11973 state2, float f, class_243 camera, class_11683.class_11792 crumbling) {
/* 32 */     double distSq = SpawnerReplay.distanceSq(spawner.method_11016(), camera);
/* 33 */     if (Features.SPAWNER_CULL.on) {
/* 34 */       double d = Features.SPAWNER_CULL.distance;
/* 35 */       if (distSq > d * d) {
/* 36 */         state.field_62750 = null;
/*    */         return;
/*    */       } 
/*    */     } 
/* 40 */     if (entity == null || !SpawnerCache.enabled()) {
/* 41 */       original.call(new Object[] { state, Float.valueOf(partialTick), entity, dispatcher, Double.valueOf(oSpin), Double.valueOf(spin) });
/*    */     } else {
/* 43 */       long tick = entity.method_73183().method_75260();
/* 44 */       class_10017 cached = SpawnerCache.get(spawner.method_11390(), tick);
/* 45 */       if (cached == null) {
/* 46 */         original.call(new Object[] { state, Float.valueOf(partialTick), entity, dispatcher, Double.valueOf(oSpin), Double.valueOf(spin) });
/* 47 */         SpawnerCache.put(spawner.method_11390(), tick, state.field_62750);
/*    */       } else {
/*    */         
/* 50 */         state.field_62750 = cached;
/* 51 */         cached.field_61820 = state.field_62676;
/* 52 */         state.field_62751 = (float)class_3532.method_16436(partialTick, oSpin, spin) * 10.0F;
/* 53 */         state.field_62752 = 0.53125F;
/* 54 */         float size = Math.max(entity.method_17681(), entity.method_17682());
/* 55 */         if (size > 1.0F) {
/* 56 */           state.field_62752 /= size;
/*    */         }
/*    */       } 
/*    */     } 
/* 60 */     if (Features.SPAWNER_REPLAY.on && distSq > 36.0D)
/*    */     {
/* 62 */       state.field_62751 = (float)spin * 10.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_3589(Lnet/minecraft/class_11973;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_839;method_55253(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_10017;Lnet/minecraft/class_898;FFLnet/minecraft/class_12075;)V")})
/*    */   private void zakoopt$replayFar(class_4587 pose, class_11659 collector, class_10017 entity, class_898 dispatcher, float spin, float scale, class_12075 camera, Operation<Void> original, class_11973 state) {
/* 70 */     class_310 mc = class_310.method_1551();
/* 71 */     if (!Features.SPAWNER_REPLAY.on || !SpawnerCache.enabled() || mc.field_1687 == null || 
/* 72 */       SpawnerReplay.distanceSq(state.field_62673, camera.field_63078) <= 36.0D) {
/* 73 */       original.call(new Object[] { pose, collector, entity, dispatcher, Float.valueOf(spin), Float.valueOf(scale), camera });
/*    */       
/*    */       return;
/*    */     } 
/* 77 */     if (!SpawnerReplay.replay(entity, mc.field_1687.method_75260(), camera.field_63078, collector))
/* 78 */       SpawnerReplay.submit(entity, mc.field_1687.method_75260(), camera.field_63078, collector, c -> original.call(new Object[] { pose, c, entity, dispatcher, Float.valueOf(spin), Float.valueOf(scale), camera })); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\SpawnerRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */