package com.brewingcoder.brewtools.blockbase;
import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

@SuppressWarnings({"unused", "rawtypes"})
public class StairBlockBase<E extends IVariant> extends StairsBlock implements IBlock<E> {

    public StairBlockBase(BlockState state, Properties properties) {
        super(state,properties);
    }

    @Override
    public E getVariant() {
        return IVariant.getEmpty();
    }


}