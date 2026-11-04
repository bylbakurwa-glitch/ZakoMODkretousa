/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_4597;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_4597.class_4598.class})
/*    */ public class BufferSourceMixin
/*    */ {
/*    */   @ModifyExpressionValue(method = {"method_60893(Lnet/minecraft/class_1921;Lnet/minecraft/class_287;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_1921;method_60894()Z")})
/*    */   private boolean zakoopt$skipEntitySort(boolean sort, class_1921 renderType) {
/* 16 */     if (!sort) {
/* 17 */       return false;
/*    */     }
/* 19 */     String name = ((RenderTypeAccessor)renderType).zakoopt$name();
/* 20 */     return ((!Features.NO_ENTITY_SORT.on || !"entity_translucent".equals(name)) && (!Features.NO_ITEM_SORT.on || !"item_entity_translucent_cull".equals(name)));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\BufferSourceMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */