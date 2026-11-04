/*    */ package zako.opt.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.pipeline.RenderPipeline;
/*    */ import com.mojang.blaze3d.platform.DepthTestFunction;
/*    */ import com.mojang.blaze3d.systems.RenderPass;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.textures.FilterMode;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import java.util.OptionalInt;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_10789;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_6367;
/*    */ 
/*    */ public final class ScaleGpu {
/*    */   @Generated
/*    */   private ScaleGpu() {
/* 21 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 22 */   } private static final RenderPipeline UPSCALE = RenderPipeline.builder(new RenderPipeline.Snippet[0])
/* 23 */     .withLocation(class_2960.method_60655("zakoopt", "pipeline/render_scale"))
/* 24 */     .withVertexShader(class_2960.method_60655("zakoopt", "core/render_scale"))
/* 25 */     .withFragmentShader(class_2960.method_60655("zakoopt", "core/render_scale"))
/* 26 */     .withUniform("Globals", class_10789.field_60031)
/* 27 */     .withSampler("InSampler")
/* 28 */     .withDepthWrite(false)
/* 29 */     .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
/* 30 */     .withVertexFormat(class_290.field_60033, VertexFormat.class_5596.field_27379)
/* 31 */     .build();
/*    */ 
/*    */   
/*    */   public static boolean ready() {
/* 35 */     return RenderSystem.getDevice().precompilePipeline(UPSCALE).isValid();
/*    */   }
/*    */   
/*    */   public static class_6367 createTarget(int w, int h) {
/* 39 */     return new class_6367("zakoopt render scale", w, h, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void fitAlongside(class_310 mc, int w, int h) {}
/*    */ 
/*    */   
/*    */   public static void upscale(class_6367 low, class_276 main) {
/* 48 */     RenderPass pass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "zakoopt render scale", main.method_71639(), OptionalInt.empty()); try {
/* 49 */       pass.setPipeline(UPSCALE);
/* 50 */       RenderSystem.bindDefaultUniforms(pass);
/* 51 */       pass.bindTexture("InSampler", low.method_71639(), RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
/* 52 */       pass.draw(0, 3);
/* 53 */       if (pass != null) pass.close(); 
/*    */     } catch (Throwable throwable) {
/*    */       if (pass != null)
/*    */         try {
/*    */           pass.close();
/*    */         } catch (Throwable throwable1) {
/*    */           throwable.addSuppressed(throwable1);
/*    */         }  
/*    */       throw throwable;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\ScaleGpu.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */