package io.github.fergoman123.fergotools.core.block.ore;

import io.github.fergoman123.fergotools.util.base.BlockFT;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Fergoman123.
 */
public class BlockOreAdamantium extends BlockFT
{

    public BlockOreAdamantium(Material material, String blockName) {
        super(material, blockName);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(this);
    }
}
