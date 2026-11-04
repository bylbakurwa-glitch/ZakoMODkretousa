package zako.opt.feature.render;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "outline_skip", group = FeatureGroup.RENDER, mixins = {"entity.LevelRendererOutlineMixin"}, bench = BenchView.CROWD)
public class OutlineSkipFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\OutlineSkipFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */