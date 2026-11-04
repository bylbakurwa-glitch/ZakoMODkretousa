/*    */ package zako.opt.block;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.class_4587;
/*    */ 
/*    */ 
/*    */ final class Call
/*    */   extends Record
/*    */ {
/*    */   private final Method order;
/*    */   private final Object[] orderArgs;
/*    */   private final Method method;
/*    */   private final Object[] args;
/*    */   private final class_4587.class_4665 pose;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/block/SpawnerReplay$Call;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/block/SpawnerReplay$Call;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/block/SpawnerReplay$Call;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/block/SpawnerReplay$Call;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/block/SpawnerReplay$Call;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #29	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/block/SpawnerReplay$Call;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private Call(Method order, Object[] orderArgs, Method method, Object[] args, class_4587.class_4665 pose) {
/* 29 */     this.order = order; this.orderArgs = orderArgs; this.method = method; this.args = args; this.pose = pose; } public Method order() { return this.order; } public Object[] orderArgs() { return this.orderArgs; } public Method method() { return this.method; } public Object[] args() { return this.args; } public class_4587.class_4665 pose() { return this.pose; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\SpawnerReplay$Call.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */