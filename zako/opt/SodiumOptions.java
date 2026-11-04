/*    */ package zako.opt;
/*    */ import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
/*    */ import net.caffeinemc.mods.sodium.api.config.structure.ModOptionsBuilder;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_437;
/*    */ 
/*    */ public class SodiumOptions implements ConfigEntryPoint {
/*    */   @Generated
/* 10 */   private static final Logger log = LoggerFactory.getLogger("zakoopt");
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerConfigLate(ConfigBuilder builder) {
/*    */     try {
/* 16 */       register(builder);
/* 17 */     } catch (LinkageError e) {
/* 18 */       log.warn("This Sodium version has no room for the ZakoOpt page; settings stay in Mod Menu", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void register(ConfigBuilder builder) {
/* 26 */     ExternalButtonOptionBuilder open = builder.createExternalButtonOption(class_2960.method_60655("zakoopt", "open")).setName((class_2561)class_2561.method_43471("zakoopt.screen.open")).setTooltip((class_2561)class_2561.method_43471("zakoopt.screen.open.tooltip")).setScreenConsumer(screen -> UpdateUi.setScreen(new ZakoOptScreen(screen)));
/* 27 */     ModOptionsBuilder mod = builder.registerOwnModOptions();
/* 28 */     class_2960 icon = class_2960.method_60655("zakoopt", "icon.png");
/*    */     try {
/* 30 */       mod.setNonTintedIcon(icon);
/* 31 */     } catch (NoSuchMethodError e) {
/*    */       
/* 33 */       mod.setIcon(icon);
/*    */     } 
/* 35 */     mod.addPage((PageBuilder)builder.createOptionPage().setName((class_2561)class_2561.method_43470("ZakoOpt"))
/* 36 */         .addOptionGroup(builder.createOptionGroup().addOption((OptionBuilder)open)));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\SodiumOptions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */