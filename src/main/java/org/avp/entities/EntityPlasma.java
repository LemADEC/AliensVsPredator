package org.avp.entities;

import java.util.List;

import org.avp.AliensVsPredator;

import com.arisux.mdxlib.lib.game.GameSounds;
import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.Worlds;
import com.arisux.mdxlib.lib.world.entity.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityPlasma extends EntityThrowable
{
    private boolean belongsToPlayer, released;
    private float size;
    public Entity shootingEntity;

    public EntityPlasma(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityPlasma(World world, EntityLivingBase shootingEntity)
    {
        super(world, shootingEntity);
        this.setSize(0.5F, 0.5F);
        this.shootingEntity = shootingEntity;
        this.belongsToPlayer = shootingEntity instanceof EntityPlayer;
    }

    @Override
    public void onUpdate()
    {
        if (this.released)
        {
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        }

        if (this.ticksExisted >= 20 * 20)
        {
            this.setDead();
        }

        MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY, this.posZ), Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ));

        if (!this.worldObj.isRemote)
        {
            Entity entityHit = Entities.getEntityInCoordsRange(worldObj, EntityLiving.class, new CoordData(this), 1, 1);

            if (entityHit != null)
            {
                this.onImpact(movingObjectPosition);
            }
        }

        if (movingObjectPosition != null || this.isCollidedHorizontally)
        {
            this.onImpact(movingObjectPosition);
            this.setDead();
        }

        Entity entity = this;

        for (int x = 0; x < 20; x++)
        {
            entity.worldObj.spawnParticle("enchantmenttable", entity.posX + rand.nextDouble(), entity.posY + rand.nextDouble(), entity.posZ + rand.nextDouble(), 0.0D - rand.nextDouble(), 0.0D - rand.nextDouble(), 0.0D - rand.nextDouble());
            entity.worldObj.spawnParticle("reddust", entity.posX - rand.nextDouble() + rand.nextDouble(), entity.posY - rand.nextDouble() + rand.nextDouble(), entity.posZ - rand.nextDouble() + rand.nextDouble(), 0.5D, 1D, 5.0D);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("player", this.belongsToPlayer);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.belongsToPlayer = nbt.getBoolean("player");
    }

    @Override
    public void onImpact(MovingObjectPosition movingObjectPosition)
    {
        if (!this.worldObj.isRemote)
        {
            Worlds.createExplosion(null, worldObj, new CoordData(this), 1.5F * size, false, true, AliensVsPredator.settings().areExplosionsEnabled());
            GameSounds.fxPop.playSound(this,  0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);

            @SuppressWarnings("unchecked")
            List<Entity> entitiesInRange = (List<Entity>) Entities.getEntitiesInCoordsRange(worldObj, EntityLivingBase.class, new CoordData(this.posX, this.posY, this.posZ), (int) Math.ceil(1.5F * size));

            for (Entity entity : entitiesInRange)
            {
                entity.attackEntityFrom(new DamageSource("Plasma"), 20F * size);
            }

            this.setDead();
        }
    }

    public float getPlasmaSize()
    {
        return size;
    }

    public EntityPlasma setPlasmaSize(float size)
    {
        this.size = size;
        return this;
    }

    public void increaseSize()
    {
        if (size < 2.0F)
        {
            size += 0.02F;
        }
    }

    public void release()
    {
        this.released = true;
    }
}
