package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.Items.ItemTabs;
import com.brewingcoder.brewtools.Items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings({"unused", "UnusedReturnValue", "deprecation"})
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BrewTools.MODID);

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().tab(ItemTabs.MAIN)));
    }
    
    private static final BlockBehaviour.Properties defaultProps = BlockBehaviour.Properties
            .of(Material.STONE)
            .sound(SoundType.STONE)
            .strength(0.2f,0.2f);

    public static final RegistryObject<Block> REDSTONE_CLOCK                = registerBlock("redstone_clock", ()->new RedstoneClock(BlockBehaviour.Properties.of(Material.WOOL).lightLevel((lvl)->15)));
    public static final RegistryObject<Block> MINING_PORTAL                 = registerBlock("mining_portal",()->new MiningPortalBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL                    = registerBlock("abyssal",()-> new Block(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICKS                = registerBlock("abyssal_bricks",() -> new Block(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_CRACKED        = registerBlock("abyssal_bricks_cracked", () -> new Block(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_COBBLE                = registerBlock("abyssal_cobble",()-> new Block(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH                = registerBlock("abyssal_smooth",()-> new Block(defaultProps));

    public static final RegistryObject<Block> ABYSSAL_STAIRS                 = registerBlock("abyssal_stairs", ()->new  StairBlock(ModBlocks.ABYSSAL.get().defaultBlockState(),defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICK_STAIRS           = registerBlock("abyssal_brick_stairs",()->new StairBlock(ModBlocks.ABYSSAL_BRICKS.get().defaultBlockState(),defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICK_CRACKED_STAIRS   = registerBlock("abyssal_brick_cracked_stairs",() -> new StairBlock(ModBlocks.ABYSSAL_BRICKS_CRACKED.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_STAIRS          = registerBlock("abyssal_cobble_stairs",() -> new StairBlock(ModBlocks.ABYSSAL_COBBLE.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_STAIRS          = registerBlock("abyssal_smooth_stairs",() -> new StairBlock(ModBlocks.ABYSSAL_SMOOTH.get().defaultBlockState(), defaultProps));

    public static final RegistryObject<Block> ABYSSAL_SLAB                   = registerBlock("abyssal_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_SLAB            = registerBlock("abyssal_brick_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICKS_CRACKED_SLAB    = registerBlock("abyssal_brick_cracked_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_SLAB            = registerBlock("abyssal_cobble_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_SLAB            = registerBlock("abyssal_smooth_slab", () -> new SlabBlock(defaultProps));

    public static final RegistryObject<Block> ABYSSAL_WALL                   = registerBlock("abyssal_wall", () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICK_WALL             = registerBlock("abyssal_brick_wall",() -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_BRICK_CRACKED_WALL     = registerBlock("abyssal_brick_cracked_wall",() -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_COBBLE_WALL            = registerBlock("abyssal_cobble_wall", () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> ABYSSAL_SMOOTH_WALL            = registerBlock("abyssal_smooth_wall", () -> new WallBlock(defaultProps));

    public static final RegistryObject<Block> QUARRIED                      =registerBlock("quarried",   () -> new Block(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS                =registerBlock("quarried_bricks",  () -> new Block(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED        =registerBlock("quarried_bricks_cracked",   () -> new Block(defaultProps));
    public static final RegistryObject<Block> QUARRIED_COBBLE                =registerBlock("quarried_cobble",   () -> new Block(defaultProps));
    public static final RegistryObject<Block> QUARRIED_SMOOTH                =registerBlock("quarried_smooth",   () -> new Block(defaultProps));

    public static final RegistryObject<Block> QUARRIED_STAIRS                =registerBlock("quarried_stairs", () -> new StairBlock(ModBlocks.QUARRIED.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_STAIRS         =registerBlock("quarried_bricks_stairs", () -> new StairBlock(ModBlocks.QUARRIED_BRICKS.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_STAIRS =registerBlock("quarried_bricks_cracked_stairs", () -> new StairBlock(ModBlocks.QUARRIED_BRICKS_CRACKED.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> QUARRIED_COBBLE_STAIRS         =registerBlock("quarried_cobble_stairs", () -> new StairBlock(ModBlocks.QUARRIED_COBBLE.get().defaultBlockState(), defaultProps));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_STAIRS         =registerBlock("quarried_smooth_stairs", () -> new StairBlock(ModBlocks.QUARRIED_SMOOTH.get().defaultBlockState(), defaultProps));

    public static final RegistryObject<Block> QUARRIED_SLAB                  =registerBlock("quarried_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_SLAB           =registerBlock("quarried_bricks_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_SLAB   =registerBlock("quarried_bricks_cracked_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_COBBLE_SLAB           =registerBlock("quarried_cobble_slab", () -> new SlabBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_SLAB           =registerBlock("quarried_smooth_slab", () -> new SlabBlock(defaultProps));

    public static final RegistryObject<Block> QUARRIED_WALL                  =registerBlock("quarried_wall",                () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_WALL           =registerBlock("quarried_bricks_wall",         () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_BRICKS_CRACKED_WALL   =registerBlock("quarried_bricks_cracked_wall", () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_COBBLE_WALL           =registerBlock("quarried_cobble_wall",         () -> new WallBlock(defaultProps));
    public static final RegistryObject<Block> QUARRIED_SMOOTH_WALL           =registerBlock("quarried_smooth_wall",         () -> new WallBlock(defaultProps));

}
