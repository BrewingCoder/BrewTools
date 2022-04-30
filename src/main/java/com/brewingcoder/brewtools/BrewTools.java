package com.brewingcoder.brewtools;

import com.brewingcoder.brewtools.Items.ModItems;
import com.brewingcoder.brewtools.blockentities.MiningPortalEntity;
import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.config.Configs;
import com.brewingcoder.brewtools.sound.ModSounds;
import com.brewingcoder.brewtools.world.OreGeneration;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;


@SuppressWarnings("unused")
@Mod(BrewTools.MODID)
public class BrewTools
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "brewtools";
    public static ResourceKey<Level> MINING_WORLD;
    public static ResourceKey<Level> OVERWORLD;
    public static BlockEntityType<MiningPortalEntity> TE_PORTAL;
    public static final String CURIOS = "curios";

    public BrewTools()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Configs.register();
        ModBlocks.register(bus);
        ModItems.register(bus);
        ModSounds.register(bus);
        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addGenericListener(BlockEntityType.class,this::registerTileEntities);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, OreGeneration::onBiomeLoadingEvent);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        MINING_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BrewTools.MODID,"mining_world"));
        OVERWORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation("minecraft:overworld"));
    }

    private void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event){
        TE_PORTAL = BlockEntityType.Builder.of(
                MiningPortalEntity::new,
                ModBlocks.MINING_PORTAL.get()).build(null);
        TE_PORTAL.setRegistryName(new ResourceLocation(MODID,"tileentityportal"));
        event.getRegistry().registerAll(TE_PORTAL);
    }

    private void enqueueIMC(final InterModEnqueueEvent event){
        InterModComms.sendTo(CURIOS, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
    }

}
