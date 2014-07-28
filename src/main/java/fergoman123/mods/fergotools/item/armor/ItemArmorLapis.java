package fergoman123.mods.fergotools.item.armor;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.reference.Textures;
import fergoman123.mods.fergotools.util.base.ItemArmorFT;
import fergoman123.mods.fergotools.util.item.ToolArmorMaterials;
import fergoman123.mods.fergoutil.item.Armor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemArmorLapis extends ItemArmorFT {

    public ItemArmorLapis(Armor.EnumArmorType type)
    {
        super("lapis", ToolArmorMaterials.lapisArmor, type);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(stack.getItem() == ModItems.lapisHelmet || stack.getItem() == ModItems.lapisChestplate || stack.getItem() == ModItems.lapisBoots)
        {
            return Textures.lapisArmorLayer1;
        }

        if(stack.getItem() == ModItems.lapisLeggings)
        {
            return Textures.lapisArmorLayer2;
        }
        else
        {
            return null;
        }
    }

}