package com.brewingcoder.brewtools.compat;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.Items.NightVisionGoggleItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class CuriosCompat {
    private static final ResourceLocation GOGGLE_TEXTURE = new ResourceLocation(BrewTools.MODID,"textures/item/goggles.png");

    public static ICapabilityProvider initCapabilities(ItemStack itemstack) {
        ICurio curio = new ICurio() {

            @Override
            public void curioTick(SlotContext slotContext) {
                if (itemstack.getItem() instanceof NightVisionGoggleItem && slotContext.entity() instanceof Player){
                    Player player = (Player)slotContext.entity();
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,240,1));
                }
            }

            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
                Multimap<Attribute,AttributeModifier> multimap = ICurio.super.getAttributeModifiers(slotContext, uuid);
                if (itemstack.getItem() instanceof NightVisionGoggleItem && slotContext.entity() instanceof Player) {
                    ListMultimap<Attribute, AttributeModifier> mutableMultimap = toMutableMultimap(multimap);
                    mutableMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Max Health", -10, AttributeModifier.Operation.ADDITION));
                    return toImmutableMultimap(mutableMultimap);
                }
                return multimap;
            }

            @Override
            public ItemStack getStack() {
                return itemstack;
            }
            public static<K, V> ListMultimap<K, V> toMutableMultimap(Multimap<K, V> original) {
                    ListMultimap<K, V> copy = ArrayListMultimap.create();
                    copy.putAll(original);
                    return copy;
            }

            public static<K, V> ImmutableMultimap<K, V> toImmutableMultimap(ListMultimap<K, V> original) {
                    return new ImmutableMultimap.Builder<K, V>().putAll(original).build();
            }

        };
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(()->curio);
            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return CuriosCapability.ITEM.orEmpty(cap,curioOpt);
            }
        };
    }
}
