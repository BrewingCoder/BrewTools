package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.SlabBlock;

@SuppressWarnings({"unused", "rawtypes"})
public class SlabBlockBase<E extends IVariant> extends SlabBlock implements IBlock<E> {

    public SlabBlockBase(Properties properties){
        super(properties);
    }

    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }
}
