/*    */ package zako.opt.block;
/*    */ import lombok.Generated;
/*    */ import net.caffeinemc.mods.sodium.client.world.LevelSlice;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2680;
/*    */ 
/*    */ public final class LeafCulling {
/*    */   @Generated
/*    */   private LeafCulling() {
/* 11 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/*    */   public static boolean hidden(class_2680 self, LevelSlice slice, class_2338 pos, class_2350 dir) {
/* 15 */     if (!(self.method_26204() instanceof net.minecraft.class_2397)) {
/* 16 */       return false;
/*    */     }
/* 18 */     int x = pos.method_10263() + dir.method_10148(), y = pos.method_10264() + dir.method_10164(), z = pos.method_10260() + dir.method_10165();
/* 19 */     if (!(slice.getBlockState(x, y, z).method_26204() instanceof net.minecraft.class_2397)) {
/* 20 */       return false;
/*    */     }
/* 22 */     class_2680 beyond = slice.getBlockState(x + dir.method_10148(), y + dir.method_10164(), z + dir.method_10165());
/* 23 */     return (beyond.method_26204() instanceof net.minecraft.class_2397 || beyond.method_26216());
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\LeafCulling.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */