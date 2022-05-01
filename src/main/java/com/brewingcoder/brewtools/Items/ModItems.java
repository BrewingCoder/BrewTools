package com.brewingcoder.brewtools.Items;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrewTools.MODID);

    public static final RegistryObject<Item> GOGGLES = registerItem("goggles", () -> new NightVisionGoggleItem(new Item.Properties().tab(ItemTabs.MAIN).stacksTo(1).durability(100).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LASSO = registerItem("golden_lasso",()-> new GoldenLasso(new Item.Properties().tab(ItemTabs.MAIN).stacksTo(1).durability(6).setNoRepair()));
    public static final RegistryObject<Item> EMERALD_LASSO = registerItem("emerald_lasso",()-> new EmeraldLasso(new Item.Properties().tab(ItemTabs.MAIN).stacksTo(1).durability(20).setNoRepair()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }


    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item ){
            RegistryObject<T> toReturn = ITEMS.register(name,item);
            return toReturn;
    }
}
