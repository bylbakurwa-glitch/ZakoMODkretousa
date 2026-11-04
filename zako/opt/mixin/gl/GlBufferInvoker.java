/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.class_10859;
/*    */ import net.minecraft.class_10874;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.gen.Invoker;
/*    */ 
/*    */ 
/*    */ @Mixin({class_10859.class})
/*    */ public interface GlBufferInvoker
/*    */ {
/*    */   @Invoker("<init>")
/*    */   static class_10859 zakoopt$create(Supplier<String> label, class_10874 dsa, int usage, long size, int handle, ByteBuffer persistent) {
/* 16 */     throw new AssertionError();
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\GlBufferInvoker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */