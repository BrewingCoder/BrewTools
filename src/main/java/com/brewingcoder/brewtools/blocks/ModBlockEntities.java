package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BrewTools.MODID);


    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }


}
