/*    */ package zako.opt.mixin;
/*    */ import net.minecraft.class_437;
/*    */ import net.minecraft.class_442;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.bench.Bench;
/*    */ import zako.opt.update.UpdateCheck;
/*    */ 
/*    */ @Mixin({class_442.class})
/*    */ public class TitleScreenUpdateMixin {
/*    */   @Inject(method = {"method_25426"}, at = {@At("RETURN")})
/*    */   private void zakoopt$updateCheck(CallbackInfo ci) {
/* 16 */     UpdateCheck.start();
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_25393"}, at = {@At("HEAD")})
/*    */   private void zakoopt$offerUpdate(CallbackInfo ci) {
/* 21 */     if (!ZakoOptConfig.welcomed) {
/* 22 */       ZakoOptConfig.welcomed = true;
/* 23 */       ZakoOptConfig.save();
/* 24 */       Bench.welcome((class_437)this);
/*    */       return;
/*    */     } 
/* 27 */     UpdateCheck.offer((class_437)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\TitleScreenUpdateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */