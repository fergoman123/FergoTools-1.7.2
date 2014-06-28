package fergoman123.mods.fergotools.item.shovel;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergotools.util.tool.ShovelGeneric;
import net.minecraft.item.ItemStack;

public class ItemEmeraldShovel extends ShovelGeneric {

    public static ItemEmeraldShovel instance = new ItemEmeraldShovel();

    public ItemEmeraldShovel() {
        super(UtilToolArmor.emerald);
        this.setUnlocalizedName(Strings.ToolStrings.emeraldShovel);
        this.setMaxDamage(UtilToolArmor.getEmeraldMaxUses());
    }

    public boolean getIsRepairable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return itemstack2.isItemEqual(new ItemStack(ModItems.emeraldCrystal)) || super.getIsRepairable(itemstack1, itemstack2);
    }

}
