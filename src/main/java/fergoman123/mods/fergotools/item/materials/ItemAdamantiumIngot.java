package fergoman123.mods.fergotools.item.materials;

import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.base.ItemFT;

public class ItemAdamantiumIngot extends ItemFT{

    public static ItemAdamantiumIngot instance = new ItemAdamantiumIngot();

    public ItemAdamantiumIngot()
    {
        super();
        this.setUnlocalizedName(Strings.ItemStrings.ingotAdamantium);
    }
}
