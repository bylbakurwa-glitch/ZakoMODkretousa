/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_824;
/*    */ import net.minecraft.class_827;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.BlockEntityDistance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_824.class})
/*    */ public class BlockEntityRenderDispatcherCullWrapMixin
/*    */ {
/*    */   @Shadow
/*    */   private class_243 field_63069;
/*    */   
/*    */   @WrapOperation(method = {"method_74348"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_824;method_3550(Lnet/minecraft/class_2586;)Lnet/minecraft/class_827;")})
/*    */   private class_827<?, ?> zakoopt$earlyCullRenderer(class_824 self, class_2586 blockEntity, Operation<class_827<?, ?>> original) {
/* 25 */     class_827<?, ?> renderer = (class_827<?, ?>)original.call(new Object[] { self, blockEntity });
/* 26 */     return BlockEntityDistance.culled(renderer, blockEntity, this.field_63069) ? null : renderer;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityRenderDispatcherCullWrapMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */