package org.avp.entities.mob.model;

import com.arisux.mdxlib.lib.client.Model;
import com.arisux.mdxlib.lib.client.render.OpenGL;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class ModelOctohugger extends Model
{
    public ModelRenderer body;
    public ModelRenderer fFlap1;
    public ModelRenderer lFlap1;
    public ModelRenderer bFlap1;
    public ModelRenderer rFlap1;
    public ModelRenderer head2;
    public ModelRenderer body2;
    public ModelRenderer head1;
    public ModelRenderer head3;
    public ModelRenderer head4;
    public ModelRenderer fFlap2;
    public ModelRenderer lFlap2;
    public ModelRenderer bFlap2;
    public ModelRenderer rFlap2;

    public ModelOctohugger()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rFlap1 = new ModelRenderer(this, 27, 11);
        this.rFlap1.setRotationPoint(-1.0F, 2.1F, 0.0F);
        this.rFlap1.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.fFlap2 = new ModelRenderer(this, 17, 5);
        this.fFlap2.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.fFlap2.addBox(-0.5F, 0.0F, -0.4F, 1, 2, 1, 0.0F);
        this.lFlap2 = new ModelRenderer(this, 27, 6);
        this.lFlap2.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.lFlap2.addBox(-0.6F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.bFlap1 = new ModelRenderer(this, 17, 12);
        this.bFlap1.mirror = true;
        this.bFlap1.setRotationPoint(0.0F, 2.1F, 1.0F);
        this.bFlap1.addBox(-1.0F, 0.0F, -0.5F, 2, 3, 1, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 7);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.addBox(-1.5F, -5.4F, -1.5F, 3, 2, 3, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 18);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.addBox(-1.5F, -1.7F, -1.5F, 3, 3, 3, 0.0F);
        this.bFlap2 = new ModelRenderer(this, 17, 18);
        this.bFlap2.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.bFlap2.addBox(-0.5F, 0.0F, -0.6F, 1, 2, 1, 0.0F);
        this.head3 = new ModelRenderer(this, 0, 3);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.addBox(-1.0F, -5.0F, -1.0F, 2, 1, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 17);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 13);
        this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head1.addBox(-1.0F, -3.0F, -1.0F, 2, 1, 2, 0.0F);
        this.head4 = new ModelRenderer(this, 0, 0);
        this.head4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head4.addBox(-0.5F, -5.6F, -0.5F, 1, 1, 1, 0.0F);
        this.fFlap1 = new ModelRenderer(this, 17, 0);
        this.fFlap1.setRotationPoint(0.0F, 2.1F, -1.0F);
        this.fFlap1.addBox(-1.0F, -0.1F, -0.5F, 2, 3, 1, 0.0F);
        this.lFlap1 = new ModelRenderer(this, 27, 0);
        this.lFlap1.setRotationPoint(1.0F, 2.1F, 0.0F);
        this.lFlap1.addBox(-0.5F, -0.1F, -1.0F, 1, 3, 2, 0.0F);
        this.rFlap2 = new ModelRenderer(this, 27, 17);
        this.rFlap2.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.rFlap2.addBox(-0.4F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.fFlap1.addChild(this.fFlap2);
        this.lFlap1.addChild(this.lFlap2);
        this.bFlap1.addChild(this.bFlap2);
        this.body.addChild(this.head3);
        this.body.addChild(this.head1);
        this.body.addChild(this.head4);
        this.rFlap1.addChild(this.rFlap2);
    }

    @Override
    protected void render(IRenderObject renderObject, float boxTranslation)
    {
        RenderObject o = (RenderObject) renderObject;
        float legMovement = -(MathHelper.cos(o.swingProgress * 2.6662F + (float) Math.PI) * 1F * o.swingProgressPrev * 0.25F) * 90;

        float speed = 0.055F;
        float legStart = 15F;
        float legDistance = 0.8F;

        yOffset = 0.045F + Math.toRadians((MathHelper.sin(o.idleProgress * speed) * (legDistance * 4F)));
        yOffset = yOffset + MathHelper.cos(o.swingProgress * 0.6662F + (float) Math.PI) * 1F * o.swingProgressPrev * 0.25F;
        OpenGL.translate(0F, yOffset, 0F);

        this.rFlap1.rotateAngleZ = (float) (Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));
        this.rFlap2.rotateAngleZ = (float) (Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));

        this.bFlap1.rotateAngleX = (float) (Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));
        this.bFlap2.rotateAngleX = (float) (Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));

        this.lFlap1.rotateAngleZ = (float) -(Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));
        this.lFlap2.rotateAngleZ = (float) -(Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));

        this.fFlap1.rotateAngleX = (float) -(Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));
        this.fFlap2.rotateAngleX = (float) -(Math.toRadians(legStart) + (float) Math.toRadians((MathHelper.sin(o.idleProgress * speed) * legDistance) * 20 + legMovement));

        this.rFlap1.render(boxTranslation);
        this.bFlap1.render(boxTranslation);
        this.fFlap1.render(boxTranslation);
        this.lFlap1.render(boxTranslation);
        OpenGL.pushMatrix();
        OpenGL.translate(this.head2.offsetX, this.head2.offsetY, this.head2.offsetZ);
        OpenGL.translate(this.head2.rotationPointX * boxTranslation, this.head2.rotationPointY * boxTranslation, this.head2.rotationPointZ * boxTranslation);
        OpenGL.scale(0.8D, 0.8D, 0.8D);
        OpenGL.translate(-this.head2.offsetX, -this.head2.offsetY, -this.head2.offsetZ);
        OpenGL.translate(-this.head2.rotationPointX * boxTranslation, -this.head2.rotationPointY * boxTranslation, -this.head2.rotationPointZ * boxTranslation);
        this.head2.render(boxTranslation);
        OpenGL.popMatrix();
        OpenGL.pushMatrix();
        OpenGL.translate(this.body2.offsetX, this.body2.offsetY, this.body2.offsetZ);
        OpenGL.translate(this.body2.rotationPointX * boxTranslation, this.body2.rotationPointY * boxTranslation, this.body2.rotationPointZ * boxTranslation);
        OpenGL.scale(1.1D, 1.0D, 1.1D);
        OpenGL.translate(-this.body2.offsetX, -this.body2.offsetY, -this.body2.offsetZ);
        OpenGL.translate(-this.body2.rotationPointX * boxTranslation, -this.body2.rotationPointY * boxTranslation, -this.body2.rotationPointZ * boxTranslation);
        this.body2.render(boxTranslation);
        OpenGL.popMatrix();
        this.body.render(boxTranslation);
    }
    
    private double yOffset;

    public double getYOffset()
    {
        return -this.yOffset;
    }
}
