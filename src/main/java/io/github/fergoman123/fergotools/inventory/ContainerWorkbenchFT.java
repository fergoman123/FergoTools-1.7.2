package io.github.fergoman123.fergotools.inventory;

import io.github.fergoman123.fergoutil.gui.IContainerFergo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbenchFT extends Container implements IContainerFergo {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftResult = new InventoryCraftResult();
	public World world;
	public BlockPos pos;
	public InventoryPlayer invPlayer;
	private boolean isBlank;

	public ContainerWorkbenchFT(InventoryPlayer invPlayer, World world, BlockPos origin) {
		this.invPlayer = invPlayer;
		this.world = world;
		this.pos = origin;
		this.addInventorySlots();
		this.addPlayerInventory();
		this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	public ContainerWorkbenchFT(InventoryPlayer invPlayer, World world){
		this(invPlayer, world, BlockPos.ORIGIN);
	}
	
	public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		if (!this.isBlank) {
			ItemStack itemstack = null;
			Slot slot = (Slot) this.inventorySlots.get(index);

			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();

				if (index == 0) {
					if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
						return null;
					}

					slot.onSlotChange(itemstack1, itemstack);
				} else if (index >= 10 && index < 37) {
					if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
						return null;
					}
				} else if (index >= 37 && index < 46) {
					if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
						return null;
					}
				} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
					return null;
				}

				if (itemstack1.stackSize == 0) {
					slot.putStack((ItemStack) null);
				} else {
					slot.onSlotChanged();
				}

				if (itemstack1.stackSize == itemstack.stackSize) {
					return null;
				}

				slot.onPickupFromSlot(playerIn, itemstack1);
			}

			return itemstack;
		} else {
			return null;
		}
	}

	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
	}

	@Override
	public void addInventorySlots() {
		if (!this.isBlank) {
			this.addSlotToContainer(new SlotCrafting(invPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));

			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
				}
			}
		} else {
			return;
		}

	}

	@Override
	public void addPlayerInventory() {
		if (!this.isBlank) {
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 9; ++j) {
					this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
				}
			}

			for (int i = 0; i < 9; ++i) {
				this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
			}
		} else {
			return;
		}
	}
	
	public Container setIsBlank(boolean isBlank){
		this.isBlank = isBlank;
		return this;
	}
	
	public boolean getIsBlank(){
		return this.isBlank;
	}
}