package org.avp.client.render.tile;

import org.avp.tile.TileEntityRedstoneSensor;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderRedstoneSensor extends TileEntitySpecialRenderer<TileEntityRedstoneSensor>
{
    @Override
    public void renderTileEntityAt(TileEntityRedstoneSensor tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        OpenGL.pushMatrix();
        {
            OpenGL.disable(GL11.GL_CULL_FACE);
            OpenGL.translate(x, y, z);
            Draw.drawRect(0, 0, 1, 1, 0xFFFF0000);
            OpenGL.translate(0, 0, 1);
            Draw.drawRect(0, 0, 1, 1, 0xFFFF0000);
            OpenGL.rotate(90, 1, 0, 0);
            Draw.drawRect(0, 0, 1, -1, 0xFFFF0000);
            OpenGL.translate(0, 0, -1);
            Draw.drawRect(0, 0, 1, -1, 0xFFFF0000);
            OpenGL.rotate(90, 0, 1, 0);
            Draw.drawRect(0, 0, -1, -1, 0xFFFF0000);
            OpenGL.translate(0, 0, 1);
            Draw.drawRect(0, 0, -1, -1, 0xFFFF0000);
        }
        OpenGL.popMatrix();
    }
}