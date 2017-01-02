package org.avp.items.render;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.render.OpenGL;
import com.arisux.mdxlib.lib.client.render.ItemRenderer;
import com.arisux.mdxlib.lib.game.Game;

import net.minecraft.item.ItemStack;

public class RenderItemTransformer extends ItemRenderer
{
    public RenderItemTransformer()
    {
        super(AliensVsPredator.resources().models().TRANSFORMER);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        super.renderItem(type, item, data);
    }

    @Override
    public void renderThirdPerson(ItemStack item, Object... data)
    {
        float glScale = 1.6F;
        OpenGL.pushMatrix();
        {
            OpenGL.scale(glScale, glScale, glScale);
            OpenGL.rotate(90F, 0F, 0F, 1F);
            OpenGL.translate(0F, -1.3F, 0.4F);
            OpenGL.disable(GL11.GL_CULL_FACE);
            this.getModelTexMap().draw();
        }
        OpenGL.popMatrix();
    }

    @Override
    public void renderFirstPerson(ItemStack item, Object... data)
    {
        float glScale = 0.8F;
        OpenGL.pushMatrix();
        {
            if (firstPersonRenderCheck(data[1]))
            {
                OpenGL.scale(glScale, -glScale, glScale);
                OpenGL.translate(0F, -1.6F, 0.2F);
                OpenGL.rotate(-45.0F, 0.0F, 0.0F, 1.0F);
                OpenGL.disable(GL11.GL_CULL_FACE);
                this.getModelTexMap().draw();
            }
        }
        OpenGL.popMatrix();
    }

    @Override
    public void renderInInventory(ItemStack item, Object... data)
    {
        float glScale = 11F;
        OpenGL.pushMatrix();
        {
            OpenGL.scale(glScale, glScale, glScale);
            OpenGL.translate(0.72F, -0.25F, 0F);
            OpenGL.rotate(-45, 0.0F, -1.0F, 0.0F);
            OpenGL.translate(0F, 0F, 0F);
            OpenGL.disable(GL11.GL_CULL_FACE);
            OpenGL.enable(GL11.GL_BLEND);
            this.getModelTexMap().draw();
        }
        OpenGL.popMatrix();
    }

    @Override
    public void renderInWorld(ItemStack item, Object... data)
    {
        super.renderInWorld(item, data);
        OpenGL.pushMatrix();
        {
            OpenGL.rotate((this.mc.theWorld.getWorldTime() + Game.partialTicks() % 360) * 10, 0.0F, 1.0F, 0.0F);
            OpenGL.disable(GL11.GL_CULL_FACE);
            this.getModelTexMap().draw();
        }
        OpenGL.popMatrix();
    }
}
