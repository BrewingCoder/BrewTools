package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.item.IItemBlock;
import com.brewingcoder.brewtools.item.ItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;


@SuppressWarnings("deprecation")
public class RedstoneClock extends Block implements  IItemBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    boolean changing = false;
    public final static int POWER_TIME = 20;
    public final static int TICK_TIME = 60;

    public RedstoneClock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED,Boolean.FALSE).setValue(LIT,Boolean.FALSE));
    }


    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rnd) {
        if (changing) return;
        int l = (int)(world.getGameTime()  % TICK_TIME);
        changing=true;
        if (world.hasNeighborSignal(pos) && !state.getValue(POWERED)){
             world.setBlockAndUpdate(pos,state.setValue(POWERED, Boolean.FALSE).setValue(LIT,Boolean.FALSE));
            world.getBlockTicks().scheduleTick(pos,this,POWER_TIME -1, TickPriority.HIGH);
        }
        else if (l < POWER_TIME ){
            world.setBlockAndUpdate(pos, state.setValue(POWERED, Boolean.TRUE).setValue(LIT,Boolean.TRUE));
            world.getBlockTicks().scheduleTick(pos,this,POWER_TIME -1, TickPriority.HIGH);
        }else{
            world.setBlockAndUpdate(pos,state.setValue(POWERED, Boolean.FALSE).setValue(LIT,Boolean.FALSE));
            world.getBlockTicks().scheduleTick(pos,this,POWER_TIME -1, TickPriority.HIGH);
        }
        changing=false;
    }

    @Override
    public void neighborChanged(BlockState blockState, World world, BlockPos blockPos, Block block, BlockPos blockPos1, boolean p_220069_6_) {
        world.getBlockTicks().scheduleTick(blockPos,this,-1,TickPriority.HIGH);
    }

    @Override
    public int getSignal(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWERED,LIT);
    }

    @Override
    public Item toItem() {
        return new BlockItem(this, new Item.Properties().tab(ItemGroups.MAIN)).setRegistryName(Objects.requireNonNull(getRegistryName()));
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return false;
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState odState, boolean p_220082_5_) {
        world.getBlockTicks().scheduleTick(pos,this,1,TickPriority.HIGH);
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        if (blockState.getValue(LIT)) spawnParticles(world, blockPos);
    }

    private static void spawnParticles(World world, BlockPos blockPos) {
        Random random = world.random;
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = blockPos.relative(direction);
            if (!world.getBlockState(blockpos).isSolidRender(world, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
                world.addParticle(RedstoneParticleData.REDSTONE, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
