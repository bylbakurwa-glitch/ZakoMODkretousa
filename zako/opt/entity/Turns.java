/*    */ package zako.opt.entity;
/*    */ 
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_7833;
/*    */ import org.joml.Quaternionf;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ public final class Turns
/*    */ {
/*    */   @Generated
/*    */   private Turns() {
/* 13 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 14 */   } private static final Quaternionf TURN = new Quaternionf();
/*    */ 
/*    */   
/*    */   public static Quaternionf degrees(class_7833 axis, float degrees) {
/* 18 */     if (!Features.FEWER_ALLOCATIONS.on) {
/* 19 */       return null;
/*    */     }
/* 21 */     float radians = degrees * 0.017453292F;
/* 22 */     if (axis == class_7833.field_40716) {
/* 23 */       return TURN.rotationY(radians);
/*    */     }
/* 25 */     if (axis == class_7833.field_40714) {
/* 26 */       return TURN.rotationX(radians);
/*    */     }
/* 28 */     if (axis == class_7833.field_40718) {
/* 29 */       return TURN.rotationZ(radians);
/*    */     }
/* 31 */     if (axis == class_7833.field_40715) {
/* 32 */       return TURN.rotationY(-radians);
/*    */     }
/* 34 */     if (axis == class_7833.field_40713) {
/* 35 */       return TURN.rotationX(-radians);
/*    */     }
/* 37 */     if (axis == class_7833.field_40717) {
/* 38 */       return TURN.rotationZ(-radians);
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\Turns.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */