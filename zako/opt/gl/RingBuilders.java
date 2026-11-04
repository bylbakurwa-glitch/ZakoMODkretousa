/*    */ package zako.opt.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
/*    */ import java.util.ArrayDeque;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_9799;
/*    */ 
/*    */ public final class RingBuilders {
/*    */   @Generated
/*    */   private RingBuilders() {
/* 13 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/* 16 */   private static final Reference2IntOpenHashMap<class_1921> SIZES = new Reference2IntOpenHashMap();
/*    */   private static final int DEFAULT_SIZE = 65536;
/* 18 */   private static final ArrayDeque<class_9799> FREE = new ArrayDeque<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class_1921 current;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class_9799 create() {
/* 29 */     class_1921 type = current;
/*    */     
/* 31 */     if (type == null || type.method_60894()) {
/* 32 */       return null;
/*    */     }
/* 34 */     int size = SIZES.getOrDefault(type, 65536);
/* 35 */     long address = ImmediateRing.reserveBuilder(type.method_23031(), size);
/* 36 */     if (address == 0L) {
/* 37 */       return null;
/*    */     }
/* 39 */     class_9799 builder = FREE.isEmpty() ? new class_9799(16) : FREE.pop();
/* 40 */     ((Backed)builder).zakoopt$attach(type, address, size);
/* 41 */     return builder;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean release(class_9799 builder) {
/* 46 */     if (!((Backed)builder).zakoopt$ringBacked()) {
/* 47 */       return false;
/*    */     }
/* 49 */     builder.close();
/*    */     
/* 51 */     if (RenderSystem.isOnRenderThread() && FREE.size() < 256 && !FREE.contains(builder)) {
/* 52 */       FREE.push(builder);
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public static void remember(class_1921 type, long size) {
/* 58 */     SIZES.put(type, (int)Math.min(size, 16777216L));
/*    */   }
/*    */   
/*    */   public static interface Backed {
/*    */     void zakoopt$attach(class_1921 param1class_1921, long param1Long, int param1Int);
/*    */     
/*    */     boolean zakoopt$ringBacked();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\RingBuilders.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */