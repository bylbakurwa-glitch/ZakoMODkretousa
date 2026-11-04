/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import net.caffeinemc.mods.sodium.client.world.LevelSlice;
/*    */ import net.minecraft.class_1922;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_2680;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.block.LeafCulling;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ @Mixin(targets = {"net/caffeinemc/mods/sodium/client/render/chunk/compile/pipeline/BlockOcclusionCache"}, remap = false)
/*    */ public class SodiumLeafCullOldMixin
/*    */ {
/*    */   @Inject(require = 0, method = {"shouldDrawSide"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$innerLeaves(class_2680 state, class_1922 view, class_2338 pos, class_2350 facing, CallbackInfoReturnable<Boolean> cir) {
/* 20 */     if (Features.LEAF_CULL.on && view instanceof LevelSlice) { LevelSlice slice = (LevelSlice)view; if (LeafCulling.hidden(state, slice, pos, facing))
/* 21 */         cir.setReturnValue(Boolean.valueOf(false));  }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\SodiumLeafCullOldMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */