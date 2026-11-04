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
/*    */ public final class Toggle
/*    */   extends Record
/*    */   implements ZakoOptions.Option
/*    */ {
/*    */   private final String key;
/*    */   private final Consumer<Boolean> set;
/*    */   private final Supplier<Boolean> get;
/*    */   private final String[] requires;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Toggle;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #35	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Toggle;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Toggle;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #35	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Toggle;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Toggle;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #35	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/ZakoOptions$Toggle;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Toggle(String key, Consumer<Boolean> set, Supplier<Boolean> get, String... requires) {
/* 35 */     this.key = key; this.set = set; this.get = get; this.requires = requires; } public String key() { return this.key; } public Consumer<Boolean> set() { return this.set; } public Supplier<Boolean> get() { return this.get; } public String[] requires() { return this.requires; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptions$Toggle.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */