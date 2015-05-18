package io.github.fergoman123.fergotools.common.itemblock;

import io.github.fergoman123.fergotools.reference.names.BlockNames;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockLeavesFT extends ItemBlock
{
    public ItemBlockLeavesFT(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + BlockNames.leaves[stack.getItemDamage()];
    }
}
