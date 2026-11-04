/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_2561;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.entity.CachedName;
/*    */ import zako.opt.entity.CachedRenderer;
/*    */ import zako.opt.entity.PooledState;
/*    */ 
/*    */ @Mixin({class_1297.class})
/*    */ public class EntityNameMixin implements CachedName, CachedRenderer, PooledState {
/*    */   @Unique
/*    */   private class_2561 zakoopt$name;
/*    */   
/*    */   @Generated
/* 18 */   public class_2561 zakoopt$name() { return this.zakoopt$name; } @Unique
/* 19 */   private long zakoopt$nameTick = Long.MIN_VALUE; @Unique private Object zakoopt$renderer; @Unique private Object zakoopt$rendererMap; @Generated
/* 20 */   public long zakoopt$nameTick() { return this.zakoopt$nameTick; } @Unique private Object zakoopt$state; @Unique
/*    */   private Object zakoopt$stateOwner; @Generated
/* 22 */   public Object zakoopt$renderer() { return this.zakoopt$renderer; }
/*    */   @Generated
/* 24 */   public Object zakoopt$rendererMap() { return this.zakoopt$rendererMap; }
/*    */   @Generated
/* 26 */   public Object zakoopt$state() { return this.zakoopt$state; } @Generated
/*    */   public Object zakoopt$stateOwner() {
/* 28 */     return this.zakoopt$stateOwner;
/*    */   }
/*    */   
/*    */   public void zakoopt$setName(class_2561 name, long tick) {
/* 32 */     this.zakoopt$name = name;
/* 33 */     this.zakoopt$nameTick = tick;
/*    */   }
/*    */ 
/*    */   
/*    */   public void zakoopt$setRenderer(Object renderer, Object map) {
/* 38 */     this.zakoopt$renderer = renderer;
/* 39 */     this.zakoopt$rendererMap = map;
/*    */   }
/*    */ 
/*    */   
/*    */   public void zakoopt$setState(Object state, Object owner) {
/* 44 */     this.zakoopt$state = state;
/* 45 */     this.zakoopt$stateOwner = owner;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityNameMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */