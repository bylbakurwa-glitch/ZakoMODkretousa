/*    */ package zako.opt.block;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends LinkedHashMap<MovingBlockCache.Key, MovingBlockCache.Entry>
/*    */ {
/*    */   null(int arg0, float arg1, boolean arg2) {
/* 42 */     super(arg0, arg1, arg2);
/*    */   }
/*    */   protected boolean removeEldestEntry(Map.Entry<MovingBlockCache.Key, MovingBlockCache.Entry> eldest) {
/* 45 */     if (size() <= 1024) {
/* 46 */       return false;
/*    */     }
/* 48 */     MovingBlockCache.free(eldest.getValue());
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\MovingBlockCache$1.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */