/*    */ package zako.opt;
/*    */ 
/*    */ import java.util.Arrays;
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
/*    */ public interface Option
/*    */ {
/*    */   String key();
/*    */   
/*    */   String[] requires();
/*    */   
/*    */   default boolean available() {
/* 30 */     return ((requires()).length == 0 || Arrays.<String>stream(requires())
/* 31 */       .anyMatch(MixinPlugin::applies));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptions$Option.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */