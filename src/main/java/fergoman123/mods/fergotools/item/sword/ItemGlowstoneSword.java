package fergoman123.mods.fergotools.item.sword;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.item.materials.ItemGlowstoneIngot;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.item.ToolArmorMaterials;
import fergoman123.mods.fergotools.util.tool.ItemSwordFT;
import net.minecraft.item.ItemStack;

import javax.tools.Tool;

public class ItemGlowstoneSword extends ItemSwordFT {

    public ItemGlowstoneSword() {
        super(ToolArmorMaterials.glowstone, ToolArmorMaterials.glowstone.getMaxUses());
        this.setUnlocalizedName(Names.Items.glowstoneSword);
    }

    public boolean getIsRepairable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return itemstack2.isItemEqual(new ItemStack(ModItems.ingotGlowstone)) || super.getIsRepairable(itemstack1, itemstack2);
    }

}
