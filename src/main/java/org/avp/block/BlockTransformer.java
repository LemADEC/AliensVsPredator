package org.avp.block;

import java.util.ArrayList;
import java.util.Random;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityTransformer;
import org.avp.packets.client.PacketRotateRotatable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTransformer extends Block
{
    public BlockTransformer(Material material)
    {
        super(material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        this.setTickRandomly(true);
    }

    @Override
    public void registerIcons(IIconRegister register)
    {
        return;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void updateTick(World world, int posX, int posY, int posZ, Random rand)
    {
        super.updateTick(world, posX, posY, posZ, rand);
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityTransformer();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public void breakBlock(World world, int posX, int posY, int posZ, Block blockBroken, int meta)
    {
        super.breakBlock(world, posX, posY, posZ, blockBroken, meta);
        world.removeTileEntity(posX, posY, posZ);
    }

    @Override
    public boolean onBlockActivated(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        if (te != null && te instanceof TileEntityTransformer)
        {
            TileEntityTransformer transformer = (TileEntityTransformer) te;

            ArrayList<ForgeDirection> forgeDirections = new ArrayList<ForgeDirection>();

            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            {
                if (dir != ForgeDirection.UP && dir != ForgeDirection.DOWN)
                {
                    forgeDirections.add(dir);
                }
            }

            if (transformer.getDirection() != null)
            {
                int index = forgeDirections.indexOf(transformer.getDirection());

                if (index + 1 >= forgeDirections.size())
                {
                    index = -1;
                }

                if (forgeDirections.get(index + 1) != null)
                {
                    transformer.setDirection(forgeDirections.get(index + 1));
                }

                if (!worldObj.isRemote)
                {
                    AliensVsPredator.network().sendToAll(new PacketRotateRotatable(transformer.getDirection().ordinal(), transformer.xCoord, transformer.yCoord, transformer.zCoord));
                }
            }

            transformer.getDescriptionPacket();
        }
        return super.onBlockActivated(worldObj, xCoord, yCoord, zCoord, player, side, subX, subY, subZ);
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
}
