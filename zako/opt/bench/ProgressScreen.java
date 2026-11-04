/*     */ package zako.opt.bench;
/*     */ 
/*     */ import net.minecraft.class_2561;
/*     */ import net.minecraft.class_5250;
/*     */ import net.minecraft.class_5348;
/*     */ import net.minecraft.class_5481;
/*     */ import zako.opt.Canvas;
/*     */ import zako.opt.DebugToggle;
/*     */ import zako.opt.Panel;
/*     */ import zako.opt.ZakoOptConfig;
/*     */ import zako.opt.gl.GpuTimers;
/*     */ 
/*     */ public class ProgressScreen
/*     */   extends Panel {
/*     */   static final int WIDTH = 300;
/*     */   static final int FRAMES = 24;
/*     */   static final int MEMORY = 16;
/*  18 */   private static final int[] FRAME_COLORS = new int[] { -12940742, -3110374, -5222358 }; static final int TRACK = -2964314; static final float LEGEND = 0.75F; private static final long LOAD_NS = 500000000L;
/*  19 */   private static final class_2561[] FRAME_LEGEND = new class_2561[] { (class_2561)class_2561.method_43471("zakoopt.bench.legend.smooth"), 
/*  20 */       (class_2561)class_2561.method_43471("zakoopt.bench.legend.stutter"), (class_2561)class_2561.method_43471("zakoopt.bench.legend.big_stutter") };
/*  21 */   private static final int[] MEMORY_COLORS = new int[] { -13997408, -5728128, -3900902 };
/*  22 */   private static final class_2561[] MEMORY_LEGEND = new class_2561[] { (class_2561)class_2561.method_43471("zakoopt.bench.legend.heap"), 
/*  23 */       (class_2561)class_2561.method_43471("zakoopt.bench.legend.gc"), (class_2561)class_2561.method_43471("zakoopt.bench.legend.native") };
/*  24 */   private static final class_2561 CPU = (class_2561)class_2561.method_43471("zakoopt.bench.cpu"), GPU = (class_2561)class_2561.method_43471("zakoopt.bench.gpu");
/*  25 */   private static final class_2561 MEMORY_TITLE = (class_2561)class_2561.method_43471("zakoopt.bench.memory.title");
/*  26 */   private static final class_2561 NONE = (class_2561)class_2561.method_43470("—"); private static long lastLoad;
/*     */   private static long painted;
/*  28 */   private static double cpu = -1.0D; private static double gpu = -1.0D;
/*  29 */   private static class_2561 cpuText = NONE, gpuText = NONE;
/*     */   
/*     */   public ProgressScreen() {
/*  32 */     super((class_2561)class_2561.method_43471("zakoopt.bench.title"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean open() {
/*  37 */     return (System.nanoTime() - painted < 200000000L);
/*     */   }
/*     */   
/*     */   protected class_2561 stage() {
/*  41 */     return Bench.status().stage();
/*     */   }
/*     */   
/*     */   protected class_2561 note() {
/*  45 */     return Bench.status().note();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int percent() {
/*  50 */     return Bench.status().percent();
/*     */   }
/*     */   
/*     */   protected class_2561 hint() {
/*  54 */     return (class_2561)class_2561.method_43471("zakoopt.bench.hands");
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean overScene() {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(Canvas c) {
/*  64 */     painted = System.nanoTime();
/*     */     
/*  66 */     if (DebugToggle.visible()) {
/*     */       return;
/*     */     }
/*  69 */     load();
/*  70 */     int w = Math.min(300, this.field_22789 - 16), inner = w - 16;
/*  71 */     int frameRows = legendRows(FRAME_LEGEND, inner), memoryRows = legendRows(MEMORY_LEGEND, inner);
/*  72 */     int h = 153 + 8 * (frameRows + memoryRows);
/*     */     
/*  74 */     int x = 8, y = Math.max(8, Math.min(this.field_22790 / 2 - h / 2, this.field_22790 - 78 - h)), l = x + 8;
/*  75 */     frame(c, x, y, x + w, y + h);
/*     */     
/*  77 */     class_2561 note = note();
/*  78 */     int noteWidth = this.field_22793.method_27525((class_5348)note);
/*  79 */     class_5481 stage = firstLine(stage(), (noteWidth > 0) ? (inner - noteWidth - 6) : inner);
/*  80 */     c.text(stage, l, y + 6, -15068148);
/*  81 */     if (noteWidth > 0) {
/*  82 */       c.text(note, l + this.field_22793.method_30880(stage) + 6, y + 6, -8754606);
/*     */     }
/*  84 */     if (percent() >= 0) {
/*  85 */       c.fill(l, y + 18, l + inner, y + 22, -3556450);
/*  86 */       c.fill(l, y + 18, l + inner * Math.min(percent(), 100) / 100, y + 22, -12940742);
/*     */     } 
/*     */     
/*  89 */     class_2561 fps = FrameGraph.fps();
/*  90 */     c.text(fps, l, y + 26, -15068148, 2.0F);
/*  91 */     c.text(FrameGraph.frame(), l + 2 * this.field_22793.method_27525((class_5348)fps) + 6, y + 33, -8754606);
/*  92 */     int row = y + 46 + 24;
/*  93 */     FrameGraph.draw(c, l, row, inner, 24);
/*  94 */     row = legend(c, l, row + 3, inner, FRAME_LEGEND, FRAME_COLORS) + 1;
/*     */     
/*  96 */     int half = (inner - 10) / 2;
/*  97 */     load(c, l, row, half, CPU, cpu, cpuText);
/*  98 */     load(c, l + inner - half, row, half, GPU, gpu, gpuText);
/*  99 */     row += 11;
/*     */     
/* 101 */     int titleWidth = this.field_22793.method_27525((class_5348)MEMORY_TITLE);
/* 102 */     c.text(MEMORY_TITLE, l, row, -8754606);
/* 103 */     c.fill(l + titleWidth + 4, row + 4, l + inner, row + 5, -2569296);
/* 104 */     row += 28;
/* 105 */     MemoryGraph.draw(c, l, row, inner, 16);
/* 106 */     row = legend(c, l, row + 3, inner, MEMORY_LEGEND, MEMORY_COLORS) + 1;
/* 107 */     c.text(MemoryGraph.label(), l, row, -13753320);
/* 108 */     c.text(MemoryGraph.garbage(), l, row + 10, -8754606);
/* 109 */     c.text(firstLine(hint(), inner), l, row + 21, -6254470);
/*     */   }
/*     */   
/*     */   private static void load() {
/* 113 */     long now = System.nanoTime();
/* 114 */     if (now - lastLoad < 500000000L) {
/*     */       return;
/*     */     }
/* 117 */     lastLoad = now;
/*     */     
/* 119 */     cpu = (MemoryGraph.OS == null) ? -1.0D : (MemoryGraph.OS.getProcessCpuLoad() * 100.0D);
/* 120 */     gpu = GpuTimers.utilization();
/* 121 */     cpuText = percentText(cpu);
/* 122 */     gpuText = percentText(gpu);
/*     */   }
/*     */   
/*     */   private static class_2561 percentText(double value) {
/* 126 */     return (value < 0.0D) ? NONE : (class_2561)class_2561.method_43470("" + Math.round(Math.min(value, 100.0D)) + "%");
/*     */   }
/*     */   
/*     */   private void load(Canvas c, int x, int y, int width, class_2561 name, double value, class_2561 text) {
/* 130 */     int bar = x + this.field_22793.method_27525((class_5348)name) + 4, end = x + width - this.field_22793.method_1727("100%") - 4;
/* 131 */     c.text(name, x, y, -13753320);
/* 132 */     c.fill(bar, y + 1, end, y + 6, -2964314);
/* 133 */     if (value > 0.0D) {
/* 134 */       c.fill(bar, y + 1, bar + (int)((end - bar) * Math.min(value, 100.0D) / 100.0D), y + 6, -12940742);
/*     */     }
/* 136 */     c.text(text, x + width - this.field_22793.method_27525((class_5348)text), y, -13753320);
/*     */   }
/*     */   
/*     */   private int legendRows(class_2561[] names, int width) {
/* 140 */     int rows = 1, at = 0;
/* 141 */     for (class_2561 name : names) {
/* 142 */       int item = 6 + (int)Math.ceil((this.field_22793.method_27525((class_5348)name) * 0.75F));
/* 143 */       if (at > 0 && at + item > width) {
/* 144 */         rows++;
/* 145 */         at = 0;
/*     */       } 
/* 147 */       at += item + 6;
/*     */     } 
/* 149 */     return rows;
/*     */   }
/*     */ 
/*     */   
/*     */   private int legend(Canvas c, int x, int y, int width, class_2561[] names, int[] colors) {
/* 154 */     int at = 0;
/* 155 */     for (int i = 0; i < names.length; i++) {
/* 156 */       int item = 6 + (int)Math.ceil((this.field_22793.method_27525((class_5348)names[i]) * 0.75F));
/* 157 */       if (at > 0 && at + item > width) {
/* 158 */         y += 8;
/* 159 */         at = 0;
/*     */       } 
/* 161 */       c.fill(x + at, y + 1, x + at + 4, y + 5, colors[i]);
/* 162 */       c.text(names[i], x + at + 6, y, -8754606, 0.75F);
/* 163 */       at += item + 6;
/*     */     } 
/* 165 */     return y + 8;
/*     */   }
/*     */   
/*     */   protected void modToggle(Canvas c, int x, int y, int width) {
/* 169 */     boolean on = ZakoOptConfig.enabled;
/* 170 */     class_5250 class_5250 = class_2561.method_43471(on ? "zakoopt.bench.compare.toggle.on" : "zakoopt.bench.compare.toggle.off");
/* 171 */     button(c, x, y, width, (class_2561)class_5250, on ? -9783190 : -2569296, on ? -12940742 : -7701920, true, () -> ZakoOptConfig.enabled = !ZakoOptConfig.enabled);
/*     */   }
/*     */   
/*     */   protected void closed() {
/* 175 */     Bench.cancel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void method_25419() {
/* 180 */     closed();
/* 181 */     super.method_25419();
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\ProgressScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */