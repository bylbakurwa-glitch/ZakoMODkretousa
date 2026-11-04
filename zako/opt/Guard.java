/*    */ package zako.opt;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.CopyOnWriteArrayList;
/*    */ import lombok.Generated;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_5250;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class Guard {
/*    */   @Generated
/* 17 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/*    */   private Guard() {
/* 19 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/* 21 */   private static final boolean CRASH = (FabricLoader.getInstance().isDevelopmentEnvironment() || Boolean.getBoolean("zakoopt.guard.crash"));
/* 22 */   private static final Set<String> FAILED = ConcurrentHashMap.newKeySet();
/*    */   
/* 24 */   private static final List<Feature> UNTOLD = new CopyOnWriteArrayList<>();
/*    */   private static volatile boolean untold;
/*    */   
/*    */   public static void fail(Throwable t, String mixin, String method) {
/* 28 */     if (CRASH) {
/* 29 */       if (t instanceof RuntimeException) { RuntimeException r = (RuntimeException)t;
/* 30 */         throw r; }
/*    */       
/* 32 */       throw (Error)t;
/*    */     } 
/* 34 */     if (!FAILED.add(mixin + "#" + mixin)) {
/*    */       return;
/*    */     }
/* 37 */     String name = mixin.substring(mixin.indexOf(".mixin.") + ".mixin.".length());
/* 38 */     List<Feature> off = Features.all().stream().filter(f -> Arrays.<String>asList(f.data().mixins()).contains(name)).toList();
/* 39 */     off.forEach(f -> f.broken = true);
/* 40 */     log.error("{}.{} failed; {}", new Object[] { name, method, off.isEmpty() ? "the call goes on without it" : ("turned off until restart: " + 
/* 41 */           String.valueOf(off.stream().map(Feature::key).toList())), t });
/* 42 */     Objects.requireNonNull(UNTOLD); off.stream().filter(f -> !UNTOLD.contains(f)).forEach(UNTOLD::add);
/* 43 */     untold = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void tell() {
/* 48 */     if (!untold || !Chat.ready()) {
/*    */       return;
/*    */     }
/* 51 */     untold = false;
/* 52 */     List<Feature> off = List.copyOf(UNTOLD);
/* 53 */     UNTOLD.removeAll(off);
/* 54 */     class_5250 names = class_2561.method_43473();
/* 55 */     for (int i = 0; i < off.size(); i++) {
/* 56 */       if (i > 0) {
/* 57 */         names.method_27693(", ");
/*    */       }
/* 59 */       names.method_10852((class_2561)class_2561.method_43471("zakoopt.option." + ((Feature)off.get(i)).key()));
/*    */     } 
/* 61 */     Chat.show(off.isEmpty() ? (class_2561)class_2561.method_43471("zakoopt.guard.chat.other") : (class_2561)class_2561.method_43469("zakoopt.guard.chat", new Object[] { names }));
/*    */   }
/*    */   
/*    */   public static List<String> lines() {
/* 65 */     List<String> broken = Features.all().stream().filter(f -> f.broken).map(Feature::key).toList();
/* 66 */     return broken.isEmpty() ? List.<String>of() : List.<String>of("ZakoOpt errors, off until restart: " + String.join(", ", (Iterable)broken));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Guard.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */