/*    */ package zako.opt.mixin.particle;
/*    */ 
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_638;
/*    */ import net.minecraft.class_703;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_703.class})
/*    */ public class ParticleMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   protected class_638 field_3851;
/*    */   @Shadow
/*    */   protected boolean field_3862;
/*    */   @Shadow
/*    */   protected double field_3874;
/*    */   @Shadow
/*    */   protected double field_3854;
/*    */   @Shadow
/*    */   protected double field_3871;
/*    */   @Unique
/*    */   private boolean zakoopt$physicsBefore;
/*    */   @Unique
/* 38 */   private long zakoopt$lightTick = Long.MIN_VALUE;
/*    */ 
/*    */   
/*    */   @Unique
/*    */   private Integer zakoopt$light;
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_3068"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cachedLight(float partialTick, CallbackInfoReturnable<Integer> cir) {
/* 47 */     if (this.zakoopt$lightTick == this.field_3851.method_75260() && Features.PARTICLE_LIGHT.on) {
/* 48 */       cir.setReturnValue(this.zakoopt$light);
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3068"}, at = {@At("RETURN")})
/*    */   private void zakoopt$storeLight(float partialTick, CallbackInfoReturnable<Integer> cir) {
/* 54 */     this.zakoopt$light = (Integer)cir.getReturnValue();
/* 55 */     this.zakoopt$lightTick = this.field_3851.method_75260();
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3069"}, at = {@At("HEAD")})
/*    */   private void zakoopt$cheapPhysics(double dx, double dy, double dz, CallbackInfo ci) {
/* 60 */     this.zakoopt$physicsBefore = this.field_3862;
/* 61 */     if (this.field_3862 && Features.PARTICLE_PHYSICS.on && zakoopt$skipCollision()) {
/* 62 */       this.field_3862 = false;
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3069"}, at = {@At("RETURN")})
/*    */   private void zakoopt$restorePhysics(double dx, double dy, double dz, CallbackInfo ci) {
/* 68 */     this.field_3862 = this.zakoopt$physicsBefore;
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private boolean zakoopt$skipCollision() {
/* 73 */     Object self = this;
/* 74 */     if (self instanceof net.minecraft.class_4794 || self instanceof net.minecraft.class_3937 || self instanceof net.minecraft.class_653) {
/* 75 */       return true;
/*    */     }
/* 77 */     class_243 cam = (class_310.method_1551()).field_1773.method_19418().method_71156();
/* 78 */     double ddx = this.field_3874 - cam.field_1352, ddy = this.field_3854 - cam.field_1351, ddz = this.field_3871 - cam.field_1350;
/* 79 */     return (ddx * ddx + ddy * ddy + ddz * ddz > 256.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\ParticleMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */