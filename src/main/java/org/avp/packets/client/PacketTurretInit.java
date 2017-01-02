package org.avp.packets.client;

import org.avp.entities.tile.TileEntityTurret;

import com.arisux.mdxlib.lib.game.Game;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

public class PacketTurretInit implements IMessage, IMessageHandler<PacketTurretInit, PacketTurretInit>
{
    public int x, y, z;
    public String idListString;

    public PacketTurretInit()
    {
        ;
    }

    public PacketTurretInit(int x, int y, int z, String idListString)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idListString = idListString;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.idListString = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeUTF8String(buf, this.idListString);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PacketTurretInit onMessage(PacketTurretInit packet, MessageContext ctx)
    {
        TileEntityTurret tile = (TileEntityTurret) Game.minecraft().theWorld.getTileEntity(packet.x, packet.y, packet.z);

        if (tile != null)
        {
            tile.applyUpgrades();

            String[] ids = packet.idListString.split("-");

            for (String id : ids)
            {
                if (!id.equals(""))
                {
                    tile.setDangerous((Class<? extends Entity>) EntityList.stringToClassMapping.get(String.valueOf(id)));
                }
            }
        }
        return null;
    }
}
