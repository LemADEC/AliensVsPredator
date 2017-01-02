package org.avp.block;

import org.avp.entities.tile.TileEntityLocker;

import com.arisux.mdxlib.lib.world.tile.IRotatable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLocker extends Block
{
    public BlockLocker(Material material)
    {
        super(material);
        setTickRandomly(true);
        this.setBlockBounds(0, 0, 0, 1, 2, 1);
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
    public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ)
    {
        return super.canPlaceBlockAt(world, posX, posY, posZ);
    }

    @Override
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float blockX, float blockY, float blockZ)
    {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);

        if (tileEntity != null && tileEntity instanceof TileEntityLocker)
        {
            TileEntityLocker tileEntityLocker = (TileEntityLocker) tileEntity;

            if (!player.isSneaking())
            {
                tileEntityLocker.openGui(player);
            }
            else
            {
                tileEntityLocker.setOpen(!tileEntityLocker.isOpen());
            }
        }

        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase placer, ItemStack itemstack)
    {
        TileEntity tile = world.getTileEntity(posX, posY, posZ);

        if (tile != null && tile instanceof IRotatable && placer != null)
        {
            IRotatable rotatable = (IRotatable) tile;
            rotatable.setDirection(getFacing(placer));
        }
    }

    @Override
    public void onBlockPreDestroy(World worldIn, int x, int y, int z, int meta)
    {
        super.onBlockPreDestroy(worldIn, x, y, z, meta);
        
        TileEntityLocker locker = (TileEntityLocker) worldIn.getTileEntity(x, y, z);
        
        if (locker != null)
        {
            for (int i = locker.inventory.getSizeInventory(); i > 0; i--)
            {
                ItemStack stack = locker.inventory.getStackInSlot(i);
                
                if (stack != null)
                {
                    EntityItem entityItem = new EntityItem(worldIn, x, y, z, stack);
                    worldIn.spawnEntityInWorld(entityItem); 
                }
            }
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityLocker();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    public static ForgeDirection getFacing(Entity entity)
    {
        int dir = MathHelper.floor_double((entity.rotationYaw / 90) + 0.5) & 3;
        return ForgeDirection.VALID_DIRECTIONS[Direction.directionToFacing[dir]];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int posX, int posY, int posZ)
    {
        TileEntity tile = world.getTileEntity(posX, posY, posZ);

        if (tile != null && tile instanceof TileEntityLocker)
        {
            TileEntityLocker locker = (TileEntityLocker) tile;
            return locker.isOpen() ? null : super.getCollisionBoundingBoxFromPool(world, posX, posY, posZ);
        }

        return super.getCollisionBoundingBoxFromPool(world, posX, posY, posZ);
    }
}
