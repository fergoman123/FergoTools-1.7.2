package fergoman123.mods.fergotools.item.armor;

import fergoman123.mods.fergotools.reference.Textures;
import fergoman123.mods.fergotools.util.base.ItemArmorFT;
import fergoman123.mods.fergotools.util.item.UtilToolArmor;
import fergoman123.mods.fergoutil.item.Armor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ItemArmorObsidian extends ItemArmorFT {

    public static ItemArmorObsidian instanceHelmet = new ItemArmorObsidian(Armor.EnumArmorType.HELMET);
    public static ItemArmorObsidian instanceChest = new ItemArmorObsidian(Armor.EnumArmorType.CHEST);
    public static ItemArmorObsidian instanceLegs = new ItemArmorObsidian(Armor.EnumArmorType.LEGS);
    public static ItemArmorObsidian instanceBoots = new ItemArmorObsidian(Armor.EnumArmorType.BOOTS);

    public ItemArmorObsidian(Armor.EnumArmorType type) {
        super("obsidian", UtilToolArmor.obsidianArmor, type);
    }

    public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
    {
        if(is.getItem() == instanceHelmet || is.getItem() == instanceChest || is.getItem() == instanceBoots)
        {return Textures.obsidianArmorLayer1;}

        if(is.getItem() == instanceLegs)
        {return Textures.obsidianArmorLayer2;}
        else{return null;}
    }
}
