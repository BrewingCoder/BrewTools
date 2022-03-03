package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blockbase.*;
import com.brewingcoder.brewtools.item.ItemGroups;
import com.brewingcoder.brewtools.item.ModItems;
import com.brewingcoder.brewtools.tileentity.TileEntityMiningPortal;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@SuppressWarnings({"unused", "rawtypes"})
public class ModBlocks{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BrewTools.MODID);

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> registryObject = BLOCKS.register(name,block);
        registerBlockItem(name,registryObject);
        return registryObject;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroups.MAIN)));
    }

    public static final RegistryObject<Block> MINING_PORTAL             = registerBlock("mining_portal",()-> new MiningPortalBlock(BlockProperties.rock(2f,8f)) );
    public static final RegistryObject<Block> REDSTONE_CLOCK            = registerBlock("redstone_clock",()-> new RedstoneClock(BlockProperties.rock(1f,4f).lightLevel((lvl)->15)));

    public static final RegistryObject<Block> ABYSSAL                   = registerBlock("abyssal",() -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_BRICKS            = registerBlock("abyssal_bricks",() -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_CRACKED    = registerBlock("abyssal_bricks_cracked", () -> new DecoBlockBase(BlockProperties.rock(1f,8f)));
    public static final RegistryObject<Block> ABYSSAL_COBBLE            = registerBlock("abyssal_cobble",()->new DecoBlockBase(BlockProperties.rock(1f,8f)));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH            = registerBlock("abyssal_smooth",()->new DecoBlockBase(BlockProperties.rock(1f,8f)));

    public static final RegistryObject<Block> ABYSSAL_STAIRS            = registerBlock("abyssal_stairs",()->new StairBlockBase(ModBlocks.ABYSSAL.get().defaultBlockState(), BlockProperties.rock(1f,8f)));
    public static final RegistryObject<Block> ABYSSAL_BRICK_STAIRS      = registerBlock("abyssal_brick_stairs",()->new StairBlockBase(ModBlocks.ABYSSAL_BRICKS.get().defaultBlockState(), BlockProperties.rock(1f,8f)));

    public static final RegistryObject<Block> ABYSSAL_BRICK_CRACKED_STAIRS   = registerBlock("abyssal_brick_cracked_stairs",() -> new StairBlockBase(ModBlocks.ABYSSAL_BRICKS_CRACKED.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_STAIRS          = registerBlock("abyssal_cobble_stairs",() -> new StairBlockBase(ModBlocks.ABYSSAL_COBBLE.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_STAIRS          = registerBlock("abyssal_smooth_stairs",() -> new StairBlockBase(ModBlocks.ABYSSAL_SMOOTH.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> ABYSSAL_SLAB                   = registerBlock("abyssal_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_SLAB            = registerBlock("abyssal_brick_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_CRACKED_SLAB    = registerBlock("abyssal_brick_cracked_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_SLAB            = registerBlock("abyssal_cobble_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_SLAB            = registerBlock("abyssal_smooth_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> ABYSSAL_WALL                   = registerBlock("abyssal_wall", () -> new WallBlockBase(AbstractBlock.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> ABYSSAL_BRICK_WALL             = registerBlock("abyssal_brick_wall",() -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_BRICK_CRACKED_WALL     = registerBlock("abyssal_brick_cracked_wall",() -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_WALL            = registerBlock("abyssal_cobble_wall", () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_WALL            = registerBlock("abyssal_smooth_wall", () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> QUARRIED                       =registerBlock("quarried",   () -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS                =registerBlock("quarried_bricks",  () -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED        =registerBlock("quarried_bricks_cracked",   () -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_COBBLE                =registerBlock("quarried_cobble",   () -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_SMOOTH                =registerBlock("quarried_smooth",   () -> new DecoBlockBase(BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> QUARRIED_STAIRS                =registerBlock("quarried_stairs", () -> new StairBlockBase(ModBlocks.QUARRIED.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_STAIRS         =registerBlock("quarried_bricks_stairs", () -> new StairBlockBase(ModBlocks.QUARRIED_BRICKS.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_STAIRS =registerBlock("quarried_bricks_cracked_stairs", () -> new StairBlockBase(ModBlocks.QUARRIED_BRICKS_CRACKED.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_COBBLE_STAIRS         =registerBlock("quarried_cobble_stairs", () -> new StairBlockBase(ModBlocks.QUARRIED_COBBLE.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_STAIRS         =registerBlock("quarried_smooth_stairs", () -> new StairBlockBase(ModBlocks.QUARRIED_SMOOTH.get().defaultBlockState(), BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> QUARRIED_SLAB                  =registerBlock("quarried_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_SLAB           =registerBlock("quarried_bricks_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_SLAB   =registerBlock("quarried_bricks_cracked_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_COBBLE_SLAB           =registerBlock("quarried_cobble_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_SLAB           =registerBlock("quarried_smooth_slab", () -> new SlabBlockBase(BlockProperties.rock(1.0f,8.0f)));

    public static final RegistryObject<Block> QUARRIED_WALL                  =registerBlock("quarried_wall",                () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_WALL           =registerBlock("quarried_bricks_wall",         () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_WALL   =registerBlock("quarried_bricks_cracked_wall", () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_COBBLE_WALL           =registerBlock("quarried_cobble_wall",         () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_WALL           =registerBlock("quarried_smooth_wall",         () -> new WallBlockBase(BlockProperties.rock(1.0f,8.0f)));


}
