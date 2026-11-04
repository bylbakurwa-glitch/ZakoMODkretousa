/*    */ package zako.opt.entity;
/*    */ 
/*    */ import net.minecraft.class_1011;
/*    */ import net.minecraft.class_1058;
/*    */ import net.minecraft.class_2960;
/*    */ import net.minecraft.class_7764;
/*    */ import net.minecraft.class_7771;
/*    */ 
/*    */ public class AtlasSprite
/*    */   extends class_1058 {
/*    */   public AtlasSprite(class_2960 atlas, class_7764 contents, int atlasSize, int x, int y) {
/* 12 */     super(atlas, contents, atlasSize, atlasSize, x, y, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class_7764 contents(class_2960 id, int size, class_1011 image) {
/* 17 */     return new class_7764(id, new class_7771(size, size), image);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\AtlasSprite.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */