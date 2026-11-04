/*    */ package zako.opt.hud;
/*    */ 
/*    */ import com.mojang.blaze3d.pipeline.BlendFunction;
/*    */ import com.mojang.blaze3d.pipeline.RenderPipeline;
/*    */ import com.mojang.blaze3d.platform.DepthTestFunction;
/*    */ import com.mojang.blaze3d.systems.RenderPass;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.textures.FilterMode;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import java.util.OptionalDouble;
/*    */ import java.util.OptionalInt;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_276;
/*    */ import net.minecraft.class_290;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_6367;
/*    */ 
/*    */ public final class HudGpu {
/*    */   @Generated
/*    */   private HudGpu() {
/* 22 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/* 24 */   private static final RenderPipeline COMPOSITE = RenderPipeline.builder(new RenderPipeline.Snippet[0])
/* 25 */     .withLocation(class_2960.method_60655("zakoopt", "pipeline/hud_composite"))
/* 26 */     .withVertexShader("core/screenquad")
/* 27 */     .withFragmentShader("core/blit_screen")
/* 28 */     .withSampler("InSampler")
/* 29 */     .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
/* 30 */     .withDepthWrite(false)
/* 31 */     .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
/* 32 */     .withColorWrite(true, false)
/* 33 */     .withVertexFormat(class_290.field_60033, VertexFormat.class_5596.field_27379)
/* 34 */     .build();
/*    */   
/*    */   public static boolean noScreen(class_310 mc) {
/* 37 */     return (mc.field_1755 == null && mc.method_18506() == null);
/*    */   }
/*    */   
/*    */   public static class_276 mainTarget(class_310 mc) {
/* 41 */     return mc.method_1522();
/*    */   }
/*    */   
/*    */   public static class_6367 createTarget(int w, int h) {
/* 45 */     return new class_6367("zakoopt hud", w, h, true);
/*    */   }
/*    */   
/*    */   public static void clear(class_6367 target) {
/* 49 */     RenderSystem.getDevice().createCommandEncoder().clearColorAndDepthTextures(target.method_30277(), 0, target.method_30278(), 1.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void composite(class_6367 hud, class_276 main) {
/* 55 */     RenderPass pass = RenderSystem.getDevice().createCommandEncoder().createRenderPass(() -> "zakoopt hud composite", main.method_71639(), OptionalInt.empty(), 
/* 56 */         main.field_1478 ? main.method_71640() : null, OptionalDouble.empty()); try {
/* 57 */       pass.setPipeline(COMPOSITE);
/* 58 */       RenderSystem.bindDefaultUniforms(pass);
/* 59 */       pass.bindTexture("InSampler", hud.method_71639(), RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
/* 60 */       pass.draw(0, 3);
/* 61 */       if (pass != null) pass.close(); 
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


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\hud\HudGpu.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */