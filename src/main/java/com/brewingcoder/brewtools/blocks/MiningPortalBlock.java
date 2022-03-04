package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.item.IItemBlock;
import com.brewingcoder.brewtools.item.ItemGroups;
import com.brewingcoder.brewtools.tileentity.TileEntityMiningPortal;
import com.brewingcoder.brewtools.world.MiningWorldTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;


@SuppressWarnings("deprecation")
public class MiningPortalBlock extends Block implements ITileEntityProvider, IItemBlock {


    public MiningPortalBlock(Properties properties) {

        super(properties);
    }

    @Override
    public Item toItem() {
        ResourceLocation loc = getRegistryName();
        if (loc != null) return new BlockItem(this, new Item.Properties().tab(ItemGroups.MAIN)).setRegistryName(getRegistryName());
        return null;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (player instanceof ServerPlayerEntity && !player.isCrouching()) {
            teleportPlayer((ServerPlayerEntity)player, pos);
        }
        return ActionResultType.SUCCESS;
    }

    public void teleportPlayer(ServerPlayerEntity player, BlockPos pos){
        if(player.getVehicle() != null || player.isVehicle()){
            return;
        }
        MinecraftServer server = player.getServer();
        if (server == null) return;
        if(player.level.dimension().equals(BrewTools.MINING_WORLD)){
            ServerWorld overworld = server.getLevel(BrewTools.OVERWORLD);
            if (overworld == null) return;
            player.changeDimension(overworld, new MiningWorldTeleporter(pos));
        }else{
            ServerWorld miningWorld = server.getLevel(BrewTools.MINING_WORLD);
            if (miningWorld == null) return;
            player.changeDimension(miningWorld, new MiningWorldTeleporter(pos));
        }
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new TileEntityMiningPortal();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        random.nextFloat();
        TileEntity te = world.getBlockEntity(pos);
        if(te instanceof TileEntityMiningPortal){
            world.addParticle(ParticleTypes.PORTAL, pos.getX() +  (random.nextDouble() - 0.5) * 1.5, pos.getY() + 2, pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
            world.addParticle(ParticleTypes.ENCHANT, pos.getX() + (random.nextDouble() - 0.5) * 1.5, pos.getY() + 2, pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
        }
    }
}
