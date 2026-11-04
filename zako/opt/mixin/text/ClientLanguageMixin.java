/*    */ package zako.opt.mixin.text;
/*    */ 
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_1078;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_5348;
/*    */ import net.minecraft.class_5481;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.text.LruMap;
/*    */ import zako.opt.text.StableText;
/*    */ 
/*    */ @Mixin({class_1078.class})
/*    */ public class ClientLanguageMixin
/*    */ {
/*    */   @Unique
/* 22 */   private final Map<class_2561, class_5481> zakoopt$visualOrder = (Map<class_2561, class_5481>)new LruMap(1024);
/*    */   
/*    */   @Unique
/* 25 */   private final Map<class_2561, class_5481> zakoopt$byIdentity = new IdentityHashMap<>();
/*    */ 
/*    */   
/*    */   @Inject(method = {"method_30934"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cached(class_5348 text, CallbackInfoReturnable<class_5481> cir) {
/* 30 */     if (text instanceof class_2561) { class_2561 component = (class_2561)text; if (Features.BIDI_CACHE.on) {
/*    */         class_5481 cached;
/*    */         
/* 33 */         boolean identity = Features.MICRO_OPTS.stableResults;
/*    */         
/* 35 */         synchronized (this.zakoopt$visualOrder) {
/* 36 */           cached = identity ? this.zakoopt$byIdentity.get(component) : null;
/* 37 */           if (cached == null) {
/* 38 */             cached = this.zakoopt$visualOrder.get(component);
/* 39 */             if (cached != null && identity) {
/* 40 */               zakoopt$remember(component, cached);
/*    */             }
/*    */           } 
/*    */         } 
/* 44 */         if (cached != null)
/* 45 */           cir.setReturnValue(cached); 
/*    */         return;
/*    */       }  }
/*    */   
/*    */   } @Inject(method = {"method_30934"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private void zakoopt$store(class_5348 text, CallbackInfoReturnable<class_5481> cir) {
/* 51 */     if (text instanceof class_2561) { class_2561 component = (class_2561)text; if (Features.BIDI_CACHE.on) {
/* 52 */         class_5481 stable = StableText.mark((class_5481)cir.getReturnValue());
/* 53 */         synchronized (this.zakoopt$visualOrder) {
/* 54 */           this.zakoopt$visualOrder.put(component, stable);
/* 55 */           if (Features.MICRO_OPTS.stableResults) {
/* 56 */             zakoopt$remember(component, stable);
/*    */           }
/*    */         } 
/* 59 */         cir.setReturnValue(stable);
/*    */       }  }
/*    */   
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private void zakoopt$remember(class_2561 component, class_5481 value) {
/* 66 */     if (this.zakoopt$byIdentity.size() >= 2048) {
/* 67 */       this.zakoopt$byIdentity.clear();
/*    */     }
/* 69 */     this.zakoopt$byIdentity.put(component, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\text\ClientLanguageMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */