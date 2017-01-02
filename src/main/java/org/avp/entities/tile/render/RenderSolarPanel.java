package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSolarPanel extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        OpenGL.pushMatrix();
        {
            OpenGL.disable(GL11.GL_CULL_FACE);
            OpenGL.translate(posX + 0.5, posY + 0.5, posZ + 0.5);

            if (tileEntity != null)
            {
                float angle = tileEntity.getWorld().getCelestialAngle(renderPartialTicks) * 360;
                OpenGL.rotate(angle > 90 && angle < 270 ? 90 : angle, 0, 0, 1);
                OpenGL.translate(0F, -1.4F, 0F);
            }

            AliensVsPredator.resources().models().SOLAR_PANEL.draw(tileEntity);
        }
        OpenGL.popMatrix();
    }
}
