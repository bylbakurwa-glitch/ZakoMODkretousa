/*    */ package zako.opt.mixin.block;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_11791;
/*    */ import net.minecraft.class_11968;
/*    */ import net.minecraft.class_1920;
/*    */ import net.minecraft.class_1937;
/*    */ import net.minecraft.class_1959;
/*    */ import net.minecraft.class_2338;
/*    */ import net.minecraft.class_243;
/*    */ import net.minecraft.class_2669;
/*    */ import net.minecraft.class_2680;
/*    */ import net.minecraft.class_6880;
/*    */ import net.minecraft.class_835;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.block.MovingSlots;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_835.class})
/*    */ public class PistonMovingStateMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_74380(Lnet/minecraft/class_2669;Lnet/minecraft/class_11968;FLnet/minecraft/class_243;Lnet/minecraft/class_11683$class_11792;)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/class_835;method_3575(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_6880;Lnet/minecraft/class_1937;)Lnet/minecraft/class_11791;")})
/*    */   private class_11791 zakoopt$keptState(class_2338 pos, class_2680 blockState, class_6880<class_1959> biome, class_1937 level, Operation<class_11791> original, class_2669 piston, class_11968 state, float partialTick, class_243 camera, class_11683.class_11792 crumbling) {
/*    */     class_11791 kept;
/* 31 */     if (!Features.FEWER_ALLOCATIONS.on) {
/* 32 */       return (class_11791)original.call(new Object[] { pos, blockState, biome, level });
/*    */     }
/* 34 */     int slot = (state.field_62729 == null) ? 0 : 1;
/* 35 */     MovingSlots slots = (MovingSlots)piston;
/* 36 */     Object object = slots.zakoopt$moving(slot); if (object instanceof class_11791) { kept = (class_11791)object; }
/* 37 */     else { class_11791 made = (class_11791)original.call(new Object[] { pos, blockState, biome, level });
/* 38 */       slots.zakoopt$setMoving(slot, made);
/* 39 */       return made; }
/*    */     
/* 41 */     kept.field_62245 = pos;
/* 42 */     kept.field_62246 = pos;
/* 43 */     kept.field_62247 = blockState;
/* 44 */     kept.field_62248 = biome;
/* 45 */     kept.field_62249 = (class_1920)level;
/* 46 */     return kept;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\block\PistonMovingStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */