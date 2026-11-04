/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import net.minecraft.class_11661;
/*    */ import net.minecraft.class_11788;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ @Mixin({class_11661.class})
/*    */ public class SubmitNodeStorageMixin
/*    */ {
/*    */   @Unique
/*    */   private int zakoopt$lastOrder;
/*    */   @Unique
/*    */   private class_11788 zakoopt$last;
/*    */   
/*    */   @Inject(method = {"method_73531"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cachedOrder(int order, CallbackInfoReturnable<class_11788> cir) {
/* 23 */     if (this.zakoopt$last != null && this.zakoopt$lastOrder == order && Features.MICRO_OPTS.stableResults) {
/* 24 */       cir.setReturnValue(this.zakoopt$last);
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_73531"}, at = {@At("RETURN")})
/*    */   private void zakoopt$remember(int order, CallbackInfoReturnable<class_11788> cir) {
/* 30 */     this.zakoopt$lastOrder = order;
/* 31 */     this.zakoopt$last = (class_11788)cir.getReturnValue();
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_72954"}, at = {@At("HEAD")})
/*    */   private void zakoopt$forget(CallbackInfo ci) {
/* 37 */     this.zakoopt$last = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\SubmitNodeStorageMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */