/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import net.minecraft.class_11681;
/*    */ import net.minecraft.class_11788;
/*    */ import net.minecraft.class_4597;
/*    */ import net.minecraft.class_4618;
/*    */ import net.minecraft.class_776;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.block.MovingBlockCache;
/*    */ 
/*    */ 
/*    */ @Mixin({class_11681.class})
/*    */ public class BlockFeatureRendererMixin
/*    */ {
/*    */   @Inject(method = {"method_72998"}, at = {@At("HEAD")})
/*    */   private void zakoopt$cachedMovingBlocks(class_11788 collection, class_4597.class_4598 buffers, class_776 dispatcher, class_4618 outlines, CallbackInfo ci) {
/* 20 */     MovingBlockCache.render(collection.method_73519(), buffers, dispatcher);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\BlockFeatureRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */