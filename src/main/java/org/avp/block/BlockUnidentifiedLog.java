package org.avp.block;

import com.arisux.mdxlib.lib.world.block.BlockSide;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockUnidentifiedLog extends BlockLog
{
    private IIcon top;

    public BlockUnidentifiedLog()
    {
        super();
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        this.blockIcon = (register.registerIcon("avp:unitree.wood"));
        this.top = (register.registerIcon("avp:unitree.wood.top"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        BlockSide iconSide = BlockSide.getSide(side);

        switch (iconSide)
        {
            case BOTTOM:
                return top;
            case TOP:
                return top;
            case BACK:
                return blockIcon;
            case FRONT:
                return blockIcon;
            case LEFT:
                return blockIcon;
            case RIGHT:
                return blockIcon;
            default:
                return super.getIcon(side, meta);
        }
    }
}
