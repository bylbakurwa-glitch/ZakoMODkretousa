package zako.opt.mixin.hud;

import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_329.class})
public interface GuiInvoker {
  @Invoker("method_55798")
  void zakoopt$renderCameraOverlays(class_332 paramclass_332, class_9779 paramclass_9779);
  
  @Invoker("method_1736")
  void zakoopt$renderCrosshair(class_332 paramclass_332, class_9779 paramclass_9779);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\GuiInvoker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */