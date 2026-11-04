/*    */ package zako.opt.mixin.text;
/*    */ 
/*    */ import net.minecraft.class_315;
/*    */ import net.minecraft.class_378;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.text.PreparedTextCache;
/*    */ 
/*    */ @Mixin({class_378.class})
/*    */ public class FontManagerMixin {
/*    */   @Inject(method = {"method_51614"}, at = {@At("TAIL")})
/*    */   private void zakoopt$clearOnReload(CallbackInfo ci) {
/* 15 */     PreparedTextCache.clear();
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_57024"}, at = {@At("TAIL")})
/*    */   private void zakoopt$clearOnOptions(class_315 options, CallbackInfo ci) {
/* 20 */     PreparedTextCache.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\text\FontManagerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */