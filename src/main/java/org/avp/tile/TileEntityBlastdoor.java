package org.avp.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.avp.AliensVsPredator;
import org.avp.api.machines.IOpenable;
import org.avp.api.power.IVoltageReceiver;
import org.avp.packets.client.PacketOpenBlastdoor;

import com.arisux.mdx.lib.game.Game;
import com.arisux.mdx.lib.world.block.IMultiBlock;
import com.arisux.mdx.lib.world.tile.IRotatable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class TileEntityBlastdoor extends TileEntityElectrical implements IVoltageReceiver, IRotatable, IOpenable, IMultiBlock
{
    private EnumFacing                     direction;
    private float                          doorProgress;
    private float                          doorProgressPrev;
    private boolean                        doorOpen;
    private boolean                        isParent;
    private boolean                        placedByPlayer;
    private TileEntityBlastdoor            parent;
    private ArrayList<TileEntityBlastdoor> children;
    private int                            ticksExisted;
    private String                         identifier;
    private String                         password;
    private long                           timeOfLastPry;

    public TileEntityBlastdoor()
    {
        super(false);
        this.children = new ArrayList<TileEntityBlastdoor>();
        this.identifier = "BD" + (1000 + new Random().nextInt(8999));
        this.password = "";
    }

    public void addToParent(TileEntityBlastdoor parent)
    {
        if (!parent.getChildren().contains(this))
        {
            parent.getChildren().add(this);
        }

        this.setParent(parent);
    }

    public ArrayList<TileEntityBlastdoor> getChildren()
    {
        return this.children;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.getPos(), 1, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
    {
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        if (this.direction != null)
        {
            nbt.setInteger("Direction", this.direction.ordinal());
        }

        nbt.setFloat("DoorProgress", this.doorProgress);
        nbt.setBoolean("DoorOpen", this.isOpen());
        nbt.setLong("TimeOfLastPry", this.getTimeOfLastPry());
        nbt.setBoolean("Parent", this.isParent);

        if (!identifier.isEmpty())
            nbt.setString("Identifier", this.identifier);

        if (!password.isEmpty())
            nbt.setString("Password", this.password);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (EnumFacing.getFront(nbt.getInteger("Direction")) != null)
        {
            this.direction = EnumFacing.getFront(nbt.getInteger("Direction"));
        }

        this.doorProgress = nbt.getFloat("DoorProgress");
        this.isParent = nbt.getBoolean("Parent");
        this.setOpen(nbt.getBoolean("DoorOpen"));
        this.timeOfLastPry = nbt.getLong("TimeOfLastPry");
        this.identifier = nbt.getString("Identifier");
        this.password = nbt.getString("Password");
    }

    @Override
    public void update()
    {
        super.update();
        this.doorProgressPrev = this.doorProgress;

        if (!this.isParent())
        {
            this.updateEnergyAsReceiver();
        }

        this.ticksExisted++;

        if (this.isParent && this.ticksExisted > 1 && !placedByPlayer)
        {
            this.setup(false);
        }

        if (this.isChild())
        {
            if (this.getParent() != null && this.getVoltage() > this.getParent().getVoltage())
            {
                this.getParent().setVoltage(this.getVoltage());
            }
        }
        
        if (this.isParent)
        {
            double childrenVoltage = 0.0D;

            for (TileEntityBlastdoor child : this.getChildren())
            {
                if (child.getVoltage() > 0.0D && child.getVoltage() > childrenVoltage)
                {
                    childrenVoltage = child.getVoltage();
                }
            }

            if (childrenVoltage <= 0)
            {
                this.updateEnergyAsReceiver();
            }
        }

        if (this.isParent())
        {
            if (this.isOpen() && this.isOperational())
            {
                this.doorProgress = this.doorProgress < getMaxDoorProgress() ? this.doorProgress + 0.02F : this.doorProgress;
            }

            if (!this.isOpen() && !isBeingPryedOpen())
            {
                this.doorProgress = this.doorProgress > 0.0F ? this.doorProgress - 0.02F : this.doorProgress;
            }

            long timeSinceLastPry = (System.currentTimeMillis() - this.getTimeOfLastPry());

            if (this.timeOfLastPry != 0 && timeSinceLastPry > getDoorResealTime())
            {
                this.timeOfLastPry = 0;
            }

            if (isBeingPryedOpen() && this.doorProgress >= getMaxDoorPryProgress())
            {
                this.timeOfLastPry = 0;
                this.setOpen(true);
            }
        }
    }

    public boolean isBeingPryedOpen()
    {
        return this.timeOfLastPry != 0;
    }

    public void setBeingPryedOpen(boolean beingPryedOpen)
    {
        if (beingPryedOpen)
        {
            this.timeOfLastPry = System.currentTimeMillis();
        }
    }

    public long getTimeOfLastPry()
    {
        return timeOfLastPry;
    }

    @Override
    public boolean canConnectPower(EnumFacing from)
    {
        return true;
    }

    @Override
    public double getCurrentVoltage(EnumFacing from)
    {
        return this.getVoltage();
    }

    @Override
    public double getMaxVoltage(EnumFacing from)
    {
        return 220;
    }

    @Override
    public boolean isOpen()
    {
        return this.isChild() ? (this.getParent() != null ? this.getParent().isOpen() : false) : doorOpen;
    }

    @Override
    public void setOpen(boolean doorOpen)
    {
        this.setOpen(doorOpen, true);
    }

    public void setOpen(boolean doorOpen, boolean sendPacket)
    {
        if (this.isChild())
        {
            if (this.getParent() != null)
            {
                this.getParent().setOpen(doorOpen);
            }
        }
        else if (this.isParent())
        {
            this.doorOpen = doorOpen;

            if (this.world != null && !this.world.isRemote && sendPacket)
            {
                AliensVsPredator.network().sendToAll(new PacketOpenBlastdoor(doorOpen, this.getPos()));
            }
        }
    }

    @Override
    public Block getBlockType()
    {
        return Blocks.BEACON;
    }

    public float getDoorProgress()
    {
        return this.doorProgress;
    }

    public boolean setChildTile(BlockPos position)
    {
        IBlockState blockstate = this.world.getBlockState(position);
        Block block = blockstate.getBlock();

        boolean validBlastDoor = true;

        if (block == AliensVsPredator.blocks().blastDoor)
        {
            TileEntityBlastdoor b = (TileEntityBlastdoor) this.world.getTileEntity(position);
            validBlastDoor = b.getParent() != null ? false : true;
        }

        if (blockstate.getMaterial() != Material.AIR && block != AliensVsPredator.blocks().blastDoor || !validBlastDoor)
        {
            if (this.world.isRemote)
            {
                if (validBlastDoor == false)
                {
                    Game.minecraft().player.sendMessage(new TextComponentString("Can't place a blast door inside of another blast door."));
                }
                else
                {
                    Game.minecraft().player.sendMessage(new TextComponentString("Unable to place a blast door here. Blocks are in the way."));
                }
            }

            return false;
        }

        world.setBlockState(position, AliensVsPredator.blocks().blastDoor.getDefaultState());
        TileEntityBlastdoor blastdoor = (TileEntityBlastdoor) world.getTileEntity(position);

        if (blastdoor == null)
        {
            Game.minecraft().player.sendMessage(new TextComponentString("Internal Error: TileEntityBlastDoor.setChildTile()/blastdoor = null"));
            return false;
        }

        if (blastdoor != null)
        {
            blastdoor.addToParent(this);
            blastdoor.setParent(this);
        }

        return true;
    }

    public void breakChildren()
    {
        for (TileEntityBlastdoor child : this.getChildren())
        {
            world.setBlockToAir(child.getPos());
        }
    }

    public boolean isChild()
    {
        return !this.isParent();
    }

    public boolean isParent()
    {
        return this.isParent;
    }

    public TileEntityBlastdoor getParent()
    {
        return parent;
    }

    public void setParent(TileEntityBlastdoor parent)
    {
        this.parent = parent;
    }

    public boolean setup(boolean placedByPlayer)
    {
        this.isParent = true;
        this.placedByPlayer = true;

        if (this.direction != null)
        {
            BlockPos[] set = this.setFor(this.direction);

            for (BlockPos offset : set)
            {
                BlockPos position = this.getPos().add(offset);

                if (!this.setChildTile(position))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public EnumFacing getDirection()
    {
        return direction;
    }

    @Override
    public void setDirection(EnumFacing direction)
    {
        this.direction = direction;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setDoorProgress(float doorProgress)
    {
        this.doorProgress = doorProgress;
    }

    public float getMaxDoorPryProgress()
    {
        return 0.4F;
    }

    public float getMaxDoorProgress()
    {
        return 1.0F;
    }

    public int getDoorResealTime()
    {
        return 600;
    }

    public float getDoorProgressPrev()
    {
        return this.doorProgressPrev;
    }

    @Override
    public BlockPos[] defaultSet()
    {
        List<BlockPos> set = new ArrayList<BlockPos>();
        BlockPos pos = new BlockPos(0, 0, 0);

        set.add(pos.add(1, 0, 0));
        set.add(pos.add(2, 0, 0));
        set.add(pos.add(3, 0, 0));
        set.add(pos.add(0, 1, 0));
        set.add(pos.add(0, 2, 0));
        set.add(pos.add(1, 2, 0));
        set.add(pos.add(1, 1, 0));
        set.add(pos.add(2, 2, 0));
        set.add(pos.add(2, 1, 0));
        set.add(pos.add(3, 2, 0));
        set.add(pos.add(3, 1, 0));

        return set.toArray(new BlockPos[set.size()]);
    }
}
