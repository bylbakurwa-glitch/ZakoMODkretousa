/*    */ package zako.opt.mixin.text;
/*    */ 
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_5481;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.text.PreparedTextCache;
/*    */ 
/*    */ @Mixin({class_327.class})
/*    */ public class FontMixin
/*    */ {
/*    */   @Inject(method = {"method_71795(Lnet/minecraft/class_5481;FFIZZI)Lnet/minecraft/class_327$class_11465;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cached(class_5481 text, float x, float y, int color, boolean shadow, boolean includeEmpty, int background, CallbackInfoReturnable<class_327.class_11465> cir) {
/* 16 */     if (PreparedTextCache.enabled() && PreparedTextCache.isStable(text)) {
/* 17 */       class_327.class_11465 cached = PreparedTextCache.get(PreparedTextCache.key(text, x, y, color, shadow, includeEmpty, background));
/* 18 */       if (cached != null) {
/* 19 */         cir.setReturnValue(cached);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_71795(Lnet/minecraft/class_5481;FFIZZI)Lnet/minecraft/class_327$class_11465;"}, at = {@At("RETURN")})
/*    */   private void zakoopt$store(class_5481 text, float x, float y, int color, boolean shadow, boolean includeEmpty, int background, CallbackInfoReturnable<class_327.class_11465> cir) {
/* 27 */     if (PreparedTextCache.enabled() && PreparedTextCache.isStable(text)) {
/* 28 */       PreparedTextCache.Key key = PreparedTextCache.key(text, x, y, color, shadow, includeEmpty, background);
/* 29 */       if (!PreparedTextCache.known(key))
/* 30 */         PreparedTextCache.put(key, (class_327.class_11465)cir.getReturnValue()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\text\FontMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */