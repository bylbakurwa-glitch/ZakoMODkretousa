/*    */ package zako.opt.gl;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import lombok.Generated;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.minecraft.class_4588;
/*    */ import org.lwjgl.system.MemoryStack;
/*    */ 
/*    */ public final class RawVertices {
/*    */   @Generated
/*    */   private RawVertices() {
/* 11 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 12 */   } private static final boolean SODIUM = FabricLoader.getInstance().isModLoaded("sodium");
/*    */ 
/*    */   
/*    */   public static boolean accepts(class_4588 consumer, VertexFormat format) {
/* 16 */     if (consumer instanceof RawBuffer) { RawBuffer raw = (RawBuffer)consumer; if (!raw.zakoopt$accepts(format))
/* 17 */         return false;  }
/*    */     
/* 19 */     return SODIUM ? SodiumVertices.accepts(consumer) : (consumer instanceof RawBuffer);
/*    */   }
/*    */   
/*    */   public static void push(class_4588 consumer, MemoryStack stack, long src, int count, VertexFormat format) {
/* 23 */     if (SODIUM) {
/* 24 */       SodiumVertices.push(consumer, stack, src, count, format);
/*    */     } else {
/* 26 */       ((RawBuffer)consumer).zakoopt$push(src, count);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\RawVertices.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */