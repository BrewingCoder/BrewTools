package com.brewingcoder.brewtools.world;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.config.Configs;
import com.brewingcoder.brewtools.data.ModTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = BrewTools.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGeneration {

    public static ConfiguredFeature<?,?> ABYSSAL_STONE_OVERWORLD;
    public static ConfiguredFeature<?,?> QUARRIED_STONE_FEATURE;

    @SubscribeEvent
    public static void createConfiguredOreFeature(FMLCommonSetupEvent event){
        if (Configs.WORLD.doAbyssal.get()) {

            ABYSSAL_STONE_OVERWORLD =
                    Feature.ORE.configured(
                                    new OreFeatureConfig(new TagMatchRuleTest(ModTags.ORE_SPAWNABLE),
                                            ModBlocks.ABYSSAL.get().defaultBlockState(),
                                            Configs.WORLD.AbyssalClusterSize.get()))
                            .decorated(Placement.RANGE.configured(
                                    new TopSolidRangeConfig(Configs.WORLD.AbyssalMinY.get(),0,Configs.WORLD.AbyssalMaxY.get())))
                            .squared()
                            .count(Configs.WORLD.AbyssalNumPerChunk.get());

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"abyssal_stone_overworld_generation",OreGeneration.ABYSSAL_STONE_OVERWORLD);
        }else{
            ABYSSAL_STONE_OVERWORLD = null;
        }
        if (Configs.WORLD.doQuarried.get()) {

            QUARRIED_STONE_FEATURE =
                    Feature.ORE.configured(
                                    new OreFeatureConfig(new TagMatchRuleTest(ModTags.ORE_SPAWNABLE),
                                            ModBlocks.QUARRIED.get().defaultBlockState(),
                                            Configs.WORLD.QuarriedClusterSize.get()))
                            .decorated(Placement.RANGE.configured(
                                    new TopSolidRangeConfig(Configs.WORLD.QuarriedMinY.get(),0,Configs.WORLD.QuarriedMaxY.get())))
                            .squared()
                            .count(Configs.WORLD.QuarriedNumPerChunk.get());

            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"quarried_stone_overworld_generation",OreGeneration.QUARRIED_STONE_FEATURE);
        }else{
            QUARRIED_STONE_FEATURE = null;
        }
    }
}
