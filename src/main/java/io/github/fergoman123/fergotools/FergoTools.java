/*
 * Fergoman123's Tools
 * Copyright (c) 2014 fergoman123.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl-3.0.html
 */

package io.github.fergoman123.fergotools;

import io.github.fergoman123.fergotools.config.ConfigHandler;
import io.github.fergoman123.fergotools.init.*;
import io.github.fergoman123.fergotools.log.LoggerFT;
import io.github.fergoman123.fergotools.reference.gui.MetadataFT;
import io.github.fergoman123.fergotools.reference.gui.ModInfo;
import io.github.fergoman123.fergotools.reference.gui.Reference;
import io.github.fergoman123.fergotools.reference.gui.strings.Messages;
import io.github.fergoman123.fergoutil.proxy.IProxy;
import io.github.fergoman123.fergoutil.reference.ModConstants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.versionMain, dependencies = ModConstants.dep, guiFactory = Reference.guiFactoryClass)
public class FergoTools {
    public static final CreativeTabs tabFergoTools = new CreativeTabs("fergotools.tabFergoTools") {
        @Override
        public Item getTabIconItem() {
            return ModItems.shardExp;
        }

        @Override
        public String getTranslatedTabLabel() {
            return StatCollector.translateToLocal("fergotools.tabfergotools");
        }
    };

    @Mod.Instance(ModInfo.modid)
    private static FergoTools instance;

    private static final LoggerFT logger = new LoggerFT();

    public static FergoTools getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return logger.getLogger();
    }

    public static IProxy getProxy()
    {
        return proxy;
    }

    @SidedProxy(clientSide = Reference.clientProxyClass, serverSide = Reference.serverProxyClass)
    private static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        getLogger().info(Messages.preInitMessage);
        MetadataFT.writeMetadata(evt.getModMetadata());
        ConfigHandler.init(evt.getSuggestedConfigurationFile());
        getProxy().registerEventHandlers();
        ModItems.init();
        ModBlocks.init();
        ModAchievements.init();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent evt) {
        getLogger().info(Messages.initMessage);
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        getProxy().registerTileEntities();
        getProxy().registerModels();
        Recipes.init();
    }

    @Mod.EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt) {
        getLogger().info(Messages.postInitMessage);
    }
}
