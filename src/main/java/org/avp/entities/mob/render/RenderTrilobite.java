package org.avp.entities.mob.render;

import org.avp.AliensVsPredator;

import com.arisux.mdxlib.lib.client.RenderLivingWrapper;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.entity.EntityLivingBase;

public class RenderTrilobite extends RenderLivingWrapper
{
    public RenderTrilobite()
    {
        super(AliensVsPredator.resources().models().TRILOBITE);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entitylivingBase, float partialTicks)
    {
        super.preRenderCallback(entitylivingBase, shadowSize);
        float scale = 1.7F;
        OpenGL.scale(scale, scale, scale);
        OpenGL.translate(-0.2F, 0F, 0F);
    }
}
