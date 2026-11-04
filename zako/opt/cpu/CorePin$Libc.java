package zako.opt.cpu;

import com.sun.jna.Library;

interface Libc extends Library {
  int sched_setaffinity(int paramInt, long paramLong, long[] paramArrayOflong);
  
  int sched_getaffinity(int paramInt, long paramLong, long[] paramArrayOflong);
  
  int sched_getcpu();
}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\cpu\CorePin$Libc.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */