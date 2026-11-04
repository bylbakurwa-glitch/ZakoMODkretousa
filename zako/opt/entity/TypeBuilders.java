/*    */ package zako.opt.entity;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import com.mojang.blaze3d.vertex.VertexFormatElement;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_287;
/*    */ import net.minecraft.class_9799;
/*    */ 
/*    */ public final class TypeBuilders {
/*    */   @Generated
/*    */   private TypeBuilders() {
/* 11 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   } public static class_287 create(class_9799 buffer, class_1921 type) {
/* 13 */     return new class_287(buffer, type.method_23033(), type.method_23031());
/*    */   }
/*    */   
/*    */   public static int positionOffset(VertexFormat format) {
/* 17 */     return format.getOffset(VertexFormatElement.POSITION);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\TypeBuilders.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */