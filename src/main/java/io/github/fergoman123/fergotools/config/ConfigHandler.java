 /*
  * Fergoman123's Tools
  * Copyright (c) 2014 fergoman123.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the GNU Lesser Public License v2.1
  * which accompanies this distribution, and is available at
  * http://www.gnu.org/licenses/gpl-3.0.html
  */

package io.github.fergoman123.fergotools.config;


import java.io.File;

import io.github.fergoman123.fergotools.log.LoggerFT;
import io.github.fergoman123.fergotools.reference.ModInfo;
import io.github.fergoman123.fergoutil.helper.ConfigHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler
{
    public static final ConfigHandler instance = new ConfigHandler();

    public static Configuration config;

    public static int enchantability;

    public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfig();
        }
    }

    private static void loadConfig() {
        enchantability = config.getInt("enchantability", ConfigHelper.getCategoryGeneral(), Defaults.enchantabilityDefault, 1, 30, "Enchantability Modifier (Min = 1, Max = 30, Default = 30)");
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent evt)
    {
        if (evt.modID.equalsIgnoreCase(ModInfo.modid))
        {
            LoggerFT.info("Loaded Config!");
            loadConfig();
        }
    }
}
