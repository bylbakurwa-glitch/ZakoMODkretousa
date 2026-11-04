/*    */ package zako.opt.feature;
/*    */ public abstract class Feature { private final FeatureData data; private final String property; private final List<Field> settings; private final boolean available;
/*    */   public boolean value;
/*    */   public boolean on;
/*    */   public volatile boolean broken;
/*    */   
/*    */   @Generated
/*    */   public FeatureData data() {
/*    */     return this.data;
/*    */   }
/*    */   
/*    */   @Generated
/*    */   public String property() {
/*    */     return this.property;
/*    */   }
/*    */   
/* 17 */   public Feature() { this.data = getClass().<FeatureData>getAnnotation(FeatureData.class);
/* 18 */     this.property = "zakoopt." + this.data.key();
/* 19 */     this.settings = Arrays.<Field>stream(getClass().getFields()).filter(f -> f.isAnnotationPresent((Class)Setting.class)).toList();
/*    */     
/* 21 */     this.available = ((this.data.mixins()).length == 0 || Arrays.<String>stream(this.data.mixins()).anyMatch(MixinPlugin::applies));
/*    */     
/* 23 */     this.value = this.data.enabled(); } @Generated public List<Field> settings() { return this.settings; } @Generated public boolean available() { return this.available; } @Generated public boolean value() { return this.value; }
/*    */   @Generated
/* 25 */   public boolean on() { return this.on; } @Generated
/*    */   public boolean broken() {
/* 27 */     return this.broken;
/*    */   }
/*    */   
/*    */   public String blocked() {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 36 */     this.on = (flag(this.value) && blocked() == null && available() && !this.broken);
/*    */   }
/*    */   
/*    */   public String key() {
/* 40 */     return this.data.key();
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean flag(boolean configured) {
/* 45 */     return flag0(this.property, configured);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean flag(String name, boolean configured) {
/* 50 */     return flag0("zakoopt." + name, configured);
/*    */   }
/*    */   
/*    */   private boolean flag0(String property, boolean configured) {
/* 54 */     if (!Features.active) {
/* 55 */       return false;
/*    */     }
/* 57 */     String p = System.getProperty(property);
/* 58 */     return (p == null) ? configured : Boolean.parseBoolean(p);
/*    */   }
/*    */   
/*    */   public Object get(Field setting) {
/*    */     try {
/* 63 */       return setting.get(this);
/*    */     } catch (IllegalAccessException $ex) {
/*    */       throw $ex;
/*    */     }  } public void set(Field setting, Object value) {
/*    */     try {
/* 68 */       setting.set(this, value);
/*    */     } catch (IllegalAccessException $ex) {
/*    */       throw $ex;
/*    */     } 
/*    */   } }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\Feature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */