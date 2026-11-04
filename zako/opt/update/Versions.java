/*    */ package zako.opt.update;
/*    */ 
/*    */ public final class Versions {
/*    */   @Generated
/*    */   private Versions() {
/*  6 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static boolean newer(String candidate, String current) {
/*  9 */     int[] a = parts(candidate), b = parts(current);
/* 10 */     for (int i = 0; i < Math.max(a.length, b.length); i++) {
/* 11 */       int x = (i < a.length) ? a[i] : 0, y = (i < b.length) ? b[i] : 0;
/* 12 */       if (x != y) {
/* 13 */         return (x > y);
/*    */       }
/*    */     } 
/* 16 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean offered(String candidate, String current) {
/* 21 */     return newer(release(candidate), release(current));
/*    */   }
/*    */   
/*    */   private static String release(String version) {
/* 25 */     int[] p = parts(version);
/* 26 */     return "" + p[0] + "." + p[0];
/*    */   }
/*    */   
/*    */   private static int[] parts(String version) {
/* 30 */     String core = version.replaceFirst("^[vV]", "").split("[+\\-]", 2)[0];
/* 31 */     String[] split = core.split("\\.");
/* 32 */     int[] out = new int[split.length];
/* 33 */     for (int i = 0; i < split.length; i++) {
/* 34 */       String digits = split[i].replaceAll("\\D.*", "");
/* 35 */       out[i] = digits.isEmpty() ? 0 : Integer.parseInt(digits);
/*    */     } 
/* 37 */     return out;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 41 */     check(newer("v1.0.1", "1.0.0"));
/* 42 */     check(newer("1.2.10", "1.2.9"));
/* 43 */     check(!newer("1.0.0", "1.0.0"));
/* 44 */     check(!newer("1.0.0+1.21.11", "1.0.0"));
/* 45 */     check(newer("1.1", "1.0.9"));
/* 46 */     check(!newer("0.9.9", "1.0.0"));
/* 47 */     check(!newer("garbage", "1.0.0"));
/* 48 */     check(!offered("1.1.2", "1.1.1"));
/* 49 */     check(!offered("v1.1.0", "1.1.5"));
/* 50 */     check(offered("1.2.0", "1.1.9"));
/* 51 */     check(offered("v2.0", "1.9.9"));
/* 52 */     check(offered("1.1.2", "1.0.3"));
/* 53 */     System.out.println("ok");
/*    */   }
/*    */   
/*    */   private static void check(boolean condition) {
/* 57 */     if (!condition)
/* 58 */       throw new AssertionError(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\op\\update\Versions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */