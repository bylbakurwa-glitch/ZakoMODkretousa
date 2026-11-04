/*     */ package zako.opt.cpu;
/*     */ 
/*     */ import com.sun.jna.Library;
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Platform;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.LongByReference;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import lombok.Generated;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public final class CorePin {
/*     */   @Generated
/*  20 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); private static final int WINDOWS_CACHE_RELATION = 2; @Generated
/*     */   private CorePin() {
/*  22 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static final int POWER_THROTTLING = 4;
/*  25 */   private static final ThreadLocal<int[]> APPLIED = (ThreadLocal)ThreadLocal.withInitial(() -> new int[] { 0 });
/*     */   private static Kernel32 kernel;
/*     */   private static Libc libc;
/*     */   private static long best;
/*     */   private static long all;
/*     */   private static String why;
/*     */   private static volatile boolean on;
/*  32 */   private static volatile int generation = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  62 */       if (Platform.isWindows()) {
/*  63 */         kernel = (Kernel32)Native.load("kernel32", Kernel32.class);
/*  64 */         LongByReference process = new LongByReference(), system = new LongByReference();
/*  65 */         if (!kernel.GetProcessAffinityMask(kernel.GetCurrentProcess(), process, system)) {
/*  66 */           throw new IllegalStateException("GetProcessAffinityMask failed, error " + Native.getLastError());
/*     */         }
/*  68 */         all = process.getValue();
/*  69 */         findWindows();
/*  70 */       } else if (Platform.isLinux() || Platform.isAndroid()) {
/*  71 */         libc = (Libc)Native.load("c", Libc.class);
/*  72 */         long[] mask = new long[16];
/*  73 */         if (libc.sched_getaffinity(0, mask.length * 8L, mask) != 0) {
/*  74 */           throw new IllegalStateException("sched_getaffinity failed, errno " + Native.getLastError());
/*     */         }
/*  76 */         all = mask[0];
/*  77 */         findLinux();
/*     */       } 
/*     */       
/*  80 */       String forced = System.getProperty("zakoopt.corepin.mask");
/*  81 */       if (forced != null) {
/*  82 */         best = Long.decode(forced).longValue();
/*  83 */         why = "set by hand";
/*     */       } 
/*  85 */       best &= all;
/*  86 */       if (best == 0L || best == all) {
/*  87 */         best = 0L;
/*     */       }
/*  89 */     } catch (Throwable t) {
/*  90 */       log.warn("Can't read the CPU layout, best-core pinning is off", t);
/*  91 */       best = 0L;
/*     */     } 
/*  93 */     log.info("Best cores: {}", (best == 0L) ? "none, all cores alike" : (mask(best) + " (" + mask(best) + ")"));
/*     */   }
/*     */   
/*     */   public static boolean available() {
/*  97 */     return (best != 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void set(boolean pin) {
/* 102 */     if (pin != on) {
/* 103 */       on = pin;
/* 104 */       generation++;
/* 105 */       if (kernel != null) {
/* 106 */         powerThrottling(!pin);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void check() {
/* 112 */     int[] applied = APPLIED.get();
/* 113 */     int g = generation;
/* 114 */     if (applied[0] == g || best == 0L) {
/*     */       return;
/*     */     }
/* 117 */     applied[0] = g;
/* 118 */     long mask = on ? best : all;
/* 119 */     if (kernel != null) {
/* 120 */       kernel.SetThreadAffinityMask(kernel.GetCurrentThread(), mask);
/* 121 */     } else if (libc != null) {
/* 122 */       libc.sched_setaffinity(0, 8L, new long[] { mask });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String status() {
/* 127 */     if (best == 0L) {
/* 128 */       return "n/a";
/*     */     }
/* 130 */     if (!on) {
/* 131 */       return "all (best " + mask(best) + ", " + why + ")";
/*     */     }
/* 133 */     int cpu = (kernel != null) ? kernel.GetCurrentProcessorNumber() : libc.sched_getcpu();
/* 134 */     return "best " + mask(best) + " (" + why + "), render on " + cpu;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void findWindows() {
/* 139 */     IntByReference length = new IntByReference();
/* 140 */     kernel.GetLogicalProcessorInformationEx(2, null, length);
/* 141 */     Memory buffer = new Memory(length.getValue());
/* 142 */     if (kernel.GetLogicalProcessorInformationEx(2, (Pointer)buffer, length)) {
/*     */       
/* 144 */       TreeMap<Long, Long> l3 = new TreeMap<>();
/* 145 */       for (long l = 0L; l < length.getValue(); l += buffer.getInt(l + 4L)) {
/* 146 */         if (buffer.getByte(l + 8L) == 3 && buffer.getShort(l + 48L) == 0) {
/* 147 */           l3.merge(Long.valueOf(buffer.getInt(l + 12L) & 0xFFFFFFFFL), Long.valueOf(buffer.getLong(l + 40L)), (a, b) -> Long.valueOf(a.longValue() | b.longValue()));
/*     */         }
/*     */       } 
/* 150 */       if (l3.size() > 1) {
/* 151 */         Map.Entry<Long, Long> biggest = l3.lastEntry();
/* 152 */         best = ((Long)biggest.getValue()).longValue();
/* 153 */         why = "L3 " + (((Long)biggest.getKey()).longValue() >> 20L) + " MB";
/*     */         return;
/*     */       } 
/*     */     } 
/* 157 */     IntByReference returned = new IntByReference();
/* 158 */     kernel.GetSystemCpuSetInformation(null, 0, returned, kernel.GetCurrentProcess(), 0);
/* 159 */     if (returned.getValue() == 0) {
/*     */       return;
/*     */     }
/* 162 */     Memory sets = new Memory(returned.getValue());
/* 163 */     if (!kernel.GetSystemCpuSetInformation((Pointer)sets, returned.getValue(), returned, kernel.GetCurrentProcess(), 0)) {
/*     */       return;
/*     */     }
/*     */     
/* 167 */     TreeMap<Integer, Long> classes = new TreeMap<>(); long at;
/* 168 */     for (at = 0L; at < returned.getValue(); at += sets.getInt(at)) {
/* 169 */       if (sets.getInt(at + 4L) == 0 && sets.getShort(at + 12L) == 0) {
/* 170 */         classes.merge(Integer.valueOf(sets.getByte(at + 18L) & 0xFF), Long.valueOf(1L << sets.getByte(at + 14L)), (a, b) -> Long.valueOf(a.longValue() | b.longValue()));
/*     */       }
/*     */     } 
/* 173 */     if (classes.size() > 1) {
/* 174 */       best = ((Long)classes.lastEntry().getValue()).longValue();
/* 175 */       why = "performance cores";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void findLinux() {
/* 181 */     TreeMap<Long, Long> l3 = new TreeMap<>(), clock = new TreeMap<>();
/* 182 */     for (int cpu = 0; cpu < 64; cpu++) {
/* 183 */       Path dir = Path.of("/sys/devices/system/cpu/cpu" + cpu, new String[0]);
/* 184 */       if (Files.isDirectory(dir, new java.nio.file.LinkOption[0])) {
/*     */ 
/*     */         
/* 187 */         long size = number(dir.resolve("cache/index3/size")), freq = number(dir.resolve("cpufreq/cpuinfo_max_freq"));
/* 188 */         if (size > 0L) {
/* 189 */           l3.merge(Long.valueOf(size), Long.valueOf(1L << cpu), (a, b) -> Long.valueOf(a.longValue() | b.longValue()));
/*     */         }
/* 191 */         if (freq > 0L)
/* 192 */           clock.merge(Long.valueOf(freq), Long.valueOf(1L << cpu), (a, b) -> Long.valueOf(a.longValue() | b.longValue())); 
/*     */       } 
/*     */     } 
/* 195 */     if (l3.size() > 1) {
/* 196 */       best = ((Long)l3.lastEntry().getValue()).longValue();
/* 197 */       why = "L3 " + (((Long)l3.lastKey()).longValue() >> 10L) + " MB";
/* 198 */     } else if (clock.size() > 1) {
/*     */       
/* 200 */       best = all & (((Long)clock.firstEntry().getValue()).longValue() ^ 0xFFFFFFFFFFFFFFFFL);
/* 201 */       why = "big cores";
/*     */     } 
/*     */   }
/*     */   
/*     */   private static long number(Path file) {
/*     */     try {
/* 207 */       String s = Files.readString(file).trim().toUpperCase();
/* 208 */       return s.endsWith("K") ? Long.parseLong(s.substring(0, s.length() - 1)) : Long.parseLong(s);
/* 209 */     } catch (Exception e) {
/* 210 */       return 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void powerThrottling(boolean allowed) {
/* 217 */     Memory state = new Memory(12L);
/* 218 */     state.setInt(0L, 1);
/* 219 */     state.setInt(4L, allowed ? 0 : 1);
/* 220 */     state.setInt(8L, 0);
/* 221 */     kernel.SetProcessInformation(kernel.GetCurrentProcess(), 4, (Pointer)state, 12);
/*     */   }
/*     */   
/*     */   private static String mask(long mask) {
/* 225 */     StringBuilder s = new StringBuilder();
/* 226 */     for (int i = 0; i < 64; i++) {
/* 227 */       if ((mask >> i & 0x1L) != 0L) {
/* 228 */         int j = i;
/* 229 */         while (j < 63 && (mask >> j + 1 & 0x1L) != 0L) {
/* 230 */           j++;
/*     */         }
/* 232 */         s.append(s.isEmpty() ? "" : ",").append((i == j) ? String.valueOf(i) : ("" + i + "-" + i));
/* 233 */         i = j;
/*     */       } 
/*     */     } 
/* 236 */     return s.toString();
/*     */   }
/*     */   
/*     */   static interface Kernel32 extends Library {
/*     */     Pointer GetCurrentThread();
/*     */     
/*     */     Pointer GetCurrentProcess();
/*     */     
/*     */     long SetThreadAffinityMask(Pointer param1Pointer, long param1Long);
/*     */     
/*     */     int GetCurrentProcessorNumber();
/*     */     
/*     */     boolean GetProcessAffinityMask(Pointer param1Pointer, LongByReference param1LongByReference1, LongByReference param1LongByReference2);
/*     */     
/*     */     boolean GetLogicalProcessorInformationEx(int param1Int, Pointer param1Pointer, IntByReference param1IntByReference);
/*     */     
/*     */     boolean GetSystemCpuSetInformation(Pointer param1Pointer1, int param1Int1, IntByReference param1IntByReference, Pointer param1Pointer2, int param1Int2);
/*     */     
/*     */     boolean SetProcessInformation(Pointer param1Pointer1, int param1Int1, Pointer param1Pointer2, int param1Int2);
/*     */   }
/*     */   
/*     */   static interface Libc extends Library {
/*     */     int sched_setaffinity(int param1Int, long param1Long, long[] param1ArrayOflong);
/*     */     
/*     */     int sched_getaffinity(int param1Int, long param1Long, long[] param1ArrayOflong);
/*     */     
/*     */     int sched_getcpu();
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\cpu\CorePin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */