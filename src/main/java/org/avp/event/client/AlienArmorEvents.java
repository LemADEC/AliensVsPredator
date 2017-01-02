package org.avp.event.client;

import org.avp.AliensVsPredator;

import com.arisux.mdxlib.lib.game.Game;
import com.arisux.mdxlib.lib.world.entity.player.inventory.Inventories;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class AlienArmorEvents
{
    public static final AlienArmorEvents instance = new AlienArmorEvents();
    private Minecraft mc = Game.minecraft();

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event)
    {
        if (mc.thePlayer != null)
        {
            if (Inventories.getLegsSlotItemStack(mc.thePlayer) != null)
            {
                if (Inventories.getLegsSlotItemStack(mc.thePlayer).getItem() == AliensVsPredator.items().legsXeno && mc.gameSettings.keyBindForward.isPressed() && AliensVsPredator.keybinds().KEYBIND_XENO_ARMOR_CLIMB.isPressed() && mc.thePlayer.isCollidedHorizontally)
                {
                    mc.thePlayer.motionY = 0.3D;
                    mc.thePlayer.setAIMoveSpeed(5.5F);
                    mc.thePlayer.fallDistance = -0.5F;
                }
            }
        }
    }
}
