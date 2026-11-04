/*     */ package zako.opt.bench;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class Scene {
/*     */   public static final int Y = 250;
/*     */   public static final int LEVELS = 60;
/*     */   private static final int ROWS = 10;
/*     */   private static final int STOREY = 4;
/*     */   public static final boolean MANNEQUIN;
/*     */   
/*     */   @Generated
/*     */   private Scene() {
/*  13 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  20 */     MANNEQUIN = class_7923.field_41177.method_10235().stream().anyMatch(id -> id.method_12832().equals("mannequin"));
/*  21 */   } private static final String[] KINDS = new String[] { "zombie", "skeleton", "husk", "stray", "piglin", "vindicator" };
/*  22 */   private static final String[] ITEMS = new String[] { "diamond", "iron_ingot", "gold_ingot", "emerald", "cobblestone", "oak_log", "redstone", "bone", "arrow", "rotten_flesh", "diamond_sword", "golden_apple", "ender_pearl", "torch", "oak_planks", "glass", "string", "gunpowder", "apple", "bread" };
/*     */   
/*  24 */   private static final String[] BLOCKS = new String[] { "chest[type=left,facing=north]", "chest[type=right,facing=north]", "oak_sign[rotation=8]", "campfire[lit=true]", "player_head", "white_banner" };
/*     */   public static final class View extends Record { private final String key; private final int x; private final int y; private final int z; private final int yaw; private final int pitch;
/*  26 */     public View(String key, int x, int y, int z, int yaw, int pitch) { this.key = key; this.x = x; this.y = y; this.z = z; this.yaw = yaw; this.pitch = pitch; } public final String toString() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> toString : (Lzako/opt/bench/Scene$View;)Ljava/lang/String;
/*     */       //   6: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*  26 */       //   0	7	0	this	Lzako/opt/bench/Scene$View; } public String key() { return this.key; } public final int hashCode() { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: <illegal opcode> hashCode : (Lzako/opt/bench/Scene$View;)I
/*     */       //   6: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	7	0	this	Lzako/opt/bench/Scene$View; } public final boolean equals(Object o) { // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: <illegal opcode> equals : (Lzako/opt/bench/Scene$View;Ljava/lang/Object;)Z
/*     */       //   7: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #26	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	8	0	this	Lzako/opt/bench/Scene$View;
/*  26 */       //   0	8	1	o	Ljava/lang/Object; } public int x() { return this.x; } public int y() { return this.y; } public int z() { return this.z; } public int yaw() { return this.yaw; } public int pitch() { return this.pitch; }
/*     */      }
/*     */   
/*  29 */   public static final View OVERVIEW = new View("overview", 60, 290, -25, 0, 40);
/*     */   
/*  31 */   public static final View LOD = new View("lod", -1, 252, -6, 0, 8);
/*     */   
/*  33 */   public static final View ITEMS_CLOSE = new View("items_close", 48, 252, 1, 0, 45);
/*     */   
/*  35 */   public static final View SPAWNERS = new View("spawners", 232, 253, -6, 0, 15);
/*     */   
/*  37 */   public static final View FOREST = new View("forest", 32, 266, -40, 180, 25);
/*     */   
/*  39 */   public static View terrain = new View("terrain", 60, 190, -40, 180, 10); public static final int SPAWNER_STRIPS = 15; public static final int FOREST_X0 = -10; public static final int FOREST_Z0 = -130; public static final int FOREST_X1 = 74; public static final int FOREST_Z1 = -46; public static final String RULER = "zakoopt_ruler"; public static final String PILE = "zakoopt_pile";
/*     */   
/*     */   public static void terrainAt(int x, int y, int z) {
/*  42 */     terrain = new View("terrain", x, y + 40, z, 180, 10);
/*     */   }
/*     */   
/*     */   public static List<View> views() {
/*  46 */     return List.of(terrain, FOREST, new View("crowd", 14, 266, -10, 0, 35), new View("items", 48, 266, -10, 0, 35), new View("blocks", 76, 266, -10, 0, 35), new View("pistons", 123, 266, -10, 0, 35), new View("holograms", 104, 266, -10, 0, 30), OVERVIEW);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> base() {
/*  51 */     List<String> c = new ArrayList<>();
/*     */     
/*  53 */     for (String rule : List.<String>of("doDaylightCycle false", "advance_time false", "doWeatherCycle false", "advance_weather false", "doMobSpawning false", "spawn_mobs false", "randomTickSpeed 0", "random_tick_speed 0", "commandBlockOutput false", "command_block_output false"))
/*     */     {
/*  55 */       c.add("gamerule " + rule);
/*     */     }
/*  57 */     c.add("time set 6000");
/*  58 */     c.add("weather clear");
/*  59 */     c.add("gamemode creative @a");
/*  60 */     c.add(f("fill -4 %d -8 130 %d 4 minecraft:smooth_stone", new Object[] { Integer.valueOf(249), Integer.valueOf(249) }));
/*     */ 
/*     */     
/*  63 */     c.add("scoreboard objectives add zakoopt_t dummy");
/*  64 */     c.add("scoreboard players set #4 zakoopt_t 4");
/*  65 */     c.addAll(zone(OVERVIEW));
/*  66 */     c.add(f("setblock 130 %d -6 minecraft:repeating_command_block[facing=up]{auto:1b,Command:\"execute store result score #p zakoopt_t run time query gametime\"}", new Object[] { Integer.valueOf(244) }));
/*  67 */     c.add(f("setblock 130 %d -6 minecraft:chain_command_block[facing=up]{auto:1b,Command:\"scoreboard players operation #p zakoopt_t %%= #4 zakoopt_t\"}", new Object[] { Integer.valueOf(245) })); int i;
/*  68 */     for (i = 0; i < 3; i++) {
/*  69 */       c.add(f("bossbar add zakoopt:b%d %s", new Object[] { Integer.valueOf(i), text("Boss " + i) }));
/*  70 */       c.add(f("bossbar set zakoopt:b%d value %d", new Object[] { Integer.valueOf(i), Integer.valueOf(30 + i * 25) }));
/*  71 */       c.add(f("bossbar set zakoopt:b%d players @a", new Object[] { Integer.valueOf(i) }));
/*     */     } 
/*  73 */     c.add("scoreboard objectives add zakoopt dummy " + text("ZakoOpt"));
/*  74 */     c.add("scoreboard objectives setdisplay sidebar zakoopt");
/*  75 */     for (i = 0; i < 15; i++) {
/*  76 */       c.add(f("scoreboard players set line%d zakoopt %d", new Object[] { Integer.valueOf(i), Integer.valueOf(i * 7) }));
/*     */     } 
/*  78 */     for (i = 0; i < 20; i++) {
/*  79 */       c.add("tellraw @a " + text("Chat line " + i + ": ZakoOpt benchmark scene"));
/*     */     }
/*  81 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> ruler(String blocksLabel) {
/*  87 */     List<String> c = new ArrayList<>();
/*  88 */     c.add(f("fill -11 %d -2 -5 %d 60 minecraft:smooth_stone", new Object[] { Integer.valueOf(249), Integer.valueOf(249) }));
/*  89 */     for (int d = 8; d <= 64; d += 4) {
/*  90 */       int z = LOD.z() + d;
/*  91 */       String armour = "equipment:{head:{id:\"diamond_helmet\",count:1},chest:{id:\"diamond_chestplate\",count:1},legs:{id:\"diamond_leggings\",count:1},feet:{id:\"diamond_boots\",count:1},mainhand:{id:\"diamond_sword\",count:1}}";
/*     */ 
/*     */       
/*  94 */       c.add(f("summon minecraft:zombie -7 %d %d {NoAI:1b,Silent:1b,PersistenceRequired:1b,Tags:[\"%s\"],Rotation:[180f,0f],%s}", new Object[] { Integer.valueOf(250), Integer.valueOf(z), "zakoopt_ruler", armour }));
/*  95 */       c.add(f("summon minecraft:%s -9 %d %d {NoAI:1b,Silent:1b,PersistenceRequired:1b,Tags:[\"%s\"],Rotation:[180f,0f],%s}", new Object[] { MANNEQUIN ? "mannequin" : "skeleton", Integer.valueOf(250), Integer.valueOf(z), "zakoopt_ruler", armour }));
/*  96 */       if (d % 8 == 0) {
/*  97 */         c.add(f("summon minecraft:text_display -8 %d.6 %d {billboard:\"center\",alignment:\"center\",Tags:[\"%s\"],text:%s}", new Object[] { Integer.valueOf(252), Integer.valueOf(z), "zakoopt_ruler", nbtText(blocksLabel.formatted(new Object[] { Integer.valueOf(d) })) }));
/*     */       }
/*     */     } 
/* 100 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> spawnerLabels(String blocksLabel) {
/* 108 */     List<String> c = new ArrayList<>();
/* 109 */     c.add(f("fill 216 %d -8 248 %d 64 minecraft:smooth_stone", new Object[] { Integer.valueOf(249), Integer.valueOf(249) }));
/* 110 */     for (int d = 8; d <= 64; d += 8) {
/* 111 */       c.add(f("summon minecraft:text_display 218 %d.6 %d {billboard:\"center\",alignment:\"center\",Tags:[\"%s\"],text:%s}", new Object[] { Integer.valueOf(251), Integer.valueOf(SPAWNERS.z() + d), "zakoopt_ruler", nbtText(blocksLabel.formatted(new Object[] { Integer.valueOf(d) })) }));
/*     */     } 
/* 113 */     return c;
/*     */   }
/*     */   
/*     */   public static List<String> spawnerStrip(int strip) {
/* 117 */     int z = SPAWNERS.z() + 8 + strip * 4;
/* 118 */     return List.of(f("fill 220 %d %d 244 %d %d minecraft:spawner{SpawnData:{entity:{id:\"minecraft:%s\"}},Delay:32767s,MinSpawnDelay:32767s,MaxSpawnDelay:32767s,RequiredPlayerRange:128s}", new Object[] {
/* 119 */             Integer.valueOf(250), Integer.valueOf(z), Integer.valueOf(250), Integer.valueOf(z + 3), KINDS[strip % KINDS.length] }));
/*     */   }
/*     */   
/*     */   public static List<String> clearSpawnerField() {
/* 123 */     return List.of(f("fill 220 %d %d 244 %d %d minecraft:air", new Object[] { Integer.valueOf(250), Integer.valueOf(SPAWNERS.z() + 8), Integer.valueOf(250), Integer.valueOf(SPAWNERS.z() + 8 + 60) }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> zone(View view) {
/* 129 */     boolean all = (view == OVERVIEW);
/* 130 */     return List.of(f("scoreboard players set #pistons zakoopt_t %d", new Object[] { Integer.valueOf((all || view.key().equals("pistons")) ? 1 : 0)
/* 131 */           }), f("scoreboard players set #particles zakoopt_t %d", new Object[] { Integer.valueOf((all || view.key().equals("holograms")) ? 1 : 0) }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> forestLoaded(boolean loaded) {
/* 138 */     return List.of(f("forceload %s %d %d %d %d", new Object[] { loaded ? "add" : "remove", Integer.valueOf(-10), Integer.valueOf(-130), Integer.valueOf(74), Integer.valueOf(-46) }));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> forest() {
/* 143 */     Random random = new Random(42L);
/* 144 */     List<String> c = new ArrayList<>();
/* 145 */     c.add(f("fill %d %d %d %d %d %d minecraft:grass_block", new Object[] { Integer.valueOf(-10), Integer.valueOf(249), Integer.valueOf(-130), Integer.valueOf(74), Integer.valueOf(249), Integer.valueOf(-46) }));
/* 146 */     for (int x = -8; x <= 72; x += 5) {
/* 147 */       for (int z = -128; z <= -50; z += 5) {
/* 148 */         c.add(f("place feature minecraft:%s %d %d %d", new Object[] { (random.nextInt(4) == 0) ? "oak" : "dark_oak", Integer.valueOf(x + random.nextInt(3)), Integer.valueOf(250), Integer.valueOf(z + random.nextInt(3)) }));
/*     */       } 
/*     */     } 
/* 151 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> pile(int batch) {
/* 159 */     Random random = new Random((1000 + batch));
/* 160 */     List<String> c = new ArrayList<>();
/* 161 */     for (int i = 0; i < 200; i++) {
/* 162 */       c.add(f("summon minecraft:item %.2f %.1f %.2f {Item:{id:\"%s\",count:64},PickupDelay:32767,Age:-32768,Tags:[\"%s\"]}", new Object[] {
/* 163 */               Double.valueOf((ITEMS_CLOSE.x() - 8) + random.nextDouble() * 16.0D), Double.valueOf(250.2D), Double.valueOf((ITEMS_CLOSE.z() + 5) + random.nextDouble() * 8.0D), ITEMS[random.nextInt(ITEMS.length)], "zakoopt_pile" }));
/*     */     } 
/* 165 */     return c;
/*     */   }
/*     */   
/*     */   public static List<String> level(int lv) {
/* 169 */     Random random = new Random(lv);
/* 170 */     List<String> c = new ArrayList<>();
/* 171 */     int st = lv / 10, z = 5 + lv % 10 * 3, y = 250 + st * 4;
/* 172 */     c.add(f("fill -4 %d %d 130 %d %d minecraft:%s", new Object[] { Integer.valueOf(y - 1), Integer.valueOf(z), Integer.valueOf(y - 1), Integer.valueOf(z + 2), (st == 0) ? "smooth_stone" : "glass" }));
/*     */     int i;
/* 174 */     for (i = 0; i < 28; i++) {
/* 175 */       int n = lv * 28 + i;
/* 176 */       String name = (n % 3 == 0) ? f(",CustomName:%s,CustomNameVisible:1b", new Object[] { nbtText("Mob_" + n) }) : "";
/* 177 */       c.add(f("summon minecraft:%s %.1f %d %.1f {NoAI:1b,Silent:1b,PersistenceRequired:1b,Rotation:[%df,0f]%s,%s}", new Object[] { (MANNEQUIN && i % 4 == 3) ? "mannequin" : KINDS[(i % 14 + lv) % 6], 
/* 178 */               Double.valueOf((i % 14) * 2.2D + (i / 14) * 1.1D), Integer.valueOf(y), Double.valueOf(z + 0.7D + (i / 14) * 1.5D), Integer.valueOf(random.nextInt(360)), name, "equipment:{head:{id:\"diamond_helmet\",count:1},chest:{id:\"diamond_chestplate\",count:1},legs:{id:\"diamond_leggings\",count:1},feet:{id:\"diamond_boots\",count:1},mainhand:{id:\"diamond_sword\",count:1}}" }));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 183 */     for (i = 0; i < 60; i++) {
/* 184 */       c.add(f("summon minecraft:item %.2f %.1f %.2f {Item:{id:\"%s\",count:%d},PickupDelay:32767,Age:-32768}", new Object[] { Double.valueOf(36.0D + random.nextDouble() * 24.0D), Double.valueOf(y + 0.2D), 
/* 185 */               Double.valueOf(z + random.nextDouble() * 3.0D), ITEMS[random.nextInt(ITEMS.length)], Integer.valueOf(1 + random.nextInt(64)) }));
/*     */     } 
/*     */     
/* 188 */     for (i = 0; i < 24; i++) {
/* 189 */       String b = BLOCKS[i % 6];
/* 190 */       switch (b) { case "oak_sign[rotation=8]": case "player_head": default: break; }  String nbt = 
/*     */ 
/*     */         
/* 193 */         "";
/*     */       
/* 195 */       c.add(f("setblock %d %d %d minecraft:%s%s", new Object[] { Integer.valueOf(64 + i % 12 * 2), Integer.valueOf(y), Integer.valueOf(z + i / 12 * 2), b, nbt }));
/*     */     } 
/* 197 */     for (i = 0; i < 4; i++) {
/* 198 */       c.add(f("setblock %d %d %d minecraft:spawner{SpawnData:{entity:{id:\"minecraft:%s\"}},Delay:32767s,MinSpawnDelay:32767s,MaxSpawnDelay:32767s,RequiredPlayerRange:128s}", new Object[] { Integer.valueOf(88 + i * 2), Integer.valueOf(y), Integer.valueOf(z + 1), KINDS[(lv + i) % 6] }));
/*     */     } 
/*     */ 
/*     */     
/* 202 */     for (i = 0; i < 6; i++) {
/* 203 */       c.add(f("setblock %d %d %d minecraft:air", new Object[] { Integer.valueOf(118 + i * 2), Integer.valueOf(y - 1), Integer.valueOf(z + 1) }));
/* 204 */       c.add(f("setblock %d %d %d minecraft:piston[facing=up]", new Object[] { Integer.valueOf(118 + i * 2), Integer.valueOf(y), Integer.valueOf(z + 1) }));
/* 205 */       c.add(f("setblock %d %d %d minecraft:stone", new Object[] { Integer.valueOf(118 + i * 2), Integer.valueOf(y + 1), Integer.valueOf(z + 1) }));
/*     */     } 
/* 207 */     c.add(f("setblock 120 %d %d minecraft:repeating_command_block{auto:1b,Command:\"execute if score #pistons zakoopt_t matches 1 if score #p zakoopt_t matches 0..1 run fill 118 %d %d 128 %d %d minecraft:redstone_block replace minecraft:air\"}", new Object[] {
/* 208 */             Integer.valueOf(248 - st), Integer.valueOf(z + 1), Integer.valueOf(y - 1), Integer.valueOf(z + 1), Integer.valueOf(y - 1), Integer.valueOf(z + 1) }));
/* 209 */     c.add(f("setblock 124 %d %d minecraft:repeating_command_block{auto:1b,Command:\"execute if score #p zakoopt_t matches 2..3 run fill 118 %d %d 128 %d %d minecraft:air replace minecraft:redstone_block\"}", new Object[] {
/* 210 */             Integer.valueOf(248 - st), Integer.valueOf(z + 1), Integer.valueOf(y - 1), Integer.valueOf(z + 1), Integer.valueOf(y - 1), Integer.valueOf(z + 1)
/*     */           }));
/* 212 */     for (i = 0; i < 6; i++) {
/* 213 */       c.add(f("summon minecraft:text_display %d %.1f %d {billboard:\"center\",alignment:\"center\",text:%s}", new Object[] { Integer.valueOf(94 + i * 4), Double.valueOf(y + 2.5D), Integer.valueOf(z + 1), 
/* 214 */               nbtText("Hologram " + lv + "." + i + "\nZakoOpt benchmark\nline three") }));
/*     */     } 
/* 216 */     if (lv % 2 == 0) {
/* 217 */       c.add(f("setblock 104 %d %d minecraft:repeating_command_block{auto:1b,Command:\"execute if score #particles zakoopt_t matches 1 run particle minecraft:end_rod 104 %d %d 6 1 1 0.02 15\"}", new Object[] { Integer.valueOf(248 - st), Integer.valueOf(z + 1), Integer.valueOf(y + 3), Integer.valueOf(z + 1) }));
/* 218 */       c.add(f("setblock 112 %d %d minecraft:repeating_command_block{auto:1b,Command:\"execute if score #particles zakoopt_t matches 1 run particle minecraft:flame 112 %d %d 4 0.5 1 0.01 10\"}", new Object[] { Integer.valueOf(248 - st), Integer.valueOf(z + 1), Integer.valueOf(y + 1), Integer.valueOf(z + 1) }));
/*     */     } 
/* 220 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String f(String format, Object... args) {
/* 225 */     return String.format(Locale.ROOT, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String text(String s) {
/* 230 */     return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String nbtText(String s) {
/* 235 */     return text(s);
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\bench\Scene.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */