package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.item.IItemBlock;
import com.brewingcoder.brewtools.item.ItemGroups;
import com.brewingcoder.brewtools.tileentity.TileEntityMiningPortal;
import com.brewingcoder.brewtools.world.MiningWorldTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class MiningPortalBlock extends Block implements EntityBlock, IItemBlock {


    public MiningPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Item toItem() {
        return new BlockItem(this, new Item.Properties().tab(ItemGroups.MAIN)).setRegistryName(getRegistryName());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(player != null && player instanceof ServerPlayer && !player.isCrouching()){
            teleportPlayer((ServerPlayer)player, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    public boolean teleportPlayer(ServerPlayer player, BlockPos pos){
        if(player.getVehicle() != null || player.isVehicle()){
            return false;
        }
        MinecraftServer server = player.getServer();
        if (server == null) return false;
        if(player.level.dimension().equals(BrewTools.MINING_WORLD)){
            ServerLevel overworld = server.overworld();
            player.changeDimension(overworld, new MiningWorldTeleporter(pos));
        }else{
            ServerLevel miningWorld = player.server.getLevel(BrewTools.MINING_WORLD);
            if (miningWorld == null) return false;
            player.changeDimension(miningWorld, new MiningWorldTeleporter(pos));
        }
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        if(random.nextFloat() < 3.95f){
            BlockEntity te = world.getBlockEntity(pos);
            if(te instanceof TileEntityMiningPortal){
                world.addParticle(ParticleTypes.PORTAL, pos.getX() +  (random.nextDouble() - 0.5) * 1.5, pos.getY() + 2, pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
                world.addParticle(ParticleTypes.ENCHANT, pos.getX() + (random.nextDouble() - 0.5) * 1.5, pos.getY() + 2, pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
            }
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityMiningPortal(pos,state);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return EntityBlock.super.getTicker(p_153212_, p_153213_, p_153214_);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level p_153210_, T p_153211_) {
        return EntityBlock.super.getListener(p_153210_, p_153211_);
    }
}
