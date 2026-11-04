/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import net.minecraft.class_9799;
/*    */ import net.raphimc.immediatelyfast.feature.core.ByteBufferBuilderPool;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.RingBuilders;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {ByteBufferBuilderPool.class}, remap = false)
/*    */ public class ByteBufferBuilderPoolMixin
/*    */ {
/*    */   @Inject(method = {"returnBufferBuilderSafe"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void zakoopt$release(class_9799 builder, CallbackInfo ci) {
/* 17 */     if (RingBuilders.release(builder))
/* 18 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\ByteBufferBuilderPoolMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */