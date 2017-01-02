package org.avp.entities.tile.render;

import org.avp.AliensVsPredator;
import org.avp.entities.tile.TileEntityTurret;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTurret extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float renderPartialTicks)
    {
        TileEntityTurret tile = (TileEntityTurret) tileEntity;

        OpenGL.pushMatrix();
        {
            OpenGL.disable(GL11.GL_CULL_FACE);
            OpenGL.translate(posX + 0.5F, posY + 3.0F, posZ - 0.0F);
            OpenGL.rotate(tile.getDirection() * (-90F), 0F, 1F, 0F);

            OpenGL.scale(2F, -2F, 2F);
            AliensVsPredator.resources().models().TURRET.draw(tile);

            if (tile.getVoltage() > 0)
            {
                OpenGL.rotate(tile.getDirection() * 90F, 0F, 1F, 0F);
                this.renderAmmoDisplay(tile);

                if (!tile.isFiring())
                {
                    this.renderBeam(0, 0, tile.getRange(), -1, 0, 50, tile.beamColor, 0x00000000, tile.getRotationYaw(), tile.getRotationPitch(), -1);
                }
            }
        }
        OpenGL.popMatrix();
    }

    public void renderAmmoDisplay(TileEntityTurret tile)
    {
        if (tile.isAmmoDisplayEnabled())
        {
            int ammo = (tile.getCurRounds() * tile.getMaxAmmo()) + tile.getCurAmmo();
            String displayText = ammo >= 0 ? ammo < 10 ? "000" + ammo : ammo < 100 ? "00" + ammo : ammo < 1000 ? "0" + ammo : "" + ammo : "----";

            OpenGL.pushMatrix();
            {
                // Reposition to the turret head's rotation point
                OpenGL.disableLight();
                OpenGL.disable(GL11.GL_LIGHTING);
                float displayScale = 0.005F;
                OpenGL.scale(displayScale, displayScale, displayScale);
                OpenGL.translate(0F, 137.5F, 0F);

                // Rotate & Reposition Display
                OpenGL.rotate(180 + -tile.getRotationYaw(), 0F, 1F, 0F);
                OpenGL.rotate(tile.getRotationPitch(), 1F, 0F, 0F);
                OpenGL.translate(-12.5F, -23.5F, 43.76F);

                // Display itself
                Draw.drawRect(-5, -3, 35, 14, 0xFF000000);
                Draw.drawRect(-5, -3, 35, 14, 0xFF000000);
                OpenGL.translate(0.0F, 0.0F, 0.001F);
                Draw.drawString(displayText, 0, 0, tile.beamColor);
                OpenGL.enable(GL11.GL_LIGHTING);
                OpenGL.enableLight();
            }
            OpenGL.popMatrix();
        }
    }

    public void renderBeam(int x, int y, int w, int h, int zLevel, int scale, int color1, int color2, float rotationYaw, float rotationPitch, int l)
    {
        w = w * scale / 2;

        OpenGL.pushMatrix();
        {
            OpenGL.translate(0F, 0.75F, 0F);
            OpenGL.rotate(-90 + -rotationYaw, 0F, 1F, 0F);
            OpenGL.rotate(rotationPitch, 0F, 0F, 1F);
            OpenGL.scale(1F / scale, 1F / scale, 1F / scale);
            OpenGL.disable(GL11.GL_TEXTURE_2D);
            OpenGL.disable(GL11.GL_LIGHTING);
            OpenGL.disableLight();
            OpenGL.enable(GL11.GL_BLEND);
            OpenGL.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F((color2 >> 16 & 255) / 255.0F, (color2 >> 8 & 255) / 255.0F, (color2 & 255) / 255.0F, (color2 >> 24 & 255) / 255.0F);
            tessellator.addVertex(w, y, zLevel);
            tessellator.addVertex(x, y, zLevel);
            tessellator.setColorRGBA_F((color1 >> 16 & 255) / 255.0F, (color1 >> 8 & 255) / 255.0F, (color1 & 255) / 255.0F, (color1 >> 24 & 255) / 255.0F);
            tessellator.addVertex(x, l, zLevel);
            tessellator.addVertex(w, h, zLevel);
            tessellator.draw();
            GL11.glShadeModel(GL11.GL_FLAT);
            OpenGL.enable(GL11.GL_LIGHTING);
            OpenGL.enableLight();
            OpenGL.enable(GL11.GL_TEXTURE_2D);
            OpenGL.disable(GL11.GL_BLEND);
        }
        OpenGL.popMatrix();
    }
}
