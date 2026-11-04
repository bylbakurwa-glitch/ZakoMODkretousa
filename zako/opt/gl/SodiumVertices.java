/*    */ package zako.opt.gl;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import lombok.Generated;
/*    */ import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
/*    */ import net.minecraft.class_4588;
/*    */ import org.lwjgl.system.MemoryStack;
/*    */ 
/*    */ final class SodiumVertices {
/*    */   @Generated
/*    */   private SodiumVertices() {
/* 11 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   
/*    */   private static Object lastWriter;
/*    */   
/*    */   static boolean accepts(class_4588 consumer) {
/* 17 */     if (consumer == lastWriter) {
/* 18 */       return true;
/*    */     }
/* 20 */     if (VertexBufferWriter.tryOf(consumer) == null) {
/* 21 */       return false;
/*    */     }
/* 23 */     lastWriter = consumer;
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   static void push(class_4588 consumer, MemoryStack stack, long src, int count, VertexFormat format) {
/* 28 */     ((consumer == lastWriter) ? (VertexBufferWriter)consumer : VertexBufferWriter.of(consumer)).push(stack, src, count, format);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\SodiumVertices.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */