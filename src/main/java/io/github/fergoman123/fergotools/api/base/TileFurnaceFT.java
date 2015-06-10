package io.github.fergoman123.fergotools.api.base;

import io.github.fergoman123.fergoutil.tileentity.TileFurnaceFergo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class TileFurnaceFT extends TileFurnaceFergo
{

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList list = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        slots = new ItemStack[getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound compound1 = list.getCompoundTagAt(i);
            byte b0 = compound1.getByte("Slot");

            if (b0 >= 0 && b0 < slots.length)
            {
                slots[b0] = ItemStack.loadItemStackFromNBT(compound1);
            }
        }

        burnTime = compound.getShort("BurnTime");
        cookTime = compound.getShort("CookTime");
        totalCookTime = compound.getShort("CookTimeTotal");
        currentItemBurnTime = getItemBurnTime(slots[1]);

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            customName = compound.getString("CustomName");
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static int getMaceratorBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.redstone_block))return 1350;
            if (item == Items.redstone)return 150;
            return GameRegistry.getFuelValue(stack);

        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
}