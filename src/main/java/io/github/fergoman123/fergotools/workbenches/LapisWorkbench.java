package io.github.fergoman123.fergotools.workbenches;

import io.github.fergoman123.fergotools.FergoTools;
import io.github.fergoman123.fergotools.init.ModBlocks;
import io.github.fergoman123.fergotools.reference.GuiIds;
import io.github.fergoman123.fergotools.reference.Names;
import io.github.fergoman123.fergotools.reference.Textures;

import io.github.fergoman123.fergotools.util.base.workbench.BlockWorkbenchFT;
import io.github.fergoman123.fergotools.util.base.workbench.ContainerWorkbenchFT;
import io.github.fergoman123.fergotools.util.base.workbench.GuiWorkbenchFT;
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

public class LapisWorkbench
{
    public static final class BlockLapisWorkbench extends BlockWorkbenchFT
    {
        public BlockLapisWorkbench()
        {
            super();
            this.setBlockName(Names.Blocks.lapisWorkbench);
        }

        public IIcon getIcon(int side, int meta)
        {
            return side == 1 ? this.topIcon : (side == 0 ? ModBlocks.plankEmerald.getBlockTextureFromSide(side) : (side != 2 && side != 4 ? this.blockIcon : this.frontIcon));
        }

        public void registerBlockIcons(IIconRegister register)
        {
            this.blockIcon = register.registerIcon(Textures.lapisWorkbenchTextures[0]);
            this.topIcon = register.registerIcon(Textures.lapisWorkbenchTextures[1]);
            this.frontIcon = register.registerIcon(Textures.lapisWorkbenchTextures[2]);
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
            if (world.isRemote)
            {
                return true;
            }
            else if (!player.isSneaking())
            {
                player.openGui(FergoTools.instance, GuiIds.lapisWorkbench.ordinal(), world, x, y, z);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static final class ContainerLapisWorkbench extends ContainerWorkbenchFT
    {
        public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
        public IInventory craftResult = new InventoryCraftResult();
        private World world;
        private int x, y, z;

        public ContainerLapisWorkbench(InventoryPlayer inventory, World world, int x, int y, int z)
        {
            super(inventory, world, x, y, z);
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.addSlotToContainer(new SlotCrafting(inventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));
            int l;
            int i1;

            for (l = 0; l < 3; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
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

        @Override
        public void onCraftMatrixChanged(IInventory inventory) {
            this.craftMatrix.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
        }

        public void onContainerClosed(EntityPlayer player)
        {
            super.onContainerClosed(player);

            if (!this.world.isRemote)
            {
                for (int i = 0; i < 9; i++) {
                    ItemStack stack = this.craftMatrix.getStackInSlotOnClosing(i);
                    if (stack != null)
                    {
                        player.dropPlayerItemWithRandomChoice(stack, false);
                    }
                }
            }
        }

        public boolean canInteractWith(EntityPlayer player)
        {
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

    public static final class GuiLapisWorkbench extends GuiWorkbenchFT
    {
        public GuiLapisWorkbench(InventoryPlayer inventory, World world, int x, int y, int z)
        {
            super(new ContainerLapisWorkbench(inventory, world, x, y, z));
        }

        public void drawGuiContainerForegroundLayer(int par1, int par2)
        {
            this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
            this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
        }

        public void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
        {
            GL11.glColor4f(1f, 1f, 1f, 1f);
            this.mc.getTextureManager().bindTexture(Textures.lapisWorkbenchGuiTexture);
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        }

    }
}