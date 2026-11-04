/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_8113;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_8113.class})
/*    */ public abstract class DisplayCullMixin
/*    */ {
/*    */   @Shadow
/*    */   private boolean field_52434;
/*    */   
/*    */   @Inject(method = {"method_61402"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cull(CallbackInfoReturnable<Boolean> cir) {
/* 22 */     if (this.field_52434 && Features.DISPLAY_CULL.on) {
/* 23 */       cir.setReturnValue(Boolean.valueOf(true));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_5830", "getInterpolatedBoundingBox"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$box(CallbackInfoReturnable<class_238> cir) {
/* 31 */     if (this.field_52434 && Features.DISPLAY_CULL.on) {
/* 32 */       class_8113 self = (class_8113)this;
/* 33 */       cir.setReturnValue(new class_238(self.method_23317() - 4.0D, self.method_23318() - 4.0D, self.method_23321() - 4.0D, self.method_23317() + 4.0D, self.method_23318() + 4.0D, self.method_23321() + 4.0D));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\DisplayCullMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */