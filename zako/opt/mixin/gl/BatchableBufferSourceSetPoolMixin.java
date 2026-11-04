/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
/*    */ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
/*    */ import java.util.ArrayDeque;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin(value = {BatchableBufferSource.class}, remap = false)
/*    */ public class BatchableBufferSourceSetPoolMixin
/*    */ {
/*    */   @Unique
/* 21 */   private final ArrayDeque<Set<?>> zakoopt$free = new ArrayDeque<>();
/*    */   
/*    */   @Unique
/*    */   private Set<?> zakoopt$drawn;
/*    */   
/*    */   @WrapOperation(method = {"drawDirect"}, at = {@At(value = "INVOKE", target = "Ljava/util/Map;remove(Ljava/lang/Object;)Ljava/lang/Object;")})
/*    */   private Object zakoopt$keepSet(Map<?, ?> map, Object key, Operation<Object> original) {
/* 28 */     zakoopt$recycle();
/* 29 */     Object set = original.call(new Object[] { map, key });
/* 30 */     if (Features.FEWER_ALLOCATIONS.on && set instanceof Set) { Set<?> s = (Set)set;
/* 31 */       this.zakoopt$drawn = s; }
/*    */     
/* 33 */     return set;
/*    */   }
/*    */   
/*    */   @WrapOperation(method = {"method_73477", "method_73477"}, at = {@At(value = "INVOKE", target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;")})
/*    */   private Object zakoopt$reuseSet(Map<Object, Object> map, Object key, Function<Object, Object> make, Operation<Object> original) {
/* 38 */     zakoopt$recycle();
/* 39 */     if (this.zakoopt$free.isEmpty()) {
/* 40 */       return original.call(new Object[] { map, key, make });
/*    */     }
/* 42 */     Object<?> set = (Object<?>)map.get(key);
/* 43 */     if (set == null) {
/* 44 */       set = (Object<?>)this.zakoopt$free.pop();
/* 45 */       map.put(key, set);
/*    */     } 
/* 47 */     return set;
/*    */   }
/*    */ 
/*    */   
/*    */   @Unique
/*    */   private void zakoopt$recycle() {
/* 53 */     if (this.zakoopt$drawn != null) {
/* 54 */       this.zakoopt$drawn.clear();
/* 55 */       this.zakoopt$free.push(this.zakoopt$drawn);
/* 56 */       this.zakoopt$drawn = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\BatchableBufferSourceSetPoolMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */