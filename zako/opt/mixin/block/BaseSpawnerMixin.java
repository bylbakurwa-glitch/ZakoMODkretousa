/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_1917;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.block.TickCached;
/*    */ 
/*    */ @Mixin({class_1917.class})
/*    */ public class BaseSpawnerMixin implements TickCached {
/*    */   @Unique
/*    */   private Object zakoopt$cached;
/*    */   
/*    */   @Generated
/* 15 */   public Object zakoopt$cached() { return this.zakoopt$cached; } @Unique
/* 16 */   private long zakoopt$cachedTick = Long.MIN_VALUE; @Generated
/* 17 */   public long zakoopt$cachedTick() { return this.zakoopt$cachedTick; }
/*    */ 
/*    */   
/*    */   public void zakoopt$setCached(Object value, long tick) {
/* 21 */     this.zakoopt$cached = value;
/* 22 */     this.zakoopt$cachedTick = tick;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BaseSpawnerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */