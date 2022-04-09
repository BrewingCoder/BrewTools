package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.blockentities.MiningPortalEntity;
import com.brewingcoder.brewtools.world.MiningWorldTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@SuppressWarnings("deprecation")
public class MiningPortalBlock extends Block implements EntityBlock {

    public MiningPortalBlock(Properties props) {
        super(props);
    }


    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BrewTools.LOGGER.debug("Player Interaction with Portal Block");
        if(player instanceof ServerPlayer && !player.isCrouching()){
            BrewTools.LOGGER.debug("Player is ServerPlayer and Not Crouching");
            teleportPlayer((ServerPlayer)player,pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MiningPortalEntity(pos,state);
    }

    public void teleportPlayer(ServerPlayer player, BlockPos pos){
        if(player.getVehicle() != null || player.isVehicle()){
            BrewTools.LOGGER.debug("Player is a/in vehicle. Cannot Teleport");
            return;
        }
        MinecraftServer server = player.getServer();
        if(server == null) return;
        if(player.level.dimension().equals(BrewTools.MINING_WORLD)){
            ServerLevel overworld = server.overworld();
            player.changeDimension(overworld, new MiningWorldTeleporter(pos));
        }else{
            BrewTools.LOGGER.debug("Player is not in mining_world, send there");
            ServerLevel miningWorld = player.server.getLevel(BrewTools.MINING_WORLD);
            if (miningWorld == null){
                BrewTools.LOGGER.error("Mining World Dimension NOT located. Was it registered properly?");
                return;
            }
            player.changeDimension(miningWorld, new MiningWorldTeleporter(pos));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        random.nextFloat();
        BlockEntity te = world.getBlockEntity(pos);
        if(te instanceof MiningPortalEntity){
            doParticles(ParticleTypes.PORTAL,world,pos,random);
            doParticles(ParticleTypes.ENCHANT,world,pos,random);
        }
    }
    private void doParticles(ParticleOptions particleOptions,Level world, BlockPos pos, Random random){
            world.addParticle(particleOptions, pos.getX() +  (random.nextDouble() - 0.5) * 1.5, pos.getY() + 2, pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
    }
}
