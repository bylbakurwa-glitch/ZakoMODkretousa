package zako.opt.mixin.gl;

import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.class_276;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_276.class})
public interface RenderTargetAccessor {
  @Accessor("field_1475")
  GpuTexture zakoopt$colorTexture();
  
  @Accessor("field_1475")
  void zakoopt$colorTexture(GpuTexture paramGpuTexture);
  
  @Accessor("field_60567")
  GpuTextureView zakoopt$colorTextureView();
  
  @Accessor("field_60567")
  void zakoopt$colorTextureView(GpuTextureView paramGpuTextureView);
  
  @Accessor("field_56739")
  GpuTexture zakoopt$depthTexture();
  
  @Accessor("field_56739")
  void zakoopt$depthTexture(GpuTexture paramGpuTexture);
  
  @Accessor("field_60568")
  GpuTextureView zakoopt$depthTextureView();
  
  @Accessor("field_60568")
  void zakoopt$depthTextureView(GpuTextureView paramGpuTextureView);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderTargetAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */