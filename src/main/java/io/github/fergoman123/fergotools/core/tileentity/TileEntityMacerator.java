package io.github.fergoman123.fergotools.core.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.fergoman123.fergotools.core.block.furnace.BlockMacerator;
import io.github.fergoman123.fergotools.crafting.MaceratorRecipes;
import io.github.fergoman123.fergotools.reference.Ints;
import io.github.fergoman123.fergotools.reference.names.Locale;
import io.github.fergoman123.fergotools.reference.strings.Tile;
import io.github.fergoman123.fergotools.util.base.TileEntityFurnaceFT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Fergoman123.
 */
public final class TileEntityMacerator extends TileEntityFurnaceFT {

    public int power;
    public final int maxPower = 10000;

    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : Locale.containerMacerator;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList list = compound.getTagList(Tile.items, 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound compound1 = list.getCompoundTagAt(i);
            byte b0 = compound1.getByte(Tile.slot);

            if (b0 >= 0 && b0 < this.slots.length)
            {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(compound1);
            }
        }

        this.power = (int)compound.getShort(Tile.power);
        this.cookTime = (int)compound.getShort(Tile.cookTime);

        if (compound.hasKey(Tile.customName, 8))
        {
            this.customName = compound.getString(Tile.customName);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort(Tile.power, (short) this.power);
        compound.setShort(Tile.cookTime, (short)this.cookTime);
        compound.setShort(Tile.maxPower, (short)this.maxPower);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound compound1 = new NBTTagCompound();
                compound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(compound1);
                list.appendTag(compound1);
            }
        }

        compound.setTag("Items", list);

        if (this.hasCustomInventoryName())
        {
            compound.setString("CustomName", this.customName);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int speed)
    {
        return this.cookTime * speed / Ints.Furnace.maceratorSpeed;
    }

    @SideOnly(Side.CLIENT)
    public int getPowerRemainingScaled(int speed)
    {
        return this.power * speed / this.currentItemBurnTime;
    }

    public boolean hasPower(){return this.power > 0;}

    public void updateEntity()
    {
        boolean flag = this.hasPower();
        boolean flag1 = false;

        if (!this.worldObj.isRemote)
        {
            if (this.burnTime == 0 && this.canSmelt())
            {
                if (this.power < (this.maxPower-this.getItemPower(this.slots[1]))) {
                    this.power = getItemPower(this.slots[1]);
                }
//                if (this.burnTime > 0)
//                {
//                    flag1 = true;
//
//                    if (this.slots[1] != null)
//                    {
//                        --this.slots[1].stackSize;
//
//                        if (this.slots[1].stackSize == 0)
//                        {
//                            this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
//                        }
//                    }
//                }
            }

//            if (this.isBurning() && this.canSmelt())
//            {
//                ++this.cookTime;
//
//                if (this.cookTime == Ints.Furnace.maceratorSpeed)
//                {
//                    this.cookTime = 0;
//                    this.smeltItem();
//                    flag1 = true;
//                }
//            }
//            else
//            {
//                this.cookTime = 0;
//            }

//            if (flag != this.burnTime > 0)
//            {
//                flag1 = true;
//                BlockMacerator.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
//            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public boolean isOre(ItemStack stack)
    {
        String[] ores = OreDictionary.getOreNames();
        for (int i = 0; i < ores.length; i++)
        {
            if (ores[i].contains("ore"))
            {
                if (OreDictionary.getOres(ores[i]) != null)
                {
                    for(int j = 0; j < OreDictionary.getOres(ores[i]).size(); j++)
                    {
                        if (OreDictionary.getOres(ores[i]).get(j).getItem() == stack.getItem())
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canSmelt()
    {
        if (this.slots[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack stack = MaceratorRecipes.macerating().getSmeltingResult(this.slots[0]);
            if (stack == null)return false;
            if (!this.isOre(this.slots[0]))return false;
            if (this.slots[2] == null)return true;
            if (!this.slots[2].isItemEqual(stack))return false;
            int result = slots[2].stackSize + stack.stackSize*2;
            return (result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize());
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack stack = MaceratorRecipes.macerating().getSmeltingResult(this.slots[0]);

            if (this.slots[2] == null)
            {
                this.slots[2] = stack.copy();
                this.slots[2].stackSize*=2;
            }
            else if (this.slots[2].getItem() == stack.getItem())
            {
                this.slots[2].stackSize += stack.stackSize*2;
            }

            --this.slots[0].stackSize;

            if (this.slots[0].stackSize <= 0)
            {
                this.slots[0] = null;
            }
        }
    }

    private static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item == Items.redstone)return 150;
            if (item == Item.getItemFromBlock(Blocks.redstone_block))return 1350;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 2 ? false : (slot == 1 ? isItemFuel(stack) : true);
    }
}
