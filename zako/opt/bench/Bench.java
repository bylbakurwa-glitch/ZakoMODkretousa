/*     */ package zako.opt.bench;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Supplier;
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_1132;
/*     */ import net.minecraft.class_2168;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_32;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5285;
/*     */ import net.minecraft.class_7145;
/*     */ import net.minecraft.class_7225;
/*     */ import zako.opt.Blocked;
/*     */ import zako.opt.ConfirmPanel;
/*     */ import zako.opt.ZakoOptConfig;
/*     */ import zako.opt.ZakoOptions;
/*     */ import zako.opt.feature.Feature;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.update.UpdateUi;
/*     */ 
/*     */ public final class Bench {
/*     */   @Generated
/*  32 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); private static final String WORLD = "zakoopt-benchmark"; private static final int SETTLE_MS = 500; private static final int SEGMENT_MS = 1500; private static final double MIN_GAIN = 0.03D; @Generated
/*     */   private Bench() {
/*  34 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private static final long[] FRAME_NS = new long[65536];
/*     */   
/*  42 */   private static final long[] RECENT = new long[65536];
/*     */   private static volatile int frames;
/*     */   private static long lastFrame;
/*     */   private static volatile boolean running;
/*     */   private static volatile boolean joined;
/*     */   private static volatile boolean cancelled;
/*  48 */   private static volatile Status status = new Status((class_2561)class_2561.method_43473(), (class_2561)class_2561.method_43473(), 0);
/*     */   public static final class Status extends Record { private final class_2561 stage; private final class_2561 note; private final int percent;
/*  50 */     public Status(class_2561 stage, class_2561 note, int percent) { this.stage = stage; this.note = note; this.percent = percent; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/bench/Bench$Status;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #50	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  50 */       //   0	7	0	this	Lzako/opt/bench/Bench$Status; } public class_2561 stage() { return this.stage; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Bench$Status;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #50	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Bench$Status; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/bench/Bench$Status;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #50	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/bench/Bench$Status;
/*  50 */       //   0	8	1	o	Ljava/lang/Object; } public class_2561 note() { return this.note; } public int percent() { return this.percent; }
/*     */      }
/*     */   private static final class Aborted extends RuntimeException {}
/*     */   private static final class Test extends Record { private final String key;
/*     */     private final String view;
/*     */     
/*  56 */     private Test(String key, String view) { this.key = key; this.view = view; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/bench/Bench$Test;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Bench$Test; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Bench$Test;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Bench$Test; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/bench/Bench$Test;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #56	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/bench/Bench$Test;
/*  56 */       //   0	8	1	o	Ljava/lang/Object; } public String key() { return this.key; } public String view() { return this.view; }
/*     */      }
/*     */   private static final class Result extends Record { private final int level; private final double modGain; private final Map<String, Double> off; private final Map<String, Boolean> chosen; private final Map<String, Object> picks;
/*  59 */     private Result(int level, double modGain, Map<String, Double> off, Map<String, Boolean> chosen, Map<String, Object> picks) { this.level = level; this.modGain = modGain; this.off = off; this.chosen = chosen; this.picks = picks; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/bench/Bench$Result;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #59	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Bench$Result; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Bench$Result;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #59	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Bench$Result; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/bench/Bench$Result;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #59	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/bench/Bench$Result;
/*  59 */       //   0	8	1	o	Ljava/lang/Object; } public int level() { return this.level; } public double modGain() { return this.modGain; } public Map<String, Double> off() { return this.off; } public Map<String, Boolean> chosen() { return this.chosen; } public Map<String, Object> picks() { return this.picks; }
/*     */      }
/*     */   
/*     */   public static boolean running() {
/*  63 */     return running;
/*     */   }
/*     */   
/*     */   public static Status status() {
/*  67 */     return status;
/*     */   }
/*     */   
/*     */   public static void cancel() {
/*  71 */     cancelled = true;
/*     */   }
/*     */   
/*     */   public static void frame(long now) {
/*  75 */     if (running)
/*     */     {
/*  77 */       class_310.method_1551().method_61964().method_61939();
/*     */     }
/*     */     
/*  80 */     if ((running || ProgressScreen.open()) && lastFrame != 0L) {
/*  81 */       FRAME_NS[frames & FRAME_NS.length - 1] = now - lastFrame;
/*  82 */       frames++;
/*  83 */       MemoryGraph.frame(now);
/*     */     } 
/*  85 */     lastFrame = now;
/*     */   }
/*     */   
/*     */   public static void resetFrames() {
/*  89 */     frames = 0;
/*     */   }
/*     */   
/*     */   public static void confirm(class_437 parent) {
/*  93 */     UpdateUi.setScreen((class_437)new ConfirmPanel(yes -> {
/*     */             if (yes) {
/*     */               start(parent);
/*     */             } else {
/*     */               UpdateUi.setScreen(parent);
/*     */             } 
/*  99 */           }(class_2561)class_2561.method_43471("zakoopt.bench.title"), (class_2561)class_2561.method_43471("zakoopt.bench.intro"), 
/* 100 */           (class_2561)class_2561.method_43471("zakoopt.bench.start"), (class_2561)class_2561.method_43471("gui.cancel")));
/*     */   }
/*     */   
/*     */   public static void welcome(class_437 parent) {
/* 104 */     UpdateUi.setScreen((class_437)new ConfirmPanel(yes -> {
/*     */             if (yes) {
/*     */               start(parent);
/*     */             } else {
/*     */               UpdateUi.setScreen(parent);
/*     */             } 
/* 110 */           }(class_2561)class_2561.method_43471("zakoopt.welcome.title"), (class_2561)class_2561.method_43471("zakoopt.welcome"), 
/* 111 */           (class_2561)class_2561.method_43471("zakoopt.welcome.start"), (class_2561)class_2561.method_43471("zakoopt.welcome.later")));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void compare(class_437 parent) {
/* 116 */     start(parent, true);
/*     */   }
/*     */   
/*     */   private static void start(class_437 parent) {
/* 120 */     start(parent, false);
/*     */   }
/*     */   
/*     */   private static void start(class_437 parent, boolean compareOnly) {
/* 124 */     running = true;
/* 125 */     joined = false;
/* 126 */     cancelled = false;
/* 127 */     status = new Status((class_2561)class_2561.method_43473(), (class_2561)class_2561.method_43473(), 0);
/* 128 */     MemoryGraph.reset();
/* 129 */     class_310 mc = class_310.method_1551();
/* 130 */     boolean pause = mc.field_1690.field_1837;
/* 131 */     mc.field_1690.field_1837 = false;
/* 132 */     Map<String, Object> saved = Features.snapshot();
/* 133 */     boolean wasEnabled = ZakoOptConfig.enabled;
/* 134 */     Thread thread = new Thread(() -> {
/*     */           Result result = null; try {
/*     */             Result shown;
/*     */             result = run(mc, parent, saved, compareOnly);
/* 138 */           } catch (Aborted e) {
/*     */             Result shown; log.info("Benchmark cancelled");
/* 140 */           } catch (Exception e) {
/*     */             Result shown;
/*     */ 
/*     */ 
/*     */             
/*     */             log.error("Benchmark failed", e);
/*     */           } finally {
/*     */             Result shown = result;
/*     */ 
/*     */ 
/*     */             
/*     */             main(mc, ());
/*     */ 
/*     */             
/*     */             running = false;
/*     */           } 
/*     */         }"ZakoOpt benchmark");
/*     */ 
/*     */     
/* 159 */     thread.setDaemon(true);
/* 160 */     thread.start();
/*     */   }
/*     */   
/*     */   private static Result run(class_310 mc, class_437 parent, Map<String, Object> saved, boolean compareOnly) {
/* 164 */     main(mc, () -> {
/*     */           delete(mc);
/*     */           
/*     */           mc.method_41735().method_41895("zakoopt-benchmark", BenchWorld.settings("zakoopt-benchmark"), new class_5285(7L, false, false), (), parent);
/*     */           return null;
/*     */         });
/* 170 */     long deadline = System.currentTimeMillis() + 120000L;
/* 171 */     while (mc.field_1724 == null || mc.method_1576() == null) {
/* 172 */       if (System.currentTimeMillis() > deadline) {
/* 173 */         throw new IllegalStateException("benchmark world did not load");
/*     */       }
/* 175 */       sleep(200L);
/*     */     } 
/* 177 */     joined = true;
/* 178 */     main(mc, () -> {
/*     */           Scene.terrainAt(mc.field_1724.method_31477(), mc.field_1724.method_31478(), mc.field_1724.method_31479());
/*     */           
/*     */           UpdateUi.setScreen((class_437)new ProgressScreen());
/*     */           
/*     */           return null;
/*     */         });
/* 185 */     List<Test> tests = Features.all().stream().filter(f -> (f.data().bench() != BenchView.NONE && toggle(f.key()) != null)).sorted(Comparator.comparing(f -> f.data().bench())).map(f -> new Test(f.key(), f.data().bench().key())).toList();
/*     */     
/* 187 */     if (!compareOnly) {
/* 188 */       main(mc, () -> {
/*     */             Features.restore(Features.defaults());
/*     */             
/*     */             ZakoOptConfig.enabled = true;
/*     */             
/*     */             Features.IMMEDIATE_RING.force = true;
/*     */             return null;
/*     */           });
/*     */     }
/* 197 */     Scene.View o = Scene.OVERVIEW;
/* 198 */     server(mc, tp(o));
/* 199 */     sleep(3000L);
/* 200 */     List<String> setup = new ArrayList<>(Scene.base());
/* 201 */     setup.addAll(tp(o));
/* 202 */     server(mc, setup);
/* 203 */     sleep(2000L);
/*     */     
/* 205 */     settle(mc);
/* 206 */     double base = fps(mc, 3000);
/* 207 */     double target = Math.max(Math.min(base / 2.0D, 144.0D), 30.0D);
/* 208 */     log.info("Benchmark: empty scene {} fps, target {}", Long.valueOf(Math.round(base)), Long.valueOf(Math.round(target)));
/*     */     
/* 210 */     double t0 = 1000.0D / base, targetMs = 1000.0D / target;
/* 211 */     int level = 0, next = 2, step = 0;
/* 212 */     double fps = base;
/*     */     while (true) {
/* 214 */       step++;
/* 215 */       progress(mc, (class_2561)class_2561.method_43469("zakoopt.bench.ramp", new Object[] { Integer.valueOf(step) }), (class_2561)class_2561.method_43469("zakoopt.bench.target", new Object[] { Long.valueOf(Math.round(target)) }), step * 10 / (step + 1));
/* 216 */       List<String> add = new ArrayList<>();
/* 217 */       for (int lv = level; lv < next; lv++) {
/* 218 */         add.addAll(Scene.level(lv));
/*     */       }
/* 220 */       server(mc, add);
/* 221 */       level = next;
/* 222 */       sleep(1000L);
/* 223 */       fps = fps(mc, 2000);
/* 224 */       log.info("Benchmark: level {} {} fps", Integer.valueOf(level), Long.valueOf(Math.round(fps)));
/* 225 */       double t = 1000.0D / fps;
/* 226 */       if (t >= targetMs * 0.85D || level == 60) {
/*     */         break;
/*     */       }
/* 229 */       double k = Math.max((t - t0) / level, 1.0E-6D);
/* 230 */       next = Math.min(60, Math.min(2 * level, Math.max(level + 1, (int)Math.round(0.8D * (targetMs - t0) / k))));
/*     */     } 
/* 232 */     progress(mc, status.stage(), status.percent());
/* 233 */     if (compareOnly) {
/* 234 */       server(mc, tp(o));
/* 235 */       compare(mc);
/* 236 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 241 */     Map<String, Double> off = new LinkedHashMap<>();
/* 242 */     Map<String, Object> picks = new LinkedHashMap<>();
/* 243 */     String view = null;
/* 244 */     for (int i = 0; i < tests.size(); i++) {
/* 245 */       Test test = tests.get(i);
/* 246 */       ZakoOptions.Toggle toggle = toggle(test.key());
/* 247 */       if (!test.key().equals("ring_zero_copy") || Features.IMMEDIATE_RING.value) {
/*     */ 
/*     */         
/* 250 */         if (!test.view().equals(view)) {
/* 251 */           view = test.view();
/* 252 */           Scene.View v = Scene.views().stream().filter(x -> x.key().equals(test.view())).findFirst().orElseThrow();
/* 253 */           server(mc, tp(v));
/* 254 */           sleep(2000L);
/* 255 */           server(mc, tp(v));
/* 256 */           if (v.key().equals("terrain")) {
/* 257 */             settle(mc);
/*     */           }
/*     */           
/* 260 */           switch (view) { case "crowd":
/* 261 */               if (Scene.MANNEQUIN);
/*     */             case "items": 
/*     */             case "blocks": 
/* 264 */             default: break; }  List<String> pick = List.of();
/*     */           
/* 266 */           switch (view) { case "crowd": case "items": default: break; }  Scene.View at = 
/*     */ 
/*     */             
/* 269 */             Scene.SPAWNERS;
/*     */           
/* 271 */           if (pick(mc, saved, pick, at, "zakoopt.bench." + at.key(), picks)) {
/* 272 */             server(mc, tp(v));
/* 273 */             sleep(2000L);
/*     */           } 
/*     */         } 
/* 276 */         class_5250 class_5250 = class_2561.method_43471("zakoopt.option." + test.key());
/* 277 */         int percent = 10 + i * 85 / tests.size();
/*     */         
/* 279 */         boolean byDefault = ((Boolean)toggle.get().get()).booleanValue();
/* 280 */         double ratio = abba(mc, test, toggle, (class_2561)class_5250, i, tests.size(), percent);
/* 281 */         if (byDefault ? (ratio >= 1.03D) : (ratio <= 0.970873786407767D)) {
/* 282 */           double again = abba(mc, test, toggle, (class_2561)class_5250, i, tests.size(), percent);
/* 283 */           ratio = byDefault ? Math.min(ratio, again) : Math.max(ratio, again);
/*     */         } 
/* 285 */         boolean keep = byDefault ? ((ratio < 1.03D)) : ((ratio <= 0.970873786407767D));
/* 286 */         log.info("Benchmark: {} -> {}", test.key(), keep ? "on" : "off");
/* 287 */         if (byDefault && !keep) {
/* 288 */           off.put(test.key(), Double.valueOf(ratio - 1.0D));
/*     */         }
/* 290 */         main(mc, () -> {
/*     */               toggle.set().accept(Boolean.valueOf(keep));
/*     */               
/*     */               return null;
/*     */             });
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     ZakoOptions.Toggle scale = toggle("render_scale");
/* 299 */     if (option("far_leaves") != null || scale != null) {
/* 300 */       Scene.View forest = Scene.FOREST;
/* 301 */       server(mc, Scene.forestLoaded(true));
/* 302 */       server(mc, tp(forest));
/* 303 */       waitForestLoaded(mc);
/* 304 */       server(mc, Scene.forest());
/* 305 */       sleep(3000L);
/*     */ 
/*     */       
/* 308 */       if (scale != null) { ZakoOptions.Option option = option("render_scale_percent"); if (option instanceof ZakoOptions.Distance) { ZakoOptions.Distance percent = (ZakoOptions.Distance)option;
/* 309 */           main(mc, () -> {
/*     */                 percent.set().accept(Integer.valueOf(50));
/*     */                 return null;
/*     */               });
/* 313 */           double ratio = abba(mc, new Test(scale.key(), forest.key()), scale, (class_2561)class_2561.method_43471("zakoopt.option.render_scale"), 0, 1, 96);
/* 314 */           log.info("Benchmark: render scale at 50% {}%", Long.valueOf(Math.round((1.0D / ratio - 1.0D) * 100.0D)));
/* 315 */           main(mc, () -> {
/*     */                 set((ZakoOptions.Option)scale, saved.get(scale.key()));
/*     */                 set((ZakoOptions.Option)percent, saved.get(percent.key()));
/*     */                 return null;
/*     */               });
/* 320 */           if (ratio <= 0.970873786407767D)
/* 321 */             pick(mc, saved, List.of("render_scale", "render_scale_percent"), forest, "zakoopt.bench.render_scale", picks);  }
/*     */          }
/*     */       
/* 324 */       if (option("far_leaves") != null) {
/* 325 */         pick(mc, saved, List.of("far_leaves", "far_leaves_distance"), forest, "zakoopt.bench.forest", picks);
/*     */       }
/* 327 */       server(mc, Scene.forestLoaded(false));
/*     */     } 
/*     */     
/* 330 */     server(mc, tp(o));
/* 331 */     sleep(2000L);
/* 332 */     server(mc, tp(o));
/* 333 */     progress(mc, (class_2561)class_2561.method_43471("zakoopt.bench.final"), 97);
/* 334 */     Map<String, Boolean> chosen = new LinkedHashMap<>();
/* 335 */     tests.forEach(t -> chosen.put(t.key(), toggle(t.key()).get().get()));
/*     */     
/* 337 */     double with1 = master(mc, true), without1 = master(mc, false), without2 = master(mc, false), with2 = master(mc, true);
/* 338 */     double modGain = (with1 + with2) / (without1 + without2) - 1.0D;
/* 339 */     log.info("Benchmark: level {}, mod {}%, off {}", new Object[] { Integer.valueOf(level), Long.valueOf(Math.round(modGain * 100.0D)), off });
/*     */     
/* 341 */     compare(mc);
/* 342 */     return new Result(level, modGain, off, chosen, picks);
/*     */   }
/*     */   
/*     */   private static void compare(class_310 mc) {
/* 346 */     CompletableFuture<Void> compared = new CompletableFuture<>();
/* 347 */     main(mc, () -> {
/*     */           UpdateUi.setScreen((class_437)new CompareScreen(compared));
/*     */           return null;
/*     */         });
/* 351 */     waitWhile(() -> !compared.isDone());
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean pick(class_310 mc, Map<String, Object> saved, List<String> keys, Scene.View at, String hint, Map<String, Object> picks) {
/* 356 */     List<ZakoOptions.Option> options = keys.stream().map(Bench::option).filter(o -> (o != null && Blocked.reason(o.key()) == null)).toList();
/* 357 */     if (options.isEmpty()) {
/* 358 */       return false;
/*     */     }
/*     */     
/* 361 */     server(mc, tp(at));
/* 362 */     sleep(2000L);
/* 363 */     server(mc, tp(at));
/* 364 */     if (at == Scene.LOD) {
/* 365 */       server(mc, Scene.ruler(class_2561.method_43469("zakoopt.blocks", new Object[] { "%d" }).getString()));
/*     */     }
/* 367 */     if (at == Scene.SPAWNERS) {
/* 368 */       server(mc, Scene.spawnerLabels(class_2561.method_43469("zakoopt.blocks", new Object[] { "%d" }).getString()));
/*     */     }
/* 370 */     if (at == Scene.SPAWNERS) {
/* 371 */       growSpawners(mc, (ZakoOptions.Toggle)options.getFirst());
/*     */     }
/* 373 */     if (at == Scene.ITEMS_CLOSE) {
/* 374 */       growPile(mc, (ZakoOptions.Toggle)options.getFirst());
/*     */     }
/* 376 */     CompletableFuture<Void> done = new CompletableFuture<>();
/* 377 */     main(mc, () -> {
/*     */           options.forEach(());
/*     */           UpdateUi.setScreen((class_437)new PickScreen(options, hint, done));
/*     */           return null;
/*     */         });
/* 382 */     waitWhile(() -> !done.isDone());
/* 383 */     List<String> clear = new ArrayList<>(List.of("tp @e[tag=zakoopt_ruler] -7 -300 0", "kill @e[tag=zakoopt_ruler]", "kill @e[tag=zakoopt_pile]"));
/* 384 */     if (at == Scene.SPAWNERS) {
/* 385 */       clear.addAll(Scene.clearSpawnerField());
/*     */     }
/* 387 */     server(mc, clear);
/* 388 */     main(mc, () -> {
/*     */           UpdateUi.setScreen((class_437)new ProgressScreen());
/*     */           return null;
/*     */         });
/* 392 */     options.forEach(o -> picks.put(o.key(), value(o)));
/* 393 */     log.info("Benchmark: picked {}", picks);
/* 394 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void growPile(class_310 mc, ZakoOptions.Toggle singleCopy) {
/* 400 */     main(mc, () -> {
/*     */           singleCopy.set().accept(Boolean.valueOf(false));
/*     */           return null;
/*     */         });
/* 404 */     sleep(1000L);
/* 405 */     double start = fps(mc, 1000);
/* 406 */     for (int batch = 0; batch < 10; batch++) {
/* 407 */       server(mc, Scene.pile(batch));
/* 408 */       sleep(500L);
/* 409 */       double now = fps(mc, 1000);
/* 410 */       log.info("Benchmark: item pile {} stacks {} fps", Integer.valueOf((batch + 1) * 200), Long.valueOf(Math.round(now)));
/* 411 */       if (now < start * 0.7D) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void growSpawners(class_310 mc, ZakoOptions.Toggle cull) {
/* 420 */     main(mc, () -> {
/*     */           cull.set().accept(Boolean.valueOf(false));
/*     */           return null;
/*     */         });
/* 424 */     sleep(1000L);
/* 425 */     double start = fps(mc, 1000);
/* 426 */     for (int strip = 0; strip < 15; strip++) {
/* 427 */       server(mc, Scene.spawnerStrip(strip));
/* 428 */       sleep(500L);
/* 429 */       double now = fps(mc, 1000);
/* 430 */       log.info("Benchmark: spawner field {} spawners {} fps", Integer.valueOf((strip + 1) * 100), Long.valueOf(Math.round(now)));
/* 431 */       if (now < start * 0.7D) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object value(ZakoOptions.Option option) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: dup
/*     */     //   2: invokestatic requireNonNull : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   5: pop
/*     */     //   6: astore_1
/*     */     //   7: iconst_0
/*     */     //   8: istore_2
/*     */     //   9: aload_1
/*     */     //   10: iload_2
/*     */     //   11: <illegal opcode> typeSwitch : (Ljava/lang/Object;I)I
/*     */     //   16: lookupswitch default -> 44, 0 -> 54, 1 -> 74
/*     */     //   44: new java/lang/MatchException
/*     */     //   47: dup
/*     */     //   48: aconst_null
/*     */     //   49: aconst_null
/*     */     //   50: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   53: athrow
/*     */     //   54: aload_1
/*     */     //   55: checkcast zako/opt/ZakoOptions$Toggle
/*     */     //   58: astore_3
/*     */     //   59: aload_3
/*     */     //   60: invokevirtual get : ()Ljava/util/function/Supplier;
/*     */     //   63: invokeinterface get : ()Ljava/lang/Object;
/*     */     //   68: checkcast java/lang/Boolean
/*     */     //   71: goto -> 93
/*     */     //   74: aload_1
/*     */     //   75: checkcast zako/opt/ZakoOptions$Distance
/*     */     //   78: astore #4
/*     */     //   80: aload #4
/*     */     //   82: invokevirtual get : ()Ljava/util/function/Supplier;
/*     */     //   85: invokeinterface get : ()Ljava/lang/Object;
/*     */     //   90: checkcast java/lang/Integer
/*     */     //   93: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #438	-> 0
/*     */     //   #439	-> 54
/*     */     //   #440	-> 74
/*     */     //   #438	-> 93
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   59	15	3	t	Lzako/opt/ZakoOptions$Toggle;
/*     */     //   80	13	4	d	Lzako/opt/ZakoOptions$Distance;
/*     */     //   0	94	0	option	Lzako/opt/ZakoOptions$Option;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void set(ZakoOptions.Option option, Object value) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: dup
/*     */     //   2: invokestatic requireNonNull : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   5: pop
/*     */     //   6: astore_2
/*     */     //   7: iconst_0
/*     */     //   8: istore_3
/*     */     //   9: aload_2
/*     */     //   10: iload_3
/*     */     //   11: <illegal opcode> typeSwitch : (Ljava/lang/Object;I)I
/*     */     //   16: lookupswitch default -> 44, 0 -> 54, 1 -> 77
/*     */     //   44: new java/lang/MatchException
/*     */     //   47: dup
/*     */     //   48: aconst_null
/*     */     //   49: aconst_null
/*     */     //   50: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   53: athrow
/*     */     //   54: aload_2
/*     */     //   55: checkcast zako/opt/ZakoOptions$Toggle
/*     */     //   58: astore #4
/*     */     //   60: aload #4
/*     */     //   62: invokevirtual set : ()Ljava/util/function/Consumer;
/*     */     //   65: aload_1
/*     */     //   66: checkcast java/lang/Boolean
/*     */     //   69: invokeinterface accept : (Ljava/lang/Object;)V
/*     */     //   74: goto -> 97
/*     */     //   77: aload_2
/*     */     //   78: checkcast zako/opt/ZakoOptions$Distance
/*     */     //   81: astore #5
/*     */     //   83: aload #5
/*     */     //   85: invokevirtual set : ()Ljava/util/function/Consumer;
/*     */     //   88: aload_1
/*     */     //   89: checkcast java/lang/Integer
/*     */     //   92: invokeinterface accept : (Ljava/lang/Object;)V
/*     */     //   97: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #445	-> 0
/*     */     //   #446	-> 54
/*     */     //   #447	-> 77
/*     */     //   #449	-> 97
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   60	17	4	t	Lzako/opt/ZakoOptions$Toggle;
/*     */     //   83	14	5	d	Lzako/opt/ZakoOptions$Distance;
/*     */     //   0	98	0	option	Lzako/opt/ZakoOptions$Option;
/*     */     //   0	98	1	value	Ljava/lang/Object;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static double abba(class_310 mc, Test test, ZakoOptions.Toggle toggle, class_2561 name, int i, int n, int percent) {
/* 453 */     double on1 = measure(mc, toggle, true, name, i, n, percent);
/* 454 */     double off1 = measure(mc, toggle, false, name, i, n, percent);
/* 455 */     double off2 = measure(mc, toggle, false, name, i, n, percent);
/* 456 */     double on2 = measure(mc, toggle, true, name, i, n, percent);
/* 457 */     log.info("Benchmark: {} on {}/{} off {}/{}", new Object[] { test.key(), Long.valueOf(Math.round(on1)), Long.valueOf(Math.round(on2)), Long.valueOf(Math.round(off1)), Long.valueOf(Math.round(off2)) });
/* 458 */     return (((off1 > on1) ? true : false) == ((off2 > on2) ? true : false)) ? ((off1 + off2) / (on1 + on2)) : 1.0D;
/*     */   }
/*     */   
/*     */   private static double measure(class_310 mc, ZakoOptions.Toggle toggle, boolean on, class_2561 name, int i, int n, int percent) {
/* 462 */     main(mc, () -> {
/*     */           toggle.set().accept(Boolean.valueOf(on));
/*     */           return null;
/*     */         });
/* 466 */     progress(mc, (class_2561)class_2561.method_43469("zakoopt.bench.test", new Object[] { name, Integer.valueOf(i + 1), Integer.valueOf(n) }), percent);
/* 467 */     sleep(500L);
/* 468 */     return fps(mc, 1500);
/*     */   }
/*     */   
/*     */   private static double master(class_310 mc, boolean on) {
/* 472 */     main(mc, () -> {
/*     */           ZakoOptConfig.enabled = on;
/*     */           return null;
/*     */         });
/* 476 */     sleep(500L);
/* 477 */     return fps(mc, 2000);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class_437 report(Result r, class_437 parent) {
/* 482 */     List<String> changes = new ArrayList<>(r.chosen().entrySet().stream().filter(e -> (((Boolean)e.getValue()).booleanValue() != ((Boolean)toggle((String)e.getKey()).get().get()).booleanValue())).map(Map.Entry::getKey).toList());
/* 483 */     r.picks().forEach((key, value) -> {
/*     */           if (!value.equals(value(option(key)))) {
/*     */             changes.add(key);
/*     */           }
/*     */         });
/* 488 */     class_5250 class_5250 = class_2561.method_43469("zakoopt.bench.gain", new Object[] { "%+d%%".formatted(new Object[] { Long.valueOf(Math.round(r.modGain() * 100.0D)) }) });
/* 489 */     return (class_437)new ResultScreen(parent, (class_2561)class_5250, r.chosen(), r.picks(), changes, () -> {
/*     */           changes.forEach(());
/*     */           ZakoOptConfig.save();
/*     */         });
/*     */   }
/*     */   
/*     */   private static ZakoOptions.Toggle toggle(String key) {
/* 496 */     ZakoOptions.Option option = option(key); if (option instanceof ZakoOptions.Toggle) { ZakoOptions.Toggle t = (ZakoOptions.Toggle)option; if (Blocked.reason(key) == null); }  return null;
/*     */   }
/*     */   
/*     */   private static ZakoOptions.Option option(String key) {
/* 500 */     for (ZakoOptions.Group group : ZakoOptions.GROUPS) {
/* 501 */       for (ZakoOptions.Option option : group.available()) {
/* 502 */         if (option.key().equals(key)) {
/* 503 */           return option;
/*     */         }
/*     */       } 
/*     */     } 
/* 507 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<String> tp(Scene.View v) {
/* 512 */     List<String> c = new ArrayList<>(Scene.zone(v));
/* 513 */     c.addAll(List.of("fill %d %d %d %d %d %d minecraft:barrier".formatted(new Object[] { Integer.valueOf(v.x() - 1), Integer.valueOf(v.y() - 1), Integer.valueOf(v.z() - 1), Integer.valueOf(v.x() + 1), Integer.valueOf(v.y() - 1), Integer.valueOf(v.z() + 1) }, ), "tp @a %d.5 %d %d.5 %d %d"
/* 514 */           .formatted(new Object[] { Integer.valueOf(v.x()), Integer.valueOf(v.y()), Integer.valueOf(v.z()), Integer.valueOf(v.yaw()), Integer.valueOf(v.pitch()) })));
/* 515 */     return c;
/*     */   }
/*     */   
/*     */   private static void progress(class_310 mc, class_2561 what, int percent) {
/* 519 */     progress(mc, what, (class_2561)class_2561.method_43473(), percent);
/*     */   }
/*     */   
/*     */   private static void progress(class_310 mc, class_2561 what, class_2561 note, int percent) {
/* 523 */     status = new Status(what, note, percent);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double recentFps() {
/* 528 */     int n = 0, last = frames;
/* 529 */     long sum = 0L;
/* 530 */     long[] times = RECENT;
/* 531 */     int max = Math.min(last, FRAME_NS.length);
/* 532 */     while (n < max && sum < 1000000000L) {
/* 533 */       times[n] = FRAME_NS[last - 1 - n & FRAME_NS.length - 1];
/* 534 */       sum += times[n++];
/*     */     } 
/* 536 */     Arrays.sort(times, 0, n);
/* 537 */     return (n == 0) ? 0.0D : (1.0E9D / times[n / 2]);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void settle(class_310 mc) {
/* 542 */     long end = System.currentTimeMillis() + 30000L;
/* 543 */     double last = fps(mc, 1000);
/* 544 */     while (System.currentTimeMillis() < end) {
/* 545 */       double now = fps(mc, 1000);
/* 546 */       if (Math.abs(now - last) < last * 0.05D) {
/*     */         return;
/*     */       }
/* 549 */       last = now;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int lastFrames(long[] out, int n) {
/* 555 */     int last = frames, count = Math.min(n, Math.min(last, FRAME_NS.length));
/* 556 */     for (int i = 0; i < count; i++) {
/* 557 */       out[i] = FRAME_NS[last - count + i & FRAME_NS.length - 1];
/*     */     }
/* 559 */     return count;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double fps(class_310 mc, int ms) {
/* 564 */     return fps(mc, ms, 5);
/*     */   }
/*     */ 
/*     */   
/*     */   private static double fps(class_310 mc, int ms, int tries) {
/* 569 */     Objects.requireNonNull(mc); waitWhile(mc::method_1493);
/* 570 */     int start = frames;
/* 571 */     sleep(ms);
/* 572 */     if (mc.method_1493()) {
/* 573 */       return fps(mc, ms, tries);
/*     */     }
/* 575 */     int n = Math.min(frames - start, FRAME_NS.length);
/* 576 */     if (n < 3 && tries > 1) {
/* 577 */       return fps(mc, ms, tries - 1);
/*     */     }
/* 579 */     long[] times = new long[n];
/* 580 */     for (int i = 0; i < n; i++) {
/* 581 */       times[i] = FRAME_NS[frames - n + i & FRAME_NS.length - 1];
/*     */     }
/* 583 */     Arrays.sort(times);
/* 584 */     return (n == 0) ? 0.0D : (1.0E9D / times[n / 2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void teleport(Scene.View view) {
/* 589 */     class_1132 server = class_310.method_1551().method_1576();
/* 590 */     if (server != null) {
/* 591 */       server.execute(() -> tp(view).forEach(()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void waitForestLoaded(class_310 mc) {
/* 597 */     class_1132 server = mc.method_1576();
/* 598 */     class_2338 from = new class_2338(-10, 249, -130), to = new class_2338(74, 249, -46);
/* 599 */     for (int i = 0; i < 120 && server != null && !((Boolean)server.method_5385(() -> Boolean.valueOf(server.method_30002().method_22343(from, to))).join()).booleanValue(); i++) {
/* 600 */       sleep(250L);
/*     */     }
/* 602 */     sleep(1000L);
/*     */   }
/*     */   
/*     */   private static void server(class_310 mc, List<String> commands) {
/* 606 */     class_1132 server = mc.method_1576();
/* 607 */     if (server == null) {
/* 608 */       throw new Aborted();
/*     */     }
/* 610 */     server.method_20493(() -> {
/*     */           class_2168 source = server.method_3739().method_9217();
/*     */           commands.forEach(());
/* 613 */         }).join();
/*     */   }
/*     */   
/*     */   private static <T> T main(class_310 mc, Supplier<T> task) {
/* 617 */     return mc.method_5385(task).join();
/*     */   }
/*     */   
/*     */   private static void delete(class_310 mc) {
/* 621 */     if (!mc.method_1586().method_230("zakoopt-benchmark"))
/*     */       return; 
/*     */     
/* 624 */     try { class_32.class_5143 access = mc.method_1586().method_27002("zakoopt-benchmark"); 
/* 625 */       try { access.method_27015();
/* 626 */         if (access != null) access.close();  } catch (Throwable throwable) { if (access != null) try { access.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (Exception e)
/* 627 */     { log.warn("Could not delete the benchmark world", e); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void waitWhile(BooleanSupplier condition) {
/* 632 */     while (condition.getAsBoolean()) {
/* 633 */       sleep(200L);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void sleep(long ms) {
/*     */     try {
/* 640 */       long end = System.currentTimeMillis() + ms;
/* 641 */       while (System.currentTimeMillis() < end) {
/* 642 */         Thread.sleep(Math.min(100L, end - System.currentTimeMillis() + 1L));
/* 643 */         if (cancelled || (joined && (class_310.method_1551()).field_1687 == null))
/* 644 */           throw new Aborted(); 
/*     */       } 
/*     */     } catch (InterruptedException $ex) {
/*     */       throw $ex;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\Bench.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */