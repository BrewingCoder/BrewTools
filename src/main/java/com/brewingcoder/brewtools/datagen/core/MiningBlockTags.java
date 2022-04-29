package com.brewingcoder.brewtools.datagen.core;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

@SuppressWarnings("unused")
public class MiningBlockTags extends BlockTagsProvider {

    public MiningBlockTags(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        registerOreTags();
        registerMiningTags();
    }

    protected void registerOreTags(){}
    protected void registerMiningTags(){}

    protected void registerMineableTags(Collection<Block> blocks, Collection<Block> stone_blocks, Collection<Block> iron_blocks, Collection<Block> diamond_blocks, Collection<Block> netherite_blocks){
        TagsProvider.TagAppender<Block> blockTagAppender = this.tag(TagUtilities.modBlockTag("minecraft","mineable/pickaxe"));
        blocks.forEach(blockTagAppender::add);

        if(stone_blocks != null && !stone_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> stone = this.tag(TagUtilities.modBlockTag("minecraft","needs_stone_tool"));
            stone_blocks.forEach(stone::add);
        }

        if (iron_blocks != null && !iron_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> iron = this.tag(TagUtilities.modBlockTag("minecraft","needs_iron_tool"));
            iron_blocks.forEach(iron::add);
        }

        if (diamond_blocks != null && !diamond_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> diamond = this.tag(TagUtilities.modBlockTag("minecraft","needs_diamond_tool"));
            diamond_blocks.forEach(diamond::add);
        }

        if (netherite_blocks != null && !netherite_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> netherite = this.tag(TagUtilities.modBlockTag("minecraft","needs_netherite_tool"));
            netherite_blocks.forEach(netherite::add);
        }
    }

    protected void registerOresInGround(Collection<OreBlock> ore_blocks, Collection<OreBlock> deepslate_ore_blocks, Collection<OreBlock> nether_ore_blocks){
        if(ore_blocks != null && !ore_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> stone = this.tag(TagUtilities.forgeBlockTag("ores_in_ground/stone"));
            ore_blocks.forEach(stone::add);
        }
        if(deepslate_ore_blocks != null && !deepslate_ore_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> deepslate = this.tag(TagUtilities.forgeBlockTag("ores_in_ground/deepslate"));
            deepslate_ore_blocks.forEach(deepslate::add);
        }
        if(nether_ore_blocks != null && !nether_ore_blocks.isEmpty()){
            TagsProvider.TagAppender<Block> netherrack = this.tag(TagUtilities.forgeBlockTag("ores_in_ground/netherrack"));
            nether_ore_blocks.forEach(netherrack::add);
        }
    }

}
