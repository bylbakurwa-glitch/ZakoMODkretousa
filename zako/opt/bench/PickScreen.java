/*    */ package zako.opt.bench;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_5250;
/*    */ import zako.opt.Canvas;
/*    */ import zako.opt.Panel;
/*    */ import zako.opt.ZakoOptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PickScreen
/*    */   extends Panel
/*    */ {
/*    */   private final List<ZakoOptions.Option> options;
/*    */   private final String hint;
/*    */   private final CompletableFuture<Void> done;
/*    */   private boolean touched;
/*    */   
/*    */   public PickScreen(List<ZakoOptions.Option> options, String hint, CompletableFuture<Void> done) {
/* 24 */     super((class_2561)class_2561.method_43471(hint));
/* 25 */     this.options = options;
/* 26 */     this.hint = hint;
/* 27 */     this.done = done;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean overScene() {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void changed() {
/* 37 */     this.touched = true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paint(Canvas c) {
/* 42 */     if (!this.touched) {
/* 43 */       class_5250 class_52501 = class_2561.method_43471(this.hint + ".hint");
/* 44 */       int tw = Math.min(300, this.field_22789 - 40), th = lineCount((class_2561)class_52501, tw) * 10;
/* 45 */       int tx = this.field_22789 / 2 - tw / 2, ty = this.field_22790 / 2 - th / 2 - 20;
/* 46 */       frame(c, tx - 8, ty - 6, tx + tw + 8, ty + th + 4);
/* 47 */       lines(c, (class_2561)class_52501, tx, ty, tw, -13753320);
/*    */     } 
/* 49 */     int w = Math.min(300, this.field_22789 - 16), x0 = this.field_22789 / 2 - w / 2, x1 = x0 + w;
/* 50 */     int h = 32 + this.options.size() * 16 + 4 + 14 + 8, y0 = this.field_22790 - 8 - h;
/* 51 */     frame(c, x0, y0, x1, y0 + h);
/* 52 */     c.text(firstLine(this.field_22785, w - 16), x0 + 8, y0 + 8, -15068148);
/* 53 */     c.text((class_2561)class_2561.method_43470("" + Math.round(Bench.recentFps()) + " FPS"), x0 + 8, y0 + 20, -8754606);
/* 54 */     int y = y0 + 34;
/* 55 */     for (ZakoOptions.Option option : this.options) {
/* 56 */       setting(c, option, x0 + 8, x1 - 8, y);
/* 57 */       y += 16;
/*    */     } 
/* 59 */     class_5250 class_5250 = class_2561.method_43471("gui.done");
/* 60 */     button(c, x0 + w / 2 - 40, y + 2, 80, (class_2561)class_5250, -1911368, -5730192, true, this::method_25419);
/*    */   }
/*    */ 
/*    */   
/*    */   public void method_25419() {
/* 65 */     this.done.complete(null);
/* 66 */     super.method_25419();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\PickScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */