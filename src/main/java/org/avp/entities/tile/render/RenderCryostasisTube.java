package org.avp.entities.tile.render;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

import java.util.ArrayList;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.util.EntityRenderTransforms;
import org.lwjgl.opengl.GL12;

import com.arisux.mdxlib.lib.client.TexturedModel;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class RenderCryostasisTube extends TileEntitySpecialRenderer
{
    public static ArrayList<CryostasisEntityRenderer> renderers = new ArrayList<CryostasisEntityRenderer>();
    private static final CryostasisEntityRenderer     renderer  = new CryostasisEntityRenderer()
                                                                {
                                                                    public boolean isApplicable(Entity entity)
                                                                    {
                                                                        return true;
                                                                    };
                                                                };

    public static abstract class CryostasisEntityRenderer extends EntityRenderTransforms
    {
        public CryostasisEntityRenderer(Class<?>... entities)
        {
            super(entities);
        }

        @Override
        public void pre(Entity entity, float partialTicks)
        {
            ;
        }

        @Override
        public void post(Entity entity, float partialTicks)
        {
            ;
        }

        public void renderChassis(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            OpenGL.enable(GL_BLEND);
            OpenGL.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            OpenGL.translate(posX + 0.5F, posY + 1.125F, posZ + 0.5F);
            OpenGL.rotate(tile);
            OpenGL.enable(GL12.GL_RESCALE_NORMAL);
            OpenGL.scale(0.75F, -0.75F, 0.75F);
            OpenGL.enable(GL_ALPHA_TEST);
            OpenGL.disableCullFace();
            AliensVsPredator.resources().models().CRYOSTASIS_TUBE.draw(tile);
            OpenGL.enableCullFace();
        }

        public void renderTube(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            TexturedModel<?> mask = null;

            if (tile.isShattered())
            {
                mask = AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK_SHATTERED;
            }
            else if (tile.isCracked())
            {
                mask = AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK_CRACKED;
            }
            else
            {
                mask = AliensVsPredator.resources().models().CRYOSTASIS_TUBE_MASK;
            }

            if (tile.getVoltage() > 0)
            {
                OpenGL.disableLightMapping();
                OpenGL.disableLight();
            }

            OpenGL.enableCullFace();
            mask.draw();
            OpenGL.enableLightMapping();
            OpenGL.enableLight();
            OpenGL.enableDepthTest();
        }

        public void renderEntity(RenderCryostasisTube renderer, TileEntityCryostasisTube tile, double posX, double posY, double posZ)
        {
            if (tile.stasisEntity != null)
            {
                OpenGL.pushMatrix();

                if (tile.getVoltage() > 0)
                {
                    OpenGL.disableLight();
                }

                RenderManager.instance.renderEntityWithPosYaw(tile.stasisEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                OpenGL.popMatrix();
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float partialTicks)
    {
        TileEntityCryostasisTube tile = (TileEntityCryostasisTube) tileEntity;

        OpenGL.pushMatrix();
        {
            CryostasisEntityRenderer cachedRenderer = null;

            if (tile != null && tile.stasisEntity != null)
            {
                for (CryostasisEntityRenderer renderer : renderers)
                {
                    if (renderer.isApplicable(tile.stasisEntity))
                    {
                        cachedRenderer = renderer;
                        break;
                    }
                }
            }

            if (cachedRenderer == null)
            {
                cachedRenderer = renderer;
            }

            if (cachedRenderer != null)
            {
                cachedRenderer.renderChassis(this, tile, posX, posY, posZ);
                cachedRenderer.renderEntity(this, tile, posX, posY, posZ);
                cachedRenderer.renderTube(this, tile, posX, posY, posZ);
            }

            OpenGL.disableBlend();
            OpenGL.enableLight();
            OpenGL.enableLightMapping();
        }
        OpenGL.popMatrix();
    }
}
