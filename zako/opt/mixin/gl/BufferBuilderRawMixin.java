/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import net.minecraft.class_287;
/*    */ import net.minecraft.class_9799;
/*    */ import org.lwjgl.system.MemoryUtil;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import zako.opt.gl.RawBuffer;
/*    */ 
/*    */ @Mixin({class_287.class})
/*    */ public class BufferBuilderRawMixin
/*    */   implements RawBuffer
/*    */ {
/*    */   @Shadow
/*    */   private int field_1554;
/*    */   @Shadow
/*    */   @Final
/*    */   private int field_52074;
/*    */   @Shadow
/*    */   private long field_52072;
/*    */   @Shadow
/*    */   @Final
/*    */   private class_9799 field_52071;
/*    */   @Shadow
/*    */   private int field_52077;
/*    */   @Shadow
/*    */   @Final
/*    */   private VertexFormat field_1565;
/*    */   
/*    */   public boolean zakoopt$accepts(VertexFormat format) {
/* 33 */     return (format == this.field_1565);
/*    */   }
/*    */ 
/*    */   
/*    */   public void zakoopt$push(long src, int count) {
/* 38 */     int length = count * this.field_52074;
/* 39 */     long dst = this.field_52071.method_60808(length);
/* 40 */     MemoryUtil.memCopy(src, dst, length);
/* 41 */     this.field_1554 += count;
/*    */     
/* 43 */     this.field_52072 = dst + length - this.field_52074;
/* 44 */     this.field_52077 = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\BufferBuilderRawMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */