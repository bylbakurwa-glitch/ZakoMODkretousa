/*    */ package zako.opt;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ import java.security.CodeSource;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import lombok.Generated;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.fabricmc.loader.api.ModContainer;
/*    */ import net.fabricmc.loader.api.metadata.ModOrigin;
/*    */ 
/*    */ public final class Mods {
/*    */   @Generated
/*    */   private Mods() {
/* 19 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 20 */   } private static final Set<String> OURS = Set.of("minecraft", "java", "fabricloader", "fabric-api", "mixinextras", "zakoopt");
/*    */   
/* 22 */   private static final Pattern HANDLER = Pattern.compile("^[a-zA-Z]+\\$[a-z0-9]+\\$([a-z][a-z0-9_-]*)\\$");
/*    */   
/*    */   private static Map<Path, ModContainer> byPath;
/*    */   
/*    */   public static String owner(Class<?> type) {
/* 27 */     CodeSource source = type.getProtectionDomain().getCodeSource();
/* 28 */     if (source == null || source.getLocation() == null) {
/* 29 */       return null;
/*    */     }
/*    */     try {
/* 32 */       return name(paths().get(normalize(Path.of(source.getLocation().toURI()))));
/* 33 */     } catch (Exception e) {
/* 34 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String owner(StackWalker.StackFrame frame) {
/* 39 */     String name = owner(frame.getDeclaringClass());
/* 40 */     if (name != null) {
/* 41 */       return name;
/*    */     }
/* 43 */     Matcher m = HANDLER.matcher(frame.getMethodName());
/* 44 */     return m.find() ? name(FabricLoader.getInstance().getModContainer(m.group(1)).orElse(null)) : null;
/*    */   }
/*    */   
/*    */   private static String name(ModContainer mod) {
/* 48 */     if (mod == null) {
/* 49 */       return null;
/*    */     }
/* 51 */     for (Optional<ModContainer> parent = mod.getContainingMod(); parent.isPresent(); parent = mod.getContainingMod()) {
/* 52 */       mod = parent.get();
/*    */     }
/* 54 */     String id = mod.getMetadata().getId();
/* 55 */     return (OURS.contains(id) || id.startsWith("fabric-")) ? null : mod.getMetadata().getName();
/*    */   }
/*    */   
/*    */   private static Map<Path, ModContainer> paths() {
/* 59 */     if (byPath == null) {
/* 60 */       byPath = new HashMap<>();
/* 61 */       for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
/* 62 */         if (mod.getOrigin().getKind() == ModOrigin.Kind.PATH) {
/* 63 */           for (Path path : mod.getOrigin().getPaths()) {
/* 64 */             byPath.put(normalize(path), mod);
/*    */           }
/*    */         }
/*    */       } 
/*    */     } 
/* 69 */     return byPath;
/*    */   }
/*    */   
/*    */   private static Path normalize(Path path) {
/* 73 */     return path.toAbsolutePath().normalize();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Mods.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */