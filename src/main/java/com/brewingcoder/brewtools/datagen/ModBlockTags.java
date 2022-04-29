package com.brewingcoder.brewtools.datagen;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.datagen.core.MiningBlockTags;
import com.brewingcoder.brewtools.datagen.core.TagUtilities;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class ModBlockTags  extends MiningBlockTags {

    public ModBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, BrewTools.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        super.addTags();
        registerMiningTags();
        registerDecoBlocks();
    }

    @Override
    protected void registerMiningTags() {
        registerMineableTags(
                List.of(
                        ModBlocks.ABYSSAL.get(),
                        ModBlocks.ABYSSAL_COBBLE.get(),
                        ModBlocks.ABYSSAL_BRICKS.get(),
                        ModBlocks.ABYSSAL_BRICKS_CRACKED.get(),
                        ModBlocks.ABYSSAL_SMOOTH.get(),
                        ModBlocks.ABYSSAL_STAIRS.get(),
                        ModBlocks.ABYSSAL_BRICK_STAIRS.get(),
                        ModBlocks.ABYSSAL_BRICK_CRACKED_STAIRS.get(),
                        ModBlocks.ABYSSAL_COBBLE_STAIRS.get(),
                        ModBlocks.ABYSSAL_SMOOTH_STAIRS.get(),
                        ModBlocks.ABYSSAL_SLAB.get(),
                        ModBlocks.ABYSSAL_BRICKS_SLAB.get(),
                        ModBlocks.ABYSSAL_BRICKS_CRACKED_SLAB.get(),
                        ModBlocks.ABYSSAL_COBBLE_SLAB.get(),
                        ModBlocks.ABYSSAL_SMOOTH_SLAB.get(),
                        ModBlocks.ABYSSAL_WALL.get(),
                        ModBlocks.ABYSSAL_BRICK_WALL.get(),
                        ModBlocks.ABYSSAL_BRICK_CRACKED_WALL.get(),
                        ModBlocks.ABYSSAL_COBBLE_WALL.get(),
                        ModBlocks.ABYSSAL_SMOOTH_WALL.get(),
                        ModBlocks.QUARRIED.get(),
                        ModBlocks.QUARRIED_BRICKS.get(),
                        ModBlocks.QUARRIED_BRICKS_CRACKED.get(),
                        ModBlocks.QUARRIED_COBBLE.get(),
                        ModBlocks.QUARRIED_SMOOTH.get(),
                        ModBlocks.QUARRIED_STAIRS.get(),
                        ModBlocks.QUARRIED_BRICKS_STAIRS.get(),
                        ModBlocks.QUARRIED_BRICKS_CRACKED_STAIRS.get(),
                        ModBlocks.QUARRIED_COBBLE_STAIRS.get(),
                        ModBlocks.QUARRIED_SMOOTH_STAIRS.get(),
                        ModBlocks.QUARRIED_SLAB.get(),
                        ModBlocks.QUARRIED_BRICKS_SLAB.get(),
                        ModBlocks.QUARRIED_BRICKS_CRACKED_SLAB.get(),
                        ModBlocks.QUARRIED_COBBLE_SLAB.get(),
                        ModBlocks.QUARRIED_SMOOTH_SLAB.get(),
                        ModBlocks.QUARRIED_WALL.get(),
                        ModBlocks.QUARRIED_BRICKS_WALL.get(),
                        ModBlocks.QUARRIED_BRICKS_CRACKED_WALL.get(),
                        ModBlocks.QUARRIED_COBBLE_WALL.get(),
                        ModBlocks.QUARRIED_SMOOTH_WALL.get(),
                        ModBlocks.MINING_PORTAL.get()
                        ),
                List.of(),
                List.of(),
                List.of(),
                List.of()
                );
    }

    private void registerDecoBlocks()
    {
        this.tag(TagUtilities.modBlockTag("minecraft","slabs"))
                .add(ModBlocks.ABYSSAL_BRICKS_CRACKED_SLAB.get())
                .add(ModBlocks.ABYSSAL_BRICKS_SLAB.get())
                .add(ModBlocks.ABYSSAL_COBBLE_SLAB.get())
                .add(ModBlocks.ABYSSAL_SLAB.get())
                .add(ModBlocks.ABYSSAL_SMOOTH_SLAB.get())

                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_SLAB.get())
                .add(ModBlocks.QUARRIED_BRICKS_SLAB.get())
                .add(ModBlocks.QUARRIED_COBBLE_SLAB.get())
                .add(ModBlocks.QUARRIED_SMOOTH_SLAB.get())
                .add(ModBlocks.QUARRIED_SLAB.get());

        this.tag(TagUtilities.modBlockTag("minecraft","walls"))
                .add(ModBlocks.ABYSSAL_WALL.get())
                .add(ModBlocks.ABYSSAL_BRICK_WALL.get())
                .add(ModBlocks.ABYSSAL_BRICK_CRACKED_WALL.get())
                .add(ModBlocks.ABYSSAL_COBBLE_WALL.get())
                .add(ModBlocks.ABYSSAL_SMOOTH_WALL.get())
                .add(ModBlocks.QUARRIED_WALL.get())
                .add(ModBlocks.QUARRIED_BRICKS_WALL.get())
                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_WALL.get())
                .add(ModBlocks.QUARRIED_COBBLE_WALL.get())
                .add(ModBlocks.QUARRIED_SMOOTH_WALL.get());

        this.tag(TagUtilities.modBlockTag("minecraft","stairs"))
                .add(ModBlocks.ABYSSAL_STAIRS.get())
                .add(ModBlocks.ABYSSAL_BRICK_STAIRS.get())
                .add(ModBlocks.ABYSSAL_BRICK_CRACKED_STAIRS.get())
                .add(ModBlocks.ABYSSAL_COBBLE_STAIRS.get())
                .add(ModBlocks.ABYSSAL_SMOOTH_STAIRS.get())
                .add(ModBlocks.QUARRIED_STAIRS.get())
                .add(ModBlocks.QUARRIED_BRICKS_STAIRS.get())
                .add(ModBlocks.QUARRIED_BRICKS_CRACKED_STAIRS.get())
                .add(ModBlocks.QUARRIED_COBBLE_STAIRS.get())
                .add(ModBlocks.QUARRIED_SMOOTH_STAIRS.get());
    }
}
