/*     */ package zako.opt.block;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Consumer;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_11659;
/*     */ import net.minecraft.class_11785;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_4587;
/*     */ 
/*     */ public final class SpawnerReplay {
/*     */   public static final double DISTANCE = 6.0D;
/*     */   
/*     */   @Generated
/*     */   private SpawnerReplay() {
/*  22 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*  24 */   private static final Map<Object, Recording> CACHE = new IdentityHashMap<>();
/*  25 */   private static long cachedTick = Long.MIN_VALUE;
/*     */   
/*  27 */   private static final class_4587 SCRATCH = new class_4587();
/*     */   private static final class Call extends Record { private final Method order; private final Object[] orderArgs; private final Method method; private final Object[] args; private final class_4587.class_4665 pose;
/*  29 */     private Call(Method order, Object[] orderArgs, Method method, Object[] args, class_4587.class_4665 pose) { this.order = order; this.orderArgs = orderArgs; this.method = method; this.args = args; this.pose = pose; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/block/SpawnerReplay$Call;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  29 */       //   0	7	0	this	Lzako/opt/block/SpawnerReplay$Call; } public Method order() { return this.order; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/block/SpawnerReplay$Call;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/block/SpawnerReplay$Call; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/block/SpawnerReplay$Call;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/block/SpawnerReplay$Call;
/*  29 */       //   0	8	1	o	Ljava/lang/Object; } public Object[] orderArgs() { return this.orderArgs; } public Method method() { return this.method; } public Object[] args() { return this.args; } public class_4587.class_4665 pose() { return this.pose; }
/*     */      }
/*     */   
/*     */   private static final class Recording {
/*  33 */     final List<SpawnerReplay.Call> calls = new ArrayList<>(); final double x; final double y;
/*     */     final double z;
/*     */     boolean replayable = true;
/*     */     
/*     */     Recording(class_243 camera) {
/*  38 */       this.x = camera.field_1352;
/*  39 */       this.y = camera.field_1351;
/*  40 */       this.z = camera.field_1350;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean replay(Object key, long tick, class_243 camera, class_11659 collector) {
/*  46 */     if (tick != cachedTick) {
/*  47 */       CACHE.clear();
/*  48 */       cachedTick = tick;
/*     */     } 
/*  50 */     Recording recording = CACHE.get(key);
/*  51 */     if (recording == null || !recording.replayable) {
/*  52 */       return false;
/*     */     }
/*  54 */     replay(recording, camera, collector);
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   public static double distanceSq(class_2338 pos, class_243 camera) {
/*  59 */     double dx = pos.method_10263() + 0.5D - camera.field_1352, dy = pos.method_10264() + 0.5D - camera.field_1351, dz = pos.method_10260() + 0.5D - camera.field_1350;
/*  60 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */   
/*     */   public static void submit(Object key, long tick, class_243 camera, class_11659 collector, Consumer<class_11659> body) {
/*  64 */     if (tick != cachedTick) {
/*  65 */       CACHE.clear();
/*  66 */       cachedTick = tick;
/*     */     } 
/*  68 */     Recording recording = CACHE.get(key);
/*  69 */     if (recording == null) {
/*  70 */       recording = new Recording(camera);
/*  71 */       CACHE.put(key, recording);
/*  72 */       body.accept((class_11659)recorder(class_11659.class, collector, recording, null, -1));
/*  73 */     } else if (!recording.replayable) {
/*  74 */       body.accept(collector);
/*     */     } else {
/*  76 */       replay(recording, camera, collector);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object recorder(Class<?> type, Object target, Recording recording, Method order, int orderIndex) {
/*  81 */     return Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, (proxy, method, args) -> {
/*     */           if (method.getDeclaringClass() == Object.class) {
/*     */             return method.getName().equals("equals") ? Boolean.valueOf((proxy == args[0])) : method.invoke(target, args);
/*     */           }
/*     */           Object result = invoke(method, target, args);
/*     */           if (method.getReturnType() == class_11785.class && order == null) {
/*     */             return recorder(class_11785.class, result, recording, method, ((Integer)args[0]).intValue());
/*     */           }
/*     */           if (method.getReturnType() == void.class) {
/*     */             record(recording, order, orderIndex, method, args);
/*     */           } else {
/*     */             recording.replayable = false;
/*     */           } 
/*     */           return result;
/*     */         });
/*     */   }
/*     */   
/*     */   private static void record(Recording recording, Method order, int orderIndex, Method method, Object[] args) {
/*  99 */     Object[] stored = (args == null) ? null : (Object[])args.clone();
/* 100 */     class_4587.class_4665 pose = null;
/* 101 */     for (int i = 0; stored != null && i < stored.length; i++) {
/* 102 */       Object object = stored[i]; if (object instanceof class_4587) { class_4587 stack = (class_4587)object;
/* 103 */         if (pose != null) {
/* 104 */           recording.replayable = false;
/*     */         }
/* 106 */         pose = stack.method_23760().method_56822();
/* 107 */         stored[i] = SCRATCH; }
/*     */     
/*     */     } 
/* 110 */     (new Object[1])[0] = Integer.valueOf(orderIndex); recording.calls.add(new Call(order, (order == null) ? null : new Object[1], method, stored, pose));
/*     */   }
/*     */   
/*     */   private static void replay(Recording recording, class_243 camera, class_11659 collector) {
/* 114 */     float dx = (float)(recording.x - camera.field_1352), dy = (float)(recording.y - camera.field_1351), dz = (float)(recording.z - camera.field_1350);
/* 115 */     for (Call call : recording.calls) {
/* 116 */       if (call.pose != null) {
/* 117 */         SCRATCH.method_23760().method_66521(call.pose);
/* 118 */         SCRATCH.method_23760().method_23761().translateLocal(dx, dy, dz);
/*     */       } 
/* 120 */       Object target = (call.order == null) ? collector : invoke(call.order, collector, call.orderArgs);
/* 121 */       invoke(call.method, target, call.args);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object invoke(Method method, Object target, Object[] args) {
/*     */     try {
/* 127 */       return method.invoke(target, args);
/* 128 */     } catch (InvocationTargetException e) {
/* 129 */       Throwable throwable = e.getCause(); RuntimeException r = (RuntimeException)throwable; throw (throwable instanceof RuntimeException) ? r : new RuntimeException(e.getCause());
/* 130 */     } catch (IllegalAccessException e) {
/* 131 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\SpawnerReplay.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */