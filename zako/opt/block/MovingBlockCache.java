/*     */ package zako.opt.block;
/*     */ 
/*     */ import com.mojang.blaze3d.vertex.VertexFormat;
/*     */ import com.mojang.blaze3d.vertex.VertexFormatElement;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import lombok.Generated;
/*     */ import net.fabricmc.fabric.api.renderer.v1.render.RenderLayerHelper;
/*     */ import net.minecraft.class_1087;
/*     */ import net.minecraft.class_11661;
/*     */ import net.minecraft.class_11791;
/*     */ import net.minecraft.class_1920;
/*     */ import net.minecraft.class_1921;
/*     */ import net.minecraft.class_2680;
/*     */ import net.minecraft.class_287;
/*     */ import net.minecraft.class_310;
/*     */ import net.minecraft.class_4587;
/*     */ import net.minecraft.class_4588;
/*     */ import net.minecraft.class_4597;
/*     */ import net.minecraft.class_4608;
/*     */ import net.minecraft.class_761;
/*     */ import net.minecraft.class_776;
/*     */ import net.minecraft.class_9799;
/*     */ import net.minecraft.class_9801;
/*     */ import org.joml.Matrix4f;
/*     */ import org.joml.Vector3f;
/*     */ import org.lwjgl.system.MemoryStack;
/*     */ import org.lwjgl.system.MemoryUtil;
/*     */ import zako.opt.feature.Features;
/*     */ import zako.opt.gl.RawVertices;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MovingBlockCache
/*     */ {
/*     */   @Generated
/*     */   private MovingBlockCache() {
/*  41 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*  42 */   } private static final Map<Key, Entry> CACHE = new LinkedHashMap<Key, Entry>(64, 0.75F, true)
/*     */     {
/*     */       protected boolean removeEldestEntry(Map.Entry<MovingBlockCache.Key, MovingBlockCache.Entry> eldest) {
/*  45 */         if (size() <= 1024) {
/*  46 */           return false;
/*     */         }
/*  48 */         MovingBlockCache.free(eldest.getValue());
/*  49 */         return true;
/*     */       }
/*     */     };
/*     */   private static final class Key { class_2680 state; int tint;
/*     */     
/*     */     @Generated
/*  55 */     public Key(class_2680 state, int tint) { this.state = state; this.tint = tint; } @Generated
/*  56 */     public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof Key)) return false;  Key other = (Key)o; if (this.tint != other.tint) return false;  Object this$state = this.state, other$state = other.state; return !((this$state == null) ? (other$state != null) : !this$state.equals(other$state)); } @Generated public int hashCode() { int PRIME = 59; result = 1; result = result * 59 + this.tint; Object $state = this.state; return result * 59 + (($state == null) ? 43 : $state.hashCode()); }
/*     */      }
/*     */    private static final class Layer extends Record { private final class_1921 type; private final ByteBuffer vertices; private final int count; public final String toString() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/block/MovingBlockCache$Layer;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #62	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/block/MovingBlockCache$Layer;
/*     */     } public final int hashCode() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/block/MovingBlockCache$Layer;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #62	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/block/MovingBlockCache$Layer;
/*  62 */     } private Layer(class_1921 type, ByteBuffer vertices, int count) { this.type = type; this.vertices = vertices; this.count = count; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/block/MovingBlockCache$Layer;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #62	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/block/MovingBlockCache$Layer;
/*  62 */       //   0	8	1	o	Ljava/lang/Object; } public class_1921 type() { return this.type; } public ByteBuffer vertices() { return this.vertices; } public int count() { return this.count; }
/*     */      }
/*     */   private static final class Entry extends Record { private final class_1087 model; private final MovingBlockCache.Layer[] layers;
/*  65 */     private Entry(class_1087 model, MovingBlockCache.Layer[] layers) { this.model = model; this.layers = layers; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/block/MovingBlockCache$Entry;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/block/MovingBlockCache$Entry; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/block/MovingBlockCache$Entry;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/block/MovingBlockCache$Entry; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/block/MovingBlockCache$Entry;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #65	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/block/MovingBlockCache$Entry;
/*  65 */       //   0	8	1	o	Ljava/lang/Object; } public class_1087 model() { return this.model; } public MovingBlockCache.Layer[] layers() { return this.layers; }
/*     */      }
/*     */ 
/*     */   
/*  69 */   private static final Key LOOKUP = new Key(null, 0);
/*  70 */   private static final Vector3f TMP = new Vector3f();
/*     */ 
/*     */   
/*     */   public static void render(List<class_11661.class_11790> submits, class_4597.class_4598 buffers, class_776 dispatcher) {
/*  74 */     if (!Features.MOVING_BLOCK_CACHE.on || submits.isEmpty()) {
/*     */       return;
/*     */     }
/*  77 */     submits.removeIf(submit -> {
/*     */           class_11791 state = submit.comp_4653();
/*     */           class_1087 model = dispatcher.method_3349(state.field_62247);
/*     */           if (!(model instanceof net.minecraft.class_10893)) {
/*     */             return false;
/*     */           }
/*     */           LOOKUP.state = state.field_62247;
/*     */           LOOKUP.tint = class_310.method_1551().method_1505().method_1697(state.field_62247, (class_1920)state, state.field_62246, 0);
/*     */           Entry entry = CACHE.get(LOOKUP);
/*     */           if (entry == null || entry.model != model) {
/*     */             if (entry != null) {
/*     */               free(entry);
/*     */             }
/*     */             entry = capture(state, model, dispatcher);
/*     */             CACHE.put(new Key(LOOKUP.state, LOOKUP.tint), entry);
/*     */           } 
/*     */           Layer[] layers = entry.layers;
/*     */           for (Layer layer : layers) {
/*     */             if (!RawVertices.accepts(buffers.method_73477(layer.type), layer.type.method_23031())) {
/*     */               return false;
/*     */             }
/*     */           } 
/*     */           int light = class_761.method_23793(class_761.class_10948.field_58200, (class_1920)state, state.field_62247, state.field_62246);
/*     */           for (Layer layer : layers) {
/*     */             emit(layer, submit.comp_4652(), light, buffers.method_73477(layer.type));
/*     */           }
/*     */           return true;
/*     */         });
/*     */   }
/*     */   
/*     */   private static void free(Entry entry) {
/* 108 */     for (Layer layer : entry.layers) {
/* 109 */       MemoryUtil.memFree(layer.vertices);
/*     */     }
/*     */   }
/*     */   
/*     */   private static Entry capture(class_11791 state, class_1087 model, class_776 dispatcher) {
/* 114 */     Map<class_1921, class_9799> bytes = new LinkedHashMap<>();
/* 115 */     Map<class_1921, class_287> builders = new LinkedHashMap<>();
/* 116 */     class_4597 source = type -> (class_4588)builders.computeIfAbsent(type, ());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     dispatcher.method_3350().render((class_1920)state, model, state.field_62247, state.field_62246, new class_4587(), 
/* 122 */         RenderLayerHelper.movingDelegate(source), false, state.field_62247.method_26190(state.field_62245), class_4608.field_21444);
/* 123 */     List<Layer> layers = new ArrayList<>();
/* 124 */     builders.forEach((type, builder) -> { class_9801 mesh = builder.method_60794(); try { if (mesh != null) { ByteBuffer src = mesh.method_60818(); ByteBuffer copy = MemoryUtil.memAlloc(src.remaining()); MemoryUtil.memCopy(src, copy); layers.add(new Layer(type, copy, mesh.method_60822().comp_750())); }  if (mesh != null)
/* 125 */               mesh.close();  } catch (Throwable t$) { if (mesh != null) try { mesh.close(); } catch (Throwable x2)
/*     */               { t$.addSuppressed(x2); }
/*     */             
/*     */ 
/*     */             
/*     */             throw t$; }
/*     */         
/*     */         });
/*     */     
/* 134 */     bytes.values().forEach(class_9799::close);
/* 135 */     return new Entry(model, layers.<Layer>toArray(new Layer[0]));
/*     */   }
/*     */   
/*     */   private static void emit(Layer layer, Matrix4f pose, int light, class_4588 target) {
/* 139 */     VertexFormat format = layer.type.method_23031();
/* 140 */     int stride = format.getVertexSize();
/* 141 */     int pos = format.getOffset(VertexFormatElement.POSITION);
/* 142 */     int uv2 = format.contains(VertexFormatElement.UV2) ? format.getOffset(VertexFormatElement.UV2) : -1;
/* 143 */     int size = layer.count * stride;
/* 144 */     Vector3f tmp = TMP;
/* 145 */     MemoryStack stack = MemoryStack.stackPush(); try {
/* 146 */       long dst = stack.nmalloc(16, size);
/* 147 */       MemoryUtil.memCopy(MemoryUtil.memAddress(layer.vertices), dst, size); long v, end;
/* 148 */       for (v = dst, end = dst + size; v < end; v += stride) {
/* 149 */         pose.transformPosition(MemoryUtil.memGetFloat(v + pos), MemoryUtil.memGetFloat(v + pos + 4L), MemoryUtil.memGetFloat(v + pos + 8L), tmp);
/* 150 */         MemoryUtil.memPutFloat(v + pos, tmp.x);
/* 151 */         MemoryUtil.memPutFloat(v + pos + 4L, tmp.y);
/* 152 */         MemoryUtil.memPutFloat(v + pos + 8L, tmp.z);
/* 153 */         if (uv2 >= 0) {
/* 154 */           MemoryUtil.memPutInt(v + uv2, light);
/*     */         }
/*     */       } 
/* 157 */       RawVertices.push(target, stack, dst, layer.count, format);
/* 158 */       if (stack != null) stack.close(); 
/*     */     } catch (Throwable throwable) {
/*     */       if (stack != null)
/*     */         try {
/*     */           stack.close();
/*     */         } catch (Throwable throwable1) {
/*     */           throwable.addSuppressed(throwable1);
/*     */         }  
/*     */       throw throwable;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\MovingBlockCache.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */