package io.github.fergoman123.fergotools.api.base;

import io.github.fergoman123.fergotools.FergoTools;
import io.github.fergoman123.fergoutil.item.tools.ItemAxeFergo;

public class ItemAxeFT extends ItemAxeFergo
{
    public ItemAxeFT(ToolMaterial material, String name) {
        super(material, 0, FergoTools.tabFergoTools, name);
    }
}