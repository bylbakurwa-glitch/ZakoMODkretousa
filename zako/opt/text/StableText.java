/*    */ package zako.opt.text;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Set;
/*    */ import java.util.WeakHashMap;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_5224;
/*    */ import net.minecraft.class_5481;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class StableText
/*    */   implements class_5481
/*    */ {
/*    */   @Generated
/*    */   StableText(class_5481 text) {
/* 16 */     this.text = text;
/*    */   }
/* 18 */   private static final Set<class_5481> STABLE = Collections.newSetFromMap(new WeakHashMap<>());
/*    */   private final class_5481 text;
/*    */   
/*    */   public static class_5481 mark(class_5481 text) {
/* 22 */     StableText stable = new StableText(text);
/* 23 */     synchronized (STABLE) {
/* 24 */       STABLE.add(stable);
/*    */     } 
/* 26 */     return stable;
/*    */   }
/*    */   
/*    */   public static boolean isStable(class_5481 text) {
/* 30 */     if (Features.MICRO_OPTS.stableResults) {
/* 31 */       return text instanceof StableText;
/*    */     }
/* 33 */     synchronized (STABLE) {
/* 34 */       return STABLE.contains(text);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean accept(class_5224 sink) {
/* 40 */     return this.text.accept(sink);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\text\StableText.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */