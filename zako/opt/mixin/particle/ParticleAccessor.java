package zako.opt.mixin.particle;

import net.minecraft.class_703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_703.class})
public interface ParticleAccessor {
  @Accessor("field_3874")
  double zakoopt$x();
  
  @Accessor("field_3854")
  double zakoopt$y();
  
  @Accessor("field_3871")
  double zakoopt$z();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\ParticleAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */