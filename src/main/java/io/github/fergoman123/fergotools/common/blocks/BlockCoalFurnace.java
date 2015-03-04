package io.github.fergoman123.fergotools.common.blocks;

import io.github.fergoman123.fergotools.FergoTools;
import io.github.fergoman123.fergotools.api.BlockFurnaceFT;
import io.github.fergoman123.fergotools.common.tileentity.TileCoalFurnace;
import io.github.fergoman123.fergotools.info.BlockNames;
import io.github.fergoman123.fergotools.info.GuiIds;
import io.github.fergoman123.fergotools.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCoalFurnace extends BlockFurnaceFT
{
    public static final BlockCoalFurnace idle = new BlockCoalFurnace(false, BlockNames.coalFurnaceIdle);
    public static final BlockCoalFurnace active = new BlockCoalFurnace(true, BlockNames.coalFurnaceActive);

    public BlockCoalFurnace(boolean isActive, String name)
    {
        super(isActive, Material.iron, name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.coalFurnaceIdle);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
        {
            return true;
        }
        else if (!playerIn.isSneaking())
        {
            TileCoalFurnace furnace = (TileCoalFurnace)worldIn.getTileEntity(pos);
            if (furnace != null)
            {
                playerIn.openGui(FergoTools.instance, GuiIds.coalFurnace.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
                FergoTools.getLogger().info("Open Coal Furnace");
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void setState(boolean active, World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        TileEntity tile = world.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            world.setBlockState(pos, ModBlocks.coalFurnaceActive.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.coalFurnaceActive.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }
        else
        {
            world.setBlockState(pos, ModBlocks.coalFurnaceIdle.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.coalFurnaceIdle.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tile != null)
        {
            tile.validate();
            world.setTileEntity(pos, tile);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!keepInventory)
        {
            TileCoalFurnace furnace = (TileCoalFurnace)worldIn.getTileEntity(pos);
            if (furnace != null)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, furnace);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileCoalFurnace();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            ((TileCoalFurnace)worldIn.getTileEntity(pos)).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public Item getItem(World world, BlockPos pos) {
        return Item.getItemFromBlock(ModBlocks.coalFurnaceIdle);
    }
}
