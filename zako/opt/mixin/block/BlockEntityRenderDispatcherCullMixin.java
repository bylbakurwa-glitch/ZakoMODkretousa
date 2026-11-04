/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.minecraft.class_11954;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2586;
/*    */ import net.minecraft.class_824;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.block.BlockEntityDistance;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {class_824.class}, priority = 500)
/*    */ public class BlockEntityRenderDispatcherCullMixin
/*    */ {
/*    */   @Shadow
/*    */   private class_243 field_63069;
/*    */   
/*    */   @Inject(method = {"method_74348"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$earlyCull(CallbackInfoReturnable<class_11954> cir, @Local(argsOnly = true) class_2586 blockEntity) {
/* 24 */     if (BlockEntityDistance.culledEarly((class_824)this, blockEntity, this.field_63069))
/* 25 */       cir.setReturnValue(null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockEntityRenderDispatcherCullMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */