package zako.opt.feature.spawners;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "spawner_tick_cache", group = FeatureGroup.SPAWNERS, mixins = {"block.SpawnerRendererMixin"}, bench = BenchView.BLOCKS)
public class SpawnerTickCacheFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\spawners\SpawnerTickCacheFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */