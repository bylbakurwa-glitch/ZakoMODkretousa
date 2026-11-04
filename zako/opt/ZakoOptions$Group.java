/*    */ package zako.opt;
/*    */ 
/*    */ import java.util.List;
/*    */ import zako.opt.feature.FeatureGroup;
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
/*    */ public final class Group
/*    */   extends Record
/*    */ {
/*    */   private final FeatureGroup type;
/*    */   private final List<ZakoOptions.Option> options;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Group;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #41	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Group;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Group;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #41	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/ZakoOptions$Group;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Group;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #41	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/ZakoOptions$Group;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Group(FeatureGroup type, List<ZakoOptions.Option> options) {
/* 41 */     this.type = type; this.options = options; } public FeatureGroup type() { return this.type; } public List<ZakoOptions.Option> options() { return this.options; }
/*    */    public String key() {
/* 43 */     return this.type.key();
/*    */   }
/*    */   
/*    */   public List<ZakoOptions.Option> available() {
/* 47 */     return this.options.stream().filter(ZakoOptions.Option::available).toList();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptions$Group.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */