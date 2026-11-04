/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_10017;
/*    */ import net.minecraft.class_11661;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_11788;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_3879;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_4588;
/*    */ import net.minecraft.class_4597;
/*    */ import net.minecraft.class_4618;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.entity.AnimFreeze;
/*    */ import zako.opt.entity.ParallelModelHook;
/*    */ import zako.opt.entity.StateEntity;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_11683.class})
/*    */ public class ModelFeatureRendererMixin
/*    */ {
/*    */   @Inject(method = {"method_73001"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$parallel(class_11788 collection, class_4597.class_4598 buffers, class_4618 outline, class_4597.class_4598 crumbling, CallbackInfo ci) {
/* 31 */     if (ParallelModelHook.render((class_11683)this, collection.method_74771(), buffers, outline, crumbling)) {
/* 32 */       ci.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_73000"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_3879;method_62100(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V", ordinal = 0)})
/*    */   private void zakoopt$frozenAnimation(class_3879<?> model, class_4587 poseStack, class_4588 consumer, int light, int overlay, int color, Operation<Void> original, class_11661.class_11670<?> submit, class_1921 renderType, class_4588 rawConsumer, class_4618 outline, class_4597.class_4598 buffers) {
/* 40 */     if (Features.ENTITY_ANIM_FREEZE.on) { Object object = submit.comp_4506(); if (object instanceof class_10017) { class_10017 state = (class_10017)object;
/* 41 */         class_1297 entity = ((StateEntity)state).zakoopt$entity();
/* 42 */         if (entity != null && AnimFreeze.render(entity, model, renderType, submit.comp_4510(), poseStack.method_23760().method_23761(), rawConsumer, target -> original.call(new Object[] { model, poseStack, target, Integer.valueOf(light), Integer.valueOf(overlay), Integer.valueOf(color) }))) {
/*    */           return;
/*    */         } }
/*    */        }
/*    */     
/* 47 */     original.call(new Object[] { model, poseStack, consumer, Integer.valueOf(light), Integer.valueOf(overlay), Integer.valueOf(color) });
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ModelFeatureRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */