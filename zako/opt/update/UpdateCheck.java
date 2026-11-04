/*    */ package zako.opt.update;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.net.URI;
/*    */ import java.net.http.HttpClient;
/*    */ import java.net.http.HttpRequest;
/*    */ import java.net.http.HttpResponse;
/*    */ import java.time.Duration;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_407;
/*    */ import net.minecraft.class_437;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import zako.opt.ZakoOptions;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class UpdateCheck
/*    */ {
/*    */   private static final String REPO = "3ako/ZakoOpt";
/*    */   @Generated
/* 23 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/* 24 */   private UpdateCheck() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/*    */   
/* 26 */   private static final URI DOWNLOAD = URI.create("https://www.curseforge.com/minecraft/mc-mods/zakoopt");
/*    */   private static boolean started;
/*    */   private static volatile String latest;
/*    */   private static boolean shown;
/*    */   
/*    */   public static void start() {
/* 32 */     if (Features.UPDATE_CHECK.value && !started) {
/* 33 */       started = true;
/* 34 */       fetch();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void offer(class_437 title) {
/* 40 */     if (latest == null || shown) {
/*    */       return;
/*    */     }
/* 43 */     shown = true;
/* 44 */     UpdateUi.setScreen((class_437)new class_407(open -> {
/*    */             if (open) {
/*    */               UpdateUi.open(DOWNLOAD);
/*    */             }
/*    */             UpdateUi.setScreen(title);
/* 49 */           }(class_2561)class_2561.method_43469("zakoopt.update.title", new Object[] { latest }), (class_2561)class_2561.method_43469("zakoopt.update.message", new Object[] { ZakoOptions.VERSION }), DOWNLOAD, 
/* 50 */           (class_2561)class_2561.method_43471("zakoopt.update.later"), true));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void fetch() {
/* 58 */     HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.github.com/repos/3ako/ZakoOpt/releases/latest")).timeout(Duration.ofSeconds(5L)).header("Accept", "application/vnd.github+json").header("User-Agent", "ZakoOpt/" + ZakoOptions.VERSION).build();
/* 59 */     HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build()
/* 60 */       .<String>sendAsync(request, HttpResponse.BodyHandlers.ofString())
/* 61 */       .thenAccept(response -> {
/*    */           if (response.statusCode() != 200) {
/*    */             log.debug("Update check: HTTP {}", Integer.valueOf(response.statusCode()));
/*    */             
/*    */             return;
/*    */           } 
/*    */           JsonObject release = JsonParser.parseString(response.body()).getAsJsonObject();
/*    */           String tag = release.get("tag_name").getAsString();
/*    */           if (Versions.offered(tag, ZakoOptions.VERSION)) {
/*    */             latest = tag;
/*    */           }
/* 72 */         }).exceptionally(e -> {
/*    */           log.debug("Update check failed", e);
/*    */           return null;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\op\\update\UpdateCheck.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */