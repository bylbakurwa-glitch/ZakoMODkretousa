package zako.opt.mixin.entity;

import java.util.function.Supplier;
import net.minecraft.class_10444;
import net.minecraft.class_804;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_10444.class_10446.class})
public interface ItemLayerAccessor {
  @Accessor("field_56968")
  Supplier<?> zakoopt$extents();
  
  @Accessor("field_56967")
  class_804 zakoopt$transform();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemLayerAccessor.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */