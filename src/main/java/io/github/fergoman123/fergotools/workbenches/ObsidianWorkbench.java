package io.github.fergoman123.fergotools.workbenches;

import io.github.fergoman123.fergotools.FergoTools;
import io.github.fergoman123.fergotools.init.ModBlocks;
import io.github.fergoman123.fergotools.reference.GuiIds;
import io.github.fergoman123.fergotools.reference.Names;
import io.github.fergoman123.fergotools.reference.Textures;

import io.github.fergoman123.fergotools.util.base.workbench.BlockWorkbenchFT;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ObsidianWorkbench {
    public static final class BlockObsidianWorkbench extends BlockWorkbenchFT {
        public BlockObsidianWorkbench()
        {
            super();
            this.setBlockName(Names.Blocks.obsidianWorkbench);
        }

        @Override
        public IIcon getIcon(int side, int meta) {
            return side == 1 ? this.topIcon : (side == 0 ? ModBlocks.plankObsidian.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? this.blockIcon : this.frontIcon));
        }

        @Override
        public void registerBlockIcons(IIconRegister register) {
            this.blockIcon = register.registerIcon(Textures.obsidianWorkbenchTextures[0]);
            this.topIcon = register.registerIcon(Textures.obsidianWorkbenchTextures[1]);
            this.frontIcon = register.registerIcon(Textures.obsidianWorkbenchTextures[2]);
        }

        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
        {
            if (world.isRemote)
            {
                return true;
            }
            else if (!player.isSneaking())
            {
                player.openGui(FergoTools.instance, GuiIds.obsidianWorkbench.ordinal(), world, x, y, z);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static final class ContainerObsidianWorkbench extends Container {
        public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
        public IInventory craftResult = new InventoryCraftResult();
        private World worldObj;
        private int posX;
        private int posY;
        private int posZ;

        public ContainerObsidianWorkbench(InventoryPlayer inventory, World world, int x, int y, int z) {
            this.worldObj = world;
            this.posX = x;
            this.posY = y;
            this.posZ = z;
            this.addSlotToContainer(new SlotCrafting(inventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));
            int l;
            int i1;

            for (l = 0; l < 3; ++l) {
                for (i1 = 0; i1 < 3; ++i1) {
                    this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
                }
            }

            for (l = 0; l < 3; ++l) {
                for (i1 = 0; i1 < 9; ++i1) {
                    this.addSlotToContainer(new Slot(inventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
                }
            }

            for (l = 0; l < 9; ++l) {
                this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
            }

            this.onCraftMatrixChanged(this.craftMatrix);
        }

        public void onCraftMatrixChanged(IInventory inventory) {
            this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
        }

        @Override
        public void onContainerClosed(EntityPlayer player) {
            super.onContainerClosed(player);

            if (!this.worldObj.isRemote) {
                for (int i = 0; i < 9; ++i) {
                    ItemStack stack = this.craftMatrix.getStackInSlotOnClosing(i);

                    if (stack != null) {
                        player.dropPlayerItemWithRandomChoice(stack, false);
                    }
                }
            }
        }

        public boolean canInteractWith(EntityPlayer player) {
            return true;
        }

        public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
            ItemStack itemstack = null;
            Slot slot = (Slot) this.inventorySlots.get(slotIndex);

            if (slot != null && slot.getHasStack()) {
                ItemStack itemstack1 = slot.getStack();
                itemstack = itemstack1.copy();

                if (slotIndex == 0) {
                    if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                        return null;
                    }

                    slot.onSlotChange(itemstack1, itemstack);
                } else if (slotIndex >= 10 && slotIndex < 37) {
                    if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                        return null;
                    }
                } else if (slotIndex >= 37 && slotIndex < 46) {
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

                slot.onPickupFromSlot(player, itemstack1);
            }

            return itemstack;
        }
    }

    public static final class GuiObsidianWorkbench extends GuiContainer {
        public GuiObsidianWorkbench(InventoryPlayer inventory, World world, int x, int y, int z) {
            super(new ContainerObsidianWorkbench(inventory, world, x, y, z));
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
            this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
            this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
            GL11.glColor4f(1f, 1f, 1f, 1f);
            this.mc.getTextureManager().bindTexture(Textures.obsidianWorkbenchGuiTexture);
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        }
    }
}
