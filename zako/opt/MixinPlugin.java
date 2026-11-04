/*    */ package zako.opt;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import org.objectweb.asm.tree.ClassNode;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
/*    */ 
/*    */ public class MixinPlugin
/*    */   implements IMixinConfigPlugin
/*    */ {
/* 13 */   public static final boolean SODIUM = FabricLoader.getInstance().isModLoaded("sodium");
/* 14 */   public static final boolean VULKAN = FabricLoader.getInstance().isModLoaded("vulkanmod");
/*    */   
/* 16 */   public static final boolean LUNAR = (MixinPlugin.class.getClassLoader().getResource("com/moonsworth/lunar/genesis/Genesis.class") != null);
/* 17 */   private static final List<String> LUNAR_MIXINS = List.of("gl.LunarMultiDrawMixin", "gl.LunarSodiumMultiDrawMixin", "block.BlockEntityRenderDispatcherCullMixin");
/* 18 */   private static final List<String> NO_LUNAR_MIXINS = List.of("block.BlockEntityRenderDispatcherCullWrapMixin");
/* 19 */   public static final List<String> SODIUM_MIXINS = List.of("entity.SodiumEntityRendererMixin", "gl.VertexConsumerUtilsMixin", "block.NonTerrainBlockRenderContextMixin", "gl.LunarSodiumMultiDrawMixin", "gl.SodiumOcclusionCullMixin", "gl.SodiumTerrainTimerMixin", "block.SodiumLeafCullMixin", "block.SodiumLeafCullOldMixin", "block.SodiumFarLeavesMixin", "gl.SodiumCubicSamplerMixin");
/*    */ 
/*    */   
/* 22 */   private static final List<String> NO_SODIUM_MIXINS = List.of("entity.ModelPartFastMixin");
/*    */   
/* 24 */   private static final List<String> RING_MIXINS = List.of("gl.BatchableBufferSourceMixin", "gl.BufferUploaderMixin", "gl.ByteBufferBuilderMixin", "gl.ByteBufferBuilderPoolMixin", "gl.RenderTypeDrawMixin");
/*    */ 
/*    */   
/*    */   private static final String OLD_LEAF_CULL_TARGET = "net/caffeinemc/mods/sodium/client/render/chunk/compile/pipeline/BlockOcclusionCache.class";
/*    */ 
/*    */   
/*    */   private static boolean sodiumAdded;
/*    */ 
/*    */   
/*    */   public List<String> getMixins() {
/* 34 */     if (!SODIUM || sodiumAdded) {
/* 35 */       return null;
/*    */     }
/* 37 */     sodiumAdded = true;
/* 38 */     return SODIUM_MIXINS.stream().filter(m -> (built(m) && (LUNAR || !LUNAR_MIXINS.contains(m)) && (!m.equals("block.SodiumLeafCullOldMixin") || MixinPlugin.class.getClassLoader().getResource("net/caffeinemc/mods/sodium/client/render/chunk/compile/pipeline/BlockOcclusionCache.class") != null)))
/* 39 */       .toList();
/*    */   }
/*    */   
/*    */   public static boolean built(String mixin) {
/* 43 */     return (MixinPlugin.class.getClassLoader().getResource("zako/opt/mixin/" + mixin.replace('.', '/') + ".class") != null);
/*    */   }
/*    */   
/*    */   public static boolean applies(String mixin) {
/* 47 */     return (built(mixin) && (SODIUM || !SODIUM_MIXINS.contains(mixin)) && (!SODIUM || !NO_SODIUM_MIXINS.contains(mixin)) && (!VULKAN || !openGl(mixin)) && (LUNAR || 
/* 48 */       !LUNAR_MIXINS.contains(mixin)) && (!LUNAR || !NO_LUNAR_MIXINS.contains(mixin)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean openGl(String mixin) {
/* 55 */     return (mixin.startsWith("gl.Gl") || RING_MIXINS.contains(mixin) || (mixin.startsWith("hud.") && !mixin.equals("hud.ChatComponentBatchMixin")) || mixin
/* 56 */       .endsWith("TimerMixin") || mixin.equals("gl.RenderTargetBlitMixin") || mixin
/* 57 */       .equals("gl.GameRendererScaleMixin"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
/* 62 */     String mixin = mixinClassName.substring("zako.opt.mixin.".length());
/* 63 */     return ((!VULKAN || !openGl(mixin)) && (!SODIUM || !NO_SODIUM_MIXINS.contains(mixin)) && (LUNAR || !LUNAR_MIXINS.contains(mixin)) && (!LUNAR || 
/* 64 */       !NO_LUNAR_MIXINS.contains(mixin)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onLoad(String mixinPackage) {}
/*    */ 
/*    */   
/*    */   public String getRefMapperConfig() {
/* 73 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
/*    */ 
/*    */   
/*    */   public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
/* 86 */     GuardWeaver.weave(targetClass, mixinClassName, mixinInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\MixinPlugin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */