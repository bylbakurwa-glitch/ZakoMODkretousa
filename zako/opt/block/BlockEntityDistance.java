/*    */ package zako.opt.block;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_824;
/*    */ import net.minecraft.class_827;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class BlockEntityDistance {
/*    */   @Generated
/*    */   private BlockEntityDistance() {
/* 12 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/*    */   public static boolean inDistance(class_827 renderer, class_2586 blockEntity, class_243 camera) {
/* 16 */     if (!Features.MICRO_OPTS.fewerChecks || blockEntity.method_10997() == null) {
/* 17 */       return renderer.method_33892(blockEntity, camera);
/*    */     }
/* 19 */     CachedValidity cache = (CachedValidity)blockEntity;
/* 20 */     long tick = blockEntity.method_10997().method_75260();
/* 21 */     if (cache.zakoopt$distanceTick() != tick) {
/* 22 */       cache.zakoopt$setInDistance(renderer.method_33892(blockEntity, camera), tick);
/*    */     }
/* 24 */     return cache.zakoopt$inDistance();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean culledEarly(class_824 dispatcher, class_2586 blockEntity, class_243 camera) {
/* 30 */     return (camera != null && culled(dispatcher.method_3550(blockEntity), blockEntity, camera));
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean culled(class_827 renderer, class_2586 blockEntity, class_243 camera) {
/* 35 */     return (Features.BLOCK_ENTITY_EARLY_CULL.on && renderer != null && camera != null && !inDistance(renderer, blockEntity, camera));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\BlockEntityDistance.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */