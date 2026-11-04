/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import net.caffeinemc.mods.sodium.client.render.model.AbstractBlockRenderContext;
/*    */ import net.caffeinemc.mods.sodium.client.world.LevelSlice;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2680;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.block.LeafCulling;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ @Mixin(value = {AbstractBlockRenderContext.class}, remap = false)
/*    */ public abstract class SodiumLeafCullMixin {
/*    */   @Shadow
/*    */   protected LevelSlice slice;
/*    */   @Shadow
/*    */   protected class_2680 state;
/*    */   @Shadow
/*    */   protected class_2338 pos;
/*    */   
/*    */   @Inject(require = 0, method = {"shouldDrawSide"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$innerLeaves(class_2350 facing, CallbackInfoReturnable<Boolean> cir) {
/* 27 */     if (Features.LEAF_CULL.on && this.slice != null && LeafCulling.hidden(this.state, this.slice, this.pos, facing))
/* 28 */       cir.setReturnValue(Boolean.valueOf(false)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\SodiumLeafCullMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */