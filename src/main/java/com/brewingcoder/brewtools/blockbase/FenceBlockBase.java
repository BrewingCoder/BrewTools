package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.FenceBlock;

@SuppressWarnings({"unused", "rawtypes"})
public class FenceBlockBase<E extends IVariant> extends FenceBlock implements IBlock<E> {

    public FenceBlockBase(Properties properties) {
        super(properties);
    }
    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }
}