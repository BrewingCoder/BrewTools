package com.brewingcoder.brewtools;

import com.brewingcoder.brewtools.blocks.MiningPortalBlock;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.config.Configs;
import com.brewingcoder.brewtools.item.ModItems;
import com.brewingcoder.brewtools.tileentity.TileEntityMiningPortal;
import com.brewingcoder.brewtools.world.OreGeneration;
//import net.minecraft.block.Block;
//import net.minecraft.item.Item;
//import net.minecraft.tileentity.TileEntityType;
//import net.minecraft.util.RegistryKey;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.World;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
//import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SuppressWarnings("unused")
@Mod(BrewTools.MODID)

public class BrewTools
{
    public static final String MODID = "brewtools";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ResourceKey<Level> MINING_WORLD;
    public static ResourceKey<Level> OVERWORLD;
    public static BlockEntityType<TileEntityMiningPortal> TE_PORTAL;




    public BrewTools() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Configs.register();

        ModItems.register(bus);
        ModBlocks.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);
        bus.addGenericListener(BlockEntityType.class, this::registerTileEntities);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        MINING_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BrewTools.MODID,"mining_world"));
        OVERWORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation("minecraft:overworld"));
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }

    private void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event){
     TE_PORTAL = BlockEntityType.Builder.of(
             TileEntityMiningPortal::new,
             ModBlocks.MINING_PORTAL.get()).build(null);
     TE_PORTAL.setRegistryName(new ResourceLocation(MODID,"tileentityportal"));
     event.getRegistry().registerAll(TE_PORTAL);
    }


}
