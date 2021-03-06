/*
  * Fergoman123's Tools
  * Copyright (c) 2014 fergoman123.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the GNU Lesser Public License v2.1
  * which accompanies this distribution, and is available at
  * http://www.gnu.org/licenses/gpl-3.0.html
  */

package io.github.fergoman123.fergotools.core.itemblock.block;


import io.github.fergoman123.fergotools.api.content.FTContent;
import io.github.fergoman123.fergotools.util.base.ItemBlockFT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public final class ItemBlockExpCollector extends ItemBlockFT
{

    public ItemBlockExpCollector(Block block) {
        super(FTContent.blockExpCollector);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add("Used to Store Experience Collectors");
    }
}
