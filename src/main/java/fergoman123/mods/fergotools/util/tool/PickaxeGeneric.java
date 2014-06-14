package fergoman123.mods.fergotools.util.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fergoman123.mods.fergotools.lib.Reference;
import fergoman123.mods.fergotools.tabs.Tabs;
import fergoman123.mods.fergoutil.helper.NameHelper;
import fergoman123.mods.fergoutil.item.ITool;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public abstract class PickaxeGeneric extends ItemPickaxe implements ITool
{

    public PickaxeGeneric(ToolMaterial material) {
        super(material);
        this.setMaxStackSize(1);
        this.setCreativeTab(Tabs.tabFergoTools);
        this.setTextureName(String.format("%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return String.format("item.%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName(stack)));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        itemIcon = register.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }
}
