/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.class_1297;
/*    */ import net.minecraft.class_1299;
/*    */ import net.minecraft.class_897;
/*    */ import net.minecraft.class_898;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.entity.CachedRenderer;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_898.class})
/*    */ public abstract class EntityRendererLookupMixin
/*    */ {
/*    */   @Shadow
/*    */   private Map<class_1299<?>, class_897<?, ?>> field_4696;
/*    */   
/*    */   @Inject(method = {"method_3953(Lnet/minecraft/class_1297;)Lnet/minecraft/class_897;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$cached(class_1297 entity, CallbackInfoReturnable<class_897<?, ?>> cir) {
/* 29 */     if (Features.LOOKUP_CACHES.on && ((CachedRenderer)entity).zakoopt$rendererMap() == this.field_4696) {
/* 30 */       cir.setReturnValue(((CachedRenderer)entity).zakoopt$renderer());
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_3953(Lnet/minecraft/class_1297;)Lnet/minecraft/class_897;"}, at = {@At("RETURN")})
/*    */   private void zakoopt$keep(class_1297 entity, CallbackInfoReturnable<class_897<?, ?>> cir) {
/* 36 */     if (Features.LOOKUP_CACHES.on && this.field_4696.get(entity.method_5864()) == cir.getReturnValue())
/* 37 */       ((CachedRenderer)entity).zakoopt$setRenderer(cir.getReturnValue(), this.field_4696); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\EntityRendererLookupMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */