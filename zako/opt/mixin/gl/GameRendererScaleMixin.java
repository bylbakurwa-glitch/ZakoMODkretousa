/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11284;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_4184;
/*    */ import net.minecraft.class_757;
/*    */ import net.minecraft.class_9779;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.gl.RenderScale;
/*    */ 
/*    */ @Mixin({class_757.class})
/*    */ public class GameRendererScaleMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private class_310 field_4015;
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_11284;method_71116(IIDJLnet/minecraft/class_9779;ILnet/minecraft/class_4184;Z)V")})
/*    */   private void zakoopt$scaleGlobals(class_11284 uniform, int width, int height, double glintAlpha, long gameTime, class_9779 deltaTracker, int menuBlurRadius, class_4184 camera, boolean useRgss, Operation<Void> original) {
/* 25 */     original.call(new Object[] { uniform, Integer.valueOf(width), Integer.valueOf(height), Double.valueOf(glintAlpha), Long.valueOf(gameTime), deltaTracker, Integer.valueOf(menuBlurRadius), camera, Boolean.valueOf(useRgss) });
/* 26 */     RenderScale.globals((low, w, h) -> original.call(new Object[] { low, Integer.valueOf(w), Integer.valueOf(h), Double.valueOf(glintAlpha), Long.valueOf(gameTime), deltaTracker, Integer.valueOf(menuBlurRadius), camera, Boolean.valueOf(useRgss) }));
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_3192"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_757;method_3188(Lnet/minecraft/class_9779;)V")})
/*    */   private void zakoopt$scale(class_757 renderer, class_9779 deltaTracker, Operation<Void> original) {
/* 32 */     RenderScale.begin(this.field_4015);
/*    */     try {
/* 34 */       original.call(new Object[] { renderer, deltaTracker });
/*    */     } finally {
/* 36 */       RenderScale.end(this.field_4015);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GameRendererScaleMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */