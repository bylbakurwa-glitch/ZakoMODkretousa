package zako.opt.feature.render;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "lunar_multidraw", group = FeatureGroup.RENDER, config = "lunarMultiDraw", mixins = {"gl.LunarMultiDrawMixin", "gl.LunarSodiumMultiDrawMixin"}, bench = BenchView.TERRAIN)
public class LunarMultiDrawFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\render\LunarMultiDrawFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */