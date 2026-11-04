/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
/*    */ import net.minecraft.class_4588;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(targets = {"net/caffeinemc/mods/sodium/client/render/vertex/VertexConsumerUtils"}, remap = false)
/*    */ public class VertexConsumerUtilsMixin
/*    */ {
/*    */   @Unique
/*    */   private static volatile Object zakoopt$lastWriter;
/*    */   
/*    */   @WrapMethod(method = {"convertOrLog"})
/*    */   private static VertexBufferWriter zakoopt$cachedConvert(class_4588 consumer, Operation<VertexBufferWriter> original) {
/* 22 */     if (consumer == zakoopt$lastWriter && Features.WRITER_CACHE.on) {
/* 23 */       return (VertexBufferWriter)consumer;
/*    */     }
/* 25 */     VertexBufferWriter writer = (VertexBufferWriter)original.call(new Object[] { consumer });
/* 26 */     if (writer == consumer) {
/* 27 */       zakoopt$lastWriter = consumer;
/*    */     }
/* 29 */     return writer;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\VertexConsumerUtilsMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */