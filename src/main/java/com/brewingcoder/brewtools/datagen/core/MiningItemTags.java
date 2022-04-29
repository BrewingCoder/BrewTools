package com.brewingcoder.brewtools.datagen.core;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

@SuppressWarnings("unused")
public class MiningItemTags extends ItemTagsProvider {

    public MiningItemTags(DataGenerator generator, BlockTagsProvider provider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        registerOreTags();
    }
    protected void registerOreTags(){}

    protected void registerOresInGroundTags(Collection<OreBlock> ore_blocks, Collection<OreBlock> deepslate_blocks, Collection<OreBlock> netherrack_blocks){
        if(ore_blocks != null && !ore_blocks.isEmpty()){
            TagsProvider.TagAppender<Item> stone = this.tag(TagUtilities.forgeTag("ores_in_ground/stone"));
            ore_blocks.forEach(b->stone.add(b.asItem()));
        }
        if(deepslate_blocks != null && !deepslate_blocks.isEmpty()){
            TagsProvider.TagAppender<Item> deepslate = this.tag(TagUtilities.forgeTag("ores_in_ground/deepslate"));
            deepslate_blocks.forEach(b->deepslate.add(b.asItem()));
        }
        if(netherrack_blocks != null && !netherrack_blocks.isEmpty()){
            TagsProvider.TagAppender<Item> netherrack = this.tag(TagUtilities.forgeTag("ores_in_ground/netherrack"));
            netherrack_blocks.forEach(b->netherrack.add(b.asItem()));
        }
    }

    protected void registerOreRateTags(Collection<OreBlock> sparse_ores, Collection<OreBlock> singular_ores, Collection<OreBlock> dense_ores){
        if(sparse_ores != null && !sparse_ores.isEmpty()){
            TagsProvider.TagAppender<Item> sparse = this.tag(TagUtilities.forgeTag("ore_rates/sparse"));
            sparse_ores.forEach(b-> sparse.add(b.asItem()));
        }
        if(singular_ores != null && !singular_ores.isEmpty()){
            TagsProvider.TagAppender<Item> singular = this.tag(TagUtilities.forgeTag("ore_rates/singular"));
            singular_ores.forEach(b->singular.add(b.asItem()));
        }
        if(dense_ores != null && !dense_ores.isEmpty()){
            TagsProvider.TagAppender<Item> dense = this.tag(TagUtilities.forgeTag("ore_rates/dense"));
            dense_ores.forEach(b->dense.add(b.asItem()));
        }
    }
}
