package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.Block;

@SuppressWarnings({"unused", "rawtypes"})
public class DecoBlockBase<E extends IVariant> extends Block implements IBlock<E> {

    public DecoBlockBase(Properties properties) {
        super(properties);
    }

    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }
}
