package com.brewingcoder.brewtools.Items;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrewTools.MODID);

    public static final RegistryObject<Item> GOGGLES = ITEMS.register(
            "goggles",
            () -> new NightVisionGoggleItem(
                    new Item.Properties()
                            .tab(ItemTabs.MAIN)
                            .stacksTo(1)
                            .durability(100)
                            .rarity(Rarity.UNCOMMON)
            ));
    public static final RegistryObject<Item> LASSO = ITEMS.register("golden_lasso", ()-> new GoldenLasso(new Item.Properties().tab(ItemTabs.MAIN).stacksTo(1).durability(6).setNoRepair()));
    public static final RegistryObject<Item> EMERALD_LASSO = ITEMS.register("emerald_lasso", ()-> new EmeraldLasso(new Item.Properties().tab(ItemTabs.MAIN).stacksTo(1).durability(20).setNoRepair()));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
