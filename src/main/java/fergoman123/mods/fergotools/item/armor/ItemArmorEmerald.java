package fergoman123.mods.fergotools.item.armor;

import fergoman123.mods.fergotools.init.ModItems;
import fergoman123.mods.fergotools.lib.Textures;
import fergoman123.mods.fergotools.util.ItemArmorFT;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergoutil.item.Armor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * Created by Fergoman123 on 13/04/2014.
 */
public class ItemArmorEmerald extends ItemArmorFT {

    public static ItemArmorEmerald instanceHelmet = new ItemArmorEmerald(Armor.EnumArmorType.HELMET);
    public static ItemArmorEmerald instanceChest = new ItemArmorEmerald(Armor.EnumArmorType.CHEST);
    public static ItemArmorEmerald instanceLegs = new ItemArmorEmerald(Armor.EnumArmorType.LEGS);
    public static ItemArmorEmerald instanceBoots = new ItemArmorEmerald(Armor.EnumArmorType.BOOTS);

    public ItemArmorEmerald(Armor.EnumArmorType type)
    {
        super("emerald",  UtilToolArmor.emeraldArmor, type);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(stack.getItem() == ModItems.emeraldHelmet || stack.getItem() == ModItems.emeraldChestplate || stack.getItem() == ModItems.emeraldBoots)
        {
            return Textures.ArmorTextures.emeraldArmorRender1;
        }

        if(stack.getItem() == ModItems.emeraldLeggings)
        {
            return Textures.ArmorTextures.emeraldArmorRender2;
        }
        else
        {
            return null;
        }
    }

}
