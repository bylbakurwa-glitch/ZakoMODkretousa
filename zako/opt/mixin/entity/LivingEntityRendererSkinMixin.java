/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_10055;
/*    */ import net.minecraft.class_1058;
/*    */ import net.minecraft.class_11659;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_12249;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_3879;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_922;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.SkinAtlas;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_922.class})
/*    */ public class LivingEntityRendererSkinMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_11659;method_73490(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_1058;ILnet/minecraft/class_11683$class_11792;)V")})
/*    */   private void zakoopt$skinAtlas(class_11659 collector, class_3879<?> model, Object state, class_4587 pose, class_1921 type, int light, int overlay, int color, class_1058 sprite, int outline, class_11683.class_11792 crumbling, Operation<Void> original) {
/* 28 */     if (sprite == null && state instanceof class_10055) { class_10055 avatar = (class_10055)state; if (Features.SKIN_ATLAS.on) {
/* 29 */         class_2960 skin = avatar.field_53520.comp_1626().comp_3627();
/*    */         
/* 31 */         if (type == class_12249.method_76000(skin)) {
/* 32 */           class_1058 atlased = SkinAtlas.sprite(skin);
/* 33 */           if (atlased != null) {
/* 34 */             original.call(new Object[] { collector, model, state, pose, class_12249.method_76000(SkinAtlas.ID), Integer.valueOf(light), Integer.valueOf(overlay), Integer.valueOf(color), atlased, Integer.valueOf(outline), crumbling }); return;
/*    */           } 
/*    */         } 
/*    */       }  }
/*    */     
/* 39 */     original.call(new Object[] { collector, model, state, pose, type, Integer.valueOf(light), Integer.valueOf(overlay), Integer.valueOf(color), sprite, Integer.valueOf(outline), crumbling });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\LivingEntityRendererSkinMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */