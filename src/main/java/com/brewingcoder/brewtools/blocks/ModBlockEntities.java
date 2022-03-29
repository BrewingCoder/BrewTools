package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blockentities.MiningPortalEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BrewTools.MODID);

//    public static final RegistryObject<BlockEntityType<MiningPortalEntity>> MINING_PORTAL_ENTITY =
//            BLOCK_ENTITIES.register(
//                    "mining_portal_entity",
//                    ()->BlockEntityType.Builder.of(
//                                    MiningPortalEntity::new,
//                            ModBlocks.MINING_PORTAL.get())
//                            .build(null)
//                    );




    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }


}
