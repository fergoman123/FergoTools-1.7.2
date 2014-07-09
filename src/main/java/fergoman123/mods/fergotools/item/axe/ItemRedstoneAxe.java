package fergoman123.mods.fergotools.item.axe;

import fergoman123.mods.fergotools.item.materials.ItemRedstoneCrystal;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergotools.util.tool.AxeGeneric;
import net.minecraft.item.ItemStack;

public class ItemRedstoneAxe extends AxeGeneric {

    public static ItemRedstoneAxe instance = new ItemRedstoneAxe();
    public ItemRedstoneAxe() {
        super(UtilToolArmor.redstone);
        this.setUnlocalizedName(Names.Items.redstoneAxe);
        this.setMaxDamage(UtilToolArmor.redstone.getMaxUses());
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
    {
        return stack2.isItemEqual(new ItemStack(ItemRedstoneCrystal.instance)) || super.getIsRepairable(stack1, stack2);
    }
}
