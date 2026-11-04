/*    */ package zako.opt;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Hit
/*    */   extends Record
/*    */ {
/*    */   private final int x0;
/*    */   private final int y0;
/*    */   private final int x1;
/*    */   private final int y1;
/*    */   private final Runnable click;
/*    */   private final ZakoOptions.Distance slider;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/Panel$Hit;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/Panel$Hit;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/Panel$Hit;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/Panel$Hit;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/Panel$Hit;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/Panel$Hit;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private Hit(int x0, int y0, int x1, int y1, Runnable click, ZakoOptions.Distance slider) {
/* 29 */     this.x0 = x0; this.y0 = y0; this.x1 = x1; this.y1 = y1; this.click = click; this.slider = slider; } public int x0() { return this.x0; } public int y0() { return this.y0; } public int x1() { return this.x1; } public int y1() { return this.y1; } public Runnable click() { return this.click; } public ZakoOptions.Distance slider() { return this.slider; }
/*    */    boolean contains(double x, double y) {
/* 31 */     return (x >= this.x0 && x < this.x1 && y >= this.y0 && y < this.y1);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Panel$Hit.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */