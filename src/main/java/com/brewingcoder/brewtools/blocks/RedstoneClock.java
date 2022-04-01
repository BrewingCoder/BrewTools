package com.brewingcoder.brewtools.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings({"deprecation"})
public class RedstoneClock extends Block {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    boolean changing = false;
    public final static int POWER_TIME=10;
    public final static int TICK_TIME=20;

    public RedstoneClock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED,Boolean.FALSE).setValue(LIT,Boolean.FALSE));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if(changing) return;
        int l = (int)(level.getGameTime() % TICK_TIME);
        changing = true;
        //if we have a redstone signal and are powered shut it off
        if (level.hasNeighborSignal(pos) && state.getValue(POWERED)){
            level.setBlockAndUpdate(pos,state.setValue(POWERED,Boolean.FALSE).setValue(LIT,Boolean.FALSE));
            level.scheduleTick(pos,this,TICK_TIME, TickPriority.HIGH);
        //if we dont have a redstone signal and time has passed fire pulse
        }else if(!level.hasNeighborSignal(pos) && l < POWER_TIME && !state.getValue(POWERED)){
            level.setBlockAndUpdate(pos,state.setValue(POWERED,Boolean.TRUE).setValue(LIT,Boolean.TRUE));
            level.scheduleTick(pos,this,POWER_TIME, TickPriority.HIGH);
        //if we are powered and time
        }else{
            level.setBlockAndUpdate(pos,state.setValue(POWERED,Boolean.FALSE).setValue(LIT,Boolean.FALSE));
            level.scheduleTick(pos,this,6, TickPriority.HIGH);
        }
        changing=false;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean p_60514_) {
        ScheduledTick<Block> st = new ScheduledTick<>(this,pos,-1,TickPriority.HIGH,1);
        level.getBlockTicks().schedule(st);
    }

    @Override
    public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED,LIT);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public boolean isSignalSource(BlockState p_60571_) {
        return true;
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, LevelReader level, BlockPos pos, Direction side) {
        return false;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean p_60570_) {
      //level.scheduleTick(pos,this,POWER_TIME,TickPriority.HIGH);
      level.scheduleTick(pos,this,TICK_TIME);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos blockPos, Random random) {
        if(state.getValue(LIT)) spawnParticles(level,blockPos);
    }

    private static void spawnParticles(Level world, BlockPos blockPos) {
        Random random = world.random;
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = blockPos.relative(direction);
            if (!world.getBlockState(blockpos).isSolidRender(world, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
                world.addParticle(DustParticleOptions.REDSTONE, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
