/*    */ package zako.opt;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
/*    */ import net.minecraft.class_2561;
/*    */ 
/*    */ public class ConfirmPanel
/*    */   extends Panel {
/*    */   private final class_2561 text;
/*    */   private final class_2561 yes;
/*    */   private final class_2561 no;
/*    */   private final BooleanConsumer answer;
/*    */   
/*    */   public ConfirmPanel(BooleanConsumer answer, class_2561 title, class_2561 text, class_2561 yes, class_2561 no) {
/* 14 */     super(title);
/* 15 */     this.text = text;
/* 16 */     this.yes = yes;
/* 17 */     this.no = no;
/* 18 */     this.answer = answer;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void paint(Canvas c) {
/* 23 */     int w = Math.min(this.field_22789 - 12, 360), x0 = (this.field_22789 - w) / 2, x1 = x0 + w, inner = w - 20;
/* 24 */     int h = Math.min(this.field_22790 - 12, 24 + lineCount(this.text, inner) * 10 + 8 + 14 + 10), y0 = (this.field_22790 - h) / 2, y1 = y0 + h;
/* 25 */     frame(c, x0, y0, x1, y1);
/* 26 */     c.text(this.field_22785, x0 + 10, y0 + 8, -15068148);
/* 27 */     int bottom = y1 - 14 - 16;
/* 28 */     endScroll(c, lines(c, this.text, x0 + 10, beginScroll(c, x0, y0 + 22, x1, bottom) + 2, inner, -13753320));
/* 29 */     int nw = buttonWidth(this.no), by = y1 - 14 - 8;
/* 30 */     button(c, x1 - 8 - nw, by, this.no, () -> this.answer.accept(false));
/* 31 */     int yw = buttonWidth(this.yes);
/* 32 */     button(c, x1 - 8 - nw - 6 - yw, by, yw, this.yes, -9783190, -12940742, true, () -> this.answer.accept(true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void method_25419() {
/* 37 */     this.answer.accept(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ConfirmPanel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */