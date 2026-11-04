/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import net.minecraft.class_10055;
/*    */ import net.minecraft.class_1007;
/*    */ import net.minecraft.class_11890;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.entity.EntityLod;
/*    */ 
/*    */ @Mixin({class_1007.class})
/*    */ public class AvatarLodMixin
/*    */ {
/*    */   @Inject(method = {"method_62604(Lnet/minecraft/class_11890;Lnet/minecraft/class_10055;F)V"}, at = {@At("TAIL")})
/*    */   private void zakoopt$farSkinLayer(class_11890 avatar, class_10055 state, float partialTick, CallbackInfo ci) {
/* 17 */     if (EntityLod.farPlayer(state.field_53332)) {
/* 18 */       state.field_53543 = false;
/* 19 */       state.field_53544 = false;
/* 20 */       state.field_53545 = false;
/* 21 */       state.field_53546 = false;
/* 22 */       state.field_53530 = false;
/* 23 */       state.field_53531 = false;
/* 24 */       state.field_53532 = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\AvatarLodMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */