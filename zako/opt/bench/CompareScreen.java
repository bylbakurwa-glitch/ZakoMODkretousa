/*    */ package zako.opt.bench;
/*    */ 
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.class_2561;
/*    */ import net.minecraft.class_5250;
/*    */ import net.minecraft.class_5348;
/*    */ import zako.opt.Canvas;
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.cpu.CorePin;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompareScreen
/*    */   extends ProgressScreen
/*    */ {
/*    */   private final CompletableFuture<Void> done;
/* 19 */   private int view = Scene.views().indexOf(Scene.OVERVIEW);
/*    */   
/*    */   public CompareScreen(CompletableFuture<Void> done) {
/* 22 */     this.done = done;
/*    */   }
/*    */ 
/*    */   
/*    */   protected class_2561 stage() {
/* 27 */     return (class_2561)class_2561.method_43471(ZakoOptConfig.enabled ? "zakoopt.bench.compare.on" : "zakoopt.bench.compare.off");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int percent() {
/* 32 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected class_2561 hint() {
/* 37 */     return (class_2561)class_2561.method_43471("zakoopt.bench.compare.hint");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paint(Canvas c) {
/* 42 */     super.paint(c);
/* 43 */     int w = 240, x0 = this.field_22789 / 2 - w / 2, x1 = x0 + w, y0 = this.field_22790 - 8 - 66, inner = w - 16;
/* 44 */     frame(c, x0, y0, x1, y0 + 66);
/* 45 */     modToggle(c, x0 + 8, y0 + 8, inner);
/*    */     
/* 47 */     int ry = y0 + 26;
/* 48 */     button(c, x0 + 8, ry, 20, (class_2561)class_2561.method_43470("◀"), -1911368, -5730192, true, () -> move(Scene.views().size() - 1));
/* 49 */     button(c, x1 - 28, ry, 20, (class_2561)class_2561.method_43470("▶"), -1911368, -5730192, true, () -> move(1));
/* 50 */     class_5250 class_52501 = class_2561.method_43471("zakoopt.bench.view." + ((Scene.View)Scene.views().get(this.view)).key());
/* 51 */     c.text((class_2561)class_52501, x0 + w / 2 - this.field_22793.method_27525((class_5348)class_52501) / 2, ry + 3, -15068148);
/*    */     
/* 53 */     ry += 18;
/* 54 */     class_5250 class_52502 = class_2561.method_43471("gui.done");
/* 55 */     if (CorePin.available()) {
/* 56 */       int half = (inner - 6) / 2;
/* 57 */       class_5250 class_5250 = class_2561.method_43469("zakoopt.bench.compare.cores", new Object[] {
/* 58 */             class_2561.method_43471(Features.CORE_PIN.value ? "zakoopt.bench.compare.cores.best" : "zakoopt.bench.compare.cores.all") });
/* 59 */       button(c, x0 + 8, ry, half, (class_2561)class_5250, -1911368, -5730192, true, () -> Features.CORE_PIN.value = !Features.CORE_PIN.value);
/* 60 */       button(c, x1 - 8 - half, ry, half, (class_2561)class_52502, -1911368, -5730192, true, this::method_25419);
/*    */     } else {
/* 62 */       button(c, x0 + 8, ry, inner, (class_2561)class_52502, -1911368, -5730192, true, this::method_25419);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void move(int step) {
/* 67 */     this.view = (this.view + step) % Scene.views().size();
/* 68 */     Bench.teleport(Scene.views().get(this.view));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void closed() {
/* 73 */     this.done.complete(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\CompareScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */