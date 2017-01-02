package org.avp.util;

import java.util.ArrayList;
import java.util.UUID;

import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntitySpeciesAlien;
import org.avp.entities.tile.TileEntityHiveResin;
import org.avp.event.HiveHandler;

import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.Worlds;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class XenomorphHive
{
    private ArrayList<EntitySpeciesAlien>  aliens;
    private ArrayList<TileEntityHiveResin> resinInHive;
    private UUID                           uuid;
    private EntityQueen                    queen;
    public World                           world;
    private int                            dimensionId;
    private int                            xCoord;
    private int                            yCoord;
    private int                            zCoord;

    public XenomorphHive(World world, UUID uuid)
    {
        this.uuid = uuid;
        this.queen = (EntityQueen) Worlds.getEntityByUUID(world, this.uuid);
        this.aliens = new ArrayList<EntitySpeciesAlien>();
        this.resinInHive = new ArrayList<TileEntityHiveResin>();

        if (this.getQueen() != null)
        {
            this.dimensionId = this.getQueen().worldObj.provider.dimensionId;
        }
    }

    public void setLocation(int xCoord, int yCoord, int zCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    public XenomorphHive setLocation(double xCoord, double yCoord, double zCoord)
    {
        this.setLocation((int) xCoord, (int) yCoord, (int) zCoord);
        return this;
    }

    public ArrayList<EntitySpeciesAlien> getAliensInHive()
    {
        return this.aliens;
    }

    public ArrayList<TileEntityHiveResin> getResinInHive()
    {
        return resinInHive;
    }

    public void addMemberToHive(EntitySpeciesAlien alien)
    {
        if (!this.aliens.contains(alien))
        {
            this.aliens.add(alien);
        }
    }

    public void addResin(TileEntityHiveResin resin)
    {
        if (!this.resinInHive.contains(resin))
        {
            this.resinInHive.add(resin);
        }
    }

    public boolean owns(TileEntityHiveResin resin)
    {
        if (resin != null && resin.getHiveSignature() != null)
        {
            if (!this.resinInHive.contains(resin) && resin.getHiveSignature().equals(this.getUniqueIdentifier()))
            {
                return true;
            }
        }

        return false;
    }

    public UUID getUniqueIdentifier()
    {
        return uuid;
    }

    public EntityQueen getQueen()
    {
        return this.queen == null && world != null ? this.queen = (EntityQueen) Worlds.getEntityByUUID(world, this.uuid) : this.queen;
    }

    public int getDimensionId()
    {
        return dimensionId;
    }

    public int xCoord()
    {
        return xCoord;
    }

    public int yCoord()
    {
        return yCoord;
    }

    public int zCoord()
    {
        return zCoord;
    }

    public void update(World world)
    {
        this.world = world;

        if (this.getQueen() != null && this.getQueen().isDead)
        {
            HiveHandler.instance.getHives().remove(this);
        }

        if (this.getQueen() != null)
        {
            this.dimensionId = this.getQueen().worldObj.provider.dimensionId;
        }

        if (world.getWorldTime() % (20 * 5) == 0)
        {
            for (EntitySpeciesAlien alien : (ArrayList<EntitySpeciesAlien>) this.getAliensInHive().clone())
            {
                if (alien == null || alien.isDead)
                {
                    this.getAliensInHive().remove(alien);
                }
            }
        }
    }

    public void destroy()
    {
        for (TileEntityHiveResin resin : new ArrayList<TileEntityHiveResin>(this.getResinInHive()))
        {
            if (resin != null)
            {
                this.world.setBlock(resin.xCoord, resin.yCoord, resin.zCoord, resin.getBlockCovering());
            }
        }

        for (EntitySpeciesAlien alien : new ArrayList<EntitySpeciesAlien>(this.getAliensInHive()))
        {
            if (alien != null)
            {
                alien.setDead();
            }
        }

        this.getAliensInHive().clear();
        this.getResinInHive().clear();
        HiveHandler.instance.getHives().remove(this);
    }

    public void load(World world, UUID uniqueIdentifier, NBTTagCompound nbt)
    {
        this.queen = (EntityQueen) Worlds.getEntityByUUID(world, this.uuid);
        this.dimensionId = nbt.getInteger("DimID");
        this.xCoord = nbt.getInteger("X");
        this.yCoord = nbt.getInteger("Y");
        this.zCoord = nbt.getInteger("Z");

        NBTTagList list = nbt.getTagList("Resin", NBT.TAG_STRING);

        for (int i = 0; i < list.tagCount(); i++)
        {
            String key = list.getStringTagAt(i);
            String[] coordSet = key.split(",");

            int x = Integer.parseInt(coordSet[0].trim());
            int y = Integer.parseInt(coordSet[1].trim());
            int z = Integer.parseInt(coordSet[2].trim());

            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityHiveResin)
            {
                TileEntityHiveResin resin = (TileEntityHiveResin) tile;
                this.addResin(resin);
            }
        }
    }

    public void save(World world, NBTTagCompound nbt)
    {
        nbt.setString("UUID", this.uuid.toString());
        nbt.setInteger("DimID", this.dimensionId);
        nbt.setInteger("X", this.xCoord);
        nbt.setInteger("Y", this.yCoord);
        nbt.setInteger("Z", this.zCoord);

        NBTTagList list = new NBTTagList();

        for (TileEntityHiveResin resin : this.resinInHive)
        {
            list.appendTag(new NBTTagString(String.format("%s, %s, %s", resin.xCoord, resin.yCoord, resin.zCoord)));
        }

        nbt.setTag("Resin", list);
    }

    @Override
    public String toString()
    {
        return String.format("[Dimension %s, %s Aliens, %s Resin, HIVE UUID: %s, QUEEN UUID: %s, XYZ(%s, %s, %s)]", this.dimensionId, this.getAliensInHive().size(), this.getResinInHive().size(), this.getUniqueIdentifier(), this.getQueen() != null ? this.getQueen().getUniqueID() : null, this.xCoord, this.yCoord, this.zCoord);
    }

    public boolean isQueenAtCore()
    {
        return this.getQueen().getDistance(this.xCoord(), this.yCoord(), this.zCoord()) < this.getCoreRange();
    }

    public boolean isPointWithinHive(CoordData coord)
    {
        return isPointWithinHive((int) coord.x(), (int) coord.y(), (int) coord.z());
    }

    public boolean isPointWithinHive(int x, int y, int z)
    {
        return this.getQueen().getDistance(x, y, z) < this.getMaxHiveRadius();
    }

    public double getDistanceFromHive(Entity entity)
    {
        return this.getQueen().getDistance(entity.posX, entity.posY, entity.posZ);
    }

    public boolean isEntityWithinRange(Entity entity)
    {
        return getDistanceFromHive(entity) < this.getMaxHiveRadius();
    }

    public int getCoreRange()
    {
        return getMaxHiveRadius() / 4;
    }

    public int getMaxHiveRadius()
    {
        return 32;
    }
}
