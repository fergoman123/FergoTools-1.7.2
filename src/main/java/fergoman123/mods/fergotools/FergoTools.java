package fergoman123.mods.fergotools;

// minecraft imports
// fml imports

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fergoman123.mods.fergotools.block.ModBlocks;
import fergoman123.mods.fergotools.handler.RecipesHandler;
import fergoman123.mods.fergotools.handler.RegHandler;
import fergoman123.mods.fergotools.item.ModItems;
import fergoman123.mods.fergotools.lib.ModInfo;
import fergoman123.mods.fergotools.lib.Reference;
import fergoman123.mods.fergotools.lib.strings.TabStrings;
import fergoman123.mods.fergotools.packet.PacketPipeline;
import fergoman123.mods.fergotools.proxy.CommonProxy;


@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class FergoTools {

    public static final PacketPipeline packPipe = new PacketPipeline();
	
    @Instance(ModInfo.modid)
    public static FergoTools instance;
    
    

    public static CreativeTabs tabFergoTools;
    public static CreativeTabs tabFergoBlocks;
    public static CreativeTabs tabFergoFurnaces;
    public static CreativeTabs tabFergoItems;
    public static CreativeTabs tabFergoArmor;
    public static CreativeTabs tabFergoBows;
    
    @SidedProxy(clientSide = Reference.clientProxy, serverSide = Reference.commonProxy)
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        tabFergoTools = new CreativeTabs(TabStrings.tabs[0]) {public Item getTabIconItem(){return ModItems.quartzPickaxe;}};
        tabFergoBlocks = new CreativeTabs(TabStrings.tabs[1]){public Item getTabIconItem(){return Item.getItemFromBlock(ModBlocks.oreExperience);}};
        tabFergoFurnaces = new CreativeTabs(TabStrings.tabs[2]) {public Item getTabIconItem(){return Item.getItemFromBlock(ModBlocks.quartzFurnaceIdle);}};
        tabFergoItems = new CreativeTabs(TabStrings.tabs[3]){public Item getTabIconItem(){return ModItems.expShard;}};
        tabFergoArmor = new CreativeTabs(TabStrings.tabs[4]){public Item getTabIconItem(){return ModItems.quartzHelmet;}};
        tabFergoBows = new CreativeTabs(TabStrings.tabs[5]){public Item getTabIconItem(){return ModItems.quartzBow;}};

        ModItems.addToolMaterials();
        ModItems.addArmorMaterials();
        ModItems.addItems();
        ModItems.addArmor();
        ModItems.addBows();
        ModBlocks.addBlocks();
        RegHandler.registerBlocks();
        RegHandler.registerItems();
    }
    

	@EventHandler
    public void load(FMLInitializationEvent evt)
    {
        RecipesHandler.init();
        RegHandler.registerGuiHandlers();
        RegHandler.registerTileEntities();
        RegHandler.registerWorldGen();
        packPipe.initialise();
    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent evt)
    {
        FMLLog.info("Mod Loaded");
        FMLLog.info("Enjoy!!!!!");
        packPipe.postInitialise();
    }
}
