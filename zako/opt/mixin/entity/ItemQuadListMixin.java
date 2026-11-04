/*    */ package zako.opt.mixin.entity;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import net.minecraft.class_10430;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({class_10430.class})
/*    */ public class ItemQuadListMixin
/*    */ {
/*    */   @WrapOperation(method = {"method_65584"}, at = {@At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z")})
/*    */   private boolean zakoopt$addQuads(List<Object> list, Collection<?> quads, Operation<Boolean> original) {
/*    */     List<?> source;
/* 20 */     if (Features.FEWER_ALLOCATIONS.on && quads instanceof List) { source = (List)quads; }
/* 21 */     else { return ((Boolean)original.call(new Object[] { list, quads })).booleanValue(); }
/*    */     
/* 23 */     for (int i = 0, n = source.size(); i < n; i++) {
/* 24 */       list.add(source.get(i));
/*    */     }
/* 26 */     return !source.isEmpty();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\entity\ItemQuadListMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */