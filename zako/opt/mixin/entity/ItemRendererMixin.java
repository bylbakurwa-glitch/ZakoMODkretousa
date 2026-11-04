/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.class_4587;
/*    */ import net.minecraft.class_777;
/*    */ import net.minecraft.class_811;
/*    */ import net.minecraft.class_918;
/*    */ import org.joml.Matrix4f;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ import zako.opt.entity.ItemLod;
/*    */ 
/*    */ @Mixin({class_918.class})
/*    */ public class ItemRendererMixin
/*    */ {
/*    */   @ModifyVariable(method = {"method_62476"}, at = {@At("HEAD")}, argsOnly = true)
/*    */   private static List<class_777> zakoopt$lod(List<class_777> quads, class_811 context, class_4587 poseStack) {
/* 19 */     if (context.method_29998() || context == class_811.field_4317 || !ItemLod.enabled()) {
/* 20 */       return quads;
/*    */     }
/* 22 */     Matrix4f m = poseStack.method_23760().method_23761();
/* 23 */     float dSq = m.m30() * m.m30() + m.m31() * m.m31() + m.m32() * m.m32();
/* 24 */     return (dSq > ItemLod.distanceSq()) ? ItemLod.flat(quads) : quads;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */