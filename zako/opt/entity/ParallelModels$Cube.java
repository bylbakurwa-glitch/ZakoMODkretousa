/*     */ package zako.opt.entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.class_630;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Cube
/*     */ {
/*     */   final float[] corners;
/*     */   final int[] index;
/*     */   final float[] uv;
/*     */   final float[] normals;
/*     */   final int[] sizes;
/*     */   
/*     */   Cube(class_630.class_628 cube) {
/* 280 */     List<Float> corner = new ArrayList<>();
/* 281 */     List<Integer> idx = new ArrayList<>();
/* 282 */     List<Float> uvs = new ArrayList<>();
/* 283 */     this.normals = new float[cube.field_3649.length * 3];
/* 284 */     this.sizes = new int[cube.field_3649.length];
/* 285 */     for (int q = 0; q < cube.field_3649.length; q++) {
/* 286 */       class_630.class_593 polygon = cube.field_3649[q];
/* 287 */       this.normals[q * 3] = polygon.comp_3185().x();
/* 288 */       this.normals[q * 3 + 1] = polygon.comp_3185().y();
/* 289 */       this.normals[q * 3 + 2] = polygon.comp_3185().z();
/* 290 */       this.sizes[q] = (polygon.comp_3184()).length;
/* 291 */       for (class_630.class_618 vertex : polygon.comp_3184()) {
/* 292 */         int found = -1;
/* 293 */         for (int i = 0; i < corner.size(); i += 3) {
/* 294 */           if (((Float)corner.get(i)).floatValue() == vertex.method_74250() && ((Float)corner.get(i + 1)).floatValue() == vertex.method_74251() && ((Float)corner.get(i + 2)).floatValue() == vertex.method_74252()) {
/* 295 */             found = i / 3;
/*     */             break;
/*     */           } 
/*     */         } 
/* 299 */         if (found < 0) {
/* 300 */           found = corner.size() / 3;
/* 301 */           corner.add(Float.valueOf(vertex.method_74250()));
/* 302 */           corner.add(Float.valueOf(vertex.method_74251()));
/* 303 */           corner.add(Float.valueOf(vertex.method_74252()));
/*     */         } 
/* 305 */         idx.add(Integer.valueOf(found));
/* 306 */         uvs.add(Float.valueOf(vertex.comp_3187()));
/* 307 */         uvs.add(Float.valueOf(vertex.comp_3188()));
/*     */       } 
/*     */     } 
/* 310 */     this.corners = new float[corner.size()]; int c;
/* 311 */     for (c = 0; c < this.corners.length; c++) {
/* 312 */       this.corners[c] = ((Float)corner.get(c)).floatValue();
/*     */     }
/* 314 */     this.index = idx.stream().mapToInt(Integer::intValue).toArray();
/* 315 */     this.uv = new float[uvs.size()];
/* 316 */     for (c = 0; c < this.uv.length; c++)
/* 317 */       this.uv[c] = ((Float)uvs.get(c)).floatValue(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\entity\ParallelModels$Cube.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */