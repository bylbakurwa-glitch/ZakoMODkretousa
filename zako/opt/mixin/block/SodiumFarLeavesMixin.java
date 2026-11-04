/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
/*    */ import net.minecraft.class_11515;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2680;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyArg;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.block.FarLeaves;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {BlockRenderer.class}, remap = false, priority = 1100)
/*    */ public class SodiumFarLeavesMixin
/*    */ {
/*    */   @Unique
/*    */   private class_2680 zakoopt$state;
/*    */   @Unique
/*    */   private class_2338 zakoopt$pos;
/*    */   @Unique
/* 26 */   private final FarLeaves.Section zakoopt$section = new FarLeaves.Section();
/*    */ 
/*    */   
/*    */   @Inject(require = 0, method = {"renderModel"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$block(CallbackInfo ci, @Local(argsOnly = true) class_2680 state, @Local(argsOnly = true, ordinal = 0) class_2338 pos) {
/* 31 */     this.zakoopt$state = state;
/* 32 */     this.zakoopt$pos = pos;
/* 33 */     if (FarLeaves.hidePlant(this.zakoopt$section, state, pos)) {
/* 34 */       ci.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @ModifyArg(require = 0, method = {"processQuad"}, at = {@At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/terrain/material/DefaultMaterials;forChunkLayer(Lnet/minecraft/class_11515;)Lnet/caffeinemc/mods/sodium/client/render/chunk/terrain/material/Material;")})
/*    */   private class_11515 zakoopt$farLeaves(class_11515 layer) {
/* 42 */     return FarLeaves.type(this.zakoopt$section, this.zakoopt$state, this.zakoopt$pos, layer);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\SodiumFarLeavesMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */