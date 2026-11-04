/*    */ package zako.opt.block;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_243;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Recording
/*    */ {
/* 33 */   final List<SpawnerReplay.Call> calls = new ArrayList<>();
/*    */   final double x;
/*    */   final double y;
/*    */   
/*    */   Recording(class_243 camera) {
/* 38 */     this.x = camera.field_1352;
/* 39 */     this.y = camera.field_1351;
/* 40 */     this.z = camera.field_1350;
/*    */   }
/*    */   
/*    */   final double z;
/*    */   boolean replayable = true;
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\SpawnerReplay$Recording.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */