/*    */ package zako.opt;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ import lombok.Generated;
/*    */ import net.fabricmc.loader.api.FabricLoader;
/*    */ import net.fabricmc.loader.api.ModContainer;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Features;
/*    */ import zako.opt.feature.Range;
/*    */ import zako.opt.feature.Setting;
/*    */ 
/*    */ public final class ZakoOptions {
/*    */   @Generated
/*    */   private ZakoOptions() {
/* 20 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/* 21 */   } public static final String VERSION = ((ModContainer)FabricLoader.getInstance().getModContainer("zakoopt").orElseThrow()).getMetadata().getVersion().getFriendlyString();
/*    */   
/*    */   public static interface Option
/*    */   {
/*    */     String key();
/*    */     
/*    */     String[] requires();
/*    */     
/*    */     default boolean available() {
/* 30 */       return ((requires()).length == 0 || Arrays.<String>stream(requires())
/* 31 */         .anyMatch(MixinPlugin::applies));
/*    */     } }
/*    */   public static final class Toggle extends Record implements Option { private final String key; private final Consumer<Boolean> set; private final Supplier<Boolean> get; private final String[] requires;
/*    */     
/* 35 */     public Toggle(String key, Consumer<Boolean> set, Supplier<Boolean> get, String... requires) { this.key = key; this.set = set; this.get = get; this.requires = requires; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Toggle;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #35	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/* 35 */       //   0	7	0	this	Lzako/opt/ZakoOptions$Toggle; } public String key() { return this.key; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Toggle;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #35	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/ZakoOptions$Toggle; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Toggle;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #35	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/ZakoOptions$Toggle;
/* 35 */       //   0	8	1	o	Ljava/lang/Object; } public Consumer<Boolean> set() { return this.set; } public Supplier<Boolean> get() { return this.get; } public String[] requires() { return this.requires; }
/*    */      }
/*    */   public static final class Distance extends Record implements Option { private final String key; private final int min; private final int max; private final String unit; private final Consumer<Integer> set; private final Supplier<Integer> get; private final String[] requires;
/* 38 */     public Distance(String key, int min, int max, String unit, Consumer<Integer> set, Supplier<Integer> get, String... requires) { this.key = key; this.min = min; this.max = max; this.unit = unit; this.set = set; this.get = get; this.requires = requires; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Distance;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #38	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/ZakoOptions$Distance; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Distance;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #38	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/ZakoOptions$Distance; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Distance;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #38	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/ZakoOptions$Distance;
/* 38 */       //   0	8	1	o	Ljava/lang/Object; } public String key() { return this.key; } public int min() { return this.min; } public int max() { return this.max; } public String unit() { return this.unit; } public Consumer<Integer> set() { return this.set; } public Supplier<Integer> get() { return this.get; } public String[] requires() { return this.requires; }
/*    */      }
/*    */   public static final class Group extends Record { private final FeatureGroup type; private final List<ZakoOptions.Option> options;
/* 41 */     public Group(FeatureGroup type, List<ZakoOptions.Option> options) { this.type = type; this.options = options; } public final String toString() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> toString : (Lzako/opt/ZakoOptions$Group;)Ljava/lang/String;
/*    */       //   6: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/ZakoOptions$Group; } public final int hashCode() { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: <illegal opcode> hashCode : (Lzako/opt/ZakoOptions$Group;)I
/*    */       //   6: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	7	0	this	Lzako/opt/ZakoOptions$Group; } public final boolean equals(Object o) { // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: <illegal opcode> equals : (Lzako/opt/ZakoOptions$Group;Ljava/lang/Object;)Z
/*    */       //   7: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #41	-> 0
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	8	0	this	Lzako/opt/ZakoOptions$Group;
/* 41 */       //   0	8	1	o	Ljava/lang/Object; } public FeatureGroup type() { return this.type; } public List<ZakoOptions.Option> options() { return this.options; }
/*    */      public String key() {
/* 43 */       return this.type.key();
/*    */     }
/*    */     
/*    */     public List<ZakoOptions.Option> available() {
/* 47 */       return this.options.stream().filter(ZakoOptions.Option::available).toList();
/*    */     } }
/*    */ 
/*    */   
/* 51 */   public static final List<Group> GROUPS = Arrays.<FeatureGroup>stream(FeatureGroup.values()).map(ZakoOptions::group).toList();
/*    */ 
/*    */   
/*    */   private static Group group(FeatureGroup group) {
/* 55 */     List<Option> options = new ArrayList<>();
/* 56 */     for (Feature f : Features.all()) {
/* 57 */       if (f.data().group() != group) {
/*    */         continue;
/*    */       }
/* 60 */       String[] requires = f.data().mixins();
/* 61 */       options.add(new Toggle(f.key(), b -> f.value = b.booleanValue(), () -> Boolean.valueOf(f.value), requires));
/* 62 */       for (Field s : f.settings()) {
/* 63 */         options.add(setting(f, s, requires));
/*    */       }
/*    */     } 
/* 66 */     return new Group(group, options);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Option setting(Feature f, Field s, String[] requires) {
/* 71 */     String key = ((Setting)s.<Setting>getAnnotation(Setting.class)).key();
/* 72 */     if (s.getType() == boolean.class) {
/* 73 */       return new Toggle(key, b -> f.set(s, b), () -> (Boolean)f.get(s), requires);
/*    */     }
/* 75 */     Range range = s.<Range>getAnnotation(Range.class);
/* 76 */     if (s.getType() == int.class && range != null) {
/* 77 */       return new Distance(key, range.min(), range.max(), range.unit(), i -> f.set(s, i), () -> (Integer)f.get(s), requires);
/*    */     }
/* 79 */     throw new IllegalStateException("Setting " + key + ": a " + s.getType().getSimpleName() + " field" + ((s.getType() == int.class) ? " needs @Range" : " is not supported"));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\ZakoOptions.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */