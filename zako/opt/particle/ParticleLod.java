/*    */ package zako.opt.particle;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_3940;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class ParticleLod {
/*    */   @Generated
/*    */   private ParticleLod() {
/* 10 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/* 12 */   public static long tick = -1L;
/*    */   
/*    */   public static boolean keep(class_3940 particle, double x, double y, double z, class_243 cam) {
/* 15 */     if (!Features.PARTICLE_LOD.on) {
/* 16 */       return true;
/*    */     }
/* 18 */     double dx = x - cam.field_1352, dy = y - cam.field_1351, dz = z - cam.field_1350;
/* 19 */     double d2 = dx * dx + dy * dy + dz * dz;
/* 20 */     if (d2 < 256.0D) {
/* 21 */       return true;
/*    */     }
/*    */     
/* 24 */     int h = System.identityHashCode(particle) >>> 4;
/* 25 */     return (d2 < 1024.0D) ? (((h & 0x1) == 0)) : (((h & 0x3) == 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\ParticleLod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */