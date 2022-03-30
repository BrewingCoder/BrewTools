package com.brewingcoder.brewtools.Items;

import com.brewingcoder.brewtools.compat.CuriosCompat;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class NightVisionGoggleItem extends Item implements ICurioItem {


    public NightVisionGoggleItem(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return CuriosCompat.initCapabilities(stack);
    }

}
