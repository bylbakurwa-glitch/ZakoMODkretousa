/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_4588;
/*    */ import net.minecraft.class_9799;
/*    */ import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.gl.RingBuilders;
/*    */ 
/*    */ @Mixin(value = {BatchableBufferSource.class}, remap = false)
/*    */ public class BatchableBufferSourceMixin
/*    */ {
/*    */   @Inject(method = {"method_73477", "method_73477"}, at = {@At("HEAD")})
/*    */   private void zakoopt$renderType(class_1921 type, CallbackInfoReturnable<class_4588> cir) {
/* 20 */     RingBuilders.current = type;
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"getNextByteBufferBuilder"}, at = {@At(value = "INVOKE", target = "Lnet/raphimc/immediatelyfast/feature/core/ByteBufferBuilderPool;borrowBufferBuilder()Lnet/minecraft/class_9799;")})
/*    */   private class_9799 zakoopt$ringBuilder(Operation<class_9799> original) {
/* 25 */     class_9799 builder = RingBuilders.create();
/* 26 */     return (builder != null) ? builder : (class_9799)original.call(new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\BatchableBufferSourceMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */