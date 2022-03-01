package com.brewingcoder.brewtools.item;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrewTools.MODID);

    public static final RegistryObject<Item> GOGGLES = ITEMS.register("goggles",  () ->   new NightVisionGoggleItem(ModArmorMaterial.GOOGLES, EquipmentSlotType.HEAD,(new Item.Properties()).tab(ItemGroups.MAIN)));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
