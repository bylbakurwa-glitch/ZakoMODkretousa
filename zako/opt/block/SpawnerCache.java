/*    */ package zako.opt.block;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_1917;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class SpawnerCache {
/*    */   @Generated
/*    */   private SpawnerCache() {
/* 10 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static boolean enabled() {
/* 13 */     return Features.SPAWNER_TICK_CACHE.on;
/*    */   }
/*    */   
/*    */   public static class_10017 get(class_1917 spawner, long tick) {
/* 17 */     TickCached c = (TickCached)spawner;
/* 18 */     return (c.zakoopt$cachedTick() == tick) ? (class_10017)c.zakoopt$cached() : null;
/*    */   }
/*    */   
/*    */   public static void put(class_1917 spawner, long tick, class_10017 state) {
/* 22 */     ((TickCached)spawner).zakoopt$setCached(state, tick);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\SpawnerCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */