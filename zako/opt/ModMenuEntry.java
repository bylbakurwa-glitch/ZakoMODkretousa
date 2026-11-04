/*   */ package zako.opt;
/*   */ 
/*   */ import com.terraformersmc.modmenu.api.ConfigScreenFactory;
/*   */ import com.terraformersmc.modmenu.api.ModMenuApi;
/*   */ 
/*   */ public class ModMenuEntry
/*   */   implements ModMenuApi {
/*   */   public ConfigScreenFactory<?> getModConfigScreenFactory() {
/* 9 */     return ZakoOptScreen::new;
/*   */   }
/*   */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ModMenuEntry.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */