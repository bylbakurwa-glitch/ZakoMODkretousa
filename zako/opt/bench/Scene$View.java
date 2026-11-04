/*    */ package zako.opt.bench;
/*    */ 
/*    */ public final class View
/*    */   extends Record
/*    */ {
/*    */   private final String key;
/*    */   private final int x;
/*    */   private final int y;
/*    */   private final int z;
/*    */   private final int yaw;
/*    */   private final int pitch;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/bench/Scene$View;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #26	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/bench/Scene$View;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Scene$View;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #26	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/bench/Scene$View;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/bench/Scene$View;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #26	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/bench/Scene$View;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public View(String key, int x, int y, int z, int yaw, int pitch) {
/* 26 */     this.key = key; this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch; } public String key() { return this.key; } public int x() { return this.x; } public int y() { return this.y; } public int z() { return this.z; } public int yaw() { return this.yaw; } public int pitch() { return this.pitch; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\Scene$View.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */