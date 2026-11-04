/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_10219;
/*    */ import net.minecraft.class_1041;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.block.FarLeaves;
/*    */ import zako.opt.entity.AnimFreeze;
/*    */ import zako.opt.entity.SkinAtlas;
/*    */ import zako.opt.gl.FrameFence;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ import zako.opt.gl.ImmediateRing;
/*    */ 
/*    */ @Mixin({RenderSystem.class})
/*    */ public class RenderSystemMixin {
/*    */   @Inject(method = {"flipFrame"}, at = {@At("HEAD")})
/*    */   private static void zakoopt$endFrame(class_1041 window, class_10219 tracy, CallbackInfo ci) {
/* 22 */     GpuTimers.endFrame();
/* 23 */     FarLeaves.frame();
/* 24 */     ImmediateRing.endFrame();
/* 25 */     FrameFence.endFrame();
/* 26 */     SkinAtlas.endFrame();
/* 27 */     AnimFreeze.frame++;
/* 28 */     ZakoOptConfig.refresh();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderSystemMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */