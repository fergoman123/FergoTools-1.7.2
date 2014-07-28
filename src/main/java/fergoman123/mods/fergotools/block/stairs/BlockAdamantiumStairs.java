package fergoman123.mods.fergotools.block.stairs;

import fergoman123.mods.fergotools.block.storage.BlockAdamantium;
import fergoman123.mods.fergotools.init.ModBlocks;
import fergoman123.mods.fergotools.reference.Names;
import fergoman123.mods.fergotools.reference.Reference;
import fergoman123.mods.fergotools.util.base.BlockStairsFT;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockAdamantiumStairs extends BlockStairsFT
{
    public BlockAdamantiumStairs()
    {
        super(ModBlocks.blockAdamantium);
        this.setBlockName(Names.Blocks.blockAdamantiumStairs);
    }

    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(Reference.textureLoc + Names.Blocks.blockAdamantium);
    }
}
