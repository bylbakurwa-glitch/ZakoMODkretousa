/*     */ package zako.opt.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import lombok.Generated;
/*     */ import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
/*     */ import net.minecraft.class_11515;
/*     */ import net.minecraft.class_2338;
/*     */ import net.minecraft.class_243;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4076;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.gl.MainView;
/*     */ 
/*     */ public final class FarLeaves {
/*     */   private static final int SLACK = 8;
/*     */   private static final double RECHECK = 4.0D;
/*     */   
/*     */   @Generated
/*     */   private FarLeaves() {
/*  23 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */ 
/*     */   
/*  27 */   private static final Map<Long, Boolean> sections = new ConcurrentHashMap<>();
/*     */   private static volatile double camX;
/*  29 */   private static double checkedX = Double.NaN;
/*     */   private static volatile double camY;
/*     */   private static volatile double camZ;
/*     */   private static double checkedY;
/*     */   private static double checkedZ;
/*     */   private static int checkedDistance;
/*     */   private static volatile int epoch;
/*     */   
/*  37 */   public static final class Section { private long key = Long.MIN_VALUE;
/*  38 */     private int epoch = -1;
/*     */     
/*     */     private boolean far; }
/*     */ 
/*     */   
/*     */   private static boolean far(Section last, class_2338 pos) {
/*  44 */     int sx = pos.method_10263() >> 4, sy = pos.method_10264() >> 4, sz = pos.method_10260() >> 4;
/*  45 */     long key = class_4076.method_18685(sx, sy, sz);
/*  46 */     int e = epoch;
/*  47 */     if (key != last.key || e != last.epoch) {
/*  48 */       int limit = Features.FAR_LEAVES.distance;
/*  49 */       last.far = (distanceSqr(sx, sy, sz) > limit * limit);
/*  50 */       last.key = key;
/*  51 */       last.epoch = e;
/*  52 */       sections.put(Long.valueOf(key), Boolean.valueOf(last.far));
/*     */     } 
/*  54 */     return last.far;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class_11515 type(Section last, class_2680 state, class_2338 pos, class_11515 original) {
/*  59 */     if (!Features.FAR_LEAVES.on || state == null || !(state.method_26204() instanceof net.minecraft.class_2397)) {
/*  60 */       return original;
/*     */     }
/*  62 */     return far(last, pos) ? class_11515.field_60923 : original;
/*     */   }
/*     */ 
/*     */   
/*  66 */   private static final boolean PLANTS = Boolean.getBoolean("zakoopt.far_plants");
/*     */ 
/*     */   
/*     */   public static boolean hidePlant(Section last, class_2680 state, class_2338 pos) {
/*  70 */     if (!PLANTS || !Features.FAR_LEAVES.on || state == null || !(state.method_26204() instanceof net.minecraft.class_2261)) {
/*  71 */       return false;
/*     */     }
/*  73 */     return far(last, pos);
/*     */   }
/*     */   
/*     */   public static void frame() {
/*  77 */     if (!Features.FAR_LEAVES.on) {
/*  78 */       if (!sections.isEmpty()) {
/*  79 */         sections.clear();
/*  80 */         epoch++;
/*     */       } 
/*     */       return;
/*     */     } 
/*  84 */     class_310 mc = class_310.method_1551();
/*  85 */     if (mc.field_1687 == null) {
/*     */       return;
/*     */     }
/*  88 */     class_243 cam = MainView.camera();
/*  89 */     camX = cam.field_1352;
/*  90 */     camY = cam.field_1351;
/*  91 */     camZ = cam.field_1350;
/*  92 */     double dx = camX - checkedX, dy = camY - checkedY, dz = camZ - checkedZ;
/*  93 */     int limit = Features.FAR_LEAVES.distance;
/*  94 */     if (dx * dx + dy * dy + dz * dz < 16.0D && limit == checkedDistance) {
/*     */       return;
/*     */     }
/*  97 */     checkedX = camX;
/*  98 */     checkedY = camY;
/*  99 */     checkedZ = camZ;
/* 100 */     checkedDistance = limit;
/* 101 */     epoch++;
/* 102 */     SodiumWorldRenderer renderer = SodiumWorldRenderer.instanceNullable();
/* 103 */     double keep = ((mc.field_1690.method_38521() + 2) * 16);
/* 104 */     for (Iterator<Map.Entry<Long, Boolean>> it = sections.entrySet().iterator(); it.hasNext(); ) {
/* 105 */       Map.Entry<Long, Boolean> e = it.next();
/* 106 */       long key = ((Long)e.getKey()).longValue();
/* 107 */       int sx = class_4076.method_18686(key), sy = class_4076.method_18689(key), sz = class_4076.method_18690(key);
/* 108 */       double d = distance(sx, sy, sz);
/* 109 */       if (d > keep) {
/* 110 */         it.remove(); continue;
/* 111 */       }  if (renderer != null && (((Boolean)e.getValue()).booleanValue() ? (d < (limit - 8)) : (d > (limit + 8)))) {
/* 112 */         e.setValue(Boolean.valueOf(!((Boolean)e.getValue()).booleanValue()));
/* 113 */         renderer.scheduleRebuildForChunk(sx, sy, sz, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static double distance(int sx, int sy, int sz) {
/* 119 */     return Math.sqrt(distanceSqr(sx, sy, sz));
/*     */   }
/*     */   
/*     */   private static double distanceSqr(int sx, int sy, int sz) {
/* 123 */     double dx = ((sx << 4) + 8) - camX, dy = ((sy << 4) + 8) - camY, dz = ((sz << 4) + 8) - camZ;
/* 124 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\FarLeaves.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */