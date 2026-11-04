/*    */ package zako.opt;
/*    */ 
/*    */ import java.util.List;
/*    */ import zako.opt.feature.Feature;
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
/*    */ final class Item
/*    */   extends Record
/*    */ {
/*    */   private final Feature feature;
/*    */   private final ZakoOptions.Toggle toggle;
/*    */   private final List<ZakoOptions.Option> settings;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptScreen$Item;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #43	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptScreen$Item;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptScreen$Item;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #43	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptScreen$Item;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptScreen$Item;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #43	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/ZakoOptScreen$Item;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private Item(Feature feature, ZakoOptions.Toggle toggle, List<ZakoOptions.Option> settings) {
/* 43 */     this.feature = feature; this.toggle = toggle; this.settings = settings; } public Feature feature() { return this.feature; } public ZakoOptions.Toggle toggle() { return this.toggle; } public List<ZakoOptions.Option> settings() { return this.settings; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptScreen$Item.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */