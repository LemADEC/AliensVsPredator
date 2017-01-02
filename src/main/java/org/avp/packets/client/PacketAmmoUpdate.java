package org.avp.packets.client;

import org.avp.items.ItemFirearm;

import com.arisux.mdxlib.lib.game.Game;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketAmmoUpdate implements IMessage, IMessageHandler<PacketAmmoUpdate, PacketAmmoUpdate>
{
    public int ammo;

    public PacketAmmoUpdate()
    {
        ;
    }

    public PacketAmmoUpdate(int ammo)
    {
        this.ammo = ammo;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.ammo = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(ammo);
    }

    @Override
    public PacketAmmoUpdate onMessage(PacketAmmoUpdate packet, MessageContext ctx)
    {
        ((ItemFirearm) Game.minecraft().thePlayer.inventory.getCurrentItem().getItem()).setAmmoCount(packet.ammo);
        return null;
    }
}
