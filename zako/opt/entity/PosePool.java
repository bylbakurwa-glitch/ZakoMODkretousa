/*    */ package zako.opt.entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_4587;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PosePool
/*    */ {
/*    */   @Generated
/*    */   private PosePool() {
/* 15 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 16 */   } private static final List<class_4587.class_4665> POOL = new ArrayList<>();
/*    */   
/*    */   private static int used;
/*    */   private static Thread renderThread;
/*    */   
/*    */   public static void reset() {
/* 22 */     used = 0;
/* 23 */     renderThread = Thread.currentThread();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean pooling() {
/* 28 */     return (Features.FEWER_ALLOCATIONS.on && Thread.currentThread() == renderThread);
/*    */   }
/*    */   
/*    */   public static class_4587.class_4665 copy(class_4587.class_4665 pose) {
/* 32 */     if (used == POOL.size()) {
/* 33 */       POOL.add(pose.method_56822());
/* 34 */       return POOL.get(used++);
/*    */     } 
/* 36 */     class_4587.class_4665 pooled = POOL.get(used++);
/* 37 */     pooled.method_66521(pose);
/* 38 */     return pooled;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\PosePool.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */