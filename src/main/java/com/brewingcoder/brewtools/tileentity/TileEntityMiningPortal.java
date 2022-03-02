package com.brewingcoder.brewtools.tileentity;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityMiningPortal extends BlockEntity {

    public TileEntityMiningPortal(BlockPos pos, BlockState state){
        super(BrewTools.TE_PORTAL,pos,state);
    }
}
