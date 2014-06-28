package fergoman123.mods.fergotools.item.materials;

import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.base.ItemFT;

public class ItemEmeraldCrystal extends ItemFT{

    public static ItemEmeraldCrystal instance = new ItemEmeraldCrystal();
    public ItemEmeraldCrystal()
    {
        super();
        this.setUnlocalizedName(Strings.ItemStrings.emeraldCrystal);
    }
}
