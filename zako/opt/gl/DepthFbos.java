/*    */ package zako.opt.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.opengl.GlStateManager;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_10868;
/*    */ import org.lwjgl.opengl.ARBDirectStateAccess;
/*    */ import org.lwjgl.opengl.GL;
/*    */ 
/*    */ public final class DepthFbos {
/*    */   @Generated
/*    */   private DepthFbos() {
/* 13 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 14 */   } private static final class Owner extends Record { private final class_10868 color; private final int fbo; private Owner(class_10868 color, int fbo) { this.color = color; this.fbo = fbo; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/gl/DepthFbos$Owner;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #14	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 14 */       //   0	7	0	this	Lzako/opt/gl/DepthFbos$Owner; } public class_10868 color() { return this.color; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/gl/DepthFbos$Owner;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #14	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/gl/DepthFbos$Owner; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/gl/DepthFbos$Owner;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #14	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/gl/DepthFbos$Owner;
/* 14 */       //   0	8	1	o	Ljava/lang/Object; } public int fbo() { return this.fbo; }
/*    */      }
/*    */   
/* 17 */   private static final Int2ObjectOpenHashMap<Owner> OWNERS = new Int2ObjectOpenHashMap();
/* 18 */   private static final float[] VALUE = new float[1];
/*    */   private static Boolean dsa;
/*    */   
/*    */   public static void remember(class_10868 color, int depthId, int fbo) {
/* 22 */     OWNERS.put(depthId, new Owner(color, fbo));
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean clear(int depthId, double depth) {
/* 27 */     if (dsa == null) {
/* 28 */       dsa = Boolean.valueOf(((GL.getCapabilities()).OpenGL45 || (GL.getCapabilities()).GL_ARB_direct_state_access));
/*    */     }
/* 30 */     Owner owner = (Owner)OWNERS.get(depthId);
/* 31 */     if (!dsa.booleanValue() || owner == null || owner.color.isClosed()) {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     GlStateManager._depthMask(true);
/* 36 */     GlStateManager._disableScissorTest();
/* 37 */     VALUE[0] = (float)depth;
/* 38 */     ARBDirectStateAccess.glClearNamedFramebufferfv(owner.fbo, 6145, 0, VALUE);
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\DepthFbos.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */