package zako.opt.feature.blocks;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "sign_cache", group = FeatureGroup.BLOCKS, mixins = {"block.AbstractSignRendererMixin"}, bench = BenchView.BLOCKS)
public class SignCacheFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\SignCacheFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */