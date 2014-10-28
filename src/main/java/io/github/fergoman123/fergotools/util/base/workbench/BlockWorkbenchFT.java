package io.github.fergoman123.fergotools.util.base.workbench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.fergoman123.fergoutil.helper.NameHelper;
import io.github.fergoman123.fergotools.creativetab.Tabs;
import io.github.fergoman123.fergotools.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockWorkbenchFT extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon topIcon, frontIcon, bottomIcon;

    public BlockWorkbenchFT()
    {
        super(Material.wood);
        this.setHardness(2.5f);
        this.setCreativeTab(Tabs.tabFergoCT);
    }

    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public abstract IIcon getIcon(int side, int meta);

    public abstract void registerBlockIcons(IIconRegister register);

    public abstract boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ);

    public abstract Item getItemDropped(int metadata, Random rand, int fortune);
}
