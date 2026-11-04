/*     */ package zako.opt.mixin.block;
/*     */ 
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_2680;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import zako.opt.block.ChestFunctions;
/*     */ 
/*     */ @Mixin({class_2586.class})
/*     */ public class BlockEntityMixin implements TickCached, CachedLight, CachedValidity, PooledState, ChestFunctions, MovingSlots {
/*     */   @Unique
/*     */   private Object zakoopt$moving0;
/*     */   @Unique
/*     */   private Object zakoopt$moving1;
/*     */   @Unique
/*     */   private Object zakoopt$chestOpen;
/*     */   @Unique
/*     */   private Object zakoopt$chestLight;
/*     */   
/*     */   @Generated
/*  21 */   public Object zakoopt$moving0() { return this.zakoopt$moving0; }
/*     */   @Generated
/*  23 */   public Object zakoopt$moving1() { return this.zakoopt$moving1; }
/*     */   @Generated
/*  25 */   public Object zakoopt$chestOpen() { return this.zakoopt$chestOpen; }
/*     */   @Generated
/*  27 */   public Object zakoopt$chestLight() { return this.zakoopt$chestLight; } @Unique
/*  28 */   private long zakoopt$chestTick = Long.MIN_VALUE; @Unique private Object zakoopt$state; @Generated
/*  29 */   public long zakoopt$chestTick() { return this.zakoopt$chestTick; } @Unique private Object zakoopt$stateOwner; @Unique
/*     */   private Object zakoopt$cached; @Generated
/*  31 */   public Object zakoopt$state() { return this.zakoopt$state; }
/*     */   @Generated
/*  33 */   public Object zakoopt$stateOwner() { return this.zakoopt$stateOwner; }
/*     */   @Generated
/*  35 */   public Object zakoopt$cached() { return this.zakoopt$cached; } @Unique
/*  36 */   private long zakoopt$cachedTick = Long.MIN_VALUE; @Unique private int zakoopt$light; @Unique private class_2680 zakoopt$validState; @Unique private boolean zakoopt$valid; @Generated
/*  37 */   public long zakoopt$cachedTick() { return this.zakoopt$cachedTick; }
/*     */   @Generated
/*  39 */   public int zakoopt$light() { return this.zakoopt$light; }
/*     */   @Generated
/*  41 */   public class_2680 zakoopt$validState() { return this.zakoopt$validState; }
/*     */   @Generated
/*  43 */   public boolean zakoopt$valid() { return this.zakoopt$valid; } @Unique
/*  44 */   private long zakoopt$lightTick = Long.MIN_VALUE; @Generated
/*  45 */   public long zakoopt$lightTick() { return this.zakoopt$lightTick; } @Unique
/*  46 */   private long zakoopt$distanceTick = Long.MIN_VALUE; @Unique private boolean zakoopt$inDistance; @Unique private int zakoopt$pairLight; @Generated
/*  47 */   public long zakoopt$distanceTick() { return this.zakoopt$distanceTick; }
/*     */   @Generated
/*  49 */   public boolean zakoopt$inDistance() { return this.zakoopt$inDistance; }
/*     */   @Generated
/*  51 */   public int zakoopt$pairLight() { return this.zakoopt$pairLight; } @Unique
/*  52 */   private long zakoopt$pairLightTick = Long.MIN_VALUE; @Generated
/*  53 */   public long zakoopt$pairLightTick() { return this.zakoopt$pairLightTick; }
/*     */ 
/*     */   
/*     */   public Object zakoopt$moving(int slot) {
/*  57 */     return (slot == 0) ? this.zakoopt$moving0 : this.zakoopt$moving1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setMoving(int slot, Object state) {
/*  62 */     if (slot == 0) {
/*  63 */       this.zakoopt$moving0 = state;
/*     */     } else {
/*  65 */       this.zakoopt$moving1 = state;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object zakoopt$openFn(long tick) {
/*  71 */     return (this.zakoopt$chestTick == tick) ? this.zakoopt$chestOpen : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object zakoopt$lightFn(long tick) {
/*  76 */     return (this.zakoopt$chestTick == tick) ? this.zakoopt$chestLight : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setOpenFn(Object fn, long tick) {
/*  81 */     if (this.zakoopt$chestTick != tick) {
/*  82 */       this.zakoopt$chestTick = tick;
/*  83 */       this.zakoopt$chestLight = null;
/*     */     } 
/*  85 */     this.zakoopt$chestOpen = fn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setLightFn(Object fn, long tick) {
/*  90 */     if (this.zakoopt$chestTick != tick) {
/*  91 */       this.zakoopt$chestTick = tick;
/*  92 */       this.zakoopt$chestOpen = null;
/*     */     } 
/*  94 */     this.zakoopt$chestLight = fn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setState(Object state, Object owner) {
/*  99 */     this.zakoopt$state = state;
/* 100 */     this.zakoopt$stateOwner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setCached(Object value, long tick) {
/* 105 */     this.zakoopt$cached = value;
/* 106 */     this.zakoopt$cachedTick = tick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setLight(int light, long tick) {
/* 111 */     this.zakoopt$light = light;
/* 112 */     this.zakoopt$lightTick = tick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setPairLight(int light, long tick) {
/* 117 */     this.zakoopt$pairLight = light;
/* 118 */     this.zakoopt$pairLightTick = tick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setValid(class_2680 state, boolean valid) {
/* 123 */     this.zakoopt$validState = state;
/* 124 */     this.zakoopt$valid = valid;
/*     */   }
/*     */ 
/*     */   
/*     */   public void zakoopt$setInDistance(boolean inDistance, long tick) {
/* 129 */     this.zakoopt$inDistance = inDistance;
/* 130 */     this.zakoopt$distanceTick = tick;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */