 /*
  * Fergoman123's Tools
  * Copyright (c) 2014 fergoman123.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the GNU Lesser Public License v2.1
  * which accompanies this distribution, and is available at
  * http://www.gnu.org/licenses/gpl-3.0.html
  */

package io.github.fergoman123.fergotools.util.tool;

 import io.github.fergoman123.fergotools.config.ConfigHandler;
 import io.github.fergoman123.fergotools.reference.names.Locale;
 import io.github.fergoman123.fergotools.util.item.Materials;
 import io.github.fergoman123.fergoutil.helper.NameHelper;
 import io.github.fergoman123.fergotools.creativetab.Tabs;
 import io.github.fergoman123.fergotools.reference.Reference;
 import net.minecraft.client.renderer.texture.IIconRegister;
 import net.minecraft.entity.player.EntityPlayer;
 import net.minecraft.item.ItemHoe;
 import net.minecraft.item.ItemStack;

 import java.util.List;

 public abstract class ItemHoeFT extends ItemHoe{

     /**
      * this is the constructor for the base
      * hoe for FergoTools
      * @param material the tool material of the hoe
      * @param maxUses the durability of the hoe
      * @param itemName the name of the hoe
      */
    public ItemHoeFT(ToolMaterial material, int maxUses, String itemName) {
        super(material);
        this.setUnlocalizedName(itemName);
        this.setMaxStackSize(1);
        this.setMaxDamage(maxUses);
        this.setCreativeTab(Tabs.tabFergoTools);
        this.setTextureName(String.format("%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

     @Override
     public String getUnlocalizedName() {
         return String.format("item.%s%s", Reference.textureLoc, NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
     }

     public String getUnlocalizedName(ItemStack stack)
     {
         return String.format("item.%s%s", Reference.textureLoc,  NameHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
     }

     public void registerIcons(IIconRegister register)
     {
         itemIcon = register.registerIcon(String.format("%s", NameHelper.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
     }

     @Override
     public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
         list.add(getTranslatedText(Locale.durabilityToolTip) + (stack.getMaxDamage() - stack.getItemDamageForDisplay()) + "/" + stack.getMaxDamage());
     }

     public String getTranslatedText(String translatedText)
     {
         String string = NameHelper.translateToLocal(translatedText);
         return string;
     }

     @Override
     public int getItemEnchantability() {
         return ConfigHandler.enchantability;
     }
 }
