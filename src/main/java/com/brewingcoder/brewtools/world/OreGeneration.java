package com.brewingcoder.brewtools.world;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.config.Configs;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.tags.BlockTags;

import java.util.List;

@Mod.EventBusSubscriber(modid = BrewTools.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGeneration {

    //public static ConfiguredFeature<?,?> ABYSSAL_FEATURE;
    public static ConfiguredFeature<?,?> QUARRIED_FEATURE;
    public static PlacedFeature ABYSSAL_FEATURE;
    //public static final BlockTags<Block> ORE_SPAWNABLE = BlockTags.bind(new ResourceLocation(BrewTools.MODID,"oregen_stones").toString());

    static final RuleTest BASE_BLOCKS_TAG = new TagMatchTest(Tags.Blocks.STONE);


        @SubscribeEvent
        public static void createConfiguredOreFeature(FMLCommonSetupEvent event){
            int height;
            List<OreConfiguration.TargetBlockState> target;
            ConfiguredFeature<OreConfiguration,?> feature;

            if (Configs.WORLD.doAbyssal.get()) {
                target = List.of(
                        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ABYSSAL.get().defaultBlockState()),
                        OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.ABYSSAL.get().defaultBlockState())
                );
                feature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(target, Configs.WORLD.AbyssalClusterSize.get()));
             //   ABYSSAL_FEATURE = new PlacedFeature(feature, )
            }
        }

        private static PlacedFeature MakeFeature(String name, Block block, RuleTest rule, int vienSize, int veinsPerChunk, int height ){






    }


}
