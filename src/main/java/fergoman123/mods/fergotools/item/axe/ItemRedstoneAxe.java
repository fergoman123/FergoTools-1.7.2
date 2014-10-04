package fergoman123.mods.fergotools.item.axe;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.item.Materials;
import fergoman123.mods.fergotools.util.tool.ItemAxeFT;
import net.minecraft.item.ItemStack;

public class ItemRedstoneAxe extends ItemAxeFT {

    public ItemRedstoneAxe() {
        super(Materials.Tools.redstone, Materials.Tools.redstone.getMaxUses());
        this.setUnlocalizedName(Names.Items.redstoneAxe);
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
    {
        return stack2.isItemEqual(new ItemStack(ModItems.gemRedstone)) || super.getIsRepairable(stack1, stack2);
    }
}
