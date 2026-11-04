/*    */ package zako.opt.particle;
/*    */ import com.mojang.blaze3d.buffers.GpuBufferSlice;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import org.joml.Matrix4fc;
/*    */ import org.joml.Vector3fc;
/*    */ import org.joml.Vector4fc;
/*    */ 
/*    */ public final class ParticleTransforms {
/*    */   @Generated
/*    */   private ParticleTransforms() {
/* 11 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static GpuBufferSlice write() {
/* 14 */     return RenderSystem.getDynamicUniforms()
/* 15 */       .method_71106((Matrix4fc)RenderSystem.getModelViewMatrix(), (Vector4fc)new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), (Vector3fc)new Vector3f(), (Matrix4fc)new Matrix4f());
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\ParticleTransforms.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */