package io.github.fergoman123.fergotools.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockWorkbenchFT extends BlockFT {

    public BlockWorkbenchFT(String name) {
        super(Material.wood, name);
    }
    
    public abstract boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ);

//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
//        if (!worldIn.isRemote && !playerIn.isSneaking()) {
//            switch (this.getType()) {
//                case 0:FTHelper.openGui(playerIn, GuiIds.obsidianWorkbench, worldIn, pos);break;
//                case 1:FTHelper.openGui(playerIn, GuiIds.emeraldWorkbench, worldIn, pos);break;
//                case 2:FTHelper.openGui(playerIn, GuiIds.lapisWorkbench, worldIn, pos);break;
//                case 3:FTHelper.openGui(playerIn, GuiIds.bronzeWorkbench, worldIn, pos);break;
//                case 4:FTHelper.openGui(playerIn, GuiIds.coalWorkbench, worldIn, pos);break;
//                case 5:FTHelper.openGui(playerIn, GuiIds.glowstoneWorkbench, worldIn, pos);break;
//                case 6:FTHelper.openGui(playerIn, GuiIds.adamantiumWorkbench, worldIn, pos);break;
//                case 7:FTHelper.openGui(playerIn, GuiIds.silkWorkbench, worldIn, pos);break;
//                case 8:FTHelper.openGui(playerIn, GuiIds.redstoneWorkbench, worldIn, pos);
//            }
//                return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(this);
    }
}