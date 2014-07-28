package fergoman123.mods.fergotools.block.furnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fergoman123.mods.fergotools.init.ModBlocks;
import fergoman123.mods.fergotools.reference.*;
import fergoman123.mods.fergotools.creativetab.CreativeTabsFergoTools;
import fergoman123.mods.fergotools.tileentity.TileEntityRedstoneFurnace;
import fergoman123.mods.fergotools.util.base.BlockFurnaceFT;
import fergoman123.mods.fergoutil.helper.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRedstoneFurnace extends BlockFurnaceFT
{

    private static boolean keepInventory;

    public BlockRedstoneFurnace(boolean isActive)
    {
        super(Material.rock, Strings.FurnaceType.redstoneFurnace);
        this.isActive = isActive;
    }

    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return BlockHelper.getItemFromBlock(ModBlocks.redstoneFurnaceIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    public void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote) {
            Block a = world.getBlock(x, y, z - 1);
            Block b = world.getBlock(x, y, z + 1);
            Block c = world.getBlock(x - 1, y, z);
            Block d = world.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (a.func_149730_j() && !b.func_149730_j()) {
                b0 = 3;
            }

            if (b.func_149730_j() && !a.func_149730_j()) {
                b0 = 2;
            }

            if (c.func_149730_j() && !d.func_149730_j()) {
                b0 = 5;
            }

            if (d.func_149730_j() && !c.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 == 0)
            par2 = 3;
        return par1 == 1 ? this.icons[1] : (par1 == 0 ? this.icons[1] : (par1 != par2 ? this.blockIcon : this.icons[0]));
    }

    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(Textures.resourcePrefix + Names.Blocks.blockRedstoneCrystal);
        this.icons[0] = register.registerIcon(Reference.textureLoc + (this.isActive ? Names.Blocks.redstoneFurnaceActive : Names.Blocks.redstoneFurnaceIdle));
        this.icons[1] = register.registerIcon(Textures.resourcePrefix + Names.Blocks.blockRedstoneCrystal);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            TileEntityRedstoneFurnace furnace = (TileEntityRedstoneFurnace)world.getTileEntity(x, y, z);
            if (furnace != null)
            {
                player.openGui(getInstance(), getGuiID(), world, x, y, z);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void updateRedstoneFurnaceBlockState(boolean active, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tile = world.getTileEntity(x, y, z);
        keepInventory = true;

        if (active)
        {
            world.setBlock(x, y, z, ModBlocks.redstoneFurnaceActive);
        }
        else
        {
            world.setBlock(x, y, z, ModBlocks.redstoneFurnaceIdle);
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tile != null)
        {
            tile.validate();
            world.setTileEntity(x, y, z, tile);
        }
    }

    public TileEntity createNewTileEntity(World world, int var)
    {
        return new TileEntityRedstoneFurnace();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase elb, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(elb.rotationYaw * 4.0f / 360.0) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (stack.hasDisplayName())
        {
            ((TileEntityRedstoneFurnace)world.getTileEntity(x, y, z)).setGuiDisplayName(stack.getDisplayName());
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int oldBlock)
    {
        if (!keepInventory)
        {
            TileEntityRedstoneFurnace furnace = (TileEntityRedstoneFurnace)world.getTileEntity(x, y, z);

            if (furnace != null)
            {
                for (int i = 0; i < furnace.getSizeInventory(); ++i)
                {
                    ItemStack stack = furnace.getStackInSlot(i);

                    if (stack != null)
                    {
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while (stack.stackSize > 0)
                        {
                            int j = this.rand.nextInt(21) + 10;

                            if (j > stack.stackSize)
                            {
                                j = stack.stackSize;
                            }

                            stack.stackSize -= j;
                            EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.getItem(), j, stack.getItemDamage()));

                            if (stack.hasTagCompound())
                            {
                                item.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            item.motionX = (double)((float)this.rand.nextGaussian() * f3);
                            item.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                            item.motionZ = (double)((float)this.rand.nextGaussian() * f3);
                            world.spawnEntityInWorld(item);
                        }
                    }
                }

                world.func_147453_f(x, y, z, block);
            }
        }
        super.breakBlock(world, x, y, z, block, oldBlock);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (this.isActive)
        {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                world.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                world.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public boolean hasComparatorInputOverride(){return true;}

    public int getComparatorInputOverride(World world, int x, int y, int z, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
    }

    public Item getItem(World world, int x, int y, int z)
    {
        return BlockHelper.getItemFromBlock(ModBlocks.redstoneFurnaceIdle);
    }

    @Override
    public int getGuiID() {
        return GuiIds.redstoneFurnace.ordinal();
    }
}
