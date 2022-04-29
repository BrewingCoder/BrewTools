package com.brewingcoder.brewtools.datagen.core;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.datagen.ModBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class ModItemTags extends MiningItemTags {

    public ModItemTags(DataGenerator generator,@Nullable ExistingFileHelper existingFileHelper) {
        super(generator, new ModBlockTags(generator, existingFileHelper), BrewTools.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        super.addTags();
        registerDecoItems();

    }

    @Override
    protected void registerOreTags() {
        this.registerOreRateTags(List.of(),List.of(ModBlocks.ABYSSAL.get(),ModBlocks.QUARRIED.get()),List.of());
        this.registerOresInGroundTags(List.of(ModBlocks.ABYSSAL.get(),ModBlocks.QUARRIED.get()),List.of(),List.of());
    }

    private void registerDecoItems(){
        this.tag(TagUtilities.modTag("minecraft","stairs"))
                .add(ModBlocks.ABYSSAL_STAIRS.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICK_CRACKED_STAIRS.get().asItem())
                .add(ModBlocks.ABYSSAL_COBBLE_STAIRS.get().asItem())
                .add(ModBlocks.ABYSSAL_SMOOTH_STAIRS.get().asItem())
                .add(ModBlocks.QUARRIED_STAIRS.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_STAIRS.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_STAIRS.get().asItem())
                .add(ModBlocks.QUARRIED_COBBLE_STAIRS.get().asItem())
                .add(ModBlocks.QUARRIED_SMOOTH_STAIRS.get().asItem());

        this.tag(TagUtilities.modTag("minecraft","slabs"))
                .add(ModBlocks.ABYSSAL_SLAB.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICKS_SLAB.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICKS_CRACKED_SLAB.get().asItem())
                .add(ModBlocks.ABYSSAL_COBBLE_SLAB.get().asItem())
                .add(ModBlocks.ABYSSAL_SMOOTH_SLAB.get().asItem())
                .add(ModBlocks.QUARRIED_SLAB.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_SLAB.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_SLAB.get().asItem())
                .add(ModBlocks.QUARRIED_COBBLE_SLAB.get().asItem())
                .add(ModBlocks.QUARRIED_SMOOTH_SLAB.get().asItem());

        this.tag(TagUtilities.modTag("minecraft","walls"))
                .add(ModBlocks.ABYSSAL_WALL.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICK_WALL.get().asItem())
                .add(ModBlocks.ABYSSAL_BRICK_CRACKED_WALL.get().asItem())
                .add(ModBlocks.ABYSSAL_COBBLE_WALL.get().asItem())
                .add(ModBlocks.ABYSSAL_SMOOTH_WALL.get().asItem())
                .add(ModBlocks.QUARRIED_WALL.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_WALL.get().asItem())
                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_WALL.get().asItem())
                .add(ModBlocks.QUARRIED_COBBLE_WALL.get().asItem())
                .add(ModBlocks.QUARRIED_SMOOTH_WALL.get().asItem());
    }
}
