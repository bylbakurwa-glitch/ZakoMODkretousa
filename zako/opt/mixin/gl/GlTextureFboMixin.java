/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_10868;
/*    */ import net.minecraft.class_10874;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.gl.DepthFbos;
/*    */ 
/*    */ @Mixin({class_10868.class})
/*    */ public class GlTextureFboMixin {
/*    */   @Inject(method = {"method_68426"}, at = {@At("RETURN")})
/*    */   private void zakoopt$rememberDepth(class_10874 dsa, GpuTexture depth, CallbackInfoReturnable<Integer> cir) {
/* 16 */     if (depth instanceof class_10868) { class_10868 glDepth = (class_10868)depth;
/* 17 */       DepthFbos.remember((class_10868)this, glDepth.method_68427(), ((Integer)cir.getReturnValue()).intValue()); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlTextureFboMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */