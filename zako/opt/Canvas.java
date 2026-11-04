package zako.opt;

import net.minecraft.class_2561;
import net.minecraft.class_5481;

public interface Canvas {
  void fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  void text(class_2561 paramclass_2561, int paramInt1, int paramInt2, int paramInt3);
  
  void text(class_5481 paramclass_5481, int paramInt1, int paramInt2, int paramInt3);
  
  void text(class_2561 paramclass_2561, int paramInt1, int paramInt2, int paramInt3, float paramFloat);
  
  void clip(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void unclip();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Canvas.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */