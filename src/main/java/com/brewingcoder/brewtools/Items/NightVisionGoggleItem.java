package com.brewingcoder.brewtools.Items;

import com.brewingcoder.brewtools.compat.CuriosCompat;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class NightVisionGoggleItem extends Item implements ICurioItem {

//    private static final UUID[] ARMOR_MODIFIERS =
//            new UUID[] {
//                    UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
//                    UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
//                    UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
//                    UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")
//            };
//
//    public EquipmentSlot equipmentSlot;

    public NightVisionGoggleItem(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return CuriosCompat.initCapabilities(stack);
    }
    //        super(material, slot, properties);
//        this.equipmentSlot = slot;
//    }

//    @Override
//    public void onArmorTick(ItemStack stack, Level level, Player player) {
//        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,240,1));
//        super.onArmorTick(stack, level, player);
//    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Attribute,AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
//        Item item = stack.getItem();
//        if(item instanceof NightVisionGoggleItem && slot == this.slot) {
//            ListMultimap<Attribute,AttributeModifier> mutableMultimap = toMutableMultimap(multimap);
//            mutableMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ARMOR_MODIFIERS[(slot.getIndex())], "Max Health", -10, AttributeModifier.Operation.ADDITION));
//        }
//        return multimap;
//    }

//    public static<K, V> ListMultimap<K, V> toMutableMultimap(Multimap<K, V> original) {
//        ListMultimap<K, V> copy = ArrayListMultimap.create();
//        copy.putAll(original);
//        return copy;
//    }
//
//    public static<K, V> ImmutableMultimap<K, V> toImmutableMultimap(ListMultimap<K, V> original) {
//        return new ImmutableMultimap.Builder<K, V>().putAll(original).build();
//    }



//    @Override
//    public void curioTick(SlotContext slotContext) {
//        if (slotContext.entity() instanceof Player){
//            Player player = (Player)slotContext.entity();
//            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,240,1));
//        }
//    }
}
