/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.class_10444;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_811;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import zako.opt.entity.ItemBounds;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ @Mixin({class_10444.class})
/*    */ public class ItemStackRenderStateMixin {
/*    */   @Shadow
/*    */   private int field_55339;
/*    */   @Shadow
/*    */   private class_10444.class_10446[] field_55340;
/*    */   @Shadow
/*    */   class_811 field_55337;
/*    */   
/*    */   @WrapMethod(method = {"method_72173"})
/*    */   private class_238 zakoopt$sharedBounds(Operation<class_238> original) {
/* 25 */     if (!Features.MICRO_OPTS.sharedItemBounds || this.field_55339 != 1) {
/* 26 */       return (class_238)original.call(new Object[0]);
/*    */     }
/* 28 */     ItemLayerAccessor layer = (ItemLayerAccessor)this.field_55340[0];
/* 29 */     Objects.requireNonNull(original); Operation<class_238> operation = original; return ItemBounds.get(layer.zakoopt$extents(), layer.zakoopt$transform(), this.field_55337.method_67675(), () -> (class_238)rec$.call(new Object[0]));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemStackRenderStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */