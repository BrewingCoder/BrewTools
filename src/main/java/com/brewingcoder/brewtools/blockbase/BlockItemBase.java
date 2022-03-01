package com.brewingcoder.brewtools.blockbase;

import com.brewingcoder.brewtools.itembase.IItemBase;
import com.brewingcoder.brewtools.registry.IVariant;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;


public class BlockItemBase<E extends IVariant, T extends Block & IBlock<E>> extends BlockItem implements IItemBase {

    private final T block;


    public BlockItemBase(T block, Properties properties) {
        super(block,properties);
          this.block = block;
    }
}
