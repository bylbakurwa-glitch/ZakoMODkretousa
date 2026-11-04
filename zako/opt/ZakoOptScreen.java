/*     */ package zako.opt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.class_2477;
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_437;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_5481;
/*     */ import zako.opt.bench.Bench;
/*     */ import zako.opt.bench.MonitorScreen;
/*     */ import zako.opt.feature.Feature;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.update.UpdateUi;
/*     */ 
/*     */ 
/*     */ public class ZakoOptScreen
/*     */   extends Panel
/*     */ {
/*  22 */   static int BAR = 22; static int ROW = 16; static int PAD = 6; static int FIELD = -659745; static int FIELD_FOCUSED = -132365;
/*     */   
/*     */   private final class_437 parent;
/*  25 */   private final List<Section> sections = new ArrayList<>();
/*     */ 
/*     */   
/*     */   private int selected;
/*     */   
/*     */   private class_2561 settingHint;
/*     */   
/*     */   private class_2561 visualHint;
/*     */   
/*  34 */   private String query = ""; private boolean focused; private int fieldX0;
/*     */   private int fieldX1;
/*     */   private int fieldY;
/*     */   private final int[] heads;
/*     */   
/*     */   private static final class Item extends Record { private final Feature feature;
/*     */     private final ZakoOptions.Toggle toggle;
/*     */     private final List<ZakoOptions.Option> settings;
/*     */     
/*  43 */     private Item(Feature feature, ZakoOptions.Toggle toggle, List<ZakoOptions.Option> settings) { this.feature = feature; this.toggle = toggle; this.settings = settings; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptScreen$Item;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  43 */       //   0	7	0	this	Lzako/opt/ZakoOptScreen$Item; } public Feature feature() { return this.feature; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptScreen$Item;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/ZakoOptScreen$Item; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptScreen$Item;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #43	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/ZakoOptScreen$Item;
/*  43 */       //   0	8	1	o	Ljava/lang/Object; } public ZakoOptions.Toggle toggle() { return this.toggle; } public List<ZakoOptions.Option> settings() { return this.settings; }
/*     */      }
/*     */   private static final class Section extends Record { private final ZakoOptions.Group group; private final List<ZakoOptScreen.Item> items;
/*  46 */     private Section(ZakoOptions.Group group, List<ZakoOptScreen.Item> items) { this.group = group; this.items = items; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptScreen$Section;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #46	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/ZakoOptScreen$Section; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptScreen$Section;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #46	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/ZakoOptScreen$Section; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptScreen$Section;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #46	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/ZakoOptScreen$Section;
/*  46 */       //   0	8	1	o	Ljava/lang/Object; } public ZakoOptions.Group group() { return this.group; } public List<ZakoOptScreen.Item> items() { return this.items; }
/*     */      }
/*     */   
/*     */   public ZakoOptScreen(class_437 parent) {
/*  50 */     super((class_2561)class_2561.method_43470("ZakoOpt"));
/*  51 */     this.parent = parent;
/*  52 */     for (ZakoOptions.Group group : ZakoOptions.GROUPS) {
/*  53 */       List<Item> items = new ArrayList<>();
/*  54 */       for (ZakoOptions.Option option : group.available()) {
/*  55 */         Feature feature = Features.byKey(option.key());
/*  56 */         if (feature != null) {
/*  57 */           items.add(new Item(feature, (ZakoOptions.Toggle)option, new ArrayList<>())); continue;
/*  58 */         }  if (!items.isEmpty()) {
/*  59 */           ((Item)items.getLast()).settings().add(option);
/*     */         }
/*     */       } 
/*  62 */       if (!items.isEmpty()) {
/*  63 */         this.sections.add(new Section(group, items));
/*     */       }
/*     */     } 
/*  66 */     this.heads = new int[this.sections.size()];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void changed() {
/*  71 */     ZakoOptConfig.save();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(Canvas c) {
/*  76 */     this.settingHint = null;
/*  77 */     int w = Math.min(this.field_22789 - 2 * PAD, 520), x0 = (this.field_22789 - w) / 2, x1 = x0 + w, y0 = PAD, y1 = this.field_22790 - PAD;
/*  78 */     frame(c, x0, y0, x1, y1);
/*  79 */     this.visualHint = null;
/*     */     
/*  81 */     c.text((class_2561)class_2561.method_43470("ZakoOpt"), x0 + 8, y0 + 7, -15068148);
/*  82 */     int vx = x0 + 8 + this.field_22793.method_1727("ZakoOpt") + 5;
/*  83 */     c.text((class_2561)class_2561.method_43470(ZakoOptions.VERSION), vx, y0 + 7, -6254470);
/*     */     
/*  85 */     boolean inWorld = (this.field_22787.field_1687 != null);
/*  86 */     boolean benchOk = (!inWorld && !Bench.running());
/*  87 */     class_5250 class_52501 = class_2561.method_43471("zakoopt.bench.button");
/*  88 */     int bx = x1 - 6 - buttonWidth((class_2561)class_52501);
/*  89 */     boolean overBench = button(c, bx, y0 + 4, buttonWidth((class_2561)class_52501), (class_2561)class_52501, -1911368, -5730192, benchOk, () -> Bench.confirm(this));
/*  90 */     class_5250 class_52502 = class_2561.method_43471(inWorld ? "zakoopt.monitor.button" : "zakoopt.bench.compare.button");
/*  91 */     bx -= 6 + buttonWidth((class_2561)class_52502);
/*  92 */     boolean overCompare = button(c, bx, y0 + 4, buttonWidth((class_2561)class_52502), (class_2561)class_52502, -1911368, -5730192, !Bench.running(), () -> {
/*     */           if (inWorld) {
/*     */             UpdateUi.setScreen((class_437)new MonitorScreen());
/*     */           } else {
/*     */             Bench.compare(this);
/*     */           } 
/*     */         });
/*     */     
/* 100 */     class_5250 class_52503 = class_2561.method_43471(ZakoOptConfig.enabled ? "zakoopt.screen.on" : "zakoopt.screen.off");
/* 101 */     int masterWidth = Math.max(buttonWidth((class_2561)class_52503), buttonWidth((class_2561)class_2561.method_43471("zakoopt.screen.on")));
/* 102 */     int masterX = bx - 6 - masterWidth;
/* 103 */     boolean overMaster = button(c, masterX, y0 + 4, masterWidth, (class_2561)class_52503, ZakoOptConfig.enabled ? -9783190 : -2569296, ZakoOptConfig.enabled ? -12940742 : -7701920, true, () -> ZakoOptConfig.enabled = !ZakoOptConfig.enabled);
/*     */     
/* 105 */     searchField(c, vx + this.field_22793.method_1727(ZakoOptions.VERSION) + 8, masterX - 6, y0 + 4);
/* 106 */     c.fill(x0, y0 + BAR, x1, y0 + BAR + 2, -2569296);
/*     */     
/* 108 */     String needle = norm(this.query.strip());
/* 109 */     boolean searching = !needle.isEmpty();
/* 110 */     List<List<Item>> found = new ArrayList<>();
/* 111 */     int total = 0;
/* 112 */     for (Section s : this.sections) {
/* 113 */       List<Item> items = searching ? s.items().stream().filter(it -> matches(it, needle)).toList() : s.items();
/* 114 */       found.add(items);
/* 115 */       total += items.size();
/*     */     } 
/*     */     
/* 118 */     int side = 0;
/* 119 */     for (Section s : this.sections) {
/* 120 */       side = Math.max(side, this.field_22793.method_27525((class_5348)s.group().type().title()) + this.field_22793.method_1727("" + s.items().size() + "/" + s.items().size()) + 22);
/*     */     }
/* 122 */     int top = y0 + BAR + 2, bottom = y1 - BAR - 2, sx1 = x0 + Math.min(side, w / 3);
/* 123 */     c.fill(sx1, top, sx1 + 2, bottom, -2569296);
/* 124 */     for (int i = 0; i < this.sections.size(); i++) {
/* 125 */       Section s = this.sections.get(i);
/* 126 */       int ry = top + 4 + i * ROW;
/* 127 */       boolean empty = (searching && ((List)found.get(i)).isEmpty());
/* 128 */       boolean over = (!empty && over(x0, ry, sx1, ry + ROW));
/* 129 */       boolean current = (!searching && i == this.selected);
/* 130 */       if (current) {
/* 131 */         c.fill(x0, ry, sx1, ry + ROW, -1911368);
/* 132 */         c.fill(x0, ry, x0 + 2, ry + ROW, -12940742);
/* 133 */       } else if (over) {
/* 134 */         c.fill(x0, ry, sx1, ry + ROW, -1647419);
/*     */       } 
/* 136 */       c.text(s.group().type().title(), x0 + 8, ry + 4, empty ? -3556450 : (current ? -15068148 : (over ? -13753320 : -8754606)));
/*     */       
/* 138 */       String count = searching ? String.valueOf(((List)found.get(i)).size()) : ("" + s.items().stream().filter(it -> ((it.feature()).value && it.feature().blocked() == null)).count() + "/" + s.items().stream().filter(it -> ((it.feature()).value && it.feature().blocked() == null)).count());
/* 139 */       c.text((class_2561)class_2561.method_43470(count), sx1 - 6 - this.field_22793.method_1727(count), ry + 4, empty ? -3556450 : (searching ? -6661616 : -8754606));
/* 140 */       int index = i;
/* 141 */       if (searching) {
/* 142 */         if (!empty) {
/* 143 */           hit(x0, ry, sx1, ry + ROW, () -> scrollTo(this.heads[index]), (ZakoOptions.Distance)null);
/*     */         }
/*     */       } else {
/* 146 */         hit(x0, ry, sx1, ry + ROW, () -> { this.selected = index; resetScroll(); }(ZakoOptions.Distance)null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     int start = beginScroll(c, sx1 + 2, top, x1, bottom), y = start;
/* 154 */     if (!searching) {
/* 155 */       for (Item item : ((Section)this.sections.get(this.selected)).items()) {
/* 156 */         y = item(c, item, sx1 + 2, x1, y, top, bottom, needle);
/*     */       }
/*     */     } else {
/* 159 */       for (int j = 0; j < this.sections.size(); j++) {
/* 160 */         List<Item> items = found.get(j);
/* 161 */         if (!items.isEmpty()) {
/*     */ 
/*     */           
/* 164 */           this.heads[j] = y - start;
/* 165 */           c.text((class_2561)class_2561.method_43473().method_10852(((Section)this.sections.get(j)).group().type().title()).method_27693(" · " + items.size()), sx1 + 10, y + 5, -8754606);
/* 166 */           c.fill(sx1 + 2, y + 15, x1, y + 17, -2569296);
/* 167 */           y += 17;
/* 168 */           for (Item item : items)
/* 169 */             y = item(c, item, sx1 + 2, x1, y, top, bottom, needle); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 173 */     endScroll(c, y);
/* 174 */     if (searching && total == 0) {
/* 175 */       int cx = (sx1 + 2 + x1) / 2, cy = (top + bottom) / 2 - 10;
/* 176 */       class_5250 class_52506 = class_2561.method_43469("zakoopt.search.none", new Object[] { this.query.strip() });
/* 177 */       c.text((class_2561)class_52506, cx - this.field_22793.method_27525((class_5348)class_52506) / 2, cy, -15068148);
/* 178 */       class_5250 class_52507 = class_2561.method_43471("zakoopt.search.none.hint");
/* 179 */       c.text((class_2561)class_52507, cx - this.field_22793.method_27525((class_5348)class_52507) / 2, cy + 12, -8754606);
/*     */     } 
/*     */     
/* 182 */     c.fill(x0, bottom, x1, bottom + 2, -2569296);
/* 183 */     int by = bottom + 6;
/* 184 */     class_5250 class_52504 = class_2561.method_43471("gui.done");
/* 185 */     int dx = x1 - 6 - buttonWidth((class_2561)class_52504);
/* 186 */     button(c, dx, by, (class_2561)class_52504, this::method_25419);
/* 187 */     class_5250 class_52505 = class_2561.method_43471("zakoopt.screen.defaults");
/* 188 */     int defaultsX = dx - 6 - buttonWidth((class_2561)class_52505);
/* 189 */     button(c, defaultsX, by, (class_2561)class_52505, this::confirmDefaults);
/*     */ 
/*     */ 
/*     */     
/* 193 */     class_2561 hint = (this.settingHint != null) ? this.settingHint : ((this.visualHint != null) ? this.visualHint : (overMaster ? (class_2561)class_2561.method_43471("zakoopt.option.enabled.tooltip") : (overCompare ? (class_2561)class_2561.method_43469(Bench.running() ? "zakoopt.bench.running" : (inWorld ? "zakoopt.monitor.tooltip" : "zakoopt.bench.compare.button.tooltip"), new Object[] { ZakoKey.MONITOR.method_16007() }) : (overBench ? (class_2561)class_2561.method_43471(benchOk ? "zakoopt.bench.button.tooltip" : (inWorld ? "zakoopt.bench.button.in_world" : "zakoopt.bench.running")) : (searching ? (class_2561)class_2561.method_43471("zakoopt.search.esc") : null)))));
/* 194 */     if (hint != null) {
/* 195 */       List<class_5481> lines = this.field_22793.method_1728((class_5348)hint, defaultsX - x0 - 16);
/* 196 */       int hy = (lines.size() > 1) ? (bottom + 4) : (bottom + 9);
/* 197 */       for (class_5481 line : lines.subList(0, Math.min(2, lines.size()))) {
/* 198 */         c.text(line, x0 + 8, hy, -8754606);
/* 199 */         hy += 9;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int item(Canvas c, Item item, int x0, int x1, int y, int top, int bottom, String needle) {
/* 205 */     Feature f = item.feature();
/* 206 */     class_2561 blocked = Blocked.reason(f.key());
/* 207 */     boolean on = (f.value && blocked == null);
/* 208 */     class_2561 text = (blocked != null) ? blocked : (class_2561)class_2561.method_43471("zakoopt.option." + f.key() + ".tooltip");
/* 209 */     List<ZakoOptions.Option> settings = on ? item.settings() : List.<ZakoOptions.Option>of();
/* 210 */     class_5250 class_5250 = class_2561.method_43471("zakoopt.option." + f.key());
/* 211 */     int height = 16 + lineCount(text, x1 - x0 - 48) * 10 + settings.size() * 14 + 5;
/* 212 */     if (y + height <= top || y >= bottom) {
/* 213 */       return y + height;
/*     */     }
/* 215 */     if (over(x0, y, x1, y + height)) {
/* 216 */       c.fill(x0, y, x1, y + height, -1647419);
/*     */     }
/* 218 */     toggle(c, x0 + 10, y + 5, on, (blocked != null));
/* 219 */     if (!needle.isEmpty()) {
/* 220 */       mark(c, text("zakoopt.option." + f.key()), needle, x0 + 34, y + 3);
/*     */     }
/* 222 */     c.text((class_2561)class_5250, x0 + 34, y + 4, (blocked != null) ? -6254470 : -15068148);
/* 223 */     if (f.data().visual()) {
/* 224 */       int ex = x1 - 10 - EYE[0].length(), ey = y + 5;
/* 225 */       pixels(c, EYE, ex, ey, (blocked != null) ? -6254470 : -6661616);
/* 226 */       if (over(ex - 2, ey - 2, ex + EYE[0].length() + 2, ey + EYE.length + 2)) {
/* 227 */         this.visualHint = (class_2561)class_2561.method_43471("zakoopt.visual." + f.key());
/*     */       }
/*     */     } 
/* 230 */     int ly = lines(c, text, x0 + 34, y + 16, x1 - x0 - 48, (blocked != null) ? -5222358 : -8754606);
/*     */     
/* 232 */     if (blocked == null) {
/* 233 */       hit(x0, y, x1, ly, () -> f.value = !f.value, (ZakoOptions.Distance)null);
/*     */     }
/* 235 */     for (ZakoOptions.Option s : settings) {
/* 236 */       String tooltip = "zakoopt.option." + s.key() + ".tooltip";
/* 237 */       if (setting(c, s, x0 + 34, x1 - 12, ly + 2) && class_2477.method_10517().method_4678(tooltip)) {
/* 238 */         this.settingHint = (class_2561)class_2561.method_43471(tooltip);
/*     */       }
/* 240 */       ly += 14;
/*     */     } 
/* 242 */     c.fill(x0 + 6, y + height - 1, x1 - 6, y + height, -2569296);
/* 243 */     return y + height;
/*     */   }
/*     */   
/*     */   private void searchField(Canvas c, int left, int right, int y) {
/* 247 */     int w = Math.min(120, right - left);
/* 248 */     this.fieldX0 = right - w;
/* 249 */     this.fieldX1 = (w < 40) ? this.fieldX0 : right;
/* 250 */     this.fieldY = y;
/* 251 */     if (w < 40) {
/*     */       return;
/*     */     }
/* 254 */     int x = this.fieldX0, tx = x + 6 + LENS[0].length();
/* 255 */     c.fill(x, y, right, y + 14, this.focused ? -15068148 : (over(x, y, right, y + 14) ? -10859984 : -5730192));
/* 256 */     c.fill(x + 1, y + 1, right - 1, y + 14 - 1, this.focused ? FIELD_FOCUSED : FIELD);
/* 257 */     pixels(c, LENS, x + 4, y + 3, -8754606);
/* 258 */     String shown = this.query;
/* 259 */     while (this.field_22793.method_1727(shown) > right - tx - 4) {
/* 260 */       shown = shown.substring(1);
/*     */     }
/* 262 */     int cx = tx + this.field_22793.method_1727(shown);
/* 263 */     if (this.query.isEmpty()) {
/* 264 */       class_5250 class_5250 = class_2561.method_43471("zakoopt.search.focused");
/* 265 */       if (!this.focused || this.field_22793.method_27525((class_5348)class_5250) > right - tx - 6) {
/* 266 */         class_5250 = class_2561.method_43471("zakoopt.search");
/*     */       }
/* 268 */       c.text((class_2561)class_5250, tx + (this.focused ? 2 : 0), y + 3, -6254470);
/*     */     } else {
/* 270 */       c.text((class_2561)class_2561.method_43470(shown), tx, y + 3, -15068148);
/*     */     } 
/* 272 */     if (this.focused && System.currentTimeMillis() / 500L % 2L == 0L) {
/* 273 */       c.fill(cx, y + 2, cx + 1, y + 12, -15068148);
/*     */     }
/*     */   }
/*     */   
/*     */   private static String text(String key) {
/* 278 */     return class_2477.method_10517().method_48307(key);
/*     */   }
/*     */   
/*     */   private static String norm(String s) {
/* 282 */     return s.toLowerCase(Locale.ROOT).replace('ё', 'е');
/*     */   }
/*     */   
/*     */   private static boolean matches(Item item, String needle) {
/* 286 */     String key = item.feature().key();
/* 287 */     if (norm(text("zakoopt.option." + key)).contains(needle) || norm(text("zakoopt.option." + key + ".tooltip")).contains(needle) || (item
/* 288 */       .feature().data().visual() && norm(text("zakoopt.visual." + key)).contains(needle))) {
/* 289 */       return true;
/*     */     }
/* 291 */     for (ZakoOptions.Option s : item.settings()) {
/* 292 */       if (norm(text("zakoopt.option." + s.key())).contains(needle)) {
/* 293 */         return true;
/*     */       }
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */   
/*     */   private void mark(Canvas c, String text, String needle, int x, int y) {
/* 300 */     String n = norm(text);
/*     */     
/* 302 */     if (n.length() != text.length())
/*     */       return; 
/*     */     int i;
/* 305 */     for (i = n.indexOf(needle); i >= 0; i = n.indexOf(needle, i + needle.length())) {
/* 306 */       int hx = x + this.field_22793.method_1727(text.substring(0, i));
/* 307 */       c.fill(hx - 1, y, hx + this.field_22793.method_1727(text.substring(i, i + needle.length())), y + 10, -866709);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean click(double x, double y, int button) {
/* 313 */     this.focused = (button == 0 && x >= this.fieldX0 && x < this.fieldX1 && y >= this.fieldY && y < (this.fieldY + 14));
/* 314 */     return (super.click(x, y, button) || this.focused);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean escape() {
/* 319 */     if (this.query.isEmpty()) {
/* 320 */       if (!this.focused) {
/* 321 */         return false;
/*     */       }
/* 323 */       this.focused = false;
/* 324 */       return true;
/*     */     } 
/* 326 */     search("");
/* 327 */     this.focused = false;
/* 328 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean erase(boolean all) {
/* 333 */     if (this.query.isEmpty()) {
/* 334 */       return false;
/*     */     }
/* 336 */     search(all ? "" : this.query.substring(0, this.query.length() - 1));
/* 337 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean typed(int codepoint) {
/* 342 */     if (Character.isISOControl(codepoint) || (codepoint == 32 && this.query.isEmpty())) {
/* 343 */       return false;
/*     */     }
/* 345 */     if (this.query.length() < 64) {
/* 346 */       search(this.query + this.query);
/*     */     }
/* 348 */     return true;
/*     */   }
/*     */   
/*     */   private void search(String query) {
/* 352 */     this.query = query;
/* 353 */     this.focused = true;
/* 354 */     resetScroll();
/*     */   }
/*     */   
/*     */   private void confirmDefaults() {
/* 358 */     UpdateUi.setScreen(new ConfirmPanel(yes -> {
/*     */             if (yes) {
/*     */               Features.restore(Features.defaults());
/*     */               changed();
/*     */             } 
/*     */             UpdateUi.setScreen(this);
/* 364 */           }(class_2561)class_2561.method_43471("zakoopt.screen.defaults.title"), (class_2561)class_2561.method_43471("zakoopt.screen.defaults.confirm"), 
/* 365 */           (class_2561)class_2561.method_43471("zakoopt.screen.defaults.yes"), (class_2561)class_2561.method_43471("gui.cancel")));
/*     */   }
/*     */ 
/*     */   
/* 369 */   private static final String[] EYE = new String[] { "....###....", "..##...##..", ".#..###..#.", "#..#####..#", ".#..###..#.", "..##...##..", "....###...." };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   private static final String[] LENS = new String[] { "..####...", ".#....#..", "#......#.", "#......#.", "#......#.", "#......#.", ".#....#..", "..####.#.", "........#" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void pixels(Canvas c, String[] image, int x, int y, int color) {
/* 392 */     for (int row = 0; row < image.length; row++) {
/* 393 */       String line = image[row];
/* 394 */       for (int i = 0; i < line.length(); i++) {
/* 395 */         if (line.charAt(i) == '#') {
/*     */ 
/*     */           
/* 398 */           int start = i;
/* 399 */           while (i < line.length() && line.charAt(i) == '#') {
/* 400 */             i++;
/*     */           }
/* 402 */           c.fill(x + start, y + row, x + i, y + row + 1, color);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void method_25419() {
/* 409 */     UpdateUi.setScreen(this.parent);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */