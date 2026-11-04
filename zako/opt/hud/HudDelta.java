/*    */ package zako.opt.hud;
/*    */ 
/*    */ import lombok.Generated;
/*    */ 
/*    */ public final class HudDelta {
/*    */   @Generated
/*    */   private HudDelta() {
/*  8 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/*    */   private static float sinceRefresh;
/*    */   
/*    */   public static void idle() {
/* 14 */     spent = true;
/*    */   }
/*    */   private static boolean spent = true;
/*    */   
/*    */   public static void frame(boolean refresh, float delta) {
/* 19 */     sinceRefresh = (spent ? 0.0F : sinceRefresh) + delta;
/* 20 */     spent = refresh;
/*    */   }
/*    */   
/*    */   public static float sinceRefresh() {
/* 24 */     return sinceRefresh;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\hud\HudDelta.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */