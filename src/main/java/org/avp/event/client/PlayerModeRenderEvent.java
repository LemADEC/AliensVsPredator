package org.avp.event.client;

import org.avp.PlayerModeHandler;
import org.avp.entities.extended.ExtendedEntityPlayer;
import org.avp.items.ItemFirearm;
import org.avp.items.ItemFlamethrower;
import org.avp.util.PlayerMode;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.Model.RenderObject;
import com.arisux.mdxlib.lib.client.render.OpenGL;
import com.arisux.mdxlib.lib.util.Math;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerModeRenderEvent
{
    public static final PlayerModeRenderEvent instance = new PlayerModeRenderEvent();
    private RenderPlayer renderLiving = new RenderPlayer();

    private class RenderPlayer extends RendererLivingEntity
    {
        public RenderPlayer()
        {
            super(new ModelBiped(), 0F);
            this.renderManager = RenderManager.instance;
        }

        @Override
        public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
        {
            EntityLivingBase entityLiving = (EntityLivingBase) entity;
            float yawOffset = Math.interpolateRotation(entityLiving.prevRenderYawOffset, entityLiving.renderYawOffset, renderPartialTicks);
            float yawHead = Math.interpolateRotation(entityLiving.prevRotationYawHead, entityLiving.rotationYawHead, renderPartialTicks);
            float swingProgress = (entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - renderPartialTicks));
            float swingProgressPrevious = (entityLiving.prevLimbSwingAmount + (entityLiving.limbSwingAmount - entityLiving.prevLimbSwingAmount) * renderPartialTicks);
            float idleProgress = (entityLiving.ticksExisted + renderPartialTicks);
            float headRotateAngleY = (yawHead - yaw);
            float headRotationPitch = (entityLiving.prevRotationPitch + (entityLiving.rotationPitch - entityLiving.prevRotationPitch) * renderPartialTicks);
            

            OpenGL.pushMatrix();
            {
                OpenGL.rotate(-yaw, 0F, 1F, 0F);
                OpenGL.rotate(180F, 1F, 0F, 0F);
                OpenGL.disable(GL11.GL_CULL_FACE);

                PlayerModeHandler.instance().getTextureForPlayer((EntityPlayer) entityLiving).bind();
                PlayerModeHandler.instance().getModelForPlayer((EntityPlayer) entityLiving).render(new RenderObject(new Object[] { entity, swingProgress, swingProgressPrevious, idleProgress, headRotateAngleY, headRotationPitch }));
            }
            OpenGL.popMatrix();
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity entity)
        {
            return null;
        }
    };

    @SubscribeEvent
    public void renderEntityTick(RenderPlayerEvent.Pre event)
    {
        ExtendedEntityPlayer extendedPlayer = (ExtendedEntityPlayer) event.entityPlayer.getExtendedProperties(ExtendedEntityPlayer.IDENTIFIER);
        ItemStack itemstack = event.entityPlayer.inventory.getCurrentItem();

        if (itemstack != null && (itemstack.getItem() instanceof ItemFirearm || itemstack.getItem() instanceof ItemFlamethrower))
        {
            event.renderer.modelArmor.aimedBow = event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelBipedMain.aimedBow = true;
        }
        else
        {
            event.renderer.modelArmor.aimedBow = event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelBipedMain.aimedBow = false;
        }

        if (event.entity != null && extendedPlayer.getPlayerMode() != PlayerMode.NORMAL)
        {
            renderLiving.doRender(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, event.entity.rotationYaw, event.partialRenderTick);
            event.setCanceled(true);
        }
    }
}
