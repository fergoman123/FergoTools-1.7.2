package fergoman123.mods.fergotools.block.ore;

import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.UtilBlockItem;
import fergoman123.mods.fergotools.util.base.BlockFT;
import fergoman123.mods.fergoutil.helper.BlockHelper;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreAdamantium extends BlockFT{


    public BlockOreAdamantium() {
        super(Material.rock);
        this.setBlockName(Names.Blocks.oreAdamantium);
        this.setHardness(2.5f);
        this.setResistance(50f);
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return BlockHelper.getItemFromBlock(this);
    }
}
