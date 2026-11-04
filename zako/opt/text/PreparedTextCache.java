/*    */ package zako.opt.text;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2583;
/*    */ import net.minecraft.class_327;
/*    */ import net.minecraft.class_5481;
/*    */ import net.minecraft.class_8030;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class PreparedTextCache
/*    */ {
/*    */   @Generated
/*    */   private PreparedTextCache() {
/* 16 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 17 */   } private static final class_327.class_11465 UNCACHEABLE = new class_327.class_11465()
/*    */     {
/*    */       public void method_71801(class_327.class_11464 visitor) {}
/*    */ 
/*    */ 
/*    */       
/*    */       public class_8030 method_71800() {
/* 24 */         return null;
/*    */       }
/*    */     };
/*    */   
/* 28 */   private static final Map<Key, class_327.class_11465> CACHE = new LinkedHashMap<Key, class_327.class_11465>(512, 0.75F, true)
/*    */     {
/*    */       protected boolean removeEldestEntry(Map.Entry<PreparedTextCache.Key, class_327.class_11465> eldest) {
/* 31 */         return (size() > 2048);
/*    */       }
/*    */     };
/*    */   
/*    */   public static boolean isStable(class_5481 text) {
/* 36 */     return StableText.isStable(text);
/*    */   }
/*    */   
/*    */   public static boolean enabled() {
/* 40 */     return Features.PREPARED_TEXT_CACHE.on;
/*    */   }
/*    */   
/*    */   public static Key key(class_5481 text, float x, float y, int color, boolean shadow, boolean includeEmpty, int background) {
/* 44 */     return new Key(text, x, y, color, shadow, includeEmpty, background);
/*    */   }
/*    */   
/*    */   public static class_327.class_11465 get(Key key) {
/* 48 */     synchronized (CACHE) {
/* 49 */       class_327.class_11465 cached = CACHE.get(key);
/* 50 */       return (cached == UNCACHEABLE) ? null : cached;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean known(Key key) {
/* 55 */     synchronized (CACHE) {
/* 56 */       return CACHE.containsKey(key);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void put(Key key, class_327.class_11465 prepared) {
/* 62 */     boolean plain = key.text.accept((index, style, codePoint) -> !style.method_10987());
/* 63 */     synchronized (CACHE) {
/* 64 */       CACHE.put(key, plain ? prepared : UNCACHEABLE);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void clear() {
/* 69 */     synchronized (CACHE) {
/* 70 */       CACHE.clear();
/*    */     } 
/*    */   } public static final class Key extends Record {
/*    */     private final class_5481 text; private final float x; private final float y;
/* 74 */     public Key(class_5481 text, float x, float y, int color, boolean shadow, boolean includeEmpty, int background) { this.text = text; this.x = x; this.y = y; this.color = color; this.shadow = shadow; this.includeEmpty = includeEmpty; this.background = background; } private final int color; private final boolean shadow; private final boolean includeEmpty; private final int background; public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/text/PreparedTextCache$Key;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #74	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 74 */       //   0	7	0	this	Lzako/opt/text/PreparedTextCache$Key; } public class_5481 text() { return this.text; } public float x() { return this.x; } public float y() { return this.y; } public int color() { return this.color; } public boolean shadow() { return this.shadow; } public boolean includeEmpty() { return this.includeEmpty; } public int background() { return this.background; }
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean equals(Object o) {
/*    */       // Byte code:
/*    */       //   0: aload_1
/*    */       //   1: instanceof zako/opt/text/PreparedTextCache$Key
/*    */       //   4: ifeq -> 95
/*    */       //   7: aload_1
/*    */       //   8: checkcast zako/opt/text/PreparedTextCache$Key
/*    */       //   11: astore_2
/*    */       //   12: aload_2
/*    */       //   13: getfield text : Lnet/minecraft/class_5481;
/*    */       //   16: aload_0
/*    */       //   17: getfield text : Lnet/minecraft/class_5481;
/*    */       //   20: if_acmpne -> 95
/*    */       //   23: aload_2
/*    */       //   24: getfield x : F
/*    */       //   27: aload_0
/*    */       //   28: getfield x : F
/*    */       //   31: fcmpl
/*    */       //   32: ifne -> 95
/*    */       //   35: aload_2
/*    */       //   36: getfield y : F
/*    */       //   39: aload_0
/*    */       //   40: getfield y : F
/*    */       //   43: fcmpl
/*    */       //   44: ifne -> 95
/*    */       //   47: aload_2
/*    */       //   48: getfield color : I
/*    */       //   51: aload_0
/*    */       //   52: getfield color : I
/*    */       //   55: if_icmpne -> 95
/*    */       //   58: aload_2
/*    */       //   59: getfield shadow : Z
/*    */       //   62: aload_0
/*    */       //   63: getfield shadow : Z
/*    */       //   66: if_icmpne -> 95
/*    */       //   69: aload_2
/*    */       //   70: getfield includeEmpty : Z
/*    */       //   73: aload_0
/*    */       //   74: getfield includeEmpty : Z
/*    */       //   77: if_icmpne -> 95
/*    */       //   80: aload_2
/*    */       //   81: getfield background : I
/*    */       //   84: aload_0
/*    */       //   85: getfield background : I
/*    */       //   88: if_icmpne -> 95
/*    */       //   91: iconst_1
/*    */       //   92: goto -> 96
/*    */       //   95: iconst_0
/*    */       //   96: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #78	-> 0
/*    */       //   #77	-> 7
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   12	83	2	k	Lzako/opt/text/PreparedTextCache$Key;
/*    */       //   0	97	0	this	Lzako/opt/text/PreparedTextCache$Key;
/*    */       //   0	97	1	o	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 83 */       int h = System.identityHashCode(this.text);
/* 84 */       h = 31 * h + Float.floatToIntBits(this.x);
/* 85 */       h = 31 * h + Float.floatToIntBits(this.y);
/* 86 */       h = 31 * h + this.color;
/* 87 */       h = 31 * h + this.background;
/* 88 */       return 31 * h + (this.shadow ? 1 : 0) + (this.includeEmpty ? 2 : 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\text\PreparedTextCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */