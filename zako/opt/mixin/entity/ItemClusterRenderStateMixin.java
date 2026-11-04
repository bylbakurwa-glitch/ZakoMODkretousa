/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*    */ import net.minecraft.class_10428;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ @Mixin({class_10428.class})
/*    */ public class ItemClusterRenderStateMixin
/*    */ {
/*    */   @ModifyExpressionValue(method = {"method_65581"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_10428;method_65580(I)I")})
/*    */   private int zakoopt$singleCopy(int copies) {
/* 14 */     return Features.ITEM_SINGLE_COPY.on ? 1 : copies;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemClusterRenderStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */