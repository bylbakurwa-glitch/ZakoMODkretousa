package zako.opt.mixin.gl;

import net.minecraft.class_11280;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_11280.class})
public interface DynamicUniformStorageAccessor {
  @Accessor("field_60091")
  class_11280.class_11281 zakoopt$lastUniform();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\DynamicUniformStorageAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */