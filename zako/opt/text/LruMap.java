/*    */ package zako.opt.text;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public final class LruMap<K, V>
/*    */   extends LinkedHashMap<K, V>
/*    */ {
/*    */   private final int maxSize;
/*    */   
/*    */   public LruMap(int maxSize) {
/* 13 */     super(256, 0.75F, true);
/* 14 */     this.maxSize = maxSize;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
/* 19 */     return (size() > this.maxSize);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\text\LruMap.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */