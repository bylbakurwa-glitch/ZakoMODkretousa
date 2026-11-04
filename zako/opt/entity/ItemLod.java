/*    */ package zako.opt.entity;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2350;
/*    */ import net.minecraft.class_777;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class ItemLod {
/*    */   private static final float EDGE = 0.0626F;
/*    */   
/*    */   @Generated
/*    */   private ItemLod() {
/* 14 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/* 17 */   private static final Map<class_777, Flat> FLAT = (new MapMaker()).weakKeys().makeMap();
/*    */   
/*    */   public static boolean enabled() {
/* 20 */     return Features.ITEM_LOD.on;
/*    */   }
/*    */   
/*    */   public static float distanceSq() {
/* 24 */     float d = Features.ITEM_LOD.distance;
/* 25 */     return d * d;
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<class_777> flat(List<class_777> quads) {
/* 30 */     if (quads.isEmpty()) {
/* 31 */       return quads;
/*    */     }
/* 33 */     Flat cached = FLAT.get(quads.getFirst());
/* 34 */     if (cached == null || cached.sourceSize != quads.size()) {
/* 35 */       cached = new Flat(quads.size(), compute(quads));
/* 36 */       FLAT.put(quads.getFirst(), cached);
/*    */     } 
/* 38 */     return (cached.quads == null) ? quads : cached.quads;
/*    */   }
/*    */   private static final class Flat extends Record { private final int sourceSize; private final List<class_777> quads;
/* 41 */     private Flat(int sourceSize, List<class_777> quads) { this.sourceSize = sourceSize; this.quads = quads; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/entity/ItemLod$Flat;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 41 */       //   0	7	0	this	Lzako/opt/entity/ItemLod$Flat; } public int sourceSize() { return this.sourceSize; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/entity/ItemLod$Flat;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/entity/ItemLod$Flat; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/entity/ItemLod$Flat;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/entity/ItemLod$Flat;
/* 41 */       //   0	8	1	o	Ljava/lang/Object; } public List<class_777> quads() { return this.quads; }
/*    */      }
/*    */   
/*    */   private static List<class_777> compute(List<class_777> quads) {
/* 45 */     if (quads.size() < 8) {
/* 46 */       return null;
/*    */     }
/* 48 */     for (class_777 q : quads) {
/* 49 */       if (Quads.direction(q).method_10166() != class_2350.class_2351.field_11051 && !thin(q)) {
/* 50 */         return null;
/*    */       }
/*    */     } 
/* 53 */     List<class_777> faces = List.copyOf(quads.stream().filter(q -> (Quads.direction(q).method_10166() == class_2350.class_2351.field_11051)).toList());
/* 54 */     return faces.isEmpty() ? null : faces;
/*    */   }
/*    */   
/*    */   private static boolean thin(class_777 q) {
/* 58 */     float minZ = Float.MAX_VALUE, maxZ = -3.4028235E38F;
/* 59 */     for (int i = 0; i < 4; i++) {
/* 60 */       float z = Quads.z(q, i);
/* 61 */       minZ = Math.min(minZ, z);
/* 62 */       maxZ = Math.max(maxZ, z);
/*    */     } 
/* 64 */     return (maxZ - minZ <= 0.0626F);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\ItemLod.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */