package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.OreBlock;

public class OreBlockBase<E extends IVariant> extends OreBlock implements IBlock<E> {
    private int minXpDrop;
    private int maxXpDrop;

    public OreBlockBase(Properties properties) {
        super(properties);
    }

    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }

    public OreBlockBase setExperience(int minXpDrop, int maxXpDrop) {
        this.minXpDrop = minXpDrop;
        this.maxXpDrop = maxXpDrop;
        return this;
    }
}
