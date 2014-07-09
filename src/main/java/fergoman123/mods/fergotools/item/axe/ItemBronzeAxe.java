package fergoman123.mods.fergotools.item.axe;

import fergoman123.mods.fergotools.item.materials.ItemBronzeIngot;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergotools.util.tool.AxeGeneric;
import net.minecraft.item.ItemStack;

public class ItemBronzeAxe extends AxeGeneric {

    public static ItemBronzeAxe instance = new ItemBronzeAxe();

    public ItemBronzeAxe() {
        super(UtilToolArmor.bronze);
        this.setUnlocalizedName(Names.Items.bronzeAxe);
        this.setMaxDamage(UtilToolArmor.bronze.getMaxUses());
    }

    public boolean getIsRepairable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return itemstack2.isItemEqual(new ItemStack(ItemBronzeIngot.instance)) || super.getIsRepairable(itemstack1, itemstack2);
    }

}
