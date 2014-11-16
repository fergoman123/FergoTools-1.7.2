package io.github.fergoman123.fergotools.core.gui.furnace.container;

import io.github.fergoman123.fergotools.core.tileentity.TileEntityLapisFurnace;
import io.github.fergoman123.fergotools.util.base.ContainerFT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Created by Fergoman123.
 */
public class ContainerLapisFurnace extends ContainerFT
{
    private TileEntityLapisFurnace furnace;

    public ContainerLapisFurnace(InventoryPlayer invPlayer, TileEntityLapisFurnace furnace)
    {
        this.furnace = furnace;
        this.invPlayer = invPlayer;
        this.addInventorySlots();
        this.addPlayerInventory();
    }

    @Override
    public void addInventorySlots() {
        this.addSlotToContainer(new Slot(this.furnace, 0, 62, 22));
        this.addSlotToContainer(new Slot(this.furnace, 1, 42, 22));
        this.addSlotToContainer(new SlotFurnace(this.invPlayer.player, furnace, 2, 110, 22));
    }

    @Override
    public void addPlayerInventory() {
        for (int invRowIndex = 0; invRowIndex < playerInvRows; invRowIndex++) {
            for (int invColIndex = 0; invColIndex < playerInvCols; ++invColIndex)
            {
                this.addSlotToContainer(new Slot(this.invPlayer, invColIndex + invRowIndex * 9 + 9, 8 + invColIndex * 18, 53 + invRowIndex * 18));
            }
        }

        for (int actionBarSlotIndex = 0; actionBarSlotIndex < playerInvCols; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(this.invPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 111));
        }
    }

    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
        crafting.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
        crafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting crafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.furnace.cookTime)
            {
                crafting.sendProgressBarUpdate(this, 0, this.furnace.cookTime);
            }

            if (this.lastBurnTime != this.furnace.burnTime)
            {
                crafting.sendProgressBarUpdate(this, 1, this.furnace.burnTime);
            }

            if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
            {
                crafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.furnace.cookTime;
        this.lastBurnTime = this.furnace.burnTime;
        this.lastItemBurnTime = this.furnace.currentItemBurnTime;
    }

    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.furnace.cookTime = par2;
        }

        if (par1 == 1)
        {
            this.furnace.burnTime = par2;
        }

        if (par1 == 2)
        {
            this.furnace.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.furnace.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityLapisFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
