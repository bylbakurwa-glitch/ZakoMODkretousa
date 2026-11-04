/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_3879;
/*    */ import net.minecraft.class_630;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_3879.class})
/*    */ public class ModelResetPoseMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private List<class_630> field_54013;
/*    */   
/*    */   @Overwrite
/*    */   public final void method_63514() {
/* 22 */     List<class_630> parts = this.field_54013;
/* 23 */     for (int i = 0, n = parts.size(); i < n; i++)
/* 24 */       ((class_630)parts.get(i)).method_41923(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ModelResetPoseMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */