/*    */ package zako.opt.text;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_327;
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
/*    */   extends LinkedHashMap<PreparedTextCache.Key, class_327.class_11465>
/*    */ {
/*    */   null(int arg0, float arg1, boolean arg2) {
/* 28 */     super(arg0, arg1, arg2);
/*    */   }
/*    */   protected boolean removeEldestEntry(Map.Entry<PreparedTextCache.Key, class_327.class_11465> eldest) {
/* 31 */     return (size() > 2048);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\text\PreparedTextCache$2.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */