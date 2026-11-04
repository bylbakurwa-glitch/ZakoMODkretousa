/*     */ package zako.opt;public abstract class Panel extends PanelScreen { public static final int PANEL = -168827182; public static final int FRAME = -7703728; public static final int LINE = -2569296; public static final int HOVER = -1647419; public static final int SELECTED = -1911368; public static final int TEXT = -13753320;
/*     */   public static final int STRONG = -15068148;
/*     */   public static final int MUTED = -8754606;
/*     */   public static final int DIM = -6254470;
/*     */   public static final int BLOCKED = -5222358;
/*     */   public static final int LINK = -13997408;
/*     */   public static final int ACCENT = -12940742;
/*     */   public static final int ON = -9783190;
/*     */   public static final int KNOB_ON = -1;
/*     */   public static final int OFF = -3556450;
/*     */   public static final int KNOB_OFF = -6649232;
/*     */   public static final int PIP = -3900902;
/*     */   public static final int VISUAL = -6661616;
/*     */   public static final int MARK = -866709;
/*     */   public static final int MASTER_OFF = -2569296;
/*     */   public static final int MASTER_OFF_FRAME = -7701920;
/*     */   public static final int BUTTON = -1911368;
/*     */   public static final int BUTTON_FRAME = -5730192;
/*     */   public static final int BUTTON_HOVER = -10859984;
/*     */   protected static final int BUTTON_HEIGHT = 14;
/*  21 */   private final List<Hit> hits = new ArrayList<>(); protected int mx; protected int my; private Hit dragging; private int clipTop; private int clipBottom; private double scroll; private int contentHeight; private int scrollTop; private int scrollBottom; private int scrollX1;
/*     */   private static final class Hit extends Record { private final int x0;
/*     */     private final int y0;
/*     */     private final int x1;
/*     */     private final int y1;
/*     */     private final Runnable click;
/*     */     private final ZakoOptions.Distance slider;
/*     */     
/*  29 */     private Hit(int x0, int y0, int x1, int y1, Runnable click, ZakoOptions.Distance slider) { this.x0 = x0; this.y0 = y0; this.x1 = x1; this.y1 = y1; this.click = click; this.slider = slider; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/Panel$Hit;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  29 */       //   0	7	0	this	Lzako/opt/Panel$Hit; } public int x0() { return this.x0; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/Panel$Hit;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/Panel$Hit; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/Panel$Hit;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #29	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/Panel$Hit;
/*  29 */       //   0	8	1	o	Ljava/lang/Object; } public int y0() { return this.y0; } public int x1() { return this.x1; } public int y1() { return this.y1; } public Runnable click() { return this.click; } public ZakoOptions.Distance slider() { return this.slider; }
/*     */      boolean contains(double x, double y) {
/*  31 */       return (x >= this.x0 && x < this.x1 && y >= this.y0 && y < this.y1);
/*     */     } }
/*     */ 
/*     */   
/*     */   protected Panel(class_2561 title) {
/*  36 */     super(title);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void changed() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void draw(Canvas c, int mouseX, int mouseY) {
/*  46 */     this.hits.clear();
/*  47 */     this.clipTop = 0;
/*  48 */     this.clipBottom = this.field_22790;
/*  49 */     this.mx = mouseX;
/*  50 */     this.my = mouseY;
/*  51 */     paint(c);
/*     */   }
/*     */   
/*     */   protected boolean over(int x0, int y0, int x1, int y1) {
/*  55 */     return (this.mx >= x0 && this.mx < x1 && this.my >= y0 && this.my < y1 && this.my >= this.clipTop && this.my < this.clipBottom);
/*     */   }
/*     */   
/*     */   protected void frame(Canvas c, int x0, int y0, int x1, int y1) {
/*  59 */     c.fill(x0 - 2, y0 - 2, x1 + 2, y1 + 2, -7703728);
/*  60 */     c.fill(x0, y0, x1, y1, -168827182);
/*     */   }
/*     */   
/*     */   protected int lines(Canvas c, class_2561 text, int x, int y, int width, int color) {
/*  64 */     for (class_5481 line : this.field_22793.method_1728((class_5348)text, width)) {
/*  65 */       c.text(line, x, y, color);
/*  66 */       y += 10;
/*     */     } 
/*  68 */     return y;
/*     */   }
/*     */   
/*     */   protected class_5481 firstLine(class_2561 text, int width) {
/*  72 */     List<class_5481> lines = this.field_22793.method_1728((class_5348)text, width);
/*  73 */     return lines.isEmpty() ? class_5481.field_26385 : lines.getFirst();
/*     */   }
/*     */   
/*     */   protected int lineCount(class_2561 text, int width) {
/*  77 */     return this.field_22793.method_1728((class_5348)text, width).size();
/*     */   }
/*     */   
/*     */   protected void toggle(Canvas c, int x, int y, boolean on, boolean blocked) {
/*  81 */     c.fill(x, y, x + 18, y + 8, on ? -9783190 : -3556450);
/*  82 */     int kx = on ? (x + 11) : (x + 1);
/*  83 */     c.fill(kx, y + 1, kx + 6, y + 7, blocked ? -7703728 : (on ? -1 : -6649232));
/*     */   }
/*     */   
/*     */   protected int buttonWidth(class_2561 label) {
/*  87 */     return this.field_22793.method_27525((class_5348)label) + 16;
/*     */   }
/*     */   
/*     */   protected boolean button(Canvas c, int x, int y, class_2561 label, Runnable click) {
/*  91 */     return button(c, x, y, buttonWidth(label), label, -1911368, -5730192, true, click);
/*     */   }
/*     */   
/*     */   protected boolean button(Canvas c, int x, int y, int w, class_2561 label, int fill, int frame, boolean active, Runnable click) {
/*  95 */     boolean over = over(x, y, x + w, y + 14);
/*  96 */     c.fill(x, y, x + w, y + 14, (active && over) ? -10859984 : frame);
/*  97 */     c.fill(x + 1, y + 1, x + w - 1, y + 14 - 1, fill);
/*  98 */     c.text(label, x + (w - this.field_22793.method_27525((class_5348)label)) / 2, y + 3, active ? -13753320 : -6254470);
/*  99 */     if (active) {
/* 100 */       hit(x, y, x + w, y + 14, click, (ZakoOptions.Distance)null);
/*     */     }
/* 102 */     return over;
/*     */   }
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
/*     */ 
/*     */   
/*     */   protected boolean setting(Canvas c, ZakoOptions.Option option, int x0, int x1, int y) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: invokeinterface key : ()Ljava/lang/String;
/*     */     //   6: <illegal opcode> makeConcatWithConstants : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   11: invokestatic method_43471 : (Ljava/lang/String;)Lnet/minecraft/class_5250;
/*     */     //   14: astore #6
/*     */     //   16: aload_2
/*     */     //   17: dup
/*     */     //   18: invokestatic requireNonNull : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   21: pop
/*     */     //   22: astore #7
/*     */     //   24: iconst_0
/*     */     //   25: istore #8
/*     */     //   27: aload #7
/*     */     //   29: iload #8
/*     */     //   31: <illegal opcode> typeSwitch : (Ljava/lang/Object;I)I
/*     */     //   36: lookupswitch default -> 64, 0 -> 74, 1 -> 163
/*     */     //   64: new java/lang/MatchException
/*     */     //   67: dup
/*     */     //   68: aconst_null
/*     */     //   69: aconst_null
/*     */     //   70: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   73: athrow
/*     */     //   74: aload #7
/*     */     //   76: checkcast zako/opt/ZakoOptions$Toggle
/*     */     //   79: astore #9
/*     */     //   81: aload_0
/*     */     //   82: aload_1
/*     */     //   83: iload_3
/*     */     //   84: iload #5
/*     */     //   86: iconst_1
/*     */     //   87: iadd
/*     */     //   88: aload #9
/*     */     //   90: invokevirtual get : ()Ljava/util/function/Supplier;
/*     */     //   93: invokeinterface get : ()Ljava/lang/Object;
/*     */     //   98: checkcast java/lang/Boolean
/*     */     //   101: invokevirtual booleanValue : ()Z
/*     */     //   104: iconst_0
/*     */     //   105: invokevirtual toggle : (Lzako/opt/Canvas;IIZZ)V
/*     */     //   108: aload_1
/*     */     //   109: aload #6
/*     */     //   111: iload_3
/*     */     //   112: bipush #24
/*     */     //   114: iadd
/*     */     //   115: iload #5
/*     */     //   117: ldc -13753320
/*     */     //   119: invokeinterface text : (Lnet/minecraft/class_2561;III)V
/*     */     //   124: aload_0
/*     */     //   125: iload_3
/*     */     //   126: iload #5
/*     */     //   128: iconst_2
/*     */     //   129: isub
/*     */     //   130: iload_3
/*     */     //   131: bipush #24
/*     */     //   133: iadd
/*     */     //   134: aload_0
/*     */     //   135: getfield field_22793 : Lnet/minecraft/class_327;
/*     */     //   138: aload #6
/*     */     //   140: invokevirtual method_27525 : (Lnet/minecraft/class_5348;)I
/*     */     //   143: iadd
/*     */     //   144: iload #5
/*     */     //   146: bipush #11
/*     */     //   148: iadd
/*     */     //   149: aload #9
/*     */     //   151: <illegal opcode> run : (Lzako/opt/ZakoOptions$Toggle;)Ljava/lang/Runnable;
/*     */     //   156: aconst_null
/*     */     //   157: invokevirtual hit : (IIIILjava/lang/Runnable;Lzako/opt/ZakoOptions$Distance;)V
/*     */     //   160: goto -> 465
/*     */     //   163: aload #7
/*     */     //   165: checkcast zako/opt/ZakoOptions$Distance
/*     */     //   168: astore #10
/*     */     //   170: aload_0
/*     */     //   171: getfield field_22793 : Lnet/minecraft/class_327;
/*     */     //   174: aload #6
/*     */     //   176: invokevirtual method_27525 : (Lnet/minecraft/class_5348;)I
/*     */     //   179: bipush #12
/*     */     //   181: iadd
/*     */     //   182: iload #4
/*     */     //   184: iload_3
/*     */     //   185: isub
/*     */     //   186: iconst_2
/*     */     //   187: idiv
/*     */     //   188: invokestatic min : (II)I
/*     */     //   191: istore #11
/*     */     //   193: bipush #40
/*     */     //   195: istore #12
/*     */     //   197: aload_1
/*     */     //   198: aload_0
/*     */     //   199: aload #6
/*     */     //   201: iload #11
/*     */     //   203: iconst_4
/*     */     //   204: isub
/*     */     //   205: invokevirtual firstLine : (Lnet/minecraft/class_2561;I)Lnet/minecraft/class_5481;
/*     */     //   208: iload_3
/*     */     //   209: iload #5
/*     */     //   211: ldc -13753320
/*     */     //   213: invokeinterface text : (Lnet/minecraft/class_5481;III)V
/*     */     //   218: iload_3
/*     */     //   219: iload #11
/*     */     //   221: iadd
/*     */     //   222: istore #13
/*     */     //   224: iload #4
/*     */     //   226: iload #12
/*     */     //   228: isub
/*     */     //   229: istore #14
/*     */     //   231: aload #10
/*     */     //   233: invokevirtual get : ()Ljava/util/function/Supplier;
/*     */     //   236: invokeinterface get : ()Ljava/lang/Object;
/*     */     //   241: checkcast java/lang/Integer
/*     */     //   244: invokevirtual intValue : ()I
/*     */     //   247: istore #15
/*     */     //   249: iload #13
/*     */     //   251: iload #14
/*     */     //   253: iload #13
/*     */     //   255: isub
/*     */     //   256: iload #15
/*     */     //   258: aload #10
/*     */     //   260: invokevirtual min : ()I
/*     */     //   263: isub
/*     */     //   264: imul
/*     */     //   265: iconst_1
/*     */     //   266: aload #10
/*     */     //   268: invokevirtual max : ()I
/*     */     //   271: aload #10
/*     */     //   273: invokevirtual min : ()I
/*     */     //   276: isub
/*     */     //   277: invokestatic max : (II)I
/*     */     //   280: idiv
/*     */     //   281: iadd
/*     */     //   282: istore #16
/*     */     //   284: aload_1
/*     */     //   285: iload #13
/*     */     //   287: iload #5
/*     */     //   289: iconst_3
/*     */     //   290: iadd
/*     */     //   291: iload #14
/*     */     //   293: iload #5
/*     */     //   295: iconst_5
/*     */     //   296: iadd
/*     */     //   297: ldc -3556450
/*     */     //   299: invokeinterface fill : (IIIII)V
/*     */     //   304: aload_1
/*     */     //   305: iload #13
/*     */     //   307: iload #5
/*     */     //   309: iconst_3
/*     */     //   310: iadd
/*     */     //   311: iload #16
/*     */     //   313: iload #5
/*     */     //   315: iconst_5
/*     */     //   316: iadd
/*     */     //   317: ldc -12940742
/*     */     //   319: invokeinterface fill : (IIIII)V
/*     */     //   324: aload_1
/*     */     //   325: iload #16
/*     */     //   327: iconst_2
/*     */     //   328: isub
/*     */     //   329: iload #5
/*     */     //   331: iconst_1
/*     */     //   332: isub
/*     */     //   333: iload #16
/*     */     //   335: iconst_2
/*     */     //   336: iadd
/*     */     //   337: iload #5
/*     */     //   339: bipush #9
/*     */     //   341: iadd
/*     */     //   342: ldc -12940742
/*     */     //   344: invokeinterface fill : (IIIII)V
/*     */     //   349: aload_1
/*     */     //   350: iload #16
/*     */     //   352: iconst_1
/*     */     //   353: isub
/*     */     //   354: iload #5
/*     */     //   356: iload #16
/*     */     //   358: iconst_1
/*     */     //   359: iadd
/*     */     //   360: iload #5
/*     */     //   362: bipush #8
/*     */     //   364: iadd
/*     */     //   365: aload_0
/*     */     //   366: getfield dragging : Lzako/opt/Panel$Hit;
/*     */     //   369: ifnull -> 389
/*     */     //   372: aload_0
/*     */     //   373: getfield dragging : Lzako/opt/Panel$Hit;
/*     */     //   376: invokevirtual slider : ()Lzako/opt/ZakoOptions$Distance;
/*     */     //   379: aload #10
/*     */     //   381: if_acmpne -> 389
/*     */     //   384: ldc -15068148
/*     */     //   386: goto -> 390
/*     */     //   389: iconst_m1
/*     */     //   390: invokeinterface fill : (IIIII)V
/*     */     //   395: aload #10
/*     */     //   397: invokevirtual unit : ()Ljava/lang/String;
/*     */     //   400: iconst_1
/*     */     //   401: anewarray java/lang/Object
/*     */     //   404: dup
/*     */     //   405: iconst_0
/*     */     //   406: iload #15
/*     */     //   408: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */     //   411: aastore
/*     */     //   412: invokestatic method_43469 : (Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/class_5250;
/*     */     //   415: astore #17
/*     */     //   417: aload_1
/*     */     //   418: aload #17
/*     */     //   420: iload #4
/*     */     //   422: aload_0
/*     */     //   423: getfield field_22793 : Lnet/minecraft/class_327;
/*     */     //   426: aload #17
/*     */     //   428: invokevirtual method_27525 : (Lnet/minecraft/class_5348;)I
/*     */     //   431: isub
/*     */     //   432: iload #5
/*     */     //   434: ldc -13753320
/*     */     //   436: invokeinterface text : (Lnet/minecraft/class_2561;III)V
/*     */     //   441: aload_0
/*     */     //   442: iload #13
/*     */     //   444: iconst_4
/*     */     //   445: isub
/*     */     //   446: iload #5
/*     */     //   448: iconst_3
/*     */     //   449: isub
/*     */     //   450: iload #14
/*     */     //   452: iconst_4
/*     */     //   453: iadd
/*     */     //   454: iload #5
/*     */     //   456: bipush #11
/*     */     //   458: iadd
/*     */     //   459: aconst_null
/*     */     //   460: aload #10
/*     */     //   462: invokevirtual hit : (IIIILjava/lang/Runnable;Lzako/opt/ZakoOptions$Distance;)V
/*     */     //   465: aload_0
/*     */     //   466: iload_3
/*     */     //   467: iload #5
/*     */     //   469: iconst_2
/*     */     //   470: isub
/*     */     //   471: iload #4
/*     */     //   473: iload #5
/*     */     //   475: bipush #12
/*     */     //   477: iadd
/*     */     //   478: invokevirtual over : (IIII)Z
/*     */     //   481: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #107	-> 0
/*     */     //   #108	-> 16
/*     */     //   #109	-> 74
/*     */     //   #110	-> 81
/*     */     //   #111	-> 108
/*     */     //   #112	-> 124
/*     */     //   #113	-> 160
/*     */     //   #114	-> 163
/*     */     //   #115	-> 170
/*     */     //   #116	-> 197
/*     */     //   #117	-> 218
/*     */     //   #118	-> 231
/*     */     //   #119	-> 249
/*     */     //   #120	-> 284
/*     */     //   #121	-> 304
/*     */     //   #122	-> 324
/*     */     //   #123	-> 349
/*     */     //   #124	-> 395
/*     */     //   #125	-> 417
/*     */     //   #126	-> 441
/*     */     //   #129	-> 465
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   81	82	9	t	Lzako/opt/ZakoOptions$Toggle;
/*     */     //   193	272	11	labelWidth	I
/*     */     //   197	268	12	valueWidth	I
/*     */     //   224	241	13	tx0	I
/*     */     //   231	234	14	tx1	I
/*     */     //   249	216	15	value	I
/*     */     //   284	181	16	kx	I
/*     */     //   417	48	17	text	Lnet/minecraft/class_2561;
/*     */     //   170	295	10	d	Lzako/opt/ZakoOptions$Distance;
/*     */     //   0	482	0	this	Lzako/opt/Panel;
/*     */     //   0	482	1	c	Lzako/opt/Canvas;
/*     */     //   0	482	2	option	Lzako/opt/ZakoOptions$Option;
/*     */     //   0	482	3	x0	I
/*     */     //   0	482	4	x1	I
/*     */     //   0	482	5	y	I
/*     */     //   16	466	6	name	Lnet/minecraft/class_2561;
/*     */   }
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
/*     */   
/*     */   protected int beginScroll(Canvas c, int x0, int top, int x1, int bottom) {
/* 133 */     this.scrollTop = top;
/* 134 */     this.scrollBottom = bottom;
/* 135 */     this.scrollX1 = x1;
/* 136 */     this.scroll = class_3532.method_15350(this.scroll, 0.0D, Math.max(0, this.contentHeight - bottom - top));
/* 137 */     c.clip(x0, top, x1, bottom);
/* 138 */     this.clipTop = top;
/* 139 */     this.clipBottom = bottom;
/* 140 */     return top - (int)this.scroll;
/*     */   }
/*     */   
/*     */   protected void endScroll(Canvas c, int y) {
/* 144 */     this.contentHeight = y + (int)this.scroll - this.scrollTop;
/* 145 */     c.unclip();
/* 146 */     this.clipTop = 0;
/* 147 */     this.clipBottom = this.field_22790;
/* 148 */     int view = this.scrollBottom - this.scrollTop;
/* 149 */     if (this.contentHeight > view) {
/* 150 */       int barHeight = Math.max(12, view * view / this.contentHeight);
/* 151 */       int barY = this.scrollTop + (int)((view - barHeight) * this.scroll / (this.contentHeight - view));
/* 152 */       c.fill(this.scrollX1 - 3, barY, this.scrollX1 - 1, barY + barHeight, -7703728);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void resetScroll() {
/* 157 */     this.scroll = 0.0D;
/*     */   }
/*     */   
/*     */   protected void scrollTo(double y) {
/* 161 */     this.scroll = y;
/*     */   }
/*     */   
/*     */   protected void hit(int x0, int y0, int x1, int y1, Runnable click, ZakoOptions.Distance slider) {
/* 165 */     if (Math.max(y0, this.clipTop) < Math.min(y1, this.clipBottom)) {
/* 166 */       this.hits.add(new Hit(x0, Math.max(y0, this.clipTop), x1, Math.min(y1, this.clipBottom), click, slider));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean click(double x, double y, int button) {
/* 172 */     if (button != 0) {
/* 173 */       return false;
/*     */     }
/* 175 */     for (Hit hit : this.hits.reversed()) {
/* 176 */       if (hit.contains(x, y)) {
/* 177 */         if (hit.slider() != null) {
/* 178 */           this.dragging = hit;
/* 179 */           slide(x);
/*     */         } else {
/* 181 */           hit.click().run();
/* 182 */           changed();
/*     */         } 
/* 184 */         return true;
/*     */       } 
/*     */     } 
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drag(double x, double y) {
/* 192 */     if (this.dragging != null) {
/* 193 */       slide(x);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void release() {
/* 199 */     if (this.dragging != null) {
/* 200 */       this.dragging = null;
/* 201 */       changed();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void slide(double x) {
/* 206 */     ZakoOptions.Distance d = this.dragging.slider();
/* 207 */     double t = class_3532.method_15350((x - this.dragging.x0() - 4.0D) / (this.dragging.x1() - this.dragging.x0() - 8), 0.0D, 1.0D);
/* 208 */     d.set().accept(Integer.valueOf(d.min() + (int)Math.round(t * (d.max() - d.min()))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean method_25401(double x, double y, double scrollX, double scrollY) {
/* 213 */     this.scroll -= scrollY * 24.0D;
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   protected abstract void paint(Canvas paramCanvas); }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\Panel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */