/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.ChunkRenderMatrices;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.DefaultChunkRenderer;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.lists.ChunkRenderListIterable;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.region.RenderRegion;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
/*    */ import net.caffeinemc.mods.sodium.client.render.viewport.CameraTransform;
/*    */ import net.caffeinemc.mods.sodium.client.util.iterator.ByteIterator;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.gl.OcclusionCull;
/*    */ 
/*    */ 
/*    */ @Mixin(value = {DefaultChunkRenderer.class}, remap = false)
/*    */ public class SodiumOcclusionCullMixin
/*    */ {
/*    */   @Inject(require = 0, method = {"render"}, at = {@At("HEAD")})
/*    */   private void zakoopt$begin(CallbackInfo ci, @Local(argsOnly = true) TerrainRenderPass pass, @Local(argsOnly = true) CameraTransform camera, @Local(argsOnly = true) ChunkRenderMatrices matrices) {
/* 25 */     if (Features.OCCLUSION_CULL.on) {
/* 26 */       OcclusionCull.beginPass(pass, camera, matrices);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(require = 0, method = {"render"}, at = {@At("RETURN")})
/*    */   private void zakoopt$test(CallbackInfo ci, @Local(argsOnly = true) ChunkRenderMatrices matrices, @Local(argsOnly = true) ChunkRenderListIterable lists, @Local(argsOnly = true) TerrainRenderPass pass, @Local(argsOnly = true) CameraTransform camera) {
/* 33 */     if (Features.OCCLUSION_CULL.on) {
/* 34 */       OcclusionCull.endPass(matrices, lists, pass, camera);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @ModifyExpressionValue(require = 0, method = {"fillCommandBuffer"}, at = {@At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/lists/ChunkRenderList;sectionsWithGeometryIterator(Z)Lnet/caffeinemc/mods/sodium/client/util/iterator/ByteIterator;")})
/*    */   private static ByteIterator zakoopt$skipHidden(ByteIterator sections, @Local(argsOnly = true) RenderRegion region) {
/* 41 */     return Features.OCCLUSION_CULL.on ? OcclusionCull.filter(sections, region) : sections;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\SodiumOcclusionCullMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */