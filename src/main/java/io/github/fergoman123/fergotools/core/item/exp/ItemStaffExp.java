package io.github.fergoman123.fergotools.core.item.exp;

import io.github.fergoman123.fergotools.core.FTContent;
import io.github.fergoman123.fergotools.creativetab.Tabs;
import io.github.fergoman123.fergotools.reference.Reference;
import io.github.fergoman123.fergotools.reference.names.ItemNames;
import io.github.fergoman123.fergotools.reference.names.Locale;
import io.github.fergoman123.fergoutil.helper.NameHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Fergoman123.
 */
public final class ItemStaffExp extends Item
{
    private final String[] subTypes = new String[]{"staffExp", "staffExp2", "staffExp3"};

    public ItemStaffExp(String itemName) {
        super();
        this.setUnlocalizedName(itemName);
        this.setMaxStackSize(1);
        this.setCreativeTab(Tabs.tabFergoItems);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return String.format("item.%s%s.%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName(stack)), subTypes[MathHelper.clamp_int(stack.getItemDamage(), 0, subTypes.length - 1)]);
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(Reference.textureLoc + ItemNames.staffExp);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (stack.getItemDamage() == 0)
        {
            if (player.experienceLevel > 4 && !player.isSneaking())
            {
                player.addExperienceLevel(-5);
                player.inventory.addItemStackToInventory(new ItemStack(FTContent.gemExp));
            }
            else if (player.isSneaking() && player.inventory.hasItemStack(new ItemStack(FTContent.gemExp)))
            {
                player.addExperienceLevel(5);
                player.inventory.consumeInventoryItem(FTContent.gemExp);
            }
        }

        if (stack.getItemDamage() == 1)
        {
            if (player.experienceLevel > 9 && !player.isSneaking())
            {
                player.addExperienceLevel(-10);
                player.inventory.addItemStackToInventory(new ItemStack(FTContent.gemExp2));
            }
            else if (player.isSneaking() && player.inventory.hasItemStack(new ItemStack(FTContent.gemExp2)))
            {
                player.addExperienceLevel(10);
                player.inventory.consumeInventoryItem(FTContent.gemExp2);
            }
        }

        if (stack.getItemDamage() == 2)
        {
            if (player.experienceLevel > 29 && !player.isSneaking())
            {
                player.addExperienceLevel(-30);
                player.inventory.addItemStackToInventory(new ItemStack(FTContent.gemExp3));
            }
            else if (player.isSneaking() && player.inventory.hasItemStack(new ItemStack(FTContent.gemExp3)))
            {
                player.addExperienceLevel(30);
                player.inventory.consumeInventoryItem(FTContent.gemExp3);
            }
        }
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        list.add(NameHelper.translateToLocal(Locale.staffExpToolTip));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < subTypes.length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
