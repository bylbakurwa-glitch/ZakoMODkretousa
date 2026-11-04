/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import org.lwjgl.system.MemoryStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.entity.CachedStack;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(targets = {"net/caffeinemc/mods/sodium/client/render/immediate/model/EntityRenderer"}, remap = false)
/*    */ public class SodiumEntityRendererMixin
/*    */ {
/*    */   @WrapOperation(method = {"renderCuboid"}, at = {@At(value = "INVOKE", target = "Lorg/lwjgl/system/MemoryStack;stackPush()Lorg/lwjgl/system/MemoryStack;")})
/*    */   private static MemoryStack zakoopt$cachedStack(Operation<MemoryStack> original) {
/* 18 */     return Features.MICRO_OPTS.on ? CachedStack.push() : (MemoryStack)original.call(new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\SodiumEntityRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */