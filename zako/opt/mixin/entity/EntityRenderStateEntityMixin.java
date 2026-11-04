/*    */ package zako.opt.mixin.entity;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_1297;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.entity.StateEntity;
/*    */ 
/*    */ @Mixin({class_10017.class})
/*    */ public class EntityRenderStateEntityMixin implements StateEntity {
/*    */   @Generated
/*    */   public void zakoopt$entity(class_1297 zakoopt$entity) {
/* 13 */     this.zakoopt$entity = zakoopt$entity;
/*    */   } @Unique
/*    */   private class_1297 zakoopt$entity;
/*    */   @Generated
/*    */   public class_1297 zakoopt$entity() {
/* 18 */     return this.zakoopt$entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityRenderStateEntityMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */