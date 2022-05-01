//package com.brewingcoder.brewtools.datagen;
//
//import com.brewingcoder.brewtools.BrewTools;
//import com.brewingcoder.brewtools.datagen.core.ModItemTags;
//import net.minecraft.data.DataGenerator;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
//
//import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
//
//@Mod(BrewTools.MODID)
//@Mod.EventBusSubscriber(bus= MOD)
//public class ModDataGenerator {
//
//    @SubscribeEvent
//    static void gatherData(final GatherDataEvent event){
//        DataGenerator generator = event.getGenerator();
//        if(event.includeServer())
//        {
//            generator.addProvider(new ModBlockTags(generator, event.getExistingFileHelper()));
//            generator.addProvider(new ModItemTags(generator, event.getExistingFileHelper()));
//        }
//    }
//
//}
