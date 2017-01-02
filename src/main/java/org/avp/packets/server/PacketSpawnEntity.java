package org.avp.packets.server;

import com.arisux.mdxlib.lib.world.entity.Entities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class PacketSpawnEntity implements IMessage, IMessageHandler<PacketSpawnEntity, PacketSpawnEntity>
{
    public double x, y, z;
    public String entityId;

    public PacketSpawnEntity()
    {
        ;
    }

    public PacketSpawnEntity(double x, double y, double z, String entityId)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityId = entityId.trim();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        ByteBufUtils.writeUTF8String(buffer, entityId);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
        this.entityId = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public PacketSpawnEntity onMessage(PacketSpawnEntity message, MessageContext ctx)
    {
        if (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.worldObj != null)
        {
            Entity entity = Entities.constructEntity(ctx.getServerHandler().playerEntity.worldObj, Entities.getRegisteredEntityClass(message.entityId));

            if (entity != null)
            {
                entity.setLocationAndAngles(message.x, message.y, message.z, 0.0F, 0.0F);
                ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(entity);
            }
        }

        return null;
    }
}
