package zako.opt.mixin.entity;

import java.util.List;
import java.util.Map;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_630.class})
public interface ModelPartAccessor {
  @Accessor("field_3663")
  List<class_630.class_628> zakoopt$cubes();
  
  @Accessor("field_3661")
  Map<String, class_630> zakoopt$children();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ModelPartAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */