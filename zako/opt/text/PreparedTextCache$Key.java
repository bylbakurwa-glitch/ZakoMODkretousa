/*    */ package zako.opt.text;
/*    */ 
/*    */ import net.minecraft.class_5481;
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
/*    */ public final class Key
/*    */   extends Record
/*    */ {
/*    */   private final class_5481 text;
/*    */   private final float x;
/*    */   private final float y;
/*    */   private final int color;
/*    */   private final boolean shadow;
/*    */   private final boolean includeEmpty;
/*    */   private final int background;
/*    */   
/*    */   public final String toString() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lzako/opt/text/PreparedTextCache$Key;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #74	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lzako/opt/text/PreparedTextCache$Key;
/*    */   }
/*    */   
/*    */   public Key(class_5481 text, float x, float y, int color, boolean shadow, boolean includeEmpty, int background) {
/* 74 */     this.text = text; this.x = x; this.y = y; this.color = color; this.shadow = shadow; this.includeEmpty = includeEmpty; this.background = background; } public class_5481 text() { return this.text; } public float x() { return this.x; } public float y() { return this.y; } public int color() { return this.color; } public boolean shadow() { return this.shadow; } public boolean includeEmpty() { return this.includeEmpty; } public int background() { return this.background; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof zako/opt/text/PreparedTextCache$Key
/*    */     //   4: ifeq -> 95
/*    */     //   7: aload_1
/*    */     //   8: checkcast zako/opt/text/PreparedTextCache$Key
/*    */     //   11: astore_2
/*    */     //   12: aload_2
/*    */     //   13: getfield text : Lnet/minecraft/class_5481;
/*    */     //   16: aload_0
/*    */     //   17: getfield text : Lnet/minecraft/class_5481;
/*    */     //   20: if_acmpne -> 95
/*    */     //   23: aload_2
/*    */     //   24: getfield x : F
/*    */     //   27: aload_0
/*    */     //   28: getfield x : F
/*    */     //   31: fcmpl
/*    */     //   32: ifne -> 95
/*    */     //   35: aload_2
/*    */     //   36: getfield y : F
/*    */     //   39: aload_0
/*    */     //   40: getfield y : F
/*    */     //   43: fcmpl
/*    */     //   44: ifne -> 95
/*    */     //   47: aload_2
/*    */     //   48: getfield color : I
/*    */     //   51: aload_0
/*    */     //   52: getfield color : I
/*    */     //   55: if_icmpne -> 95
/*    */     //   58: aload_2
/*    */     //   59: getfield shadow : Z
/*    */     //   62: aload_0
/*    */     //   63: getfield shadow : Z
/*    */     //   66: if_icmpne -> 95
/*    */     //   69: aload_2
/*    */     //   70: getfield includeEmpty : Z
/*    */     //   73: aload_0
/*    */     //   74: getfield includeEmpty : Z
/*    */     //   77: if_icmpne -> 95
/*    */     //   80: aload_2
/*    */     //   81: getfield background : I
/*    */     //   84: aload_0
/*    */     //   85: getfield background : I
/*    */     //   88: if_icmpne -> 95
/*    */     //   91: iconst_1
/*    */     //   92: goto -> 96
/*    */     //   95: iconst_0
/*    */     //   96: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #78	-> 0
/*    */     //   #77	-> 7
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   12	83	2	k	Lzako/opt/text/PreparedTextCache$Key;
/*    */     //   0	97	0	this	Lzako/opt/text/PreparedTextCache$Key;
/*    */     //   0	97	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 83 */     int h = System.identityHashCode(this.text);
/* 84 */     h = 31 * h + Float.floatToIntBits(this.x);
/* 85 */     h = 31 * h + Float.floatToIntBits(this.y);
/* 86 */     h = 31 * h + this.color;
/* 87 */     h = 31 * h + this.background;
/* 88 */     return 31 * h + (this.shadow ? 1 : 0) + (this.includeEmpty ? 2 : 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\text\PreparedTextCache$Key.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */