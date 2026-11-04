/*    */ package zako.opt.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.buffers.GpuFence;
/*    */ import lombok.Generated;
/*    */ import org.lwjgl.opengl.GL11C;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ public final class FrameFence implements GpuFence {
/*    */   private static Shared pending;
/*    */   
/*    */   @Generated
/*    */   private FrameFence(Shared shared) {
/* 13 */     this.shared = shared;
/*    */   }
/*    */ 
/*    */   
/*    */   private final Shared shared;
/*    */   
/*    */   private boolean closed;
/*    */   
/*    */   public static boolean enabled() {
/* 22 */     return Features.FRAME_FENCE.on;
/*    */   }
/*    */   
/*    */   public static GpuFence create() {
/* 26 */     if (pending == null) {
/* 27 */       pending = new Shared();
/*    */     }
/* 29 */     pending.refs++;
/* 30 */     return new FrameFence(pending);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void endFrame() {
/* 35 */     if (pending != null) {
/* 36 */       if (pending.refs > 0) {
/* 37 */         pending.real = RealFence.create();
/*    */       }
/* 39 */       pending = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean awaitCompletion(long timeout) {
/* 47 */     if (this.shared.real == null) {
/* 48 */       this.shared.real = RealFence.create();
/*    */       
/* 50 */       GL11C.glFlush();
/* 51 */       if (pending == this.shared) {
/* 52 */         pending = null;
/*    */       }
/*    */     } 
/* 55 */     return this.shared.real.awaitCompletion(timeout);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 60 */     if (this.closed) {
/*    */       return;
/*    */     }
/* 63 */     this.closed = true;
/* 64 */     if (--this.shared.refs == 0 && this.shared.real != null)
/* 65 */       this.shared.real.close(); 
/*    */   }
/*    */   
/*    */   private static final class Shared {
/*    */     private GpuFence real;
/*    */     private int refs;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\FrameFence.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */