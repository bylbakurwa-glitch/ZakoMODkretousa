/*    */ package zako.opt.mixin.hud;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.ModifyReturnValue;
/*    */ import net.minecraft.class_9779;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.hud.HudCache;
/*    */ import zako.opt.hud.HudDelta;
/*    */ 
/*    */ @Mixin({class_9779.class_9781.class})
/*    */ public class DeltaTrackerHudMixin
/*    */ {
/*    */   @ModifyReturnValue(method = {"method_60636"}, at = {@At("RETURN")})
/*    */   private float zakoopt$hudDelta(float delta) {
/* 15 */     return HudCache.suppressLiveParts ? HudDelta.sinceRefresh() : delta;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\hud\DeltaTrackerHudMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */