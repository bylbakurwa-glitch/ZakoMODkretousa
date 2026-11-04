/*    */ package zako.opt.mixin.particle;
/*    */ 
/*    */ import net.minecraft.class_11944;
/*    */ import net.minecraft.class_11977;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import zako.opt.particle.ParallelParticleVertices;
/*    */ 
/*    */ @Mixin({class_11944.class})
/*    */ public class QuadParticleRenderStateMixin {
/*    */   @Inject(method = {"method_74755"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$parallelVertices(class_11977.class_12051 cache, CallbackInfoReturnable<class_11944.class_12041> cir) {
/* 15 */     class_11944.class_12041 prepared = ParallelParticleVertices.prepare((class_11944)this, cache);
/* 16 */     if (prepared != null)
/* 17 */       cir.setReturnValue(prepared); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\QuadParticleRenderStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */