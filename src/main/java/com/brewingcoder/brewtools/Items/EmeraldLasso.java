package com.brewingcoder.brewtools.Items;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class EmeraldLasso extends Item {

    public final static String NBT_HAS_ANIMAL = BrewTools.MODID + ".HAS_ANIMAL";
    public final static String NBT_ANIMAL = BrewTools.MODID + ".ANIMAL";

    public EmeraldLasso(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasAnimal(stack);
    }

    public boolean hasAnimal(ItemStack itemStack){
        CompoundTag nbt = itemStack.getTag();
        return nbt != null && nbt.getBoolean(NBT_HAS_ANIMAL);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        ItemStack itemStack = ctx.getItemInHand();
        CompoundTag nbt = itemStack.getTag();
        Level world = ctx.getLevel();
        if( (world instanceof ServerLevel) && nbt != null && nbt.contains(NBT_HAS_ANIMAL)){
            BlockPos pos = ctx.getClickedPos();
            Player player = ctx.getPlayer();
            if (player == null) return InteractionResult.FAIL;
            player.playSound(ModSounds.LASSO_WHOOSH.get(),2,1);
            CompoundTag spawn_nbt = nbt.getCompound(NBT_ANIMAL);
            EntityType<?> et = EntityType.by(spawn_nbt).orElse(EntityType.DONKEY);
            BlockPos spawn_position = new BlockPos(pos.getX(),pos.getY()+1,pos.getZ());
            et.spawn((ServerLevel)world,spawn_nbt,null,player,spawn_position, MobSpawnType.COMMAND,false,false );
            itemStack.removeTagKey(NBT_HAS_ANIMAL);
            itemStack.removeTagKey(NBT_ANIMAL);
            itemStack.hurtAndBreak(1,player,(p)->p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if (hasAnimal(stack) || !livingEntity.isAlive() || livingEntity instanceof Player) return InteractionResult.PASS;

        if(livingEntity instanceof Monster){
            player.displayClientMessage(new TranslatableComponent(livingEntity.getName().getString() + " is a Monster!"),true);
        }else if(!(livingEntity instanceof Merchant)) {
            player.displayClientMessage(new TranslatableComponent(livingEntity.getName().getString() + " is NOT a Merchant or Villager!"),true);
        }else{
            player.displayClientMessage(new TranslatableComponent("Capturing " + livingEntity.getName().getString() ),true);
            player.playSound(ModSounds.LASSO_WHOOSH.get(),2,1);
            String name = livingEntity.hasCustomName() ? Objects.requireNonNull(livingEntity.getCustomName()).getString() : livingEntity.getName().getString();
            if (!player.level.isClientSide && livingEntity.isAlive()){
                stack.getOrCreateTag().putBoolean(NBT_HAS_ANIMAL, true);
                CompoundTag entityNBT = livingEntity.serializeNBT();
                stack.getOrCreateTag().put(NBT_ANIMAL,entityNBT);
                stack.setHoverName(new TranslatableComponent("Captured " + name));
                stack.hurtAndBreak(1,player,(p)->p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                livingEntity.remove(Entity.RemovalReason.DISCARDED);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
