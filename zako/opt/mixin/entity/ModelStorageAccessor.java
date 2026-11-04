package zako.opt.mixin.entity;

import java.util.List;
import java.util.Map;
import net.minecraft.class_11661;
import net.minecraft.class_11683;
import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_11683.class_12048.class})
public interface ModelStorageAccessor {
  @Accessor("field_62982")
  Map<class_1921, List<class_11661.class_11670<?>>> zakoopt$opaque();
  
  @Accessor("field_62983")
  List<class_11661.class_11744<?>> zakoopt$translucent();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ModelStorageAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */