package com.brewingcoder.brewtools.blocks;

import com.brewingcoder.brewtools.BrewTools;
import com.brewingcoder.brewtools.item.IItemBlock;
import com.brewingcoder.brewtools.item.ItemGroups;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.Random;

public class RedstoneClock extends HorizontalBlock implements  IItemBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    //public static final BooleanProperty LIT = BlockStateProperties.LIT;

    boolean changing = false;
    boolean canProvidePower = true;
    public final static int POWER_TIME = 2;
    public final static int TICK_TIME = 20;

    public long LastActivation = 0;

    enum PowerState {
        ENABLED_NOT_POWERED,
        ENABLED_POWERED,
        DISABLED;
        public BlockState state;
    }


    public RedstoneClock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED,false));
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rnd) {
        if (changing) return;

        boolean flag = state.getValue(POWERED);
        int l = (int)(world.getGameTime() % TICK_TIME);
        changing=true;
        if (l < POWER_TIME && shouldCycle(world,pos,state)){
            world.setBlock(pos,state.setValue(POWERED,Boolean.valueOf(true)),2);
            world.getBlockTicks().scheduleTick(pos,this,TICK_TIME -1, TickPriority.HIGH);
        }else{
            world.setBlock(pos,state.setValue(POWERED,Boolean.valueOf(false)),2);
            world.getBlockTicks().scheduleTick(pos,this,TICK_TIME -1, TickPriority.HIGH);
        }
        changing=false;
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        if (world.isClientSide() || changing) return;

        changing = true;
        ServerWorld serverWorld = (ServerWorld) world;
        if (serverWorld.hasNeighborSignal(pos) && canProvidePower){
            canProvidePower=false;
        }else if (!serverWorld.hasNeighborSignal(pos) && !canProvidePower){
            canProvidePower = true;
            serverWorld.getBlockTicks().scheduleTick(pos,this,TICK_TIME -1, TickPriority.HIGH);
        }
        changing = false;
    }

    @Override
    public int getSignal(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    public Item toItem() {
        return new BlockItem(this, new Item.Properties().tab(ItemGroups.MAIN)).setRegistryName(getRegistryName());
    }

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
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

    protected boolean shouldCycle(World world, BlockPos blockPos, BlockState blockState){
        return getInputSignal(world,blockPos,blockState) == 0;
    }

    protected int getInputSignal(World world, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        BlockPos blockpos = blockPos.relative(direction);
        int i = world.getSignal(blockpos, direction);
        if (i >= 15) {
            return i;
        } else {
            BlockState blockstate = world.getBlockState(blockpos);
            return Math.max(i, blockstate.is(Blocks.REDSTONE_WIRE) ? blockstate.getValue(RedstoneWireBlock.POWER) : 0);
        }
    }
}
