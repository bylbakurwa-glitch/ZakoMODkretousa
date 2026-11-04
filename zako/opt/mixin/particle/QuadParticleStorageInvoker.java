package zako.opt.mixin.particle;

import net.minecraft.class_11944;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = {"net/minecraft/class_11944$class_11946"})
public interface QuadParticleStorageInvoker {
  @Invoker("method_74328")
  void zakoopt$forEach(class_11944.class_11945 paramclass_11945);
  
  @Invoker("method_74329")
  void zakoopt$grow();
  
  @Accessor("field_62653")
  int zakoopt$capacity();
  
  @Accessor("field_62654")
  float[] zakoopt$floats();
  
  @Accessor("field_62655")
  int[] zakoopt$ints();
  
  @Accessor("field_62656")
  int zakoopt$count();
  
  @Accessor("field_62656")
  void zakoopt$setCount(int paramInt);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\QuadParticleStorageInvoker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */