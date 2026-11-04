/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyReturnValue;
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import com.mojang.blaze3d.textures.GpuTextureView;
/*    */ import net.minecraft.class_276;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.hud.HudCache;
/*    */ 
/*    */ @Mixin({class_276.class})
/*    */ public class RenderTargetHudMixin {
/*    */   @ModifyReturnValue(method = {"method_30277"}, at = {@At("RETURN")})
/*    */   private GpuTexture zakoopt$hudColor(GpuTexture texture) {
/* 15 */     class_276 hud = HudCache.redirect((class_276)this);
/* 16 */     return (hud != null) ? hud.method_30277() : texture;
/*    */   }
/*    */   
/*    */   @ModifyReturnValue(method = {"method_71639"}, at = {@At("RETURN")})
/*    */   private GpuTextureView zakoopt$hudColorView(GpuTextureView view) {
/* 21 */     class_276 hud = HudCache.redirect((class_276)this);
/* 22 */     return (hud != null) ? hud.method_71639() : view;
/*    */   }
/*    */   
/*    */   @ModifyReturnValue(method = {"method_30278"}, at = {@At("RETURN")})
/*    */   private GpuTexture zakoopt$hudDepth(GpuTexture texture) {
/* 27 */     class_276 hud = HudCache.redirect((class_276)this);
/* 28 */     return (hud != null) ? hud.method_30278() : texture;
/*    */   }
/*    */   
/*    */   @ModifyReturnValue(method = {"method_71640"}, at = {@At("RETURN")})
/*    */   private GpuTextureView zakoopt$hudDepthView(GpuTextureView view) {
/* 33 */     class_276 hud = HudCache.redirect((class_276)this);
/* 34 */     return (hud != null) ? hud.method_71640() : view;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\RenderTargetHudMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */