package com.brewingcoder.brewtools.blockbase;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockProperties {
    public static Block.Properties rock(float hardnessAndResistance) {
        return rock(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties rock(float hardness, float resistance) {
        return AbstractBlock.Properties.of(Material.STONE)
                .strength(hardness, resistance);
    }

    public static Block.Properties wood(float hardnessAndResistance) {
        return wood(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties wood(float hardness, float resistance) {
        return Block.Properties.of(Material.WOOD)
                .strength(hardness, resistance)
                .sound(SoundType.WOOD);
    }

    public static Block.Properties metal(float hardnessAndResistance) {
        return metal(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties metal(float hardness, float resistance) {
        return Block.Properties.of(Material.METAL)
                .strength(hardness, resistance)
                .sound(SoundType.METAL);
    }

    public static Block.Properties rockNoSolid(float hardnessAndResistance) {
        return rockNoSolid(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties rockNoSolid(float hardness, float resistance) {
        return Block.Properties.of(Material.STONE)
                .strength(hardness, resistance)
                .air();
    }

    public static Block.Properties woodNoSolid(float hardnessAndResistance) {
        return woodNoSolid(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties woodNoSolid(float hardness, float resistance) {
        return Block.Properties.of(Material.WOOD)
                .strength(hardness, resistance)
                .sound(SoundType.WOOD)
                .air();
    }

    public static Block.Properties metalNoSolid(float hardnessAndResistance) {
        return metalNoSolid(hardnessAndResistance, hardnessAndResistance);

    }

    public static Block.Properties metalNoSolid(float hardness, float resistance) {
        return Block.Properties.of(Material.METAL)
                .strength(hardness, resistance)
                .sound(SoundType.METAL)
                .air();
    }
}
