/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import net.minecraft.class_10859;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.TexBufferCache;
/*    */ 
/*    */ @Mixin({class_10859.class})
/*    */ public class GlBufferCloseMixin
/*    */ {
/*    */   @Inject(method = {"close"}, at = {@At("HEAD")})
/*    */   private void zakoopt$forget(CallbackInfo ci) {
/* 15 */     TexBufferCache.forget();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlBufferCloseMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */