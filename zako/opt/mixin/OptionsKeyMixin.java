/*    */ package zako.opt.mixin;
/*    */ 
/*    */ import net.minecraft.class_304;
/*    */ import net.minecraft.class_315;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Mutable;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.ZakoKey;
/*    */ 
/*    */ @Mixin({class_315.class})
/*    */ public class OptionsKeyMixin
/*    */ {
/*    */   @Mutable
/*    */   @Final
/*    */   @Shadow
/*    */   public class_304[] field_1839;
/*    */   
/*    */   @Inject(method = {"method_1636"}, at = {@At("HEAD")})
/*    */   private void zakoopt$key(CallbackInfo ci) {
/* 25 */     if (!ArrayUtils.contains((Object[])this.field_1839, ZakoKey.MONITOR))
/* 26 */       this.field_1839 = (class_304[])ArrayUtils.add((Object[])this.field_1839, ZakoKey.MONITOR); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\OptionsKeyMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */