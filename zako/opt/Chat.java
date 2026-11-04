/*    */ package zako.opt;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_310;
/*    */ 
/*    */ public final class Chat {
/*    */   @Generated
/*    */   private Chat() {
/*  9 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   } public static boolean ready() {
/* 11 */     return ((class_310.method_1551()).field_1724 != null);
/*    */   }
/*    */   
/*    */   public static void show(class_2561 text) {
/* 15 */     (class_310.method_1551()).field_1705.method_1743().method_1812(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Chat.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */