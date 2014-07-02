package fergoman123.mods.fergotools.block.ore;

import fergoman123.mods.fergotools.item.materials.ItemRedstoneCrystal;
import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.base.BlockFT;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreRedstoneCrystal extends BlockFT{

    public static BlockOreRedstoneCrystal instance = new BlockOreRedstoneCrystal();


    public BlockOreRedstoneCrystal()
    {
        super();
        this.setBlockName(Strings.BlockStrings.oreRedstoneCrystal);
        this.setHardness(2.5f);
        this.setResistance(50f);
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return ItemRedstoneCrystal.instance;
    }

    public int damageDropped(int dmg)
    {
        return 9;
    }
}