/*    */ package zako.opt.mixin;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_11630;
/*    */ import net.minecraft.class_11632;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_2818;
/*    */ import net.minecraft.class_2960;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.DebugInfo;
/*    */ 
/*    */ @Mixin({class_11631.class})
/*    */ public class DebugScreenEntriesMixin {
/*    */   @Shadow
/*    */   private static class_2960 method_72763(class_2960 id, class_11632 entry) {
/* 17 */     throw new AssertionError();
/*    */   }
/*    */   
/*    */   @Inject(method = {"<clinit>"}, at = {@At("RETURN")})
/*    */   private static void zakoopt$register(CallbackInfo ci) {
/* 22 */     method_72763(class_2960.method_60655("zakoopt", "status"), (displayer, level, clientChunk, serverChunk) -> {
/*    */           Objects.requireNonNull(displayer);
/*    */           DebugInfo.lines().forEach(displayer::method_72747);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\DebugScreenEntriesMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */