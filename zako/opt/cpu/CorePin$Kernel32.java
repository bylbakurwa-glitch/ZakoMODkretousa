package zako.opt.cpu;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

interface Kernel32 extends Library {
  Pointer GetCurrentThread();
  
  Pointer GetCurrentProcess();
  
  long SetThreadAffinityMask(Pointer paramPointer, long paramLong);
  
  int GetCurrentProcessorNumber();
  
  boolean GetProcessAffinityMask(Pointer paramPointer, LongByReference paramLongByReference1, LongByReference paramLongByReference2);
  
  boolean GetLogicalProcessorInformationEx(int paramInt, Pointer paramPointer, IntByReference paramIntByReference);
  
  boolean GetSystemCpuSetInformation(Pointer paramPointer1, int paramInt1, IntByReference paramIntByReference, Pointer paramPointer2, int paramInt2);
  
  boolean SetProcessInformation(Pointer paramPointer1, int paramInt1, Pointer paramPointer2, int paramInt2);
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\cpu\CorePin$Kernel32.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */