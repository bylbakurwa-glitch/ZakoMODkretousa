package zako.opt.feature.ui;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "bidi_cache", group = FeatureGroup.INTERFACE, mixins = {"text.ClientLanguageMixin"}, bench = BenchView.HOLOGRAMS)
public class BidiCacheFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\featur\\ui\BidiCacheFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */