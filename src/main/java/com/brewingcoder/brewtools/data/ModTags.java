package com.brewingcoder.brewtools.data;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class ModTags {
    public static final ITag<Block> ORE_SPAWNABLE = BlockTags.bind(new ResourceLocation(BrewTools.MODID,"oregen_stones").toString());
    }
