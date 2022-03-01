package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.FenceGateBlock;

@SuppressWarnings({"unused", "rawtypes"})
public class FenceGateBlockBase<E extends IVariant> extends FenceGateBlock implements IBlock<E> {

    public FenceGateBlockBase(Properties properties){
        super(properties);
    }
    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }
}
