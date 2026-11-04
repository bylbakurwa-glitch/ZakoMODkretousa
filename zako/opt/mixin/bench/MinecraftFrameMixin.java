/*    */ package zako.opt.mixin.bench;
/*    */ 
/*    */ import net.minecraft.class_310;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.bench.Bench;
/*    */ import zako.opt.bench.MonitorScreen;
/*    */ 
/*    */ @Mixin({class_310.class})
/*    */ public class MinecraftFrameMixin {
/*    */   @Inject(method = {"method_1523"}, at = {@At("HEAD")})
/*    */   private void zakoopt$frame(boolean advanceGameTime, CallbackInfo ci) {
/* 15 */     Bench.frame(System.nanoTime());
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_1574"}, at = {@At("HEAD")})
/*    */   private void zakoopt$monitorKey(CallbackInfo ci) {
/* 20 */     MonitorScreen.pollKey();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\bench\MinecraftFrameMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */