/*    */ package zako.opt;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2561;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class Blocked {
/*    */   @Generated
/*    */   private Blocked() {
/* 10 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   } public static class_2561 reason(String key) {
/* 12 */     Feature feature = Features.byKey(key);
/* 13 */     String cause = (feature == null) ? null : feature.blocked();
/* 14 */     return (cause == null) ? null : (class_2561)class_2561.method_43469("zakoopt.blocked", new Object[] {
/* 15 */           cause.startsWith("zakoopt.") ? class_2561.method_43471(cause) : class_2561.method_43470(cause)
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Blocked.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */