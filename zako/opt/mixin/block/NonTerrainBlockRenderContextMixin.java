/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.sugar.Local;
/*    */ import net.caffeinemc.mods.sodium.client.model.light.LightMode;
/*    */ import net.caffeinemc.mods.sodium.client.render.frapi.render.NonTerrainBlockRenderContext;
/*    */ import net.minecraft.class_1920;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {NonTerrainBlockRenderContext.class}, remap = false)
/*    */ public class NonTerrainBlockRenderContextMixin
/*    */ {
/*    */   @Unique
/*    */   private boolean zakoopt$flatLight;
/*    */   
/*    */   @Inject(method = {"renderModel"}, at = {@At("HEAD")})
/*    */   private void zakoopt$detectMoving(CallbackInfo ci, @Local(argsOnly = true) class_1920 level) {
/* 25 */     this.zakoopt$flatLight = (level instanceof net.minecraft.class_11791 && Features.MOVING_BLOCK_FLAT_LIGHT.on);
/*    */   }
/*    */   
/*    */   @ModifyVariable(method = {"shadeQuad"}, at = {@At("HEAD")}, argsOnly = true)
/*    */   private LightMode zakoopt$flat(LightMode mode) {
/* 30 */     return this.zakoopt$flatLight ? LightMode.FLAT : mode;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\NonTerrainBlockRenderContextMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */