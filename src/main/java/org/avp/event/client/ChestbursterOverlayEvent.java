package org.avp.event.client;

import org.avp.AliensVsPredator;
import org.avp.entities.extended.ExtendedEntityLivingBase;

import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.game.Game;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class ChestbursterOverlayEvent
{
    public static final ChestbursterOverlayEvent instance = new ChestbursterOverlayEvent();

    @SubscribeEvent
    public void renderTickOverlay(Pre event)
    {
        if (Game.minecraft().thePlayer != null)
        {
            if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
            {
                ExtendedEntityLivingBase livingBaseProperties = (ExtendedEntityLivingBase) Game.minecraft().thePlayer.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

                if (livingBaseProperties.doesEntityContainEmbryo())
                {
                    if (livingBaseProperties.doesEntityContainEmbryo() && Game.minecraft().thePlayer.isDead && livingBaseProperties.getEmbryo().getTicksExisted() >= livingBaseProperties.getEmbryo().getGestationPeriod() - 80)
                    {
                        Draw.drawOverlay(AliensVsPredator.resources().BLUR_CHESTBURSTER_EMERGE, 1F, 0F, 0F, 1F);
                    }
                }
            }
        }
    }
}
