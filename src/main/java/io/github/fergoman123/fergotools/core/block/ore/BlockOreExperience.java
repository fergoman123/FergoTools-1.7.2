package io.github.fergoman123.fergotools.core.block.ore;

import io.github.fergoman123.fergotools.core.FTContent;
import io.github.fergoman123.fergotools.util.base.BlockFT;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Fergoman123.
 */
public class BlockOreExperience extends BlockFT
{

    public BlockOreExperience(Material material, String blockName) {
        super(material, blockName);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return FTContent.shardExp;
    }

    public int quantityDropped(Random rand)
    {
        return 5 + rand.nextInt(3);
    }
}
