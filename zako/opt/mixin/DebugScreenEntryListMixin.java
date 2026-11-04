/*    */ package zako.opt.mixin;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_11633;
/*    */ import net.minecraft.class_11635;
/*    */ import net.minecraft.class_2960;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({class_11633.class})
/*    */ public abstract class DebugScreenEntryListMixin
/*    */ {
/*    */   @Shadow
/*    */   private Map<class_2960, class_11635> field_61586;
/*    */   
/*    */   @Shadow
/*    */   public abstract void method_72777();
/*    */   
/*    */   @Inject(method = {"method_72765", "method_72768"}, at = {@At("RETURN")})
/*    */   private void zakoopt$showByDefault(CallbackInfo ci) {
/* 25 */     if (this.field_61586.putIfAbsent(class_2960.method_60655("zakoopt", "status"), class_11635.field_61594) == null)
/* 26 */       method_72777(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\DebugScreenEntryListMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */