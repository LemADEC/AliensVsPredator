package org.avp.entities.fx;

import java.util.Random;

import org.avp.AliensVsPredator;
import org.lwjgl.opengl.GL11;

import com.arisux.mdxlib.lib.client.render.Draw;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFXElectricArc extends EntityFX
{
    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");

    private Random                        rand;
    private int                           color;
    private int                           tessellation;
    private float                         rotYaw;
    private float                         rotPitch;
    private float                         density;
    private double                        targetX;
    private double                        targetY;
    private double                        targetZ;
    private double                        displacement;
    private double                        complexity;

    public EntityFXElectricArc(World world, double x, double y, double z, double targetX, double targetY, double targetZ, int age)
    {
        this(world, x, y, z, targetX, targetY, targetZ, age, 1.6D, 0.1D, 0.1F, 0xFF6655CC);
    }

    public EntityFXElectricArc(World world, double x, double y, double z, double targetX, double targetY, double targetZ, int age, int color)
    {
        this(world, x, y, z, targetX, targetY, targetZ, age, 1.6D, 0.1D, 0.1F, color);
    }

    public EntityFXElectricArc(World world, double x, double y, double z, double targetX, double targetY, double targetZ, int age, double displacement, double complexity, float density, int color)
    {
        super(world, x, y, z);
        this.rand = new Random();
        this.tessellation = 2;
        this.particleMaxAge = age;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.displacement = displacement;
        this.complexity = complexity;
        this.density = density;
        this.color = color;
        this.changeDirection((float) (this.posX - this.targetX), (float) (this.posY - this.targetY), (float) (this.posZ - this.targetZ));
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float rX, float rXZ, float rZ, float rYZ, float rXY)
    {
        tessellator.draw();
        super.renderParticle(tessellator, partialTicks, rX, rXZ, rZ, rYZ, rXY);
        this.drawArc(tessellator, posX, posY, posZ, targetX, targetY, targetZ, displacement, complexity, density);
        Draw.bindTexture(particleTextures);
        tessellator.startDrawingQuads();
    }

    public EntityFXElectricArc setTessellation(int tessellation)
    {
        this.tessellation = tessellation;
        return this;
    }

    private void changeDirection(float x, float y, float z)
    {
        double variance = MathHelper.sqrt_double(x * x + z * z);
        this.rotYaw = ((float) (Math.atan2(x, z) * 180.0D / Math.PI));
        this.rotPitch = ((float) (Math.atan2(y, variance) * 180.0D / Math.PI));
    }

    private void drawArc(Tessellator tessellator, double sX, double sY, double sZ, double tX, double tY, double tZ, double displacement, double complexity, float density)
    {
        if (displacement < complexity)
        {
            float x = (float) (sX - tX);
            float y = (float) (sY - tY);
            float z = (float) (sZ - tZ);

            this.changeDirection(x, y, z);

            GL11.glPushMatrix();
            GL11.glTranslatef((float) (sX - interpPosX), (float) (sY - interpPosY), (float) (sZ - interpPosZ));
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_CURRENT_BIT);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(180.0F + this.rotYaw, 0.0F, 0.0F, -1.0F);
            GL11.glRotatef(this.rotPitch, 1.0F, 0.0F, 0.0F);
            OpenGL.disableLight();
            AliensVsPredator.resources().BLANK.bind();

            double vX1 = density * -0.15;
            double vX2 = density * -0.15 * 1.0;
            double vY2 = MathHelper.sqrt_float(x * x + y * y + z * z);
            double vY1 = 0.0D;

            int a = (color >> 24 & 255);
            int r = (color >> 16 & 255);
            int g = (color >> 8 & 255);
            int b = (color & 255);

            for (int i = 0; i < tessellation; i++)
            {
                GL11.glRotatef((360F / tessellation) / 2, 0.0F, 1.0F, 0.0F);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA(r, g, b, a);
                tessellator.addVertexWithUV(vX2, vY2, 0.0, 0.0, 1.0);
                tessellator.addVertexWithUV(vX1, vY1, 0.0, 0.0, 0.0);
                tessellator.addVertexWithUV(-vX1, vY1, 0.0, 1.0, 0.0);
                tessellator.addVertexWithUV(-vX2, vY2, 0.0, 1.0, 1.0);
                tessellator.draw();
            }

            OpenGL.enableLight();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        }
        else
        {
            double splitX = (tX + sX) / 2;
            double splitY = (tY + sY) / 2;
            double splitZ = (tZ + sZ) / 2;
            splitX += (rand.nextFloat() - 0.5) * displacement;
            splitY += (rand.nextFloat() - 0.5) * displacement;
            splitZ += (rand.nextFloat() - 0.5) * displacement;
            drawArc(tessellator, sX, sY, sZ, splitX, splitY, splitZ, displacement / 2, complexity, density);
            drawArc(tessellator, tX, tY, tZ, splitX, splitY, splitZ, displacement / 2, complexity, density);
        }
    }

    @Override
    public void onUpdate()
    {
        if (this.particleAge++ > this.particleMaxAge)
        {
            this.setDead();
        }
    }
}
