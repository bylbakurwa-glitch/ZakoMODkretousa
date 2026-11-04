package zako.opt.mixin.gl;

import net.minecraft.class_276;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_761.class})
public interface LevelRendererScaleAccessor {
  @Accessor("field_53080")
  class_276 zakoopt$entityOutlineTarget();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\LevelRendererScaleAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */