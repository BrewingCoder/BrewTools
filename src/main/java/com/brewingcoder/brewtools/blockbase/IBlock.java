package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;

public interface IBlock<E extends IVariant> extends IForgeBlock {

    E getVariant();

    @Override
    default boolean hasTileEntity(BlockState state) {
        return createTileEntity(state,null) != null;
    }
}
