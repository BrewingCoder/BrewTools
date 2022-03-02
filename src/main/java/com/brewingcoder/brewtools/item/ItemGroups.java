package com.brewingcoder.brewtools.item;

import com.brewingcoder.brewtools.BrewTools;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemGroups {
    public static final CreativeModeTab  MAIN = new CreativeModeTab(BrewTools.MODID) {


        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GOGGLES.get());
        }
    };
}
