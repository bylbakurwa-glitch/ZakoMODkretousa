package zako.opt.mixin.particle;

import java.util.Map;
import net.minecraft.class_11944;
import net.minecraft.class_3940;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_11944.class})
public interface QuadParticleRenderStateAccessor {
  @Accessor("field_62652")
  Map<class_3940.class_11941, Object> zakoopt$particles();
  
  @Accessor("field_62946")
  int zakoopt$particleCount();
  
  @Accessor("field_62946")
  void zakoopt$setParticleCount(int paramInt);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\QuadParticleRenderStateAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */