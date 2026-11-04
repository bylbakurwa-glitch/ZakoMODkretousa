/*    */ package zako.opt.entity;
/*    */ 
/*    */ import lombok.Generated;
/*    */ import org.lwjgl.system.MemoryStack;
/*    */ 
/*    */ public final class CachedStack {
/*    */   @Generated
/*  8 */   private CachedStack() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); } private static final class ThreadStack extends Record { private final Thread thread; private final MemoryStack stack;
/*  9 */     private ThreadStack(Thread thread, MemoryStack stack) { this.thread = thread; this.stack = stack; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/entity/CachedStack$ThreadStack;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #9	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*  9 */       //   0	7	0	this	Lzako/opt/entity/CachedStack$ThreadStack; } public Thread thread() { return this.thread; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/entity/CachedStack$ThreadStack;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #9	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/entity/CachedStack$ThreadStack; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/entity/CachedStack$ThreadStack;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #9	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/entity/CachedStack$ThreadStack;
/*  9 */       //   0	8	1	o	Ljava/lang/Object; } public MemoryStack stack() { return this.stack; }
/*    */      }
/*    */ 
/*    */   
/* 13 */   private static ThreadStack cached = new ThreadStack(null, null);
/*    */   
/*    */   public static MemoryStack push() {
/* 16 */     ThreadStack current = cached;
/* 17 */     Thread thread = Thread.currentThread();
/* 18 */     if (current.thread != thread) {
/* 19 */       current = new ThreadStack(thread, MemoryStack.stackGet());
/* 20 */       cached = current;
/*    */     } 
/* 22 */     return current.stack.push();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\CachedStack.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */