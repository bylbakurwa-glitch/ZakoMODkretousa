/*     */ package zako.opt.hud;
/*     */ 
/*     */ import lombok.Generated;
/*     */ import net.minecraft.class_276;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_6367;
/*     */ import zako.opt.feature.Features;
/*     */ 
/*     */ public final class HudCache
/*     */ {
/*     */   @Generated
/*     */   private HudCache() {
/*  13 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static class_6367 target;
/*     */   private static class_276 main;
/*     */   private static boolean active;
/*     */   private static boolean refresh;
/*     */   private static boolean drawing;
/*     */   private static boolean extracted;
/*     */   private static long lastRefresh;
/*     */   public static boolean suppressLiveParts;
/*     */   public static boolean pipPrepare;
/*     */   
/*     */   public static void beginFrame(boolean renderLevel) {
/*  26 */     class_310 mc = class_310.method_1551();
/*  27 */     boolean was = active;
/*  28 */     long now = System.nanoTime();
/*  29 */     active = (HudWorth.update(now, MonitorRate.intervalNs(now)) && Features.HUD_CACHE.on && !HudWorth.isMainSwapped() && renderLevel && mc.field_1687 != null && HudGpu.noScreen(mc));
/*  30 */     if (!active) {
/*  31 */       HudDelta.idle();
/*     */       return;
/*     */     } 
/*  34 */     main = HudGpu.mainTarget(mc);
/*  35 */     int w = mc.method_22683().method_4489(), h = mc.method_22683().method_4506();
/*  36 */     refresh = (!was || target == null || target.field_1482 != w || target.field_1481 != h || now - lastRefresh >= MonitorRate.intervalNs(now));
/*  37 */     HudDelta.frame(refresh, mc.method_61966().method_60636());
/*  38 */     if (refresh) {
/*  39 */       lastRefresh = now;
/*  40 */       if (target == null) {
/*  41 */         target = HudGpu.createTarget(w, h);
/*  42 */       } else if (target.field_1482 != w || target.field_1481 != h) {
/*  43 */         target.method_1234(w, h);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean active() {
/*  49 */     return active;
/*     */   }
/*     */   
/*     */   public static boolean skipExtraction() {
/*  53 */     return (active && !refresh);
/*     */   }
/*     */   
/*     */   public static class_276 drawTarget(class_276 requested) {
/*  57 */     if (!drawing) {
/*  58 */       return null;
/*     */     }
/*  60 */     if (requested == main) {
/*  61 */       return (class_276)target;
/*     */     }
/*     */     
/*  64 */     if (requested != target && requested.field_1482 == main.field_1482 && requested.field_1481 == main.field_1481) {
/*  65 */       HudWorth.mainSwapped(requested);
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class_276 redirect(class_276 requested) {
/*  74 */     return (drawing && !pipPrepare && requested != target && requested == main) ? (class_276)target : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void extractIntoTexture(Runnable extract) {
/*  79 */     HudGpu.clear(target);
/*  80 */     extracted = true;
/*  81 */     drawing = true;
/*     */     try {
/*  83 */       extract.run();
/*     */     } finally {
/*  85 */       drawing = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawIntoTexture(Runnable draw) {
/*  90 */     if (!extracted) {
/*  91 */       HudGpu.clear(target);
/*     */     }
/*  93 */     extracted = false;
/*  94 */     drawing = true;
/*     */     try {
/*  96 */       draw.run();
/*     */     } finally {
/*  98 */       drawing = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void composite() {
/* 103 */     class_276 main = HudGpu.mainTarget(class_310.method_1551());
/* 104 */     HudGpu.composite(target, main);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\hud\HudCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */