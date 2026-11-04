/*    */ package zako.opt.gl;
/*    */ import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;
/*    */ import lombok.Generated;
/*    */ 
/*    */ public final class TexBufferCache {
/*    */   @Generated
/*    */   private TexBufferCache() {
/*  8 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*  9 */   } private static final Int2LongOpenHashMap ATTACHED = new Int2LongOpenHashMap();
/*    */   public static int boundTexture;
/*    */   
/*    */   public static boolean attached(int format, int buffer) {
/* 13 */     return (ATTACHED.getOrDefault(boundTexture, -1L) == key(format, buffer));
/*    */   }
/*    */   
/*    */   public static void attach(int format, int buffer) {
/* 17 */     ATTACHED.put(boundTexture, key(format, buffer));
/*    */   }
/*    */   
/*    */   public static void forget() {
/* 21 */     ATTACHED.clear();
/*    */   }
/*    */   
/*    */   private static long key(int format, int buffer) {
/* 25 */     return format << 32L | buffer & 0xFFFFFFFFL;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\TexBufferCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */