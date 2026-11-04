/*    */ package zako.opt.gl;
/*    */ import lombok.Generated;
/*    */ import net.caffeinemc.mods.sodium.client.render.chunk.data.SectionRenderDataUnsafe;
/*    */ 
/*    */ public final class SodiumSections {
/*    */   @Generated
/*    */   private SodiumSections() {
/*  8 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   } public static long count(long data, int facing) {
/* 10 */     return SectionRenderDataUnsafe.getVertexCount(data, facing);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\SodiumSections.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */