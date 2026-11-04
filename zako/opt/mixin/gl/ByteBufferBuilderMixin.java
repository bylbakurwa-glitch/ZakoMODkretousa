/*    */ package zako.opt.mixin.gl;
/*    */ 
/*    */ import com.mojang.jtracy.MemoryPool;
/*    */ import net.minecraft.class_1921;
/*    */ import net.minecraft.class_9799;
/*    */ import org.lwjgl.system.MemoryUtil;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import zako.opt.gl.ImmediateRing;
/*    */ import zako.opt.gl.RingBuilders;
/*    */ 
/*    */ @Mixin({class_9799.class})
/*    */ public class ByteBufferBuilderMixin implements RingBuilders.Backed {
/*    */   @Shadow
/*    */   @Final
/*    */   private static MemoryPool field_54266;
/*    */   @Shadow
/*    */   long field_52082;
/*    */   @Shadow
/*    */   private long field_52083;
/*    */   @Shadow
/*    */   private long field_52084;
/*    */   @Shadow
/*    */   private int field_52087;
/*    */   @Shadow
/*    */   private long field_52085;
/*    */   @Shadow
/*    */   private int field_52086;
/*    */   @Unique
/* 35 */   private static int zakoopt$generation = 16777216;
/*    */   
/*    */   @Unique
/*    */   private class_1921 zakoopt$type;
/*    */   
/*    */   @Unique
/*    */   private int zakoopt$state;
/*    */ 
/*    */   
/*    */   public void zakoopt$attach(class_1921 type, long address, int size) {
/* 45 */     if (this.field_52082 != 0L) {
/*    */       
/* 47 */       field_54266.free(this.field_52082);
/* 48 */       MemoryUtil.getAllocator(false).free(this.field_52082);
/*    */     } 
/* 50 */     this.field_52082 = address;
/* 51 */     this.field_52083 = size;
/* 52 */     this.field_52084 = 0L;
/* 53 */     this.field_52085 = 0L;
/* 54 */     this.field_52086 = 0;
/*    */     
/* 56 */     this.field_52087 = zakoopt$generation = zakoopt$generation + 1 & Integer.MAX_VALUE;
/* 57 */     this.zakoopt$type = type;
/* 58 */     this.zakoopt$state = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean zakoopt$ringBacked() {
/* 63 */     return (this.zakoopt$state != 0);
/*    */   }
/*    */   
/*    */   @Inject(method = {"method_60812"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$grow(long size, CallbackInfo ci) {
/* 68 */     if (this.zakoopt$state != 1) {
/*    */       return;
/*    */     }
/* 71 */     long address = ImmediateRing.reserveBuilder(this.zakoopt$type.method_23031(), (int)size);
/*    */     
/* 73 */     ImmediateRing.builderReleased();
/* 74 */     if (address == 0L) {
/* 75 */       address = MemoryUtil.nmemAlloc(size);
/* 76 */       this.zakoopt$state = 2;
/*    */     } 
/* 78 */     MemoryUtil.memCopy(this.field_52082, address, this.field_52084);
/* 79 */     this.field_52082 = address;
/* 80 */     this.field_52083 = size;
/* 81 */     ci.cancel();
/*    */   }
/*    */   
/*    */   @Inject(method = {"close"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void zakoopt$close(CallbackInfo ci) {
/* 86 */     if (this.zakoopt$state == 0 || this.field_52082 == 0L) {
/*    */       return;
/*    */     }
/* 89 */     RingBuilders.remember(this.zakoopt$type, this.field_52083);
/* 90 */     if (this.zakoopt$state == 1) {
/* 91 */       ImmediateRing.builderReleased();
/* 92 */       this.field_52082 = 0L;
/* 93 */       this.field_52087 = -1;
/* 94 */       ci.cancel();
/*    */     } else {
/* 96 */       MemoryUtil.nmemFree(this.field_52082);
/* 97 */       this.field_52082 = 0L;
/* 98 */       this.field_52087 = -1;
/* 99 */       ci.cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\mixin\gl\ByteBufferBuilderMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */