/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Pseudo;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.LunarMultiDraw;
/*    */ 
/*    */ @Pseudo
/*    */ @Mixin(targets = {"net/caffeinemc/mods/sodium/client/gl/device/GLRenderDevice$ImmediateDrawCommandList"})
/*    */ public class LunarSodiumMultiDrawMixin
/*    */ {
/*    */   @Inject(method = {"<init>"}, at = {@At("RETURN")}, require = 0)
/*    */   private void zakoopt$keepMultiDraw(CallbackInfo ci) {
/* 16 */     LunarMultiDraw.keep(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\LunarSodiumMultiDrawMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */