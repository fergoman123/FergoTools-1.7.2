package fergoman123.mods.fergotools.init;

import fergoman123.mods.fergotools.block.furnace.*;
import fergoman123.mods.fergotools.block.ore.*;
import fergoman123.mods.fergotools.block.storage.*;
import fergoman123.mods.fergotools.lib.Strings.FurnaceStrings;
import fergoman123.mods.fergotools.tabs.Tabs;
import net.minecraft.block.Block;

public class ModBlocks {
	
	public static Block oreObsidian;
    public static Block oreEmeraldCrystal;
    public static Block oreLapisCrystal;
    public static Block oreBronze;
    public static Block oreAdamantium;
    public static Block oreExperience;
    public static Block oreRedstoneCrystal;

    public static Block blockExperience;
    public static Block blockObsidian;
    public static Block blockEmeraldCrystal;
    public static Block blockLapisCrystal;
    public static Block blockBronze;
    public static Block blockCoal;
    public static Block blockGlowstone;
    public static Block blockAdamantium;
    public static Block blockSilkGem;
    public static Block blockRedstone;

    public static Block quartzFurnaceIdle;
    public static Block quartzFurnaceActive;

    public static Block obsidianFurnaceIdle;
    public static Block obsidianFurnaceActive;

    public static Block emeraldCrystalFurnaceIdle;
    public static Block emeraldCrystalFurnaceActive;

    public static Block lapisCrystalFurnaceIdle;
    public static Block lapisCrystalFurnaceActive;

    public static Block bronzeFurnaceIdle;
    public static Block bronzeFurnaceActive;

    public static Block glowstoneFurnaceIdle;
    public static Block glowstoneFurnaceActive;

    public static Block coalFurnaceIdle;
    public static Block coalFurnaceActive;

    public static Block adamantiumFurnaceIdle;
    public static Block adamantiumFurnaceActive;

    public static Block silkFurnaceIdle;
    public static Block silkFurnaceActive;

    public static Block redstoneFurnaceIdle;
    public static Block redstoneFurnaceActive;

    public static Block maceratorIdle;
    public static Block maceratorActive;
    
	public static void initBlocks()
	{
        oreExperience = new BlockOreExperience();
        oreObsidian = new BlockOreObsidian();
        oreEmeraldCrystal = new BlockOreEmeraldCrystal();
        oreLapisCrystal = new BlockOreLapisCrystal();
        oreBronze = new BlockOreBronze();
        oreAdamantium = new BlockOreAdamantium();
        oreRedstoneCrystal = new BlockOreRedstone();


        blockExperience = new BlockExperience();
        blockObsidian = new BlockObsidianStorage();
        blockEmeraldCrystal = new BlockEmeraldCrystal();
        blockLapisCrystal = new BlockLapisCrystal();
        blockBronze = new BlockBronze();
        blockCoal = new BlockCoalStorage();
        blockGlowstone = new BlockGlowstoneStorage();
        blockAdamantium = new BlockAdamantium();
        blockSilkGem = new BlockSilkGem();
        blockRedstone = new BlockRedstone();

        quartzFurnaceIdle = new BlockQuartzFurnace(false).setBlockName(FurnaceStrings.quartzFurnaceIdle).setCreativeTab(Tabs.tabFergoFurnaces);
        quartzFurnaceActive = new BlockQuartzFurnace(true).setBlockName(FurnaceStrings.quartzFurnaceActive).setLightLevel(0.9f);

        obsidianFurnaceIdle = new BlockObsidianFurnace(false).setBlockName(FurnaceStrings.obsidianFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        obsidianFurnaceActive = new BlockObsidianFurnace(true).setBlockName(FurnaceStrings.obsidianFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        emeraldCrystalFurnaceIdle = new BlockEmeraldCrystalFurnace(false).setBlockName(FurnaceStrings.emeraldCrystalFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        emeraldCrystalFurnaceActive = new BlockEmeraldCrystalFurnace(true).setBlockName(FurnaceStrings.emeraldCrystalFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        lapisCrystalFurnaceIdle = new BlockLapisCrystalFurnace(false).setBlockName(FurnaceStrings.lapisCrystalFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        lapisCrystalFurnaceActive = new BlockLapisCrystalFurnace(true).setBlockName(FurnaceStrings.lapisCrystalFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        bronzeFurnaceIdle = new BlockBronzeFurnace(false).setBlockName(FurnaceStrings.bronzeFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        bronzeFurnaceActive = new BlockBronzeFurnace(true).setBlockName(FurnaceStrings.bronzeFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        coalFurnaceIdle = new BlockCoalFurnace(false).setBlockName(FurnaceStrings.coalFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        coalFurnaceActive = new BlockCoalFurnace(true).setBlockName(FurnaceStrings.coalFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        glowstoneFurnaceIdle = new BlockGlowstoneFurnace(false).setBlockName(FurnaceStrings.glowstoneFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        glowstoneFurnaceActive = new BlockGlowstoneFurnace(true).setBlockName(FurnaceStrings.glowstoneFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        adamantiumFurnaceIdle = new BlockAdamantiumFurnace(false).setBlockName(FurnaceStrings.adamantiumFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        adamantiumFurnaceActive = new BlockAdamantiumFurnace(true).setBlockName(FurnaceStrings.adamantiumFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        silkFurnaceIdle = new BlockSilkFurnace(false).setBlockName(FurnaceStrings.silkFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        silkFurnaceActive = new BlockSilkFurnace(true).setBlockName(FurnaceStrings.silkFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);

        redstoneFurnaceIdle = new BlockRedstoneFurnace(false).setBlockName(FurnaceStrings.redstoneFurnaceIdle).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setCreativeTab(Tabs.tabFergoFurnaces);
        redstoneFurnaceActive = new BlockRedstoneFurnace(true).setBlockName(FurnaceStrings.redstoneFurnaceActive).setHardness(3.5f).setResistance(2000f).setStepSound(Block.soundTypeMetal).setLightLevel(0.9f);
	}


}