package fergoman123.mods.fergotools.item.armor;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.lib.Textures;
import fergoman123.mods.fergotools.util.ItemArmorFT;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergoutil.item.ArmorType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemArmorGlowstone extends ItemArmorFT {

    public static ItemArmorGlowstone instanceHelmet = new ItemArmorGlowstone(ArmorType.HELMET);
    public static ItemArmorGlowstone instanceChest = new ItemArmorGlowstone(ArmorType.CHEST);
    public static ItemArmorGlowstone instanceLegs = new ItemArmorGlowstone(ArmorType.LEGS);
    public static ItemArmorGlowstone instanceBoots = new ItemArmorGlowstone(ArmorType.BOOTS);

    public ItemArmorGlowstone(ArmorType type)
    {
        super("glowstone", UtilToolArmor.glowstoneArmor, type);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(stack.getItem() == ModItems.glowstoneHelmet || stack.getItem() == ModItems.glowstoneChestplate || stack.getItem() == ModItems.glowstoneBoots)
        {
            return Textures.ArmorTextures.glowstoneArmorRender1;
        }

        if(stack.getItem() == ModItems.glowstoneLeggings)
        {
            return Textures.ArmorTextures.glowstoneArmorRender2;
        }
        else
        {
            return null;
        }
    }

}
