package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.RotatedPillarBlock;

@SuppressWarnings({"unused", "rawtypes"})
public class PillarBlockBase<E extends IVariant> extends RotatedPillarBlock implements IBlock<E> {

    public PillarBlockBase(Properties properties){
        super(properties);
    }

    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }

}
