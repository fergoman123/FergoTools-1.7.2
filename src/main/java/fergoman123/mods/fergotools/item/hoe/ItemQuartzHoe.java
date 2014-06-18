package fergoman123.mods.fergotools.item.hoe;

import fergoman123.mods.fergotools.lib.Strings;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergotools.util.tool.HoeGeneric;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemQuartzHoe extends HoeGeneric {

    public static ItemQuartzHoe instance = new ItemQuartzHoe();

    public ItemQuartzHoe() {
        super(UtilToolArmor.quartz);
        this.setUnlocalizedName(Strings.ToolStrings.quartzHoe);
        this.setMaxDamage(UtilToolArmor.quartz.getMaxUses());
    }


    public boolean getIsRepairable(ItemStack itemstack1, ItemStack itemstack2) {
        return itemstack2.isItemEqual(new ItemStack(Items.quartz)) || super.getIsRepairable(itemstack1, itemstack2);
    }
}


