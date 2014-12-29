package io.github.fergoman123.fergotools.core.item;

import io.github.fergoman123.fergotools.api.content.FTContent;
import io.github.fergoman123.fergotools.reference.names.ItemNames;
import io.github.fergoman123.fergotools.util.item.TM;
import io.github.fergoman123.fergotools.util.tool.ItemPickaxeFT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGlowstonePickaxe extends ItemPickaxeFT {
    public ItemGlowstonePickaxe() {
        super(TM.glowstone, FTContent.ingotGlowstone);
        this.setUnlocalizedName(ItemNames.glowstonePickaxe);
    }
}
