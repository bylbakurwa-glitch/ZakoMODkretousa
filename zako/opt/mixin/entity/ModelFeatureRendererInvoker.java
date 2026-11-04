package zako.opt.mixin.entity;

import net.minecraft.class_11661;
import net.minecraft.class_11683;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_11683.class})
public interface ModelFeatureRendererInvoker {
  @Invoker("method_73000")
  <S> void zakoopt$renderModel(class_11661.class_11670<S> paramclass_11670, class_1921 paramclass_1921, class_4588 paramclass_4588, class_4618 paramclass_4618, class_4597.class_4598 paramclass_4598);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ModelFeatureRendererInvoker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */