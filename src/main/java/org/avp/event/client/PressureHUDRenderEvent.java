package org.avp.event.client;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_CONSTANT_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glDepthMask;

import org.avp.AliensVsPredator;
import org.avp.dimension.varda.ProviderVarda;
import org.avp.entities.extended.ExtendedEntityLivingBase;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.EntitySpeciesAlien;
import org.avp.entities.tile.TileEntityPowercell;
import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.util.IPowerAcceptor;

import com.arisux.amdxlib.lib.client.render.Draw;
import com.arisux.amdxlib.lib.client.render.OpenGL;
import com.arisux.amdxlib.lib.client.render.ScaledResolution;
import com.arisux.amdxlib.lib.client.render.Screen;
import com.arisux.amdxlib.lib.game.Game;
import com.arisux.amdxlib.lib.world.CoordData;
import com.arisux.amdxlib.lib.world.Worlds;
import com.arisux.amdxlib.lib.world.block.BlockSide;
import com.arisux.amdxlib.lib.world.block.Blocks;
import com.arisux.amdxlib.lib.world.entity.player.inventory.Inventories;

import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;

public class PressureHUDRenderEvent
{
    public static final PressureHUDRenderEvent instance = new PressureHUDRenderEvent();
    private boolean gammaRestored = true;

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent event)
    {
        ;
    }

    @SubscribeEvent
    public void renderTickOverlay(RenderGameOverlayEvent.Pre event)
    {
        if (Game.minecraft().thePlayer != null)
        {
            if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
            {
                if (Inventories.getHelmSlotItemStack(Game.minecraft().thePlayer) != null && Game.minecraft().gameSettings.thirdPersonView == 0 && Inventories.getHelmSlotItemStack(Game.minecraft().thePlayer).getItem() == AliensVsPredator.items().pressureMask)
                {
                    ExtendedEntityPlayer playerProperties = ExtendedEntityPlayer.get(Game.minecraft().thePlayer);

                    this.gammaRestored = false;
                    LightmapUpdateEvent.instance.gammaValue = playerProperties.isNightvisionEnabled() ? 8F : 0F;
                    OpenGL.disableLight();
                    OpenGL.disableLightMapping();

                    OpenGL.enable(GL_BLEND);
                    OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                    OpenGL.disable(GL_DEPTH_TEST);
                    glDepthMask(false);
                    OpenGL.color(1F, 1F, 1F, 1F);
                    OpenGL.disable(GL_ALPHA_TEST);
                    // RenderUtil.bindTexture(AliensVsPredator.resources().BLUR_TACTICAL_HUD);
                    // RenderUtil.drawQuad(0, 0, RenderUtil.scaledDisplayResolution().getScaledWidth(), RenderUtil.scaledDisplayResolution().getScaledHeight());
                    glDepthMask(true);
                    OpenGL.enable(GL_DEPTH_TEST);
                    OpenGL.enable(GL_ALPHA_TEST);
                    OpenGL.color(1.0F, 1.0F, 1.0F, 1.0F);
                    OpenGL.disable(GL_BLEND);

                    this.drawInfoBar();
                    this.drawImpregnationIndicator(getProperties());
                }
                else if (!gammaRestored)
                {
                    this.gammaRestored = true;
                    LightmapUpdateEvent.instance.gammaValue = 0F;
                }
            }
        }
    }

    @SubscribeEvent
    public void renderTick(RenderTickEvent event)
    {
        if (Game.minecraft().thePlayer != null)
        {
            this.renderInventoryElements();
        }
    }

    public void renderInventoryElements()
    {
        if (Inventories.getHelmSlotItemStack(Game.minecraft().thePlayer) != null && Inventories.getHelmSlotItemStack(Game.minecraft().thePlayer).getItem() == AliensVsPredator.items().pressureMask)
        {
            ;
        }
    }

    public ExtendedEntityPlayer getProperties()
    {
        return Game.minecraft() != null ? Game.minecraft().thePlayer != null ? ExtendedEntityPlayer.get(Game.minecraft().thePlayer) : null : null;
    }

    public void drawInfoBar()
    {
        ScaledResolution res = Screen.scaledDisplayResolution();
        int guiScale = Game.minecraft().gameSettings.guiScale;
        float scale = guiScale == 0 ? res.getScaleFactor() * 0.25F : (guiScale == 1 ? res.getScaleFactor() * 1F : res.getScaleFactor() * 0.5F);
        int barPadding = 40;

        int hourOfMinecraftDay = (int) (Math.floor(Game.minecraft().thePlayer.worldObj.getWorldTime() / 1000 + 8) % 24);
        int minuteOfMinecraftDay = (int) (60 * Math.floor(Game.minecraft().thePlayer.worldObj.getWorldTime() % 1000) / 1000);

        String timeString = String.format("%02dH%02dM", hourOfMinecraftDay, minuteOfMinecraftDay);
        String fpsString = Game.minecraft().debug.substring(0, Game.minecraft().debug.indexOf(" fps")) + " FPS";
        String barString = timeString + " [" + fpsString + "]";

        OpenGL.pushMatrix();
        {
            FontRenderer fontrenderer = Game.minecraft().fontRendererObj;
            OpenGL.scale(scale, scale, scale);
            OpenGL.enable(GL_BLEND);
            OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
            Draw.drawString(barString, barPadding, 25, 0xFF666666, false);
            OpenGL.pushMatrix();
            {
                float nameScale = 1.5F;
                OpenGL.scale(nameScale, nameScale, nameScale);
                Draw.drawString("[" + 100 + "%%] " + Game.minecraft().thePlayer.getCommandSenderName(), (int) ((barPadding) / nameScale), (int) (10 / nameScale), 0xFFFFAA00, false);
            }
            OpenGL.popMatrix();
            OpenGL.color4i(0xFFFFFFFF);

            Draw.drawPlayerFace(Game.minecraft().thePlayer.getCommandSenderName(), 0, 0, 32, 32);

            /** Silica storm detection indicator **/
            WorldProvider provider = Game.minecraft().theWorld.provider;

            if (provider instanceof ProviderVarda)
            {
                ProviderVarda providerVarda = (ProviderVarda) provider;
                long stormStartTime = providerVarda.getStormHandler().getStormStartTime() * 1000L;
                long stormEndTime = providerVarda.getStormHandler().getStormEndTime() * 1000L;
                long worldTime = providerVarda.getWorldTime();
                int warningTime = 1000;
                int timeUntilStorm = (int) (stormStartTime - provider.getWorldTime());

                if ((timeUntilStorm < warningTime && worldTime < stormStartTime || worldTime > stormStartTime && worldTime % 20 <= 10) && worldTime <= stormEndTime + 1000)
                {
                    OpenGL.enableBlend();
                    OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                    int indicatorWidth = 300;
                    int indicatorHeight = 30;
                    int indicatorX = (Screen.scaledDisplayResolution().getScaledWidth() / 2) - (indicatorWidth / 2);
                    int indicatorY = 0;
                    Draw.drawRect(indicatorX, indicatorY, indicatorWidth, indicatorHeight, 0x66333333);
                    OpenGL.pushMatrix();
                    {
                        float nameScale = 1.5F;
                        OpenGL.scale(nameScale, nameScale, nameScale);

                        int actualX = (int) ((indicatorX) / nameScale);
                        int actualY = (int) ((indicatorY) / nameScale);
                        int actualWidth = (int) (indicatorWidth / nameScale);
                        int actualHeight = (int) (indicatorHeight / nameScale);
                        fontrenderer.drawString("Storm Indicator for " + provider.getDimensionName(), actualX + 7, actualY + 7, 0xFFAA00);

                        if (Worlds.canSeeSky(new CoordData(Game.minecraft().thePlayer), Game.minecraft().theWorld))
                        {
                            Draw.drawStringAlignCenter("You are outdoors, take cover immediately!", (int) ((Screen.scaledDisplayResolution().getScaledWidth() / 2) / nameScale), actualY + 35, 0xFF0000);
                        }

                        if (worldTime > stormStartTime)
                        {
                            Draw.drawProgressBar("Storm Inbound", (int) stormStartTime, 0, actualX, actualY + 20, actualWidth, 4, 2, 0xFFFFAA00, false);
                        }
                        else
                        {
                            Draw.drawProgressBar("Time Until Storm (" + (timeUntilStorm / 20) + " seconds)", (int) stormStartTime, ((int) stormStartTime - (int) worldTime), actualX, actualY + 20, actualWidth, 4, 2, (timeUntilStorm / 20) < 15 ? 0xFFFF0000 : 0xFFFFAA00, false);
                        }
                    }
                    OpenGL.popMatrix();
                    OpenGL.color4i(0xFFFFFFFF);
                }
            }

            if (Game.minecraft().thePlayer != null)
            {
                if (Game.minecraft().objectMouseOver != null)
                {
                    /** GUI Drawing Information **/
                    int subMenuX = (int) (Screen.scaledDisplayResolution().getScaledWidth() - (200 * scale));
                    int subMenuY = 0;
                    int subMenuPadding = 10;
                    int subMenuStartY = subMenuY + subMenuPadding / 2;
                    int subEntrySpacing = 10;
                    int curEntry = 0;

                    if (Game.minecraft().objectMouseOver.typeOfHit == MovingObjectType.ENTITY)
                    {
                        Entity entity = Game.minecraft().objectMouseOver.entityHit;

                        if (entity != null)
                        {
                            subMenuStartY = 5 + subMenuStartY;

                            OpenGL.pushMatrix();
                            {
                                float nameScale = 1.5F;
                                OpenGL.scale(nameScale, nameScale, nameScale);
                                fontrenderer.drawString("" + entity.getCommandSenderName(), (int) ((subMenuX + subMenuPadding) / nameScale), (int) ((subMenuStartY + (curEntry++ * subEntrySpacing)) / nameScale), 0xFFAA00);
                            }
                            OpenGL.popMatrix();

                            if (entity instanceof EntityLivingBase)
                            {
                                Draw.drawProgressBar((int) ((EntityLivingBase) entity).getHealth() + "/" + (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getMaxHealth(), (int) ((EntityLivingBase) entity).getHealth(), Screen.scaledDisplayResolution().getScaledWidth() - 190, 25, 180, 1, 0, 0xFF00AAFF, false);
                            }
                            else
                            {
                                Draw.drawProgressBar("NULL / NULL", 1, 1, 10, 7, Screen.scaledDisplayResolution().getScaledWidth() - 20, 1, 0, 0xFF777777, false);
                            }
                            OpenGL.enableBlend();
                            OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);

                            subMenuStartY = subMenuStartY + 20;

                            fontrenderer.drawString("Entity Type: " + entity.getClass().getSuperclass().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            fontrenderer.drawString("Eating: " + entity.isEating(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                            if (entity instanceof EntityLiving)
                            {

                                if (((EntityLiving) entity).getAttackTarget() != null)
                                {
                                    fontrenderer.drawString("AttackTarget: " + ((EntityLiving) entity).getAttackTarget().getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                    fontrenderer.drawString("Distance to Target: " + (((EntityLiving) entity).getAttackTarget() != null ? entity.getDistanceToEntity(((EntityLiving) entity).getAttackTarget()) : 0), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                }

                                fontrenderer.drawString("LastAttacked: " + ((EntityLiving) entity).getLastAttackerTime(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                                if (((EntityLiving) entity).getLastAttacker() != null)
                                {
                                    fontrenderer.drawString("LastAttacker: " + ((EntityLiving) entity).getLastAttacker().getCommandSenderName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                }

                                fontrenderer.drawString("Armor: " + ((EntityLiving) entity).getTotalArmorValue(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                fontrenderer.drawString("FireImmunity: " + ((EntityLiving) entity).isImmuneToFire(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityLivingBase)
                            {
                                EntityLivingBase entityLiving = (EntityLivingBase) entity;
                                ExtendedEntityLivingBase extendedLiving = (ExtendedEntityLivingBase) entityLiving.getExtendedProperties(ExtendedEntityLivingBase.IDENTIFIER);

                                fontrenderer.drawString("Age: " + entityLiving.getAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                                if (!(entity instanceof EntitySpeciesAlien) && extendedLiving.getEmbryo() != null)
                                    fontrenderer.drawString("Parasite Type: " + extendedLiving.getEmbryo().getType().getResult().getSimpleName(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntitySpeciesAlien)
                            {
                                fontrenderer.drawString("Jelly Level: " + ((EntitySpeciesAlien) entity).getJellyLevel(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityChestburster)
                            {
                                fontrenderer.drawString("Parasite Age: " + ((EntityChestburster) entity).ticksExisted + "/" + ((EntityChestburster) entity).getMaxParasiteAge(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            if (entity instanceof EntityMarine)
                            {
                                fontrenderer.drawString("Type: " + ((EntityMarine) entity).getMarineType(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                                fontrenderer.drawString("IsFiring: " + ((EntityMarine) entity).isFiring(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                            }

                            int curHeight = 20 + 12 * curEntry;

                            if (entity instanceof Entity)
                            {
                                OpenGL.disableBlend();
                                OpenGL.color(1F, 1F, 1F);
                                Draw.drawEntity(Screen.scaledDisplayResolution().getScaledWidth() - 100, curHeight + 140, 30, -45, 0, entity);
                                OpenGL.enableBlend();
                                OpenGL.disableLight();
                                OpenGL.color(1F, 1F, 1F);
                            }

                            curHeight = curHeight + 150;

                            OpenGL.enableBlend();
                            OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                            Draw.drawRect(Screen.scaledDisplayResolution().getScaledWidth() - 200, 0, 200, curHeight, 0x66333333);
                            OpenGL.blendClear();
                            OpenGL.disableBlend();
                        }
                    }

                    if (Game.minecraft().objectMouseOver.typeOfHit == MovingObjectType.BLOCK)
                    {
                        /** Block Information **/
                        CoordData coord = new CoordData(Game.minecraft().objectMouseOver.blockX, Game.minecraft().objectMouseOver.blockY, Game.minecraft().objectMouseOver.blockZ);
                        Block block = Game.minecraft().theWorld.getBlock((int) coord.x(), (int) coord.y(), (int) coord.z());
                        BlockSide side = BlockSide.getSide(Game.minecraft().objectMouseOver.sideHit);
                        TileEntity tile = coord.getTileEntity(Game.minecraft().thePlayer.worldObj);

                        Draw.drawBlockSide(block, side.getId(), subMenuX + subMenuPadding - 56, 0, 48, 48);

                        OpenGL.pushMatrix();
                        {
                            float nameScale = 1.5F;
                            OpenGL.scale(nameScale, nameScale, nameScale);
                            fontrenderer.drawString("" + block.getLocalizedName(), (int) ((subMenuX + subMenuPadding) / nameScale), (int) ((subMenuStartY + (curEntry++ * subEntrySpacing)) / nameScale), 0xFFAA00);
                        }
                        OpenGL.popMatrix();

                        subMenuStartY = subMenuStartY + 10;

                        String blockDomain = Blocks.getDomain(block);
                        ModContainer modContainer = Game.getModContainer(blockDomain.replace(":", ""));
                        String mod = modContainer != null ? modContainer.getName() : "???";
                        fontrenderer.drawString("From " + mod, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);
                        fontrenderer.drawString("Looking at " + ForgeDirection.getOrientation(side.getId()) + " face", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0x666666);

                        if (tile instanceof TileEntity)
                        {
                            fontrenderer.drawString("Tile Entity: " + true, subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof IPowerAcceptor)
                        {
                            IPowerAcceptor poweredTile = (IPowerAcceptor) tile;
                            fontrenderer.drawString("Voltage: " + ((float) poweredTile.getVoltage(ForgeDirection.SOUTH)) + "V", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof TileEntityPowercell)
                        {
                            TileEntityPowercell powercell = (TileEntityPowercell) tile;
                            double percent = (powercell.getAmpereHours() * 100) / powercell.getMaxEnergyStored();
                            fontrenderer.drawString("Charge: " + percent + "% (" + powercell.getAmpereHours() + "/" + powercell.getMaxEnergyStored() + ")", subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        if (tile instanceof TileEntityStasisMechanism)
                        {
                            TileEntityStasisMechanism stasisMechanism = (TileEntityStasisMechanism) tile;
                            fontrenderer.drawString("Stasis Entity: " + stasisMechanism.getStasisEntity(), subMenuX + subMenuPadding, subMenuStartY + (curEntry++ * subEntrySpacing), 0xFFAA00);
                        }

                        int curHeight = 20 + 12 * curEntry;

                        OpenGL.enableBlend();
                        OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                        Draw.drawRect(Screen.scaledDisplayResolution().getScaledWidth() - 246, 0, 246, curHeight, 0x66333333);
                        OpenGL.blendClear();
                        OpenGL.disableBlend();

                    }

                    OpenGL.disableBlend();
                }
            }
        }
        OpenGL.popMatrix();
    }

    public void drawImpregnationIndicator(ExtendedEntityPlayer playerProperties)
    {
        if (playerProperties != null)
        {
            ExtendedEntityLivingBase livingProperties = ExtendedEntityLivingBase.get(playerProperties.getPlayer());

            if (livingProperties.doesEntityContainEmbryo() && livingProperties.getEntityLivingBase().worldObj.getWorldTime() % 20 <= 10)
            {
                ScaledResolution res = Screen.scaledDisplayResolution();
                int lifeTimeTicks = livingProperties.getEmbryo().getGestationPeriod() - livingProperties.getEmbryo().getTicksExisted();
                int lifeTimeSeconds = lifeTimeTicks / 20;
                int iconSize = 64;

                OpenGL.enable(GL_BLEND);
                OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_CONSTANT_ALPHA);
                OpenGL.pushMatrix();
                {
                    float scale = 1.5F;
                    OpenGL.scale(scale, scale, scale);
                    Draw.drawStringAlignRight("Analysis Complete:", (int) ((res.getScaledWidth() / scale) - (iconSize / scale)), (int) (10 / scale), 0xFFFFAA00);
                }
                OpenGL.popMatrix();
                Draw.drawStringAlignRight("1 Foreign Organism(s) Detected", res.getScaledWidth() - iconSize, 25, 0x666666);
                Draw.drawStringAlignRight("Please do NOT terminate organism.", res.getScaledWidth() - iconSize, 35, 0x666666);

                if (!playerProperties.getPlayer().capabilities.isCreativeMode)
                {
                    Draw.drawStringAlignRight(lifeTimeSeconds / 60 + " Minute(s) Estimated Until Death", res.getScaledWidth() - iconSize, 45, 0xFFFFAA00);
                }

                OpenGL.color4i(0xFFFFAA00);
                AliensVsPredator.resources().INFECTION_INDICATOR.bind();
                Draw.drawQuad(res.getScaledWidth() - iconSize, 0, iconSize, iconSize);
            }
        }
    }
}
