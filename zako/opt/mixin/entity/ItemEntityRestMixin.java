/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import net.minecraft.class_1542;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_1542.class})
/*    */ public abstract class ItemEntityRestMixin
/*    */ {
/*    */   @Shadow
/*    */   private int field_7204;
/*    */   
/*    */   @Inject(method = {"method_5773"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$rest(CallbackInfo ci) {
/* 22 */     class_1542 self = (class_1542)this;
/*    */     
/* 24 */     if ((self.field_6012 + self.method_5628()) % 4 != 0 && Features.RESTING_ITEMS.on && self.method_24828() && !self.method_5799() && self
/* 25 */       .method_18798().method_37268() <= 9.999999747378752E-6D && self.method_73183() instanceof net.minecraft.class_638) {
/* 26 */       if (this.field_7204 != -32768) {
/* 27 */         this.field_7204++;
/*    */       }
/* 29 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemEntityRestMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */