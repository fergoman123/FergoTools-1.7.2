package io.github.fergoman123.fergotools.core.item;

import io.github.fergoman123.fergotools.api.content.FTContent;
import io.github.fergoman123.fergotools.reference.names.ItemNames;
import io.github.fergoman123.fergotools.util.item.TM;
import io.github.fergoman123.fergotools.util.tool.ItemShovelFT;

public class ItemObsidianShovel extends ItemShovelFT {

    public ItemObsidianShovel() {
        super(TM.obsidian, FTContent.ingotObsidian);
        this.setUnlocalizedName(ItemNames.obsidianShovel);
    }
}
