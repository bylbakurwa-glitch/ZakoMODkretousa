/*    */ package zako.opt.bench;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ final class Result
/*    */   extends Record
/*    */ {
/*    */   private final int level;
/*    */   private final double modGain;
/*    */   private final Map<String, Double> off;
/*    */   private final Map<String, Boolean> chosen;
/*    */   private final Map<String, Object> picks;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/bench/Bench$Result;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #59	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/bench/Bench$Result;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Bench$Result;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #59	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/bench/Bench$Result;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lzako/opt/bench/Bench$Result;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #59	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lzako/opt/bench/Bench$Result;
/*    */     //   0	8	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private Result(int level, double modGain, Map<String, Double> off, Map<String, Boolean> chosen, Map<String, Object> picks) {
/* 59 */     this.level = level; this.modGain = modGain; this.off = off; this.chosen = chosen; this.picks = picks; } public int level() { return this.level; } public double modGain() { return this.modGain; } public Map<String, Double> off() { return this.off; } public Map<String, Boolean> chosen() { return this.chosen; } public Map<String, Object> picks() { return this.picks; }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\Bench$Result.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */