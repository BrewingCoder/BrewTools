package com.brewingcoder.brewtools.blockentities;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MiningPortalEntity extends BlockEntity {

    public MiningPortalEntity( BlockPos blockPos, BlockState state) {
        super(BrewTools.TE_PORTAL, blockPos, state);
    }

}
