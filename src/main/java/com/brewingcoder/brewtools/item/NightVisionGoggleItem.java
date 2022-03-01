package com.brewingcoder.brewtools.item;

import com.google.common.collect.*;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.UUID;

public class NightVisionGoggleItem extends ArmorItem {

    public EquipmentSlotType equipmentSlotType;

    private static final UUID[] ARMOR_MODIFIERS =
            new UUID[] {
                    UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
                    UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
                    UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
                    UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")
            };

    public NightVisionGoggleItem(IArmorMaterial material, EquipmentSlotType equipSlotType, Properties properties) {
        super(material, equipSlotType, properties);
        equipmentSlotType = equipSlotType;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.NIGHT_VISION,240,0,true,false));
        super.onArmorTick(stack, world, player);
    }

    public static<K, V> ListMultimap<K, V> toMutableMultimap(Multimap<K, V> original) {
        ListMultimap<K, V> copy = ArrayListMultimap.create();
        copy.putAll(original);
        return copy;
    }

    public static<K, V> ImmutableMultimap<K, V> toImmutableMultimap(ListMultimap<K, V> original) {
        return new ImmutableMultimap.Builder<K, V>().putAll(original).build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
            Multimap<Attribute,AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
            Item item = stack.getItem();
            if(item instanceof NightVisionGoggleItem && slot == this.slot) {
                ListMultimap<Attribute,AttributeModifier> mutableMultimap = toMutableMultimap(multimap);
                mutableMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ARMOR_MODIFIERS[(slot.getIndex())], "Max Health", -10, AttributeModifier.Operation.ADDITION));
                return toImmutableMultimap(mutableMultimap);
            }
            return multimap;

    }
}
