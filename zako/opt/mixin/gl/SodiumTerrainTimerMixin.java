/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.DefaultChunkRenderer;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.terrain.DefaultTerrainRenderPasses;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.terrain.TerrainRenderPass;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {DefaultChunkRenderer.class}, remap = false)
/*    */ public class SodiumTerrainTimerMixin
/*    */ {
/*    */   @Inject(require = 0, method = {"render"}, at = {@At("HEAD")})
/*    */   private void zakoopt$markTerrain(CallbackInfo ci, @Local(argsOnly = true) TerrainRenderPass pass) {
/* 20 */     GpuTimers.mark((pass == DefaultTerrainRenderPasses.SOLID) ? "terrain solid" : ((pass == DefaultTerrainRenderPasses.CUTOUT) ? "terrain cutout" : "terrain translucent"));
/*    */   }
/*    */   
/*    */   @Inject(require = 0, method = {"render"}, at = {@At("RETURN")})
/*    */   private void zakoopt$markAfterTerrain(CallbackInfo ci, @Local(argsOnly = true) TerrainRenderPass pass) {
/* 25 */     GpuTimers.mark((pass == DefaultTerrainRenderPasses.SOLID) ? "after solid" : ((pass == DefaultTerrainRenderPasses.CUTOUT) ? "entities, block entities" : "after translucent"));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\SodiumTerrainTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */