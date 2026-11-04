/*     */ package zako.opt.bench;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Stream;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5250;
/*     */ import zako.opt.Canvas;
/*     */ import zako.opt.Panel;
/*     */ import zako.opt.ZakoOptions;
/*     */ import zako.opt.update.UpdateUi;
/*     */ 
/*     */ 
/*     */ public class ResultScreen
/*     */   extends Panel
/*     */ {
/*  19 */   static int PAD = 6; static int ROW = 14;
/*     */   
/*     */   private final class_437 parent;
/*     */   private final class_2561 gain;
/*     */   private final Map<String, Boolean> chosen;
/*     */   private final Map<String, Object> picks;
/*     */   private final List<String> changes;
/*     */   private final Runnable apply;
/*  27 */   private final List<ZakoOptions.Toggle> tested = new ArrayList<>(), other = new ArrayList<>();
/*     */   
/*     */   public ResultScreen(class_437 parent, class_2561 gain, Map<String, Boolean> chosen, Map<String, Object> picks, List<String> changes, Runnable apply) {
/*  30 */     super((class_2561)class_2561.method_43471("zakoopt.bench.done"));
/*  31 */     this.parent = parent;
/*  32 */     this.gain = gain;
/*  33 */     this.chosen = chosen;
/*  34 */     this.picks = picks;
/*  35 */     this.changes = changes;
/*  36 */     this.apply = apply;
/*  37 */     for (ZakoOptions.Group group : ZakoOptions.GROUPS) {
/*  38 */       for (ZakoOptions.Option option : group.available()) {
/*  39 */         if (option instanceof ZakoOptions.Toggle) { ZakoOptions.Toggle t = (ZakoOptions.Toggle)option; if (!t.key().equals("update_check")) {
/*  40 */             (chosen.containsKey(t.key()) ? this.tested : this.other).add(t);
/*     */           } }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paint(Canvas c) {
/*  48 */     int w = Math.min(this.field_22789 - 2 * PAD, 520), x0 = (this.field_22789 - w) / 2, x1 = x0 + w, y0 = PAD, y1 = this.field_22790 - PAD;
/*  49 */     frame(c, x0, y0, x1, y1);
/*  50 */     c.text(this.field_22785, x0 + 8, y0 + 7, -15068148);
/*  51 */     c.text(this.gain, x0 + 8, y0 + 19, -13753320);
/*  52 */     int top = y0 + 32;
/*  53 */     c.fill(x0, top, x1, top + 2, -2569296);
/*  54 */     int bottom = y1 - 24;
/*     */     
/*  56 */     int y = beginScroll(c, x0, top + 2, x1, bottom) + 6;
/*  57 */     y = lines(c, (class_2561)class_2561.method_43471("zakoopt.bench.legend"), x0 + 8, y, w - 16, -8754606) + 4;
/*  58 */     y = header(c, (class_2561)class_2561.method_43471("zakoopt.bench.result.tested"), x0, x1, y);
/*  59 */     y = rows(c, this.tested, x0, x1, y);
/*  60 */     y = header(c, (class_2561)class_2561.method_43471("zakoopt.bench.result.other"), x0, x1, y + 6);
/*  61 */     for (Map.Entry<String, Object> pick : this.picks.entrySet()) {
/*  62 */       Object object = pick.getValue(); if (object instanceof Integer) { Integer distance = (Integer)object;
/*  63 */         c.text((class_2561)class_2561.method_43471("zakoopt.option." + (String)pick.getKey()).method_27693(": ").method_10852((class_2561)class_2561.method_43469(unit(pick.getKey()), new Object[] { distance
/*  64 */                 })).method_27693(this.changes.contains(pick.getKey()) ? " *" : ""), x0 + 12, y + 2, -13753320);
/*  65 */         y += ROW; }
/*     */     
/*     */     } 
/*  68 */     y = rows(c, this.other, x0, x1, y);
/*  69 */     endScroll(c, y + 6);
/*     */     
/*  71 */     c.fill(x0, bottom, x1, bottom + 2, -2569296);
/*  72 */     int by = bottom + 6;
/*  73 */     if (this.changes.isEmpty()) {
/*  74 */       class_5250 class_5250 = class_2561.method_43471("gui.done");
/*  75 */       button(c, x1 - 6 - buttonWidth((class_2561)class_5250), by, (class_2561)class_5250, this::method_25419);
/*     */       return;
/*     */     } 
/*  78 */     class_5250 class_52501 = class_2561.method_43471("zakoopt.bench.keep");
/*  79 */     int kx = x1 - 6 - buttonWidth((class_2561)class_52501);
/*  80 */     button(c, kx, by, (class_2561)class_52501, this::method_25419);
/*  81 */     class_5250 class_52502 = class_2561.method_43471("zakoopt.bench.apply");
/*  82 */     int aw = buttonWidth((class_2561)class_52502);
/*  83 */     button(c, kx - 6 - aw, by, aw, (class_2561)class_52502, -9783190, -12940742, true, () -> {
/*     */           this.apply.run();
/*     */           method_25419();
/*     */         });
/*     */   }
/*     */   
/*     */   private String unit(String key) {
/*  90 */     return ZakoOptions.GROUPS.stream().flatMap(g -> g.available().stream()).filter(o -> (o.key().equals(key) && o instanceof ZakoOptions.Distance))
/*  91 */       .map(o -> ((ZakoOptions.Distance)o).unit()).findFirst().orElse("zakoopt.blocks");
/*     */   }
/*     */   
/*     */   private int header(Canvas c, class_2561 text, int x0, int x1, int y) {
/*  95 */     c.text(text, x0 + 8, y + 2, -15068148);
/*  96 */     c.fill(x0 + 8, y + 12, x1 - 8, y + 13, -2569296);
/*  97 */     return y + 16;
/*     */   }
/*     */   
/*     */   private int rows(Canvas c, List<ZakoOptions.Toggle> toggles, int x0, int x1, int y) {
/* 101 */     int column = (x1 - x0 - 16) / 2;
/* 102 */     for (int i = 0; i < toggles.size(); i++) {
/* 103 */       ZakoOptions.Toggle t = toggles.get(i);
/* 104 */       int x = x0 + 12 + i % 2 * column;
/* 105 */       Object object = this.picks.get(t.key()); Boolean b = (Boolean)object; boolean on = this.chosen.containsKey(t.key()) ? ((Boolean)this.chosen.get(t.key())).booleanValue() : ((object instanceof Boolean) ? b.booleanValue() : ((Boolean)t.get().get()).booleanValue());
/* 106 */       toggle(c, x, y + 2, on, false);
/* 107 */       class_5250 class_5250 = class_2561.method_43471("zakoopt.option." + t.key()).method_27693(this.changes.contains(t.key()) ? " *" : "");
/* 108 */       c.text(firstLine((class_2561)class_5250, column - 30), x + 24, y + 2, on ? -13753320 : -8754606);
/* 109 */       if (i % 2 == 1 || i == toggles.size() - 1) {
/* 110 */         y += ROW;
/*     */       }
/*     */     } 
/* 113 */     return y;
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 118 */     UpdateUi.setScreen(this.parent);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\ResultScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */