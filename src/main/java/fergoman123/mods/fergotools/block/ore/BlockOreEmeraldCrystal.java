package fergoman123.mods.fergotools.block.ore;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.tabs.Tabs;
import fergoman123.mods.fergotools.util.BlockFT;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Fergoman123 on 14/04/2014.
 */
public class BlockOreEmeraldCrystal extends BlockFT{

    public BlockOreEmeraldCrystal()
    {
        super(Material.rock);
        this.setBlockName(Strings.BlockStrings.oreEmeraldCrystal);
        this.setHardness(2.5f);
        this.setResistance(50f);
        this.setCreativeTab(Tabs.tabFergoBlocks);
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return ModItems.emeraldCrystal;
    }

    public int quantityDropped(Random random) {
        return 1;
    }
}
