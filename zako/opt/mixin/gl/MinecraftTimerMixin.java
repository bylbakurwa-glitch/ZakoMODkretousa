/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_310;
/*    */ import net.minecraft.class_7168;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.GpuTimers;
/*    */ 
/*    */ @Mixin({class_310.class})
/*    */ public class MinecraftTimerMixin
/*    */ {
/*    */   @Shadow
/*    */   private double field_37856;
/*    */   
/*    */   @WrapOperation(method = {"method_1523"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_7168;method_41720()V")})
/*    */   private void zakoopt$ownTimers(class_7168 query, Operation<Void> original) {
/* 22 */     if (!GpuTimers.supported()) {
/* 23 */       original.call(new Object[] { query });
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_1523"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_7168;method_41721()Lnet/minecraft/class_7168$class_7169;")})
/*    */   private class_7168.class_7169 zakoopt$ownTimersEnd(class_7168 query, Operation<class_7168.class_7169> original) {
/* 30 */     return GpuTimers.supported() ? null : (class_7168.class_7169)original.call(new Object[] { query });
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_1523"}, at = {@At("HEAD")})
/*    */   private void zakoopt$utilization(CallbackInfo ci) {
/* 35 */     if (GpuTimers.supported())
/* 36 */       this.field_37856 = GpuTimers.utilization(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\MinecraftTimerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */