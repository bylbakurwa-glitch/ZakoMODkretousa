/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.mojang.blaze3d.buffers.GpuBuffer;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import java.nio.ByteBuffer;
/*    */ import net.minecraft.class_1921;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import zako.opt.gl.ImmediateRing;
/*    */ 
/*    */ @Mixin({class_1921.class})
/*    */ public class RenderTypeDrawMixin
/*    */ {
/*    */   @Unique
/*    */   private static int zakoopt$baseVertex;
/*    */   
/*    */   @WrapOperation(method = {"method_60895"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexFormat;uploadImmediateVertexBuffer(Ljava/nio/ByteBuffer;)Lcom/mojang/blaze3d/buffers/GpuBuffer;")})
/*    */   private GpuBuffer zakoopt$ringUpload(VertexFormat format, ByteBuffer data, Operation<GpuBuffer> original) {
/* 23 */     int base = ImmediateRing.upload(format, data);
/* 24 */     if (base < 0) {
/* 25 */       zakoopt$baseVertex = 0;
/* 26 */       return (GpuBuffer)original.call(new Object[] { format, data });
/*    */     } 
/* 28 */     zakoopt$baseVertex = base;
/* 29 */     return ImmediateRing.buffer(format);
/*    */   }
/*    */   
/*    */   @ModifyArg(method = {"method_60895"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderPass;drawIndexed(IIII)V")}, index = 0)
/*    */   private int zakoopt$baseVertex(int baseVertex) {
/* 34 */     return baseVertex + zakoopt$baseVertex;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderTypeDrawMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */