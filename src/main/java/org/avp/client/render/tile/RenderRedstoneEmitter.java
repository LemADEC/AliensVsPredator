package org.avp.client.render.tile;

import org.avp.tile.TileEntityRedstoneEmitter;

import com.arisux.mdx.lib.client.render.Draw;
import com.arisux.mdx.lib.client.render.OpenGL;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderRedstoneEmitter extends TileEntitySpecialRenderer<TileEntityRedstoneEmitter>
{
    @Override
    public void renderTileEntityAt(TileEntityRedstoneEmitter tile, double x, double y, double z, float partialTicks, int destroyStage)
    {
        OpenGL.pushMatrix();
        {
            GlStateManager.disableCull();
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
