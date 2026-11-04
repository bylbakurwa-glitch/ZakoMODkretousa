/*    */ package zako.opt.mixin.particle;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.minecraft.class_11938;
/*    */ import net.minecraft.class_11940;
/*    */ import net.minecraft.class_11942;
/*    */ import net.minecraft.class_11944;
/*    */ import net.minecraft.class_3940;
/*    */ import net.minecraft.class_4184;
/*    */ import net.minecraft.class_4604;
/*    */ import net.minecraft.class_702;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.particle.ParallelParticles;
/*    */ import zako.opt.particle.ParticleLod;
/*    */ 
/*    */ @Mixin({class_11940.class})
/*    */ public abstract class QuadParticleGroupMixin extends class_11938<class_3940> {
/*    */   @Shadow
/*    */   @Final
/*    */   class_11944 field_62630;
/*    */   
/*    */   private QuadParticleGroupMixin(class_702 engine) {
/* 30 */     super(engine);
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_74276"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$parallel(class_4604 frustum, class_4184 camera, float partialTick, CallbackInfoReturnable<class_11942> cir) {
/* 35 */     if (ParallelParticles.extract(this.field_62620, this.field_62630, frustum, camera, partialTick)) {
/* 36 */       cir.setReturnValue(this.field_62630);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_74276"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_4604;method_74404(DDD)Z")})
/*    */   private boolean zakoopt$lod(class_4604 frustum, double x, double y, double z, Operation<Boolean> original, class_4604 frustumArg, class_4184 camera, float partialTick, @Local class_3940 particle) {
/* 43 */     return (((Boolean)original.call(new Object[] { frustum, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z) })).booleanValue() && ParticleLod.keep(particle, x, y, z, camera.method_71156()));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\QuadParticleGroupMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */