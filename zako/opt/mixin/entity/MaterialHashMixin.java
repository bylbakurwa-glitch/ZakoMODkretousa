/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_4730;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_4730.class})
/*    */ public class MaterialHashMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_2960 field_21769;
/*    */   @Shadow
/*    */   @Final
/*    */   private class_2960 field_21770;
/*    */   @Unique
/*    */   private int zakoopt$hash;
/*    */   @Unique
/*    */   private boolean zakoopt$hashed;
/*    */   
/*    */   @Overwrite
/*    */   public int hashCode() {
/* 30 */     if (!this.zakoopt$hashed) {
/* 31 */       this.zakoopt$hash = 31 * (31 + Objects.hashCode(this.field_21769)) + Objects.hashCode(this.field_21770);
/* 32 */       this.zakoopt$hashed = true;
/*    */     } 
/* 34 */     return this.zakoopt$hash;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\MaterialHashMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */