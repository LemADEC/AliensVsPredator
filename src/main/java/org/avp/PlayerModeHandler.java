package org.avp;

import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.util.LevelData;
import org.avp.util.PlayerMode;

import com.arisux.mdxlib.lib.client.Model;
import com.arisux.mdxlib.lib.client.TexturedModel;
import com.arisux.mdxlib.lib.client.render.Texture;
import com.arisux.mdxlib.lib.game.IInitEvent;
import com.arisux.mdxlib.lib.world.entity.player.Players;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

public class PlayerModeHandler implements IInitEvent
{
    public static final PlayerModeHandler instance = new PlayerModeHandler();

    @Override
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            this.assignLevelModelMaps();
        }
    }

    @SideOnly(Side.CLIENT)
    public void assignLevelModelMaps()
    {
        ModelBiped modelBiped = new ModelBiped();
        modelBiped.isChild = false;

        PlayerMode.NORMAL.getLevelMappingForLevel(0).setModelTexMap(AliensVsPredator.resources().models().BIPED);
        PlayerMode.MARINE.getLevelMappingForLevel(0).setModelTexMap(AliensVsPredator.resources().models().MARINE);
        PlayerMode.PREDATOR.getLevelMappingForLevel(0).setModelTexMap(AliensVsPredator.resources().models().YAUTJA);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(0).setModelTexMap(AliensVsPredator.resources().models().OVAMORPH);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(1).setModelTexMap(AliensVsPredator.resources().models().FACEHUGGER);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(10).setModelTexMap(AliensVsPredator.resources().models().CHESTBUSTER);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(20).setModelTexMap(AliensVsPredator.resources().models().DRONE_ADVANCED_BLOOD);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(45).setModelTexMap(AliensVsPredator.resources().models().WARRIOR_BLOOD);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(65).setModelTexMap(AliensVsPredator.resources().models().PRAETORIAN);
        PlayerMode.XENOMORPH.getLevelMappingForLevel(90).setModelTexMap(AliensVsPredator.resources().models().XENOQUEEN);
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            ExtendedEntityPlayer playerExtension = (ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);

            playerExtension.setPlayerMode(PlayerMode.NORMAL);
            {
                // Query the player to choose their PlayerMode type.
            }
        }
    }

    @SubscribeEvent
    public void onPlayerPickupXP(PlayerPickupXpEvent event)
    {
        EntityPlayer player = event.entityPlayer;

        if (Players.getXPCurrentLevel(player) + event.orb.getXpValue() >= Players.getXPCurrentLevelMax(player))
        {
            this.onPlayerLevelUp(player, (int) Players.getXPLevel(player) + 1);
        }
    }

    public void onPlayerLevelUp(EntityPlayer player, int newLevel)
    {
        ;
    }

    public static final PlayerModeHandler instance()
    {
        return AliensVsPredator.playermodehandler();
    }

    public static boolean isPlayerInMode(EntityPlayer player, PlayerMode playerMode)
    {
        return ((ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getPlayerMode() == playerMode;
    }

    public static boolean isNormal(EntityPlayer player)
    {
        return isPlayerInMode(player, PlayerMode.NORMAL);
    }

    public static boolean isMarine(EntityPlayer player)
    {
        return isPlayerInMode(player, PlayerMode.MARINE);
    }

    public static boolean isPredator(EntityPlayer player)
    {
        return isPlayerInMode(player, PlayerMode.PREDATOR);
    }

    public static boolean isXenomorph(EntityPlayer player)
    {
        return isPlayerInMode(player, PlayerMode.XENOMORPH);
    }

    public PlayerMode getPlayerModeForPlayer(EntityPlayer player)
    {
        return ((ExtendedEntityPlayer) player.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER)).getPlayerMode();
    }

    public LevelData getLevelMappingForPlayer(EntityPlayer player)
    {
        return getPlayerModeForPlayer(player).getLevelMappingForLevel((int) Players.getXPLevel(player));
    }

    @SideOnly(Side.CLIENT)
    public TexturedModel<? extends Model> getModelTexMapForPlayer(EntityPlayer player)
    {
        return getLevelMappingForPlayer(player).getModelTexMap();
    }

    @SideOnly(Side.CLIENT)
    public Model getModelForPlayer(EntityPlayer player)
    {
        return getModelTexMapForPlayer(player).getModel();
    }

    @SideOnly(Side.CLIENT)
    public Texture getTextureForPlayer(EntityPlayer player)
    {
        return getModelTexMapForPlayer(player).getTexture();
    }
}
