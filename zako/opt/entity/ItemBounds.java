/*    */ package zako.opt.entity;
/*    */ 
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_238;
/*    */ import net.minecraft.class_804;
/*    */ 
/*    */ public final class ItemBounds
/*    */ {
/*    */   @Generated
/*    */   private ItemBounds() {
/* 14 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 15 */   } private static final Map<Object, Entry> BOUNDS = new IdentityHashMap<>();
/*    */   private static final class Entry extends Record { private final class_804 transform; private final boolean leftHand; private final class_238 bounds;
/* 17 */     private Entry(class_804 transform, boolean leftHand, class_238 bounds) { this.transform = transform; this.leftHand = leftHand; this.bounds = bounds; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/entity/ItemBounds$Entry;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #17	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 17 */       //   0	7	0	this	Lzako/opt/entity/ItemBounds$Entry; } public class_804 transform() { return this.transform; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/entity/ItemBounds$Entry;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #17	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/entity/ItemBounds$Entry; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/entity/ItemBounds$Entry;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #17	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/entity/ItemBounds$Entry;
/* 17 */       //   0	8	1	o	Ljava/lang/Object; } public boolean leftHand() { return this.leftHand; } public class_238 bounds() { return this.bounds; }
/*    */      }
/*    */   
/*    */   public static class_238 get(Object extents, class_804 transform, boolean leftHand, Supplier<class_238> compute) {
/* 21 */     Entry e = BOUNDS.get(extents);
/* 22 */     if (e != null && e.transform == transform && e.leftHand == leftHand) {
/* 23 */       return e.bounds;
/*    */     }
/* 25 */     class_238 bounds = compute.get();
/*    */     
/* 27 */     if (BOUNDS.size() > 4096) {
/* 28 */       BOUNDS.clear();
/*    */     }
/* 30 */     BOUNDS.put(extents, new Entry(transform, leftHand, bounds));
/* 31 */     return bounds;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\ItemBounds.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */