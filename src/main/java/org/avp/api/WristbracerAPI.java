package org.avp.api;

import java.util.HashMap;

import org.avp.AliensVsPredator;
import org.avp.packets.server.PacketSpawnNuke;

import com.arisux.mdxlib.MDX;
import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.game.Game;
import com.arisux.mdxlib.lib.game.IInitEvent;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WristbracerAPI implements IInitEvent
{
    private HashMap<String, IWristbracerAction> registeredCombos = new HashMap<String, IWristbracerAction>();
    public static final WristbracerAPI instance = new WristbracerAPI();

    public static interface IWristbracerAction
    {
        public void actionPerformed(String combonation, Object... args);
    }

    public String getComboFromDisplays(int d1, int d2, int d3, int d4, int d5, int d6)
    {
        return String.format("%s%s%s%s%s%s", String.valueOf(d1), String.valueOf(d2), String.valueOf(d3), String.valueOf(d4), String.valueOf(d5), String.valueOf(d6));
    }

    public IWristbracerAction getActionForCombo(String combonation)
    {
        return this.registeredCombos.get(combonation);
    }

    public boolean isComboValid(String combonation)
    {
        if (registeredCombos.get(combonation) != null)
        {
            return true;
        }

        return false;
    }

    public void registerCombonation(String combonation, IWristbracerAction action)
    {
        if (!isComboValid(combonation))
        {
            this.registeredCombos.put(combonation, action);
        }
        else
        {
            MDX.log().warn(String.format("[AVP/API/Wristbracer] Combonation '%s' is already registered.", combonation));
        }
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        this.registerCombonation("009000", new IWristbracerAction()
        {
            @Override
            public void actionPerformed(String combonation, Object... args)
            {
                Draw.drawString("gui.avp.wristbracer.notify.detmode", 10, 10, 0xFFFF0000);
                Draw.drawString("gui.avp.wristbracer.warn.itemslost", 10, 20, 0xFFFF0000);
            }
        });

        this.registerCombonation("009001", new IWristbracerAction()
        {
            @Override
            public void actionPerformed(String combonation, Object... args)
            {
                AliensVsPredator.network().sendToServer(new PacketSpawnNuke());
                Game.minecraft().currentScreen = null;
            }
        });
    }
}
