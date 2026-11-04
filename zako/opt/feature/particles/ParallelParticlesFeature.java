/*    */ package zako.opt.feature.particles;
/*    */ 
/*    */ import zako.opt.ZakoOptConfig;
/*    */ import zako.opt.bench.BenchView;
/*    */ import zako.opt.feature.Feature;
/*    */ import zako.opt.feature.FeatureData;
/*    */ import zako.opt.feature.FeatureGroup;
/*    */ import zako.opt.feature.Features;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FeatureData(key = "parallel_particles", group = FeatureGroup.PARTICLES, mixins = {"particle.QuadParticleGroupMixin", "particle.ParticleEngineParallelMixin"}, bench = BenchView.HOLOGRAMS)
/*    */ public class ParallelParticlesFeature
/*    */   extends Feature
/*    */ {
/*    */   public boolean vertices;
/*    */   
/*    */   public String blocked() {
/* 22 */     return (ZakoOptConfig.CORES < 4) ? "zakoopt.blocked.cpu" : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh() {
/* 27 */     String p = System.getProperty(property());
/* 28 */     this.on = (Features.active && ((p == null) ? (this.value && blocked() == null) : (p.equals("true") || (p.equals("auto") && blocked() == null))));
/* 29 */     this.vertices = (this.on && flag("parallelvertices", true));
/*    */   }
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\particles\ParallelParticlesFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */