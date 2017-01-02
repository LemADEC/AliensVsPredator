package org.avp.entities;

import java.util.List;

import org.avp.AliensVsPredator;
import org.avp.entities.mob.EntityXenomorph;

import com.arisux.mdxlib.lib.world.CoordData;
import com.arisux.mdxlib.lib.world.Worlds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable
{
    public double velocity = 0.9800000190734863D;
    public double velocityGround = 0.699999988079071D;
    public boolean explodeOnImpact;
    private int fuse;

    public EntityGrenade(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
        this.yOffset = this.height / 2.0F;
        this.fuse = 0;
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
    }

    public void setFlaming(boolean value)
    {
        if (value)
            this.dataWatcher.updateObject(17, 1);
        else
            this.dataWatcher.updateObject(17, 0);
    }

    public boolean isFlaming()
    {
        if (this.dataWatcher.getWatchableObjectInt(17) == 1)
            return true;
        else
            return false;
    }

    public EntityGrenade(World world, EntityLivingBase shooter)
    {
        super(world, shooter);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.02999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= velocity;
        this.motionY *= velocity;
        this.motionZ *= velocity;

        if (this.onGround)
        {
            this.motionX *= velocityGround;
            this.motionZ *= velocityGround;
            this.motionY *= -0.5D;
        }

        if (this.onGround && this.explodeOnImpact)
        {
            this.explode();
        }

        if (this.fuse++ >= 50)
        {
            this.explode();
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @SuppressWarnings("unchecked")
    public void explode()
    {
        if (!this.worldObj.isRemote)
        {
            Explosion explosion = Worlds.createExplosion(null, worldObj, new CoordData(this), 2F, isFlaming(), true, AliensVsPredator.settings().areExplosionsEnabled());

            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(4, 4, 4));

            for (int i1 = 0; i1 < list.size(); ++i1)
            {
                Entity entity = list.get(i1);
                float targetDamage = entity instanceof EntityXenomorph ? 8F * 2 : 8F;
                entity.attackEntityFrom(DamageSource.setExplosionSource(explosion), targetDamage);
            }

        }

        this.setDead();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setByte("Fuse", (byte) this.fuse);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        this.fuse = tag.getByte("Fuse");
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition)
    {
        ;
    }
}
