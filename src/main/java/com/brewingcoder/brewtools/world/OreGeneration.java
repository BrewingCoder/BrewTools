package com.brewingcoder.brewtools.world;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.config.Configs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = BrewTools.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGeneration {

    public static Holder<PlacedFeature> ABYSSAL_FEATURE;
    public static Holder<PlacedFeature> QUARRIED_FEATURE;

    @SubscribeEvent
    public static void createConfiguredOreFeature(FMLCommonSetupEvent event) {

        OreConfiguration abyssalConfig = new OreConfiguration(
                OreFeatures.STONE_ORE_REPLACEABLES,
                ModBlocks.ABYSSAL.get().defaultBlockState(),
                Configs.WORLD.AbyssalClusterSize.get()
                );

        ABYSSAL_FEATURE = registerPlacedFeature(
                "abyssal_overworld",
                new ConfiguredFeature<>(Feature.ORE,abyssalConfig),
                CountPlacement.of(Configs.WORLD.AbyssalNumPerChunk.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(Configs.WORLD.AbyssalMinY.get()),VerticalAnchor.absolute(Configs.WORLD.AbyssalMaxY.get()))
                );

        OreConfiguration quarriedConfig = new OreConfiguration(
                OreFeatures.STONE_ORE_REPLACEABLES,
                ModBlocks.QUARRIED.get().defaultBlockState(),
                Configs.WORLD.QuarriedClusterSize.get()
        );

        QUARRIED_FEATURE = registerPlacedFeature(
                "quarried_overworld",
                new ConfiguredFeature<>(Feature.ORE,quarriedConfig),
                CountPlacement.of(Configs.WORLD.QuarriedNumPerChunk.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(Configs.WORLD.QuarriedMinY.get()),VerticalAnchor.belowTop(Configs.WORLD.QuarriedMaxY.get()))
        );

    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event){
        if(event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND){
            if(Configs.WORLD.DoAbyssal.get()) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ABYSSAL_FEATURE);
            }
            if(Configs.WORLD.DoQuarried.get()) {
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, QUARRIED_FEATURE);
            }
        }
    }
}
