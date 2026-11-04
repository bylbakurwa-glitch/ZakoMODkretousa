/*    */ package zako.opt.gl;
/*    */ import com.mojang.blaze3d.textures.GpuTexture;
/*    */ import net.minecraft.class_10868;
/*    */ import net.minecraft.class_276;
/*    */ 
/*    */ public final class GlTargets {
/*    */   @Generated
/*    */   private GlTargets() {
/*  9 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*    */   }
/*    */   public static final boolean SAMPLERS = true;
/*    */   
/*    */   public static int depth(class_276 target) {
/* 14 */     GpuTexture gpuTexture = target.method_30278(); class_10868 t = (class_10868)gpuTexture; return (gpuTexture instanceof class_10868) ? t.method_68427() : -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\gl\GlTargets.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */