/*    */ package zako.opt.entity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_11661;
/*    */ import net.minecraft.class_11683;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_4588;
/*    */ import net.minecraft.class_4597;
/*    */ import net.minecraft.class_4618;
/*    */ import org.lwjgl.system.MemoryStack;
/*    */ import zako.opt.mixin.entity.ModelFeatureRendererInvoker;
/*    */ import zako.opt.mixin.entity.ModelStorageAccessor;
/*    */ 
/*    */ public final class ParallelModelHook {
/*    */   @Generated
/*    */   private ParallelModelHook() {
/* 20 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 21 */   } private static final List<ParallelModels.Job> JOBS = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean render(class_11683 renderer, class_11683.class_12048 storage, class_4597.class_4598 buffers, class_4618 outline, class_4597.class_4598 crumbling) {
/* 26 */     if (!ParallelModels.enabled()) {
/* 27 */       return false;
/*    */     }
/* 29 */     ModelStorageAccessor acc = (ModelStorageAccessor)storage;
/* 30 */     Map<class_1921, List<class_11661.class_11670<?>>> opaque = acc.zakoopt$opaque();
/* 31 */     List<class_11661.class_11744<?>> translucent = acc.zakoopt$translucent();
/* 32 */     int submits = translucent.size();
/* 33 */     for (List<class_11661.class_11670<?>> list : opaque.values()) {
/* 34 */       submits += list.size();
/*    */     }
/* 36 */     if (submits < 24) {
/* 37 */       return false;
/*    */     }
/* 39 */     translucent.sort(Comparator.comparingDouble(t -> -t.comp_4618().lengthSquared()));
/*    */     
/* 41 */     JOBS.clear();
/* 42 */     ParallelModels.begin();
/*    */     try {
/* 44 */       for (Map.Entry<class_1921, List<class_11661.class_11670<?>>> e : opaque.entrySet()) {
/* 45 */         for (class_11661.class_11670<?> s : e.getValue()) {
/* 46 */           JOBS.add(add(s, e.getKey()));
/*    */         }
/*    */       } 
/* 49 */       for (class_11661.class_11744<?> t : translucent) {
/* 50 */         JOBS.add(add(t.comp_4615(), t.comp_4616()));
/*    */       }
/*    */     } finally {
/* 53 */       ParallelModels.end();
/*    */     } 
/*    */     
/* 56 */     ModelFeatureRendererInvoker invoker = (ModelFeatureRendererInvoker)renderer;
/* 57 */     int k = 0;
/* 58 */     MemoryStack stack = MemoryStack.stackPush(); 
/* 59 */     try { for (Map.Entry<class_1921, List<class_11661.class_11670<?>>> e : opaque.entrySet()) {
/* 60 */         class_4588 consumer = buffers.method_73477(e.getKey());
/* 61 */         for (class_11661.class_11670<?> s : e.getValue()) {
/* 62 */           emit(JOBS.get(k++), s, e.getKey(), consumer, stack, invoker, outline, crumbling);
/*    */         }
/*    */       } 
/* 65 */       for (class_11661.class_11744<?> t : translucent) {
/* 66 */         emit(JOBS.get(k++), t.comp_4615(), t.comp_4616(), buffers.method_73477(t.comp_4616()), stack, invoker, outline, crumbling);
/*    */       }
/* 68 */       if (stack != null) stack.close();  } catch (Throwable throwable) { if (stack != null)
/* 69 */         try { stack.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static ParallelModels.Job add(class_11661.class_11670<?> s, class_1921 type) {
/* 75 */     if (s.comp_4511() != 0 || s.comp_4651() != null || type.method_23031() != ParallelModels.FORMAT || AnimFreeze.freezes(s.comp_4506(), s.comp_4504().method_23761())) {
/* 76 */       return null;
/*    */     }
/* 78 */     return ParallelModels.add(s.comp_4505(), s.comp_4506(), s.comp_4504(), s.comp_4507(), s.comp_4508(), s.comp_4509(), s.comp_4510());
/*    */   }
/*    */ 
/*    */   
/*    */   private static void emit(ParallelModels.Job job, class_11661.class_11670<?> s, class_1921 type, class_4588 consumer, MemoryStack stack, ModelFeatureRendererInvoker invoker, class_4618 outline, class_4597.class_4598 crumbling) {
/* 83 */     if (job == null || !ParallelModels.emit(job, consumer, stack))
/* 84 */       invoker.zakoopt$renderModel(s, type, consumer, outline, crumbling); 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\ParallelModelHook.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */