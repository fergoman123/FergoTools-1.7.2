package fergoman123.mods.fergotools.item.pickaxe;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.item.materials.ItemGlowstoneIngot;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.util.item.ToolArmorMaterials;
import fergoman123.mods.fergotools.util.tool.ItemPickaxeFT;
import net.minecraft.item.ItemStack;

public class ItemGlowstonePickaxe extends ItemPickaxeFT
{

    public ItemGlowstonePickaxe()
    {
        super(ToolArmorMaterials.glowstone, ToolArmorMaterials.glowstone.getMaxUses());
        this.setUnlocalizedName(Names.Items.glowstonePickaxe);
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2)
    {
        return stack2.isItemEqual(new ItemStack(ModItems.ingotGlowstone)) || super.getIsRepairable(stack1, stack2);
    }
}
