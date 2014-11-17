package io.github.fergoman123.fergotools.core.item.exp;

import io.github.fergoman123.fergotools.reference.names.Locale;
import io.github.fergoman123.fergotools.util.base.ItemFT;
import io.github.fergoman123.fergoutil.helper.NameHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Fergoman123.
 */
public final class ItemExpCollector extends ItemFT
{

    public ItemExpCollector(String itemName) {
        super(itemName);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add(NameHelper.translateToLocal(Locale.expCollectorToolTip));
    }
}