/*    */ package zako.opt;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.concurrent.CopyOnWriteArrayList;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.gl.RingAutoTune;
/*    */ import zako.opt.hud.HudWorth;
/*    */ 
/*    */ public final class DebugInfo {
/*    */   @Generated
/*    */   private DebugInfo() {
/* 16 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   private static List<ZakoOptions.Toggle> available;
/* 19 */   public static final List<Supplier<List<String>>> EXTRA = new CopyOnWriteArrayList<>();
/*    */   
/*    */   public static List<String> lines() {
/* 22 */     List<String> lines = new ArrayList<>();
/* 23 */     lines.add(line());
/* 24 */     lines.addAll(Guard.lines());
/* 25 */     EXTRA.forEach(extra -> lines.addAll(extra.get()));
/* 26 */     return lines;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String line() {
/* 31 */     if (available == null)
/*    */     {
/* 33 */       available = ZakoOptions.GROUPS.stream().flatMap(g -> g.options().stream()).filter(o -> (o instanceof ZakoOptions.Toggle && o.available())).map(o -> (ZakoOptions.Toggle)o).toList();
/*    */     }
/* 35 */     long on = available.stream().filter(t -> ((Boolean)t.get().get()).booleanValue()).count();
/*    */     
/* 37 */     String ring = !Features.IMMEDIATE_RING.value ? "off" : (Features.IMMEDIATE_RING.forced() ? ((Features.IMMEDIATE_RING.on ? "on" : "off") + " (forced)") : (ZakoOptConfig.RING_PRESENT ? RingAutoTune.status() : "n/a"));
/* 38 */     String hud = MixinPlugin.VULKAN ? "off (VulkanMod)" : (ZakoOptConfig.FOREIGN_HUD ? ("off (" + ZakoOptConfig.FOREIGN_HUD_MOD + ")") : (HudWorth.isMainSwapped() ? ("off (main target swapped by " + (String)Objects.<String>requireNonNullElse(HudWorth.getSwappedBy(), "another mod") + ")") : (!Features.HUD_CACHE.value ? "off" : (HudWorth.isWorth() ? "on" : "idle (FPS too close to monitor rate)"))));
/* 39 */     return !ZakoOptConfig.enabled ? ("ZakoOpt " + 
/* 40 */       ZakoOptions.VERSION + ": off") : ("ZakoOpt " + 
/*    */       
/* 42 */       ZakoOptions.VERSION + ": " + on + "/" + available.size() + " on, ring " + ring + ", HUD cache " + hud + ", cores " + CorePin.status());
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\DebugInfo.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */