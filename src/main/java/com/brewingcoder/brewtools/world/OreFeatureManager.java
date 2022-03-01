package com.brewingcoder.brewtools.world;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.config.Configs;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BrewTools.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OreFeatureManager {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerOres(final BiomeLoadingEvent event){
        if(
                event.getCategory() != Biome.Category.NETHER
                        && event.getCategory() != Biome.Category.THEEND
                        && Configs.WORLD.doAbyssal.get()
        ){
           event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OreGeneration.ABYSSAL_STONE_OVERWORLD);
        }
        if(
                event.getCategory() != Biome.Category.NETHER
                && event.getCategory() != Biome.Category.THEEND
                && Configs.WORLD.doQuarried.get()
        ){
          event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,OreGeneration.QUARRIED_STONE_FEATURE);
        }
    }

}
