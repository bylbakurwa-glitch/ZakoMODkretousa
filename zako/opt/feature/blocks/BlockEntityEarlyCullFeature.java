package zako.opt.feature.blocks;

import zako.opt.bench.BenchView;
import zako.opt.feature.Feature;
import zako.opt.feature.FeatureData;
import zako.opt.feature.FeatureGroup;

@FeatureData(key = "block_entity_early_cull", group = FeatureGroup.BLOCKS, mixins = {"block.BlockEntityRenderDispatcherCullMixin", "block.BlockEntityRenderDispatcherCullWrapMixin"}, bench = BenchView.BLOCKS)
public class BlockEntityEarlyCullFeature extends Feature {}


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\feature\blocks\BlockEntityEarlyCullFeature.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */