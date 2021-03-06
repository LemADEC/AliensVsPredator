package org.avp.client.render.entities.living;

import org.avp.AliensVsPredator;
import org.avp.client.model.entities.living.ModelChestbursterPredalien;
import org.avp.entities.living.EntityPredalienChestburster;

import com.arisux.mdx.lib.client.RenderLivingWrapper;
import com.arisux.mdx.lib.client.render.OpenGL;

import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPredalienChestburster extends RenderLivingWrapper<EntityPredalienChestburster, ModelChestbursterPredalien>
{
    public RenderPredalienChestburster(RenderManager m)
    {
        super(m, AliensVsPredator.resources().models().CHESTBUSTER_PREDALIEN);
    }

    @Override
    protected void preRenderCallback(EntityPredalienChestburster entitylivingBase, float partialTicks)
    {
        super.preRenderCallback(entitylivingBase, shadowSize);
        OpenGL.scale(0.55F, 0.55F, 0.55F);
    }
}
