package com.brewingcoder.brewtools.datagen.core;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class MiningBlockTags extends BlockTagsProvider {

    public MiningBlockTags(DataGenerator dgen, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dgen, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }

    protected void registerOreTags(){}
    protected void registerMiningTags(){}

    protected void registerMineableTags(Collection<Block> blocks, Collection<Block> stone_blocks, Collection<Block> iron_blocks, Collection<Block> diamond_blocks, Collection<Block> netherite_blocks)
    {

    }
}
