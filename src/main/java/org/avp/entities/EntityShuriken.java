package org.avp.entities;

import java.util.List;

import org.avp.AliensVsPredator;
import org.avp.DamageSources;

import com.arisux.mdxlib.lib.game.GameSounds;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityShuriken extends Entity
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile = Blocks.air;
    private int inData = 0;
    private boolean inGround = false;
    public boolean doesArrowBelongToPlayer = false;
    public int arrowShake = 0;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir = 0;
    public boolean arrowCritical = false;

    public EntityShuriken(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityShuriken(World world, double posX, double posY, double posZ)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
        this.setPosition(posX, posY, posZ);
        this.yOffset = 0.0F;
    }

    public EntityShuriken(World world, EntityLivingBase entityliving, float velocity)
    {
        super(world);
        this.shootingEntity = entityliving;
        this.doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
        this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
        this.setArrowHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit()
    {
        ;
    }

    public void setArrowHeading(double motionX, double motionY, double motionZ, float velocity, float deviation)
    {
        float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= f2;
        motionY /= f2;
        motionZ /= f2;
        motionX += this.rand.nextGaussian() * 0.007499999832361937D * deviation;
        motionY += this.rand.nextGaussian() * 0.007499999832361937D * deviation;
        motionZ += this.rand.nextGaussian() * 0.007499999832361937D * deviation;
        motionX *= velocity;
        motionY *= velocity;
        motionZ *= velocity;
        this.motionX = motionX * 1.1D;
        this.motionY = motionY * 1.1D;
        this.motionZ = motionZ * 1.1D;
        float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(motionY, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    public void setVelocity(double d, double d1, double d2)
    {
        this.motionX = d;
        this.motionY = d1;
        this.motionZ = d2;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(d, d2) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(d1, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float i = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, i) * 180.0D / Math.PI);
        }

        Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

        if (block != Blocks.air)
        {
            block.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);

            if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
            {
                AxisAlignedBB vec3d = block.getSelectedBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

                if (vec3d != null && vec3d.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
                {
                    this.inGround = true;
                }
            }
        }

        if (this.arrowShake > 0)
        {
            --this.arrowShake;
        }

        if (this.inGround)
        {
            Block var18 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
            int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            if (var18 == this.inTile && var19 == this.inData)
            {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200)
                {
                    this.setDead();
                }
            }
            else
            {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.2F;
                this.motionY *= this.rand.nextFloat() * 0.2F;
                this.motionZ *= this.rand.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
        else
        {
            ++this.ticksInAir;
            Vec3 var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(var17, vec3d1, false, true, true);
            var17 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec3d1 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;
            int f3;
            float f6;

            for (f3 = 0; f3 < list.size(); ++f3)
            {
                Entity f4 = list.get(f3);

                if (f4.canBeCollidedWith() && (f4 != this.shootingEntity || this.ticksInAir >= 5))
                {
                    f6 = 0.3F;
                    AxisAlignedBB k1 = f4.boundingBox.expand(f6, f6, f6);
                    MovingObjectPosition f7 = k1.calculateIntercept(var17, vec3d1);

                    if (f7 != null)
                    {
                        double d1 = var17.distanceTo(f7.hitVec);

                        if (d1 < d || d == 0.0D)
                        {
                            entity = f4;
                            d = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            float velocity;

            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    int var23 = (int) Math.ceil(velocity * 2.0D);

                    if (this.arrowCritical)
                    {
                        var23 += this.rand.nextInt(var23 / 2 + 2);
                    }

                    DamageSource damagesource = null;

                    if (this.shootingEntity == null)
                    {
                        damagesource = DamageSources.causeShurikenDamage(this);
                    }
                    else
                    {
                        damagesource = DamageSources.causeShurikenDamage(this.shootingEntity);
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(damagesource, var23))
                    {
                        if (movingobjectposition.entityHit instanceof EntityLivingBase)
                        {
                            ++((EntityLivingBase) movingobjectposition.entityHit).arrowHitTimer;
                        }

                        GameSounds.fxBowHit.playSound(this, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    }
                    else
                    {
                        this.motionX *= -0.10000000149011612D;
                        this.motionY *= -0.10000000149011612D;
                        this.motionZ *= -0.10000000149011612D;
                        this.rotationYaw += 180.0F;
                        this.prevRotationYaw += 180.0F;
                        this.ticksInAir = 0;
                    }
                }
                else
                {
                    this.xTile = movingobjectposition.blockX;
                    this.yTile = movingobjectposition.blockY;
                    this.zTile = movingobjectposition.blockZ;
                    this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
                    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                    this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.posX));
                    this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.posY));
                    this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
                    velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / velocity * 0.05000000074505806D;
                    this.posY -= this.motionY / velocity * 0.05000000074505806D;
                    this.posZ -= this.motionZ / velocity * 0.05000000074505806D;
                    GameSounds.fxBowHit.playSound(this, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.arrowCritical = false;
                }
            }

            if (this.arrowCritical)
            {
                for (f3 = 0; f3 < 4; ++f3)
                {
                    this.worldObj.spawnParticle("crit", this.posX + this.motionX * f3 / 4.0D, this.posY + this.motionY * f3 / 4.0D, this.posZ + this.motionZ * f3 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

            for (this.rotationPitch = (float) (Math.atan2(this.motionY, velocity) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var22 = 0.99F;
            f6 = 0.05F;

            if (this.isInWater())
            {
                for (int var24 = 0; var24 < 4; ++var24)
                {
                    float var25 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * var25, this.posY - this.motionY * var25, this.posZ - this.motionZ * var25, this.motionX, this.motionY, this.motionZ);
                }

                var22 = 0.8F;
            }

            this.motionX *= var22;
            this.motionY *= var22;
            this.motionZ *= var22;
            this.motionY -= f6;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short) this.xTile);
        nbttagcompound.setShort("yTile", (short) this.yTile);
        nbttagcompound.setShort("zTile", (short) this.zTile);
        nbttagcompound.setByte("inData", (byte) this.inData);
        nbttagcompound.setByte("shake", (byte) this.arrowShake);
        nbttagcompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        nbttagcompound.setBoolean("player", this.doesArrowBelongToPlayer);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.xTile = nbttagcompound.getShort("xTile");
        this.yTile = nbttagcompound.getShort("yTile");
        this.zTile = nbttagcompound.getShort("zTile");
        this.inData = nbttagcompound.getByte("inData") & 255;
        this.arrowShake = nbttagcompound.getByte("shake") & 255;
        this.inGround = nbttagcompound.getByte("inGround") == 1;
        this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if (!this.worldObj.isRemote)
        {
            if (this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0)
            {
                if (entityplayer.inventory.addItemStackToInventory(new ItemStack(AliensVsPredator.items().itemShuriken, 1)))
                {
                    GameSounds.fxPop.playSound(this, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    entityplayer.onItemPickup(this, 1);
                    this.setDead();
                }
            }
        }
    }

    @Override
    public float getShadowSize()
    {
        return 1.0F;
    }
}
