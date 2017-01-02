package org.avp.entities.tile;

import java.util.ArrayList;

import org.avp.AliensVsPredator;
import org.avp.Sounds;
import org.avp.entities.EntityBullet;
import org.avp.entities.EntityTurret;
import org.avp.entities.mob.EntityAqua;
import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityCrusher;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityOvamorph;
import org.avp.entities.mob.EntityPraetorian;
import org.avp.entities.mob.EntityPredalien;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntitySpitter;
import org.avp.entities.mob.EntityWarrior;
import org.avp.inventory.container.ContainerTurret;
import org.avp.packets.client.PacketTurretInit;
import org.avp.packets.server.PacketTurretTargetUpdate;
import org.avp.util.IDataDevice;
import org.avp.util.IPowerDrain;

import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.entity.Entities;
import com.arisux.mdxlib.lib.world.storage.NBTStorage;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTurret extends TileEntityElectrical implements IDataDevice, IPowerDrain
{
    private long                               fireRate;
    private boolean                            ammoDisplayEnabled;
    private boolean                            isFiring;
    private int                                range;
    private int                                defaultRunCycles;
    private int                                runCycles;
    private int                                curAmmo;
    private int                                maxAmmo;
    private int                                curRounds;
    private int                                maxRounds;
    private int                                direction;
    private int                                firingTimeout;
    private int                                maxFiringTimeout;
    private float                              deltaLookYaw;
    private float                              deltaLookPitch;
    private float                              focusPitch;
    private float                              focusYaw;
    private float                              rotationYaw;
    private float                              rotationPitch;
    private ArrayList<Class<? extends Entity>> dangerousTargets = new ArrayList<Class<? extends Entity>>();
    public InventoryBasic                      inventoryAmmo;
    public InventoryBasic                      inventoryExpansion;
    public InventoryBasic                      inventoryDrive;
    private Entity                             targetEntity;
    private Entity                             turretEntity;
    private ContainerTurret                    container;
    private CoordData                          focusPoint;
    private Item                               itemAmmo;
    public int                                 beamColor        = 0xFFFF0000;

    public TileEntityTurret()
    {
        super();
        this.inventoryAmmo = new InventoryBasic("TurretAmmoBay", true, 9);
        this.inventoryExpansion = new InventoryBasic("TurretExpansionBay", true, 3);
        this.inventoryDrive = new InventoryBasic("TurretDriveBay", true, 1);
        this.direction = 0;
        this.fireRate = 2L;
        this.range = 24;
        this.defaultRunCycles = 4;
        this.runCycles = defaultRunCycles;
        this.curAmmo = 0;
        this.maxAmmo = 128;
        this.curRounds = 0;
        this.deltaLookYaw = 360F;
        this.deltaLookPitch = 90F;
        this.focusPitch = 0F;
        this.focusYaw = 0F;
        this.ammoDisplayEnabled = false;
        this.maxFiringTimeout = 60;
        this.itemAmmo = AliensVsPredator.items().itemAmmoSMG;
    }

    public Entity getEntity()
    {
        return this.turretEntity == null ? this.turretEntity = new EntityTurret(this, this.worldObj) : this.turretEntity;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        super.updateEnergyAsReceiver();

        this.isFiring = false;

        if (this.worldObj != null && this.getEntity() != null)
        {
            this.getEntity().setLocationAndAngles(this.xCoord + getEntity().width / 2, this.yCoord + 1, this.zCoord + getEntity().width / 2, this.rotationYaw, this.rotationPitch);
        }

        if (this.getVoltage() > 0)
        {
            this.firingTimeout = this.firingTimeout > 0 ? this.firingTimeout - 1 : this.firingTimeout;
            this.pickupItemstacks();
            this.lookAtFocusPoint();
            this.updateAmmunitionCount();
            this.reloadIfNecessary();
            this.targetAndAttack();
        }
    }

    public Entity findNewTarget()
    {
        Entity newTarget = Entities.getRandomEntityInCoordsRange(this.worldObj, EntityLiving.class, new CoordData(this), range, 12);

        if (newTarget != null && this.getEntity().getDistanceToEntity(newTarget) < range && !newTarget.isDead && Entities.canEntityBeSeenBy(newTarget, this.getEntity()) && !isSafe(newTarget))
        {
            return targetEntity = newTarget;
        }

        return null;
    }

    public void targetAndAttack()
    {
        if (this.getTargetEntity() != null && this.isSafe(this.getTargetEntity()))
        {
            this.findNewTarget();
        }

        if (targetEntity != null)
        {
            if (this.getEntity().getDistanceToEntity(targetEntity) < range && !targetEntity.isDead && Entities.canEntityBeSeenBy(targetEntity, this.getEntity()))
            {
                turnTurretToPoint(new CoordData(targetEntity.posX, targetEntity.posY, targetEntity.posZ));

                if (worldObj.getWorldInfo().getWorldTime() % fireRate == 0L && this.getEntity().rotationYaw != 0 && this.rotationYaw == this.focusYaw)
                {
                    if (curAmmo-- > 0 && Entities.canEntityBeSeenBy(targetEntity, this.getEntity()))
                    {
                        this.fire();
                    }
                    else
                    {
                        this.reload();
                    }
                }
            }
            else
            {
                this.targetEntity = null;
            }
        }
        else
        {
            Entity newTarget = this.findNewTarget();

            if (newTarget != null && this.worldObj.isRemote)
            {
                AliensVsPredator.network().sendToServer(new PacketTurretTargetUpdate(xCoord, yCoord, zCoord, newTarget.getEntityId()));
            }
        }
    }

    public void lookAtFocusPoint()
    {
        if (this.focusPoint != null)
        {
            for (int runCycles = this.runCycles; runCycles > 0; runCycles--)
            {
                if (Math.ceil(this.getRotationYaw()) < Math.ceil(this.focusYaw))
                {
                    this.setRotationYaw(this.getRotationYaw() + 1);
                }
                else if (Math.ceil(this.getRotationYaw()) > Math.ceil(this.focusYaw))
                {
                    this.setRotationYaw(this.getRotationYaw() - 1);
                }

                if (Math.ceil(this.getRotationPitch()) < Math.ceil(this.focusPitch))
                {
                    this.setRotationPitch(this.getRotationPitch() + 1);
                }
                else if (Math.ceil(this.getRotationPitch()) > Math.ceil(this.focusPitch))
                {
                    this.setRotationPitch(this.getRotationPitch() - 1);
                }

                int focusRange = 1;
                if (Math.ceil(this.getRotationPitch()) >= Math.ceil(this.focusPitch - focusRange) && Math.ceil(this.getRotationPitch()) <= Math.ceil(this.focusPitch + focusRange) && Math.ceil(this.getRotationYaw()) >= Math.ceil(this.focusYaw - focusRange) && Math.ceil(this.getRotationYaw()) <= Math.ceil(this.focusYaw + focusRange))
                {
                    this.setRotationPitch(getEntity().rotationPitch = this.focusPitch);
                    this.setRotationYaw(getEntity().rotationYaw = this.focusYaw);
                }
            }
        }
    }

    public void reloadIfNecessary()
    {
        if (this.curAmmo < maxAmmo && this.curAmmo <= 0)
        {
            this.reload();
        }
    }

    public void updateAmmunitionCount()
    {
        if (worldObj.getWorldInfo().getWorldTime() % 8L == 0L)
        {
            this.maxRounds = (9 * 64);
            this.curRounds = 0;

            for (int i = 0; i < 9; i++)
            {
                ItemStack stack = this.inventoryAmmo.getStackInSlot(i);

                if (stack != null)
                {
                    if (stack.getItem() == this.itemAmmo)
                    {
                        this.curRounds = this.curRounds + (stack.stackSize);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void pickupItemstacks()
    {
        if (this.worldObj != null && this.inventoryAmmo != null)
        {
            ArrayList<EntityItem> entityItemList = (ArrayList<EntityItem>) Entities.getEntitiesInCoordsRange(worldObj, EntityItem.class, new CoordData(this), 1);

            for (EntityItem entityItem : entityItemList)
            {
                if (entityItem.delayBeforeCanPickup <= 0)
                {
                    ItemStack stack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);

                    if (stack.getItem() == this.itemAmmo)
                    {
                        for (int x = 0; x < 9; x++)
                        {
                            ItemStack invStack = this.inventoryAmmo.getStackInSlot(x);

                            if (invStack == null || invStack != null && invStack.stackSize < 64)
                            {
                                this.inventoryAmmo.setInventorySlotContents(x, stack);
                                entityItem.setDead();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void reload()
    {
        if (this.curRounds >= 1)
        {
            this.curAmmo = maxAmmo;

            for (int x = 0; x < 9; x++)
            {
                ItemStack stack = this.inventoryAmmo.getStackInSlot(x);

                if (stack != null && stack.getItem() == this.getItemAmmo())
                {
                    if (stack.stackSize-- <= 0)
                    {
                        this.inventoryAmmo.setInventorySlotContents(x, null);
                    }

                    break;
                }
            }
        }
    }

    public void fire()
    {
        this.isFiring = true;
        this.firingTimeout = this.maxFiringTimeout;
        EntityBullet entity = new EntityBullet(this.worldObj, this.getEntity(), this.targetEntity, 2F, 0.55D);
        entity.setPhysics(false);
        entity.setLocationAndAngles(entity.posX - 0.5, entity.posY - 0.4, entity.posZ - 0.5, entity.rotationYaw, entity.rotationPitch);
        this.worldObj.spawnEntityInWorld(entity);
        this.worldObj.spawnParticle("largesmoke", xCoord, yCoord, zCoord, 1, 1, 1);
        Sounds.SOUND_WEAPON_M56SG.playSound(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public void turnTurretToPoint(CoordData coord)
    {
        this.focusPoint = coord;

        double d0 = coord.x - getEntity().posX;
        double d1 = coord.y - getEntity().posY;
        double d2 = coord.z - getEntity().posZ;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));

        this.focusPitch = this.updateRotation(getEntity().rotationPitch, f1, deltaLookPitch);
        this.focusYaw = this.updateRotation(getEntity().rotationYaw, f, deltaLookYaw);
    }

    public float updateRotation(float rotation, float rotation2, float deltaRotation)
    {
        float f = MathHelper.wrapAngleTo180_float(rotation2 - rotation);

        if (f > deltaRotation)
        {
            f = deltaRotation;
        }

        if (f < -deltaRotation)
        {
            f = -deltaRotation;
        }

        return rotation + f;
    }

    public void setSafe(Entity entity)
    {
        this.setSafe(entity.getClass());
    }

    public void setSafe(Class<? extends Entity> entityClass)
    {
        this.setTargetEntity(null);

        if (this.dangerousTargets.contains(entityClass))
        {
            this.dangerousTargets.remove(entityClass);
        }
    }

    public void setDangerous(Entity entity)
    {
        this.setDangerous(entity.getClass());
    }

    public void setDangerous(Class<? extends Entity> entityClass)
    {
        this.setTargetEntity(null);

        if (!this.dangerousTargets.contains(entityClass))
        {
            this.dangerousTargets.add(entityClass);
        }
    }

    public boolean isSafe(Entity entity)
    {
        return isSafe(entity.getClass());
    }

    public boolean isSafe(Class<? extends Entity> entityClass)
    {
        if (this.dangerousTargets.contains(entityClass))
        {
            return false;
        }

        return true;
    }

    public void setPredefinedTargets()
    {
        this.setDangerous(EntityOvamorph.class);
        this.setDangerous(EntityFacehugger.class);
        this.setDangerous(EntityChestburster.class);
        this.setDangerous(EntityDrone.class);
        this.setDangerous(EntityWarrior.class);
        this.setDangerous(EntityPraetorian.class);
        this.setDangerous(EntityQueen.class);
        this.setDangerous(EntityCrusher.class);
        this.setDangerous(EntitySpitter.class);
        this.setDangerous(EntityAqua.class);
        this.setDangerous(EntityPredalien.class);
        this.setDangerous(EntitySlime.class);
    }

    public void applyUpgrades()
    {
        int runCyclesUpgrade = this.getDefaultRunCycles();
        this.setAmmoDisplayEnabled(false);

        for (int i = 0; i < 3; i++)
        {
            ItemStack pciSlot = this.inventoryExpansion.getStackInSlot(i);

            if (pciSlot != null && pciSlot.getItem() == AliensVsPredator.items().itemProcessor)
            {
                runCyclesUpgrade += 1 * pciSlot.stackSize;
            }

            if (pciSlot != null && pciSlot.getItem() == AliensVsPredator.items().itemLedDisplay)
            {
                this.setAmmoDisplayEnabled(true);
            }
        }

        this.setRunCycles(runCyclesUpgrade);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        this.direction = nbt.getInteger("Direction");

        this.readTargetListFromNBT(nbt);
        this.readInventoryFromNBT(nbt, this.inventoryAmmo);
        this.readInventoryFromNBT(nbt, this.inventoryExpansion);
        this.readInventoryFromNBT(nbt, this.inventoryDrive);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setInteger("Direction", this.direction);

        this.saveTargetListToNBT(nbt);
        this.saveInventoryToNBT(nbt, this.inventoryAmmo);
        this.saveInventoryToNBT(nbt, this.inventoryExpansion);
        this.saveInventoryToNBT(nbt, this.inventoryDrive);
    }

    private void saveTargetListToNBT(NBTTagCompound nbt)
    {
        ArrayList<String> entityIDs = new ArrayList<String>();

        for (Class<? extends Entity> c : this.getDangerousTargets())
        {
            if (c != null)
            {
                entityIDs.add(Entities.getEntityRegistrationId(c));
            }
        }

        nbt.setTag("Targets", NBTStorage.newStringNBTList(entityIDs));
    }

    @SuppressWarnings("unchecked")
    private void readTargetListFromNBT(NBTTagCompound nbt)
    {
        StringBuilder builder = new StringBuilder();

        NBTTagList nbttaglist = nbt.getTagList("Targets", NBT.TAG_STRING);

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            String id = nbttaglist.getStringTagAt(i);

            Class<? extends Entity> c = (Class<? extends Entity>) EntityList.stringToClassMapping.get(id);
            this.setDangerous(c);
            builder.append(id + "-");
        }

        if (builder.toString().length() <= 0)
        {
            // TODO: Re-enable this
            // this.setPredefinedTargets();
        }

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            String id = nbttaglist.getStringTagAt(i);

            Class<? extends Entity> c = (Class<? extends Entity>) EntityList.stringToClassMapping.get(id);
            this.setDangerous(c);
            builder.append(id + "-");
        }

        if (builder != null && builder.toString() != null)
        {
            AliensVsPredator.network().sendToAll(new PacketTurretInit(this.xCoord, this.yCoord, this.zCoord, builder.toString()));
        }
    }

    private void saveInventoryToNBT(NBTTagCompound nbt, IInventory inventory)
    {
        NBTTagList items = new NBTTagList();

        for (byte x = 0; x < inventory.getSizeInventory(); x++)
        {
            ItemStack stack = inventory.getStackInSlot(x);

            if (stack != null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", x);
                stack.writeToNBT(item);
                items.appendTag(item);
            }
        }

        nbt.setTag(inventory.getInventoryName(), items);
    }

    private void readInventoryFromNBT(NBTTagCompound nbt, IInventory inventory)
    {
        NBTTagList items = nbt.getTagList(inventory.getInventoryName(), Constants.NBT.TAG_COMPOUND);

        for (byte x = 0; x < items.tagCount(); x++)
        {
            NBTTagCompound item = items.getCompoundTagAt(x);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot <= inventory.getSizeInventory())
            {
                inventory.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    public ContainerTurret getNewContainer(EntityPlayer player)
    {
        return (container = new ContainerTurret(player, this, worldObj, xCoord, yCoord, zCoord));
    }

    public ContainerTurret getContainer(EntityPlayer player)
    {
        return container == null && player != null ? container = getNewContainer(player) : container;
    }

    public long getFireRate()
    {
        return fireRate;
    }

    public void setFireRate(long fireRate)
    {
        this.fireRate = fireRate;
    }

    public float getDeltaLookPitch()
    {
        return deltaLookPitch;
    }

    public void setDeltaLookPitch(float deltaLookPitch)
    {
        this.deltaLookPitch = deltaLookPitch;
    }

    public float getDeltaLookYaw()
    {
        return deltaLookYaw;
    }

    public void setDeltaLookYaw(float deltaLookYaw)
    {
        this.deltaLookYaw = deltaLookYaw;
    }

    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public void setRunCycles(int runCycles)
    {
        this.runCycles = runCycles;
    }

    public int getRunCycles()
    {
        return runCycles;
    }

    public int getDefaultRunCycles()
    {
        return defaultRunCycles;
    }

    public void setDefaultRunCycles(int defaultRunCycles)
    {
        this.defaultRunCycles = defaultRunCycles;
    }

    public void setAmmoDisplayEnabled(boolean ammoDisplayEnabled)
    {
        this.ammoDisplayEnabled = ammoDisplayEnabled;
    }

    public boolean isAmmoDisplayEnabled()
    {
        return ammoDisplayEnabled;
    }

    public Entity getTargetEntity()
    {
        return targetEntity;
    }

    public void setTargetEntity(Entity targetEntity)
    {
        this.targetEntity = targetEntity;
    }

    public ArrayList<Class<? extends Entity>> getDangerousTargets()
    {
        return dangerousTargets;
    }

    public int getCurAmmo()
    {
        return curAmmo;
    }

    public int getMaxAmmo()
    {
        return maxAmmo;
    }

    public void setCurAmmo(int curAmmo)
    {
        this.curAmmo = curAmmo;
    }

    public void setMaxAmmo(int maxAmmo)
    {
        this.maxAmmo = maxAmmo;
    }

    public Item getItemAmmo()
    {
        return itemAmmo;
    }

    public void setItemAmmo(Item itemAmmo)
    {
        this.itemAmmo = itemAmmo;
    }

    public int getCurRounds()
    {
        return curRounds;
    }

    public int getMaxRounds()
    {
        return maxRounds;
    }

    public void setCurRounds(int curRounds)
    {
        this.curRounds = curRounds;
    }

    public void setMaxRounds(int maxRounds)
    {
        this.maxRounds = maxRounds;
    }

    public float getRotationYaw()
    {
        return rotationYaw;
    }

    public void setRotationYaw(float rotationYaw)
    {
        this.rotationYaw = rotationYaw;
    }

    public float getRotationPitch()
    {
        return rotationPitch;
    }

    public void setRotationPitch(float rotationPitch)
    {
        this.rotationPitch = rotationPitch;
    }

    public boolean isFiring()
    {
        return isFiring;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readFromOtherDevice(int ID)
    {
        StringBuilder builder = new StringBuilder();
        ItemStack devicePort = this.inventoryDrive.getStackInSlot(0);

        if (devicePort != null)
        {
            NBTTagCompound nbt = devicePort.getTagCompound();

            if (nbt != null)
            {
                NBTTagList list = nbt.getTagList("Targets", NBT.TAG_STRING);

                if (list != null)
                {
                    for (int i = 0; i < list.tagCount(); i++)
                    {
                        String id = list.getStringTagAt(i);

                        Class<? extends Entity> c = (Class<? extends Entity>) EntityList.stringToClassMapping.get(id);
                        this.setDangerous(c);
                        builder.append(id + "-");
                    }
                }
            }
        }

        AliensVsPredator.network().sendToAll(new PacketTurretInit(xCoord, yCoord, zCoord, builder.toString()));
    }

    @Override
    public void writeToOtherDevice(int ID)
    {
        if (container != null)
        {
            ItemStack devicePort = this.inventoryDrive.getStackInSlot(0);

            if (devicePort != null)
            {
                NBTTagCompound nbt = new NBTTagCompound();
                ArrayList<String> entityIDs = new ArrayList<String>();

                for (Class<? extends Entity> c : this.getDangerousTargets())
                {
                    if (c != null)
                    {
                        entityIDs.add(Entities.getEntityRegistrationId(c));
                    }
                }

                nbt.setTag("Targets", NBTStorage.newStringNBTList(entityIDs));

                devicePort.setTagCompound(nbt);
                devicePort.setStackDisplayName("NBT Drive - " + this.getEntity().getUniqueID());
                this.inventoryDrive.setInventorySlotContents(0, devicePort);
            }
        }
    }

    @Override
    public Block getBlockType()
    {
        return Blocks.beacon;
    }

    @Override
    public boolean canConnect(ForgeDirection from)
    {
        return super.canConnect(from);
    }

    @Override
    public double getVoltage()
    {
        return super.getVoltage();
    }

    @Override
    public double getVoltageThreshold()
    {
        return 220;
    }
}
