/*     */ package zako.opt;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.stream.Collectors;
/*     */ import lombok.Generated;
/*     */ import org.objectweb.asm.Opcodes;
/*     */ import org.objectweb.asm.Type;
/*     */ import org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.objectweb.asm.tree.AnnotationNode;
/*     */ import org.objectweb.asm.tree.ClassNode;
/*     */ import org.objectweb.asm.tree.IincInsnNode;
/*     */ import org.objectweb.asm.tree.InsnList;
/*     */ import org.objectweb.asm.tree.InsnNode;
/*     */ import org.objectweb.asm.tree.LabelNode;
/*     */ import org.objectweb.asm.tree.LdcInsnNode;
/*     */ import org.objectweb.asm.tree.MethodInsnNode;
/*     */ import org.objectweb.asm.tree.MethodNode;
/*     */ import org.objectweb.asm.tree.TryCatchBlockNode;
/*     */ import org.objectweb.asm.tree.VarInsnNode;
/*     */ import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
/*     */ 
/*     */ public final class GuardWeaver implements Opcodes {
/*     */   @Generated
/*     */   private GuardWeaver() {
/*  29 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*     */   }
/*     */   private static final String OPERATION = "Lcom/llamalad7/mixinextras/injector/wrapoperation/Operation;";
/*  32 */   private static final Set<String> WRAPPED = ConcurrentHashMap.newKeySet();
/*     */ 
/*     */   
/*  35 */   private static final Set<String> HOT = Set.of("zakoopt$earlyCull", "zakoopt$renderType", "zakoopt$release");
/*     */ 
/*     */   
/*     */   public static void weave(ClassNode target, String mixinClass, IMixinInfo mixin) {
/*  39 */     if (mixinClass.contains(".mixin.entity.") || mixinClass.contains(".mixin.particle.")) {
/*     */       return;
/*     */     }
/*  42 */     Set<String> own = (Set<String>)(mixin.getClassNode(0)).methods.stream().map(m -> m.name).filter(n -> n.startsWith("zakoopt$")).collect(Collectors.toSet());
/*  43 */     for (MethodNode m : List.copyOf(target.methods)) {
/*  44 */       int at = m.name.indexOf("zakoopt$");
/*  45 */       String merged = mergedFrom(m);
/*     */ 
/*     */       
/*  48 */       if (at < 0 || !own.contains(m.name.substring(at)) || HOT.contains(m.name.substring(at)) || (merged != null && !merged.equals(mixinClass)) || m.desc.contains("Lcom/llamalad7/mixinextras/injector/wrapoperation/Operation;") || (m.access & 0x500) != 0 || m.instructions
/*  49 */         .size() == 0 || !WRAPPED.add(target.name + "." + target.name + m.name)) {
/*     */         continue;
/*     */       }
/*  52 */       wrap(target, m, mixinClass, m.name.substring(at), sugared(m));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String mergedFrom(MethodNode m) {
/*  57 */     if (m.invisibleAnnotations != null) {
/*  58 */       for (AnnotationNode a : m.invisibleAnnotations) {
/*  59 */         if (a.desc.equals("Lorg/spongepowered/asm/mixin/transformer/meta/MixinMerged;")) {
/*  60 */           for (int i = 0; i + 1 < a.values.size(); i += 2) {
/*  61 */             if ("mixin".equals(a.values.get(i))) {
/*  62 */               return a.values.get(i + 1);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean sugared(MethodNode m) {
/*  74 */     int params = ((m.access & 0x8) != 0) ? 0 : 1;
/*  75 */     for (Type t : Type.getArgumentTypes(m.desc)) {
/*  76 */       params += t.getSize();
/*     */     }
/*  78 */     Set<Integer> stored = new HashSet<>();
/*  79 */     for (ListIterator<AbstractInsnNode> listIterator = m.instructions.iterator(); listIterator.hasNext(); ) { AbstractInsnNode insn = listIterator.next();
/*  80 */       if (insn instanceof VarInsnNode) { VarInsnNode v = (VarInsnNode)insn; if (v.var >= params)
/*  81 */         { boolean load = (v.getOpcode() >= 21 && v.getOpcode() <= 25);
/*  82 */           if (load && !stored.contains(Integer.valueOf(v.var))) {
/*  83 */             return true;
/*     */           }
/*  85 */           stored.add(Integer.valueOf(v.var)); continue; }  }
/*  86 */        if (insn instanceof IincInsnNode) { IincInsnNode i = (IincInsnNode)insn; if (i.var >= params && !stored.contains(Integer.valueOf(i.var)))
/*  87 */           return true;  }
/*     */        }
/*     */     
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void wrap(ClassNode c, MethodNode m, String mixinClass, String name, boolean inPlace) {
/*  96 */     boolean isStatic = ((m.access & 0x8) != 0), itf = ((c.access & 0x200) != 0);
/*  97 */     Type ret = Type.getReturnType(m.desc);
/*  98 */     Type[] args = Type.getArgumentTypes(m.desc);
/*  99 */     int[] slots = new int[args.length];
/* 100 */     int slot = isStatic ? 0 : 1;
/* 101 */     for (int i = 0; i < args.length; i++) {
/* 102 */       slots[i] = slot;
/* 103 */       slot += args[i].getSize();
/*     */     } 
/* 105 */     int access = 0x1002 | (isStatic ? 8 : 0);
/* 106 */     boolean fallsBack = (ret.getSort() == 0 || (args.length > 0 && args[0].equals(ret)));
/* 107 */     if (fallsBack) {
/* 108 */       MethodNode fallback = new MethodNode(access, m.name + "$fallback", m.desc, null, null);
/* 109 */       fallback.instructions = fallback(args, slots, ret);
/* 110 */       fallback.maxStack = 2;
/* 111 */       fallback.maxLocals = slot;
/* 112 */       c.methods.add(fallback);
/*     */     } 
/* 114 */     LabelNode start = new LabelNode(), end = new LabelNode(), handler = new LabelNode();
/* 115 */     InsnList w = new InsnList();
/* 116 */     if (inPlace) {
/* 117 */       m.instructions.insert((AbstractInsnNode)start);
/* 118 */       m.instructions.add((AbstractInsnNode)end);
/*     */     } else {
/* 120 */       MethodNode body = new MethodNode(access, m.name + "$body", m.desc, null, (String[])m.exceptions.toArray((Object[])new String[0]));
/* 121 */       body.instructions = m.instructions;
/* 122 */       body.tryCatchBlocks = m.tryCatchBlocks;
/* 123 */       body.localVariables = m.localVariables;
/* 124 */       body.visibleLocalVariableAnnotations = m.visibleLocalVariableAnnotations;
/* 125 */       body.invisibleLocalVariableAnnotations = m.invisibleLocalVariableAnnotations;
/* 126 */       body.maxStack = m.maxStack;
/* 127 */       body.maxLocals = m.maxLocals;
/* 128 */       c.methods.add(body);
/* 129 */       m.instructions = new InsnList();
/* 130 */       m.tryCatchBlocks = new ArrayList();
/* 131 */       m.localVariables = null;
/* 132 */       m.visibleLocalVariableAnnotations = null;
/* 133 */       m.invisibleLocalVariableAnnotations = null;
/* 134 */       w.add((AbstractInsnNode)start);
/* 135 */       call(w, c, body.name, m.desc, isStatic, itf, args, slots);
/* 136 */       w.add((AbstractInsnNode)new InsnNode(ret.getOpcode(172)));
/* 137 */       w.add((AbstractInsnNode)end);
/*     */     } 
/* 139 */     w.add((AbstractInsnNode)handler);
/* 140 */     if (!fallsBack) {
/* 141 */       w.add((AbstractInsnNode)new InsnNode(89));
/*     */     }
/* 143 */     w.add((AbstractInsnNode)new LdcInsnNode(mixinClass));
/* 144 */     w.add((AbstractInsnNode)new LdcInsnNode(name));
/* 145 */     w.add((AbstractInsnNode)new MethodInsnNode(184, "zako/opt/Guard", "fail", "(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V", false));
/* 146 */     if (fallsBack) {
/* 147 */       call(w, c, m.name + "$fallback", m.desc, isStatic, itf, args, slots);
/* 148 */       w.add((AbstractInsnNode)new InsnNode(ret.getOpcode(172)));
/*     */     } else {
/* 150 */       w.add((AbstractInsnNode)new InsnNode(191));
/*     */     } 
/* 152 */     m.instructions.add(w);
/* 153 */     m.tryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, "java/lang/RuntimeException"));
/* 154 */     m.tryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, "java/lang/LinkageError"));
/* 155 */     m.maxStack = Math.max(m.maxStack, slot + 4);
/* 156 */     m.maxLocals = Math.max(m.maxLocals, slot);
/*     */   }
/*     */   
/*     */   private static void call(InsnList w, ClassNode c, String method, String desc, boolean isStatic, boolean itf, Type[] args, int[] slots) {
/* 160 */     if (!isStatic) {
/* 161 */       w.add((AbstractInsnNode)new VarInsnNode(25, 0));
/*     */     }
/* 163 */     for (int i = 0; i < args.length; i++) {
/* 164 */       w.add((AbstractInsnNode)new VarInsnNode(args[i].getOpcode(21), slots[i]));
/*     */     }
/* 166 */     w.add((AbstractInsnNode)new MethodInsnNode(isStatic ? 184 : 183, c.name, method, desc, itf));
/*     */   }
/*     */ 
/*     */   
/*     */   private static InsnList fallback(Type[] args, int[] slots, Type ret) {
/* 171 */     InsnList h = new InsnList();
/* 172 */     if (ret.getSort() != 0) {
/* 173 */       h.add((AbstractInsnNode)new VarInsnNode(args[0].getOpcode(21), slots[0]));
/*     */     }
/* 175 */     h.add((AbstractInsnNode)new InsnNode(ret.getOpcode(172)));
/* 176 */     return h;
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\GuardWeaver.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */