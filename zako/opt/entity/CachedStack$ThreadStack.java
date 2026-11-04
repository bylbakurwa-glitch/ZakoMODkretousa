/*   */ package zako.opt.entity;
/*   */ final class ThreadStack extends Record { private final Thread thread; private final MemoryStack stack;
/*   */   public final String toString() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> toString : (Lzako/opt/entity/CachedStack$ThreadStack;)Ljava/lang/String;
/*   */     //   6: areturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lzako/opt/entity/CachedStack$ThreadStack;
/*   */   }
/*   */   public final int hashCode() {
/*   */     // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: <illegal opcode> hashCode : (Lzako/opt/entity/CachedStack$ThreadStack;)I
/*   */     //   6: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	7	0	this	Lzako/opt/entity/CachedStack$ThreadStack;
/*   */   }
/* 9 */   private ThreadStack(Thread thread, MemoryStack stack) { this.thread = thread; this.stack = stack; } public final boolean equals(Object o) { // Byte code:
/*   */     //   0: aload_0
/*   */     //   1: aload_1
/*   */     //   2: <illegal opcode> equals : (Lzako/opt/entity/CachedStack$ThreadStack;Ljava/lang/Object;)Z
/*   */     //   7: ireturn
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #9	-> 0
/*   */     // Local variable table:
/*   */     //   start	length	slot	name	descriptor
/*   */     //   0	8	0	this	Lzako/opt/entity/CachedStack$ThreadStack;
/* 9 */     //   0	8	1	o	Ljava/lang/Object; } public Thread thread() { return this.thread; } public MemoryStack stack() { return this.stack; }
/*   */    }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\CachedStack$ThreadStack.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */