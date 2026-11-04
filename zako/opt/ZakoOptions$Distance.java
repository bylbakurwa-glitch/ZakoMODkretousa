/*    */ package zako.opt;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Distance
/*    */   extends Record
/*    */   implements ZakoOptions.Option
/*    */ {
/*    */   private final String key;
/*    */   private final int min;
/*    */   private final int max;
/*    */   private final String unit;
/*    */   private final Consumer<Integer> set;
/*    */   private final Supplier<Integer> get;
/*    */   private final String[] requires;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Distance;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Distance;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Distance;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Distance;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Distance;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/ZakoOptions$Distance;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Distance(String key, int min, int max, String unit, Consumer<Integer> set, Supplier<Integer> get, String... requires) {
/* 38 */     this.key = key; this.min = min; this.max = max; this.unit = unit; this.set = set; this.get = get; this.requires = requires; } public String key() { return this.key; } public int min() { return this.min; } public int max() { return this.max; } public String unit() { return this.unit; } public Consumer<Integer> set() { return this.set; } public Supplier<Integer> get() { return this.get; } public String[] requires() { return this.requires; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptions$Distance.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */