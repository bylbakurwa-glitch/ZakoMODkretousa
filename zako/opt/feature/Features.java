/*     */ package zako.opt.feature;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import zako.opt.feature.blocks.FarLeavesFeature;
/*     */ import zako.opt.feature.blocks.FewerAllocationsFeature;
/*     */ import zako.opt.feature.blocks.LeafCullFeature;
/*     */ import zako.opt.feature.blocks.MicroOptsFeature;
/*     */ import zako.opt.feature.blocks.MovingBlockCacheFeature;
/*     */ import zako.opt.feature.items.EntityLodFeature;
/*     */ import zako.opt.feature.items.ItemLodFeature;
/*     */ import zako.opt.feature.items.RestingItemsFeature;
/*     */ import zako.opt.feature.items.StatePoolFeature;
/*     */ import zako.opt.feature.render.LunarMultiDrawFeature;
/*     */ import zako.opt.feature.render.NoItemSortFeature;
/*     */ import zako.opt.feature.render.OutlineSkipFeature;
/*     */ import zako.opt.feature.spawners.SpawnerReplayFeature;
/*     */ import zako.opt.feature.ui.GuiAnimatedItemsFeature;
/*     */ import zako.opt.feature.ui.PreparedTextCacheFeature;
/*     */ 
/*     */ public final class Features {
/*     */   @Generated
/*  25 */   private static final Logger log = LoggerFactory.getLogger("zakoopt"); @Generated
/*  26 */   private Features() { throw new UnsupportedOperationException("This is a utility class and cannot be instantiated"); }
/*  27 */    private static final List<Feature> LIST = new ArrayList<>();
/*     */   
/*  29 */   public static final CorePinFeature CORE_PIN = add(new CorePinFeature());
/*  30 */   public static final FrameFenceFeature FRAME_FENCE = add(new FrameFenceFeature());
/*  31 */   public static final LunarMultiDrawFeature LUNAR_MULTIDRAW = add(new LunarMultiDrawFeature());
/*  32 */   public static final LazyClearFeature LAZY_CLEAR = add(new LazyClearFeature());
/*  33 */   public static final NoEntitySortFeature NO_ENTITY_SORT = add(new NoEntitySortFeature());
/*  34 */   public static final NoItemSortFeature NO_ITEM_SORT = add(new NoItemSortFeature());
/*  35 */   public static final SkinAtlasFeature SKIN_ATLAS = add(new SkinAtlasFeature());
/*  36 */   public static final WriterCacheFeature WRITER_CACHE = add(new WriterCacheFeature());
/*  37 */   public static final FboShareFeature FBO_SHARE = add(new FboShareFeature());
/*  38 */   public static final OutlineSkipFeature OUTLINE_SKIP = add(new OutlineSkipFeature());
/*  39 */   public static final DisplayCullFeature DISPLAY_CULL = add(new DisplayCullFeature());
/*  40 */   public static final ImmediateRingFeature IMMEDIATE_RING = add(new ImmediateRingFeature());
/*  41 */   public static final RingZeroCopyFeature RING_ZERO_COPY = add(new RingZeroCopyFeature());
/*  42 */   public static final FastBlitFeature FAST_BLIT = add(new FastBlitFeature());
/*  43 */   public static final RenderScaleFeature RENDER_SCALE = add(new RenderScaleFeature());
/*  44 */   public static final OcclusionCullFeature OCCLUSION_CULL = add(new OcclusionCullFeature());
/*     */   
/*  46 */   public static final ItemLodFeature ITEM_LOD = add(new ItemLodFeature());
/*  47 */   public static final ItemSingleCopyFeature ITEM_SINGLE_COPY = add(new ItemSingleCopyFeature());
/*  48 */   public static final RestingItemsFeature RESTING_ITEMS = add(new RestingItemsFeature());
/*  49 */   public static final ParallelModelsFeature PARALLEL_MODELS = add(new ParallelModelsFeature());
/*  50 */   public static final StatePoolFeature STATE_POOL = add(new StatePoolFeature());
/*  51 */   public static final EntityLodFeature ENTITY_LOD = add(new EntityLodFeature());
/*  52 */   public static final PlayerLodFeature PLAYER_LOD = add(new PlayerLodFeature());
/*  53 */   public static final EntityAnimFreezeFeature ENTITY_ANIM_FREEZE = add(new EntityAnimFreezeFeature());
/*     */   
/*  55 */   public static final SpawnerCullFeature SPAWNER_CULL = add(new SpawnerCullFeature());
/*  56 */   public static final SpawnerTickCacheFeature SPAWNER_TICK_CACHE = add(new SpawnerTickCacheFeature());
/*  57 */   public static final SpawnerReplayFeature SPAWNER_REPLAY = add(new SpawnerReplayFeature());
/*     */   
/*  59 */   public static final LeafCullFeature LEAF_CULL = add(new LeafCullFeature());
/*  60 */   public static final FarLeavesFeature FAR_LEAVES = add(new FarLeavesFeature());
/*  61 */   public static final MovingBlockFlatLightFeature MOVING_BLOCK_FLAT_LIGHT = add(new MovingBlockFlatLightFeature());
/*  62 */   public static final MovingBlockCacheFeature MOVING_BLOCK_CACHE = add(new MovingBlockCacheFeature());
/*  63 */   public static final BlockEntityCacheFeature BLOCK_ENTITY_CACHE = add(new BlockEntityCacheFeature());
/*  64 */   public static final BlockEntityEarlyCullFeature BLOCK_ENTITY_EARLY_CULL = add(new BlockEntityEarlyCullFeature());
/*  65 */   public static final SignCacheFeature SIGN_CACHE = add(new SignCacheFeature());
/*  66 */   public static final LookupCachesFeature LOOKUP_CACHES = add(new LookupCachesFeature());
/*  67 */   public static final MicroOptsFeature MICRO_OPTS = add(new MicroOptsFeature());
/*  68 */   public static final FewerAllocationsFeature FEWER_ALLOCATIONS = add(new FewerAllocationsFeature());
/*     */   
/*  70 */   public static final ParticleLightFeature PARTICLE_LIGHT = add(new ParticleLightFeature());
/*  71 */   public static final ParticlePhysicsFeature PARTICLE_PHYSICS = add(new ParticlePhysicsFeature());
/*  72 */   public static final ParticleLodFeature PARTICLE_LOD = add(new ParticleLodFeature());
/*  73 */   public static final ParallelParticlesFeature PARALLEL_PARTICLES = add(new ParallelParticlesFeature());
/*     */   
/*  75 */   public static final HudCacheFeature HUD_CACHE = add(new HudCacheFeature());
/*  76 */   public static final GuiAnimatedItemsFeature GUI_ANIMATED_ITEMS = add(new GuiAnimatedItemsFeature());
/*  77 */   public static final BidiCacheFeature BIDI_CACHE = add(new BidiCacheFeature());
/*  78 */   public static final PreparedTextCacheFeature PREPARED_TEXT_CACHE = add(new PreparedTextCacheFeature());
/*  79 */   public static final UpdateCheckFeature UPDATE_CHECK = add(new UpdateCheckFeature());
/*     */ 
/*     */   
/*  82 */   private static final Feature[] ALL = LIST.<Feature>toArray(new Feature[0]); public static boolean active; public static boolean terrainChanged;
/*     */   
/*     */   private static <T extends Feature> T add(T feature) {
/*  85 */     LIST.add((Feature)feature);
/*  86 */     return feature;
/*     */   }
/*     */   
/*     */   public static List<Feature> all() {
/*  90 */     return List.of(ALL);
/*     */   }
/*     */   
/*     */   public static Feature byKey(String key) {
/*  94 */     for (Feature f : ALL) {
/*  95 */       if (f.key().equals(key)) {
/*  96 */         return f;
/*     */       }
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean shaders() {
/* 106 */     return (ZakoOptConfig.IRIS && IrisApi.getInstance().isShaderPackInUse());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refresh() {
/* 113 */     active = (ZakoOptConfig.enabled && !"false".equals(System.getProperty("zakoopt.all")));
/* 114 */     for (Feature f : ALL) {
/* 115 */       f.refresh();
/*     */     }
/* 117 */     if (terrainChanged) {
/* 118 */       terrainChanged = false;
/* 119 */       Terrain.rebuild();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, Object> snapshot() {
/* 125 */     Map<String, Object> values = new LinkedHashMap<>();
/* 126 */     for (Feature f : ALL) {
/* 127 */       values.put(f.key(), Boolean.valueOf(f.value));
/* 128 */       for (Field s : f.settings()) {
/* 129 */         values.put(((Setting)s.<Setting>getAnnotation(Setting.class)).key(), f.get(s));
/*     */       }
/*     */     } 
/* 132 */     return values;
/*     */   }
/*     */   
/*     */   public static void restore(Map<String, Object> values) {
/* 136 */     for (Feature f : ALL) {
/* 137 */       f.value = ((Boolean)values.getOrDefault(f.key(), Boolean.valueOf(f.value))).booleanValue();
/* 138 */       for (Field s : f.settings()) {
/* 139 */         f.set(s, values.getOrDefault(((Setting)s.<Setting>getAnnotation(Setting.class)).key(), f.get(s)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, Object> defaults() {
/*     */     try {
/* 147 */       Map<String, Object> values = new LinkedHashMap<>();
/* 148 */       for (Feature f : ALL) {
/* 149 */         Feature fresh = f.getClass().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/* 150 */         values.put(f.key(), Boolean.valueOf(fresh.value));
/* 151 */         for (Field s : fresh.settings()) {
/* 152 */           values.put(((Setting)s.<Setting>getAnnotation(Setting.class)).key(), fresh.get(s));
/*     */         }
/*     */       } 
/* 155 */       return values;
/*     */     } catch (ReflectiveOperationException $ex) {
/*     */       throw $ex;
/*     */     } 
/* 159 */   } private static final Map<String, Object> LAUNCH_OVERRIDDEN = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static void read(JsonObject json) {
/* 164 */     for (Feature f : ALL) {
/* 165 */       String name = config(f.data().config(), f.key());
/* 166 */       if (json.has(name)) {
/* 167 */         f.value = jsonBoolean(json, name, f.value);
/*     */       }
/* 169 */       for (Field s : f.settings()) {
/* 170 */         Setting setting = s.<Setting>getAnnotation(Setting.class);
/* 171 */         String field = config(setting.config(), setting.key());
/* 172 */         if (json.has(field)) {
/* 173 */           f.set(s, jsonValue(json, field, s, f.get(s)));
/*     */         }
/* 175 */         String launch = System.getProperty("zakoopt." + setting.key());
/* 176 */         if (launch != null) {
/* 177 */           LAUNCH_OVERRIDDEN.put(setting.key(), f.get(s));
/* 178 */           f.set(s, launchValue(launch, setting.key(), s, f.get(s)));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean jsonBoolean(JsonObject json, String name, boolean fallback) {
/* 186 */     JsonElement e = json.get(name);
/* 187 */     if (e == null || !e.isJsonPrimitive() || !e.getAsJsonPrimitive().isBoolean()) {
/* 188 */       bad(name, e, Boolean.valueOf(fallback));
/* 189 */       return fallback;
/*     */     } 
/* 191 */     return e.getAsBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object jsonValue(JsonObject json, String name, Field setting, Object fallback) {
/*     */     int value;
/* 197 */     JsonElement e = json.get(name);
/* 198 */     if (setting.getType() != int.class) {
/* 199 */       return Boolean.valueOf(jsonBoolean(json, name, ((Boolean)fallback).booleanValue()));
/*     */     }
/*     */     
/*     */     try {
/* 203 */       value = e.getAsInt();
/* 204 */     } catch (RuntimeException ex) {
/* 205 */       bad(name, e, fallback);
/* 206 */       return fallback;
/*     */     } 
/* 208 */     return Integer.valueOf(inRange(setting, name, value));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object launchValue(String given, String name, Field setting, Object fallback) {
/* 213 */     if (setting.getType() != int.class) {
/* 214 */       return Boolean.valueOf(Boolean.parseBoolean(given));
/*     */     }
/*     */     try {
/* 217 */       return Integer.valueOf(inRange(setting, name, Integer.parseInt(given.trim())));
/* 218 */     } catch (NumberFormatException e) {
/* 219 */       log.warn("-Dzakoopt.{} is \"{}\", not a number; the configured value stands", name, given);
/* 220 */       return fallback;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int inRange(Field setting, String name, int value) {
/* 225 */     Range range = setting.<Range>getAnnotation(Range.class);
/* 226 */     if (range == null || (value >= range.min() && value <= range.max())) {
/* 227 */       return value;
/*     */     }
/* 229 */     int clamped = Math.min(range.max(), Math.max(range.min(), value));
/* 230 */     log.warn("{} is {}, pulled back to {} (its range is {}..{})", new Object[] { name, Integer.valueOf(value), Integer.valueOf(clamped), Integer.valueOf(range.min()), Integer.valueOf(range.max()) });
/* 231 */     return clamped;
/*     */   }
/*     */   
/*     */   private static void bad(String name, JsonElement e, Object fallback) {
/* 235 */     log.warn("{} in the config is {}, not a usable value; keeping {}", new Object[] { name, e, fallback });
/*     */   }
/*     */   
/*     */   public static void write(JsonObject json) {
/* 239 */     for (Feature f : ALL) {
/* 240 */       json.addProperty(config(f.data().config(), f.key()), Boolean.valueOf(f.value));
/* 241 */       for (Field s : f.settings()) {
/* 242 */         Setting setting = s.<Setting>getAnnotation(Setting.class);
/* 243 */         Object value = LAUNCH_OVERRIDDEN.getOrDefault(setting.key(), f.get(s));
/* 244 */         Number n = (Number)value; json.add(config(setting.config(), setting.key()), (value instanceof Number) ? (JsonElement)new JsonPrimitive(n) : (JsonElement)new JsonPrimitive((Boolean)value));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String config(String explicit, String key) {
/* 250 */     if (!explicit.isEmpty()) {
/* 251 */       return explicit;
/*     */     }
/* 253 */     StringBuilder s = new StringBuilder();
/* 254 */     boolean upper = false;
/* 255 */     for (char c : key.toCharArray()) {
/* 256 */       if (c == '_') {
/* 257 */         upper = true;
/*     */       } else {
/* 259 */         s.append(upper ? Character.toUpperCase(c) : c);
/* 260 */         upper = false;
/*     */       } 
/*     */     } 
/* 263 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\Features.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */