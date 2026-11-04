/*    */ package zako.opt.entity;
/*    */ import lombok.Generated;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class EntityLod {
/*    */   @Generated
/*    */   private EntityLod() {
/*  8 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static boolean far(double distanceSq) {
/* 11 */     if (!Features.ENTITY_LOD.on) {
/* 12 */       return false;
/*    */     }
/* 14 */     double d = Features.ENTITY_LOD.distance;
/* 15 */     return (distanceSq > d * d);
/*    */   }
/*    */   
/*    */   public static boolean farPlayer(double distanceSq) {
/* 19 */     double d = Features.PLAYER_LOD.distance;
/* 20 */     return (Features.ENTITY_LOD.on && Features.PLAYER_LOD.on && distanceSq > d * d);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\EntityLod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */