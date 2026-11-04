/*    */ package zako.opt.mixin.entity;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_1297;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.entity.AnimFreeze;
/*    */ import zako.opt.entity.AnimHolder;
/*    */ 
/*    */ @Mixin({class_1297.class})
/*    */ public class EntityAnimHolderMixin implements AnimHolder {
/*    */   @Generated
/*    */   public void zakoopt$anim(AnimFreeze.Holder zakoopt$anim) {
/* 13 */     this.zakoopt$anim = zakoopt$anim;
/*    */   } @Unique
/*    */   private AnimFreeze.Holder zakoopt$anim;
/*    */   @Generated
/*    */   public AnimFreeze.Holder zakoopt$anim() {
/* 18 */     return this.zakoopt$anim;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityAnimHolderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */