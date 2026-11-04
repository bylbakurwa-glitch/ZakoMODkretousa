package zako.opt.feature.items;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "resting_items", group = FeatureGroup.ITEMS, mixins = {"entity.ItemEntityRestMixin"}, bench = BenchView.ITEMS)
public class RestingItemsFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\items\RestingItemsFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */