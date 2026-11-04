package zako.opt.mixin.gl;

import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1921.class})
public interface RenderTypeAccessor {
  @Accessor("field_64011")
  String zakoopt$name();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\RenderTypeAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */