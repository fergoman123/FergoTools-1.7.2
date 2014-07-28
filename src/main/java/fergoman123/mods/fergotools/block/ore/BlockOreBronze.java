package fergoman123.mods.fergotools.block.ore;

import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.base.BlockFT;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreBronze extends BlockFT{


    public BlockOreBronze()
    {
        super();
        this.setBlockName(Names.Blocks.oreBronze);
        this.setHardness(2.5f);
        this.setResistance(50f);
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return Item.getItemFromBlock(this);
    }
}
