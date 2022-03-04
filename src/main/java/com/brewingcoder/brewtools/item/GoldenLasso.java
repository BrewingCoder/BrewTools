package com.brewingcoder.brewtools.item;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Objects;


public class GoldenLasso extends Item {
    public final static String NBT_HAS_ANIMAL = BrewTools.MODID + ".HAS_ANIMAL";
    public final static String NBT_ANIMAL = BrewTools.MODID + ".ANIMAL";

    public GoldenLasso(Properties p) {
         super(p);
    }
    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable net.minecraft.nbt.CompoundNBT nbt) {
            return super.initCapabilities(stack, nbt);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasAnimal(stack);
    }

    @Override
    public ActionResultType useOn(ItemUseContext ctx) {
        ItemStack itemStack = ctx.getItemInHand();
        CompoundNBT nbt = itemStack.getTag();
        World world = ctx.getLevel();
        if ((world instanceof ServerWorld) && nbt != null && nbt.contains(NBT_HAS_ANIMAL)) {
            BlockPos pos = ctx.getClickedPos();
            PlayerEntity player = ctx.getPlayer();
            if (player == null) return ActionResultType.FAIL;
            player.playSound(ModSounds.LASSO_WHOOSH.get(),2,1);
            CompoundNBT spawn_nbt = nbt.getCompound(NBT_ANIMAL);
            EntityType<?> et = EntityType.by(spawn_nbt).orElse(EntityType.MINECART);
            BlockPos spawn_position = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
            et.spawn((ServerWorld)world,spawn_nbt,null,player,spawn_position, SpawnReason.COMMAND,false,false);
            itemStack.removeTagKey(NBT_HAS_ANIMAL);
            itemStack.removeTagKey(NBT_ANIMAL);
            itemStack.hurtAndBreak(1, player,(p)->p.broadcastBreakEvent(Hand.MAIN_HAND));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity livingEntity, Hand hand) {
        if (hasAnimal(stack) || !livingEntity.isAlive() || livingEntity instanceof PlayerEntity) return ActionResultType.PASS;

        if (livingEntity instanceof MonsterEntity) {
            player.displayClientMessage(new TranslationTextComponent(livingEntity.getName().getString() + " is a Monster!"), true);
        }else if(livingEntity instanceof IMerchant){
            player.displayClientMessage(new TranslationTextComponent(livingEntity.getName().getString() + " is a Merchant!"), true);
        }else{
            player.displayClientMessage(new TranslationTextComponent("Capturing: " + livingEntity.getName().getString()),true);
            player.playSound(ModSounds.LASSO_WHOOSH.get(),2,1);

            String name = livingEntity.hasCustomName() ? Objects.requireNonNull(livingEntity.getCustomName()).getString() : livingEntity.getName().getString();

            if (!player.level.isClientSide && livingEntity.isAlive()) {
                stack.getOrCreateTag().putBoolean(NBT_HAS_ANIMAL,true);
                CompoundNBT entityNBT = livingEntity.serializeNBT();
                stack.getOrCreateTag().put(NBT_ANIMAL,entityNBT);
                stack.setHoverName(new TranslationTextComponent("Captured "+ name));
                stack.hurtAndBreak(1,player,(p)->p.broadcastBreakEvent(Hand.MAIN_HAND));
                livingEntity.remove();
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public boolean hasAnimal(ItemStack itemStack){
        CompoundNBT nbt = itemStack.getTag();
        return nbt != null && nbt.getBoolean(NBT_HAS_ANIMAL);
    }
}
