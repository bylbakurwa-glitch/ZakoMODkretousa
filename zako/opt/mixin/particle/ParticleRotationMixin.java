/*    */ package zako.opt.mixin.particle;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.class_3940;
/*    */ import org.joml.Quaternionf;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ @Mixin({class_3940.class})
/*    */ public class ParticleRotationMixin
/*    */ {
/*    */   @Unique
/* 18 */   private static final Quaternionf ZAKOOPT$FACING = new Quaternionf();
/*    */ 
/*    */   
/*    */   @WrapOperation(method = {"method_3074"}, at = {@At(value = "NEW", target = "()Lorg/joml/Quaternionf;")})
/*    */   private Quaternionf zakoopt$facing(Operation<Quaternionf> original) {
/* 23 */     return (Features.FEWER_ALLOCATIONS.on && RenderSystem.isOnRenderThread()) ? ZAKOOPT$FACING.identity() : (Quaternionf)original.call(new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\particle\ParticleRotationMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */