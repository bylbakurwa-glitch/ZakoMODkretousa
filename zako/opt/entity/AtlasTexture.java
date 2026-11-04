/*    */ package zako.opt.entity;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.GpuDevice;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.textures.FilterMode;
/*    */ import com.mojang.blaze3d.textures.TextureFormat;
/*    */ import net.minecraft.class_1044;
/*    */ 
/*    */ public class AtlasTexture
/*    */   extends class_1044
/*    */ {
/*    */   public AtlasTexture(String label, int size) {
/* 13 */     GpuDevice device = RenderSystem.getDevice();
/* 14 */     this.field_56974 = device.createTexture(() -> label, 13, TextureFormat.RGBA8, size, size, 1, 1);
/* 15 */     this.field_60597 = device.createTextureView(this.field_56974);
/* 16 */     this.field_63613 = RenderSystem.getSamplerCache().method_75297(FilterMode.NEAREST);
/* 17 */     device.createCommandEncoder().clearColorTexture(this.field_56974, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\AtlasTexture.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */