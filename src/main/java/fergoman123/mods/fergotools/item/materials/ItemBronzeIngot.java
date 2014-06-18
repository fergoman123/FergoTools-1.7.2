package fergoman123.mods.fergotools.item.materials;

import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.ItemFT;

public class ItemBronzeIngot extends ItemFT {

    public static ItemBronzeIngot instance = new ItemBronzeIngot();

    public ItemBronzeIngot()
    {
        super();
        this.setUnlocalizedName(Strings.ItemStrings.ingotBronze);
    }
}
