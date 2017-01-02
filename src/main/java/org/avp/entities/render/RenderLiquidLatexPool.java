package org.avp.entities.render;

import org.avp.AliensVsPredator;

import com.arisux.mdxlib.lib.client.render.OpenGL;
import com.arisux.mdxlib.lib.game.Game;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderLiquidLatexPool extends Render
{
    private Tessellator tessellator = Tessellator.instance;

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float yaw, float renderPartialTicks)
    {
        OpenGL.pushMatrix();
        {
            float offset = 1.4F;
            double renderX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * renderPartialTicks;
            double renderY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * renderPartialTicks + entity.getShadowSize();
            double renderZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * renderPartialTicks;
            double partialX = posX - renderX;
            double partialY = posY - renderY;
            double partialZ = posZ - renderZ;

            AliensVsPredator.resources().LIQUID_POOL.bind();
            OpenGL.disableLighting();
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(1F, 1F, 1F, 1F);

            for (int blockX = MathHelper.floor_double(renderX - offset); blockX <= MathHelper.floor_double(renderX + offset); ++blockX)
            {
                for (int blockY = MathHelper.floor_double(renderY - offset); blockY <= MathHelper.floor_double(renderY); ++blockY)
                {
                    for (int blockZ = MathHelper.floor_double(renderZ - offset); blockZ <= MathHelper.floor_double(renderZ + offset); ++blockZ)
                    {
                        Block block = Game.minecraft().thePlayer.worldObj.getBlock(blockX, blockY - 1, blockZ);

                        if (block != Blocks.air)
                        {
                            this.renderImageOnBlock(block, posX, posY + entity.getShadowSize(), posZ, blockX, blockY, blockZ, yaw, offset, partialX, partialY + entity.getShadowSize(), partialZ, entity.ticksExisted);
                        }
                    }
                }
            }

            tessellator.draw();
            OpenGL.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
        OpenGL.popMatrix();
    }

    private void renderImageOnBlock(Block block, double posX, double posY, double posZ, int blockX, int blockY, int blockZ, float yaw, float offset, double partialX, double partialY, double partialZ, float opacity)
    {
        if (block.renderAsNormalBlock())
        {
            double x1 = blockX + block.getBlockBoundsMinX() + partialX;
            double x2 = blockX + block.getBlockBoundsMaxX() + partialX;
            double y = blockY + block.getBlockBoundsMinY() + partialY + 0.015625D;
            double z1 = blockZ + block.getBlockBoundsMinZ() + partialZ;
            double z2 = blockZ + block.getBlockBoundsMaxZ() + partialZ;
            float u1 = (float) ((posX - x1) / 2.0D / offset + 0.5D);
            float u2 = (float) ((posX - x2) / 2.0D / offset + 0.5D);
            float v1 = (float) ((posZ - z1) / 2.0D / offset + 0.5D);
            float v2 = (float) ((posZ - z2) / 2.0D / offset + 0.5D);

            OpenGL.pushMatrix();
            {
                OpenGL.rotate(yaw, 0F, 1F, 0F);
                tessellator.addVertexWithUV(x1, y, z1, u1, v1);
                tessellator.addVertexWithUV(x1, y, z2, u1, v2);
                tessellator.addVertexWithUV(x2, y, z2, u2, v2);
                tessellator.addVertexWithUV(x2, y, z1, u2, v1);
            }
            OpenGL.popMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}
