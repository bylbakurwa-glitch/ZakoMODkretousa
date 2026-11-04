/*    */ package zako.opt;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.IOException;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.nio.file.AtomicMoveNotSupportedException;
/*    */ import java.nio.file.CopyOption;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.StandardCopyOption;
/*    */ import java.util.stream.Stream;
/*    */ import lombok.Generated;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.fabricmc.loader.api.ModContainer;
/*    */ import org.slf4j.Logger;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class ZakoOptConfig {
/*    */   @Generated
/* 20 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/* 21 */   private ZakoOptConfig() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/* 22 */    public static final boolean IRIS = FabricLoader.getInstance().isModLoaded("iris");
/*    */   
/*    */   public static final String FOREIGN_HUD_MOD;
/*    */   
/*    */   static {
/* 27 */     FOREIGN_HUD_MOD = Stream.<String>of(new String[] { "axolotlclient", "exordium", "gnetum", "soupapi" }).flatMap(id -> FabricLoader.getInstance().getModContainer(id).stream()).map(mod -> mod.getMetadata().getName()).findFirst().orElse(null);
/* 28 */   } public static final boolean FOREIGN_HUD = (FOREIGN_HUD_MOD != null);
/* 29 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
/* 30 */   public static final boolean RING_PRESENT = (ZakoOptConfig.class.getClassLoader().getResource("zako/opt/gl/ImmediateRing.class") != null && !MixinPlugin.VULKAN);
/* 31 */   private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("zakoopt.json");
/* 32 */   public static final int CORES = Runtime.getRuntime().availableProcessors();
/*    */   
/*    */   public static boolean enabled = true;
/*    */   public static boolean welcomed;
/*    */   
/*    */   static {
/* 38 */     load();
/* 39 */     refresh();
/*    */   }
/*    */   
/*    */   private static void load() {
/* 43 */     if (Files.exists(FILE, new java.nio.file.LinkOption[0])) {
/*    */       try {
/* 45 */         JsonObject json = JsonParser.parseString(Files.readString(FILE, StandardCharsets.UTF_8)).getAsJsonObject();
/* 46 */         Features.read(json);
/* 47 */         enabled = Features.jsonBoolean(json, "enabled", enabled);
/* 48 */         welcomed = Features.jsonBoolean(json, "welcomed", welcomed);
/*    */         return;
/* 50 */       } catch (Exception e) {
/*    */         
/* 52 */         log.warn("Can't read {}, starting from the defaults", FILE, e);
/* 53 */         moveAside();
/*    */         return;
/*    */       } 
/*    */     }
/* 57 */     save();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void moveAside() {
/* 62 */     Path aside = FILE.resolveSibling(String.valueOf(FILE.getFileName()) + ".broken");
/*    */     try {
/* 64 */       Files.move(FILE, aside, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 65 */       log.warn("The old config is at {}", aside);
/* 66 */     } catch (IOException e) {
/* 67 */       log.error("Can't move {} aside", FILE, e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void save() {
/* 72 */     refresh();
/* 73 */     JsonObject json = new JsonObject();
/* 74 */     Features.write(json);
/* 75 */     json.addProperty("enabled", Boolean.valueOf(enabled));
/* 76 */     json.addProperty("welcomed", Boolean.valueOf(welcomed));
/* 77 */     Path tmp = FILE.resolveSibling(String.valueOf(FILE.getFileName()) + ".tmp");
/*    */     
/*    */     try {
/* 80 */       Files.writeString(tmp, GSON.toJson((JsonElement)json), StandardCharsets.UTF_8, new java.nio.file.OpenOption[0]);
/*    */       try {
/* 82 */         Files.move(tmp, FILE, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE });
/* 83 */       } catch (AtomicMoveNotSupportedException e) {
/* 84 */         Files.move(tmp, FILE, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*    */       } 
/* 86 */     } catch (IOException e) {
/* 87 */       log.error("Can't save {}", FILE, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void refresh() {
/* 93 */     Features.refresh();
/* 94 */     Guard.tell();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptConfig.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */