package zako.opt.gl;

import com.mojang.blaze3d.buffers.GpuBuffer;
import java.nio.ByteBuffer;

final class Ring {
  GpuBuffer buffer;
  
  ByteBuffer coherent;
  
  int segmentSize;
  
  int cursor;
  
  int needed;
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\ImmediateRing$Ring.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */