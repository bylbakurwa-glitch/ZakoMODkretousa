/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.buffers.GpuBufferSlice;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_11280;
/*    */ import net.minecraft.class_11282;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fc;
/*    */ import org.joml.Vector3f;
/*    */ import org.joml.Vector3fc;
/*    */ import org.joml.Vector4f;
/*    */ import org.joml.Vector4fc;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_11282.class})
/*    */ public class DynamicTransformMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_11280<class_11282.class_11283> field_60095;
/*    */   @Unique
/* 29 */   private final Matrix4f[] zakoopt$modelView = new Matrix4f[] { new Matrix4f(), new Matrix4f() };
/*    */   @Unique
/* 31 */   private final Vector4f[] zakoopt$color = new Vector4f[] { new Vector4f(), new Vector4f() };
/*    */   @Unique
/* 33 */   private final Vector3f[] zakoopt$offset = new Vector3f[] { new Vector3f(), new Vector3f() };
/*    */   @Unique
/* 35 */   private final Matrix4f[] zakoopt$texture = new Matrix4f[] { new Matrix4f(), new Matrix4f() };
/*    */   @Unique
/* 37 */   private final class_11282.class_11283[] zakoopt$transform = new class_11282.class_11283[2];
/*    */ 
/*    */   
/*    */   @Overwrite
/*    */   public GpuBufferSlice method_71106(Matrix4fc modelView, Vector4fc color, Vector3fc offset, Matrix4fc texture) {
/* 42 */     if (!Features.FEWER_ALLOCATIONS.on || !RenderSystem.isOnRenderThread()) {
/* 43 */       return this.field_60095.method_71102((class_11280.class_11281)new class_11282.class_11283((Matrix4fc)new Matrix4f(modelView), (Vector4fc)new Vector4f(color), (Vector3fc)new Vector3f(offset), (Matrix4fc)new Matrix4f(texture)));
/*    */     }
/* 45 */     int i = (this.zakoopt$transform[0] != null && this.zakoopt$transform[0] == ((DynamicUniformStorageAccessor)this.field_60095).zakoopt$lastUniform()) ? 1 : 0;
/* 46 */     if (this.zakoopt$transform[i] == null) {
/* 47 */       this.zakoopt$transform[i] = new class_11282.class_11283((Matrix4fc)this.zakoopt$modelView[i], (Vector4fc)this.zakoopt$color[i], (Vector3fc)this.zakoopt$offset[i], (Matrix4fc)this.zakoopt$texture[i]);
/*    */     }
/* 49 */     this.zakoopt$modelView[i].set(modelView);
/* 50 */     this.zakoopt$color[i].set(color);
/* 51 */     this.zakoopt$offset[i].set(offset);
/* 52 */     this.zakoopt$texture[i].set(texture);
/* 53 */     return this.field_60095.method_71102((class_11280.class_11281)this.zakoopt$transform[i]);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\DynamicTransformMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */