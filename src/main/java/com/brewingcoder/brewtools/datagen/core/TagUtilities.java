package com.brewingcoder.brewtools.datagen.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public final class TagUtilities {

    public static TagKey<Item> modTag(String modid, String name){
        return ItemTags.create(new ResourceLocation(modid, name));
    }

    public static TagKey<Item> forgeTag(String name){
        return modTag("forge",name);
    }

    public static TagKey<Block> modBlockTag(String modid, String name){
        return BlockTags.create(new ResourceLocation(modid,name));
    }

    public static TagKey<Block> forgeBlockTag(String name){
        return modBlockTag("forge", name);
    }

    public static TagKey<Block> miningTag(String name, int harvest_level){
        String levelName;
        switch(harvest_level){
            case 1:
                levelName = "needs_stone_tool";
                break;
            case 2:
                levelName = "needs_iron_tool";
                break;
            case 3:
                levelName = "needs_diamond_tool";
                break;
            case 4:
                levelName = "needs_netherite_tool";
                break;
            default:
                return null;
        }
        return modBlockTag("minecraft",levelName);
    }
}
