package org.avp.entities.mob.model;

import com.arisux.mdxlib.lib.client.Model;

import net.minecraft.client.model.ModelRenderer;

public class ModelPantheramorph extends Model
{
    public ModelRenderer chest;
    public ModelRenderer abdomen;
    public ModelRenderer backSpines1;
    public ModelRenderer backSpines2;
    public ModelRenderer neck;
    public ModelRenderer headBase;
    public ModelRenderer lThigh;
    public ModelRenderer rThigh;
    public ModelRenderer lArm1;
    public ModelRenderer rArm1;
    public ModelRenderer tail1;
    public ModelRenderer lShoulderRidge;
    public ModelRenderer rShoulderRidge;
    public ModelRenderer headTop;
    public ModelRenderer rHead;
    public ModelRenderer lHead;
    public ModelRenderer jaw2;
    public ModelRenderer jawLower;
    public ModelRenderer innerJaw;
    public ModelRenderer lCrest;
    public ModelRenderer rCrest;
    public ModelRenderer lShin1;
    public ModelRenderer lShin2;
    public ModelRenderer lFoot;
    public ModelRenderer rShin1;
    public ModelRenderer rShin2;
    public ModelRenderer rFoot;
    public ModelRenderer lArm2;
    public ModelRenderer lClaw1;
    public ModelRenderer lClaw2;
    public ModelRenderer rArm2;
    public ModelRenderer rClaw1;
    public ModelRenderer rClaw2;
    public ModelRenderer tail2;
    public ModelRenderer tailSpines1;
    public ModelRenderer tailSpines2;
    public ModelRenderer tail3;
    public ModelRenderer tailSpines3;
    public ModelRenderer tail4;
    public ModelRenderer tailSpines4;
    public ModelRenderer tail5;
    public ModelRenderer tailSpines5;
    public ModelRenderer tailStabber;

    public ModelPantheramorph()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.lClaw2 = new ModelRenderer(this, 60, 30);
        this.lClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lClaw2.addBox(-1.9F, 1.0F, -5.8F, 3, 0, 6, 0.0F);
        this.tail3 = new ModelRenderer(this, 118, 66);
        this.tail3.setRotationPoint(0.0F, 0.0F, 10.4F);
        this.tail3.addBox(-1.5F, -1.8F, 0.0F, 3, 3, 11, 0.0F);
        this.setRotation(tail3, 0.10175269539126941F, -0.0F, 0.0F);
        this.rArm2 = new ModelRenderer(this, 98, 9);
        this.rArm2.setRotationPoint(-0.9F, 12.9F, 0.6F);
        this.rArm2.addBox(-1.2F, -1.1F, -10.6F, 2, 2, 11, 0.0F);
        this.setRotation(rArm2, 0.9560913642424937F, -0.0F, -0.045553093477052F);
        this.tailSpines1 = new ModelRenderer(this, 58, 93);
        this.tailSpines1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailSpines1.addBox(0.0F, -4.7F, 0.0F, 0, 8, 11, 0.0F);
        this.tail5 = new ModelRenderer(this, 178, 66);
        this.tail5.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.tail5.addBox(-0.5F, -0.2F, 0.0F, 1, 1, 11, 0.0F);
        this.setRotation(tail5, 0.091106186954104F, -0.0F, 0.0F);
        this.lArm1 = new ModelRenderer(this, 40, 29);
        this.lArm1.setRotationPoint(3.5F, 3.5F, 3.5F);
        this.lArm1.addBox(0.0F, 0.0F, -0.5F, 2, 13, 2, 0.0F);
        this.setRotation(lArm1, 0.12217304763960307F, -0.0F, -0.24434609527920614F);
        this.backSpines2 = new ModelRenderer(this, 23, 65);
        this.backSpines2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.backSpines2.addBox(0.0F, -5.5F, 8.5F, 0, 6, 12, 0.0F);
        this.rCrest = new ModelRenderer(this, 131, 0);
        this.rCrest.mirror = true;
        this.rCrest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rCrest.addBox(-6.5F, -14.2F, -2.7F, 6, 11, 0, 0.0F);
        this.setRotation(rCrest, -0.31869712141416456F, 0.0F, -0.18203784098300857F);
        this.rShin2 = new ModelRenderer(this, 113, 53);
        this.rShin2.setRotationPoint(0.3F, 1.5F, 10.4F);
        this.rShin2.addBox(-1.0F, -0.2F, -1.1F, 2, 9, 2, 0.0F);
        this.setRotation(rShin2, 0.0F, 0.0F, -0.36425021489121656F);
        this.neck = new ModelRenderer(this, 23, 86);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-2.0F, 0.0F, -2.5F, 4, 6, 5, 0.0F);
        this.setRotation(neck, -0.19198621771937624F, -0.0F, 0.0F);
        this.rArm1 = new ModelRenderer(this, 50, 29);
        this.rArm1.setRotationPoint(-3.5F, 3.5F, 3.5F);
        this.rArm1.addBox(-2.0F, 0.0F, -0.5F, 2, 13, 2, 0.0F);
        this.setRotation(rArm1, 0.12217304763960307F, -0.0F, 0.24434609527920614F);
        this.lHead = new ModelRenderer(this, 53, 0);
        this.lHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lHead.addBox(-0.5F, 4.1F, -6.7F, 3, 3, 5, 0.0F);
        this.setRotation(lHead, 0.5009094953223726F, -0.0F, 0.0F);
        this.lCrest = new ModelRenderer(this, 131, 0);
        this.lCrest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lCrest.addBox(0.3F, -14.2F, -2.7F, 6, 11, 0, 0.0F);
        this.setRotation(lCrest, -0.31869712141416456F, 0.0F, 0.18203784098300857F);
        this.innerJaw = new ModelRenderer(this, 0, 18);
        this.innerJaw.setRotationPoint(0.0F, 6.4F, 1.2F);
        this.innerJaw.addBox(-1.0F, -0.7F, -4.6F, 2, 2, 5, 0.0F);
        this.setRotation(innerJaw, 0.7740535232594852F, 0.0F, 0.0F);
        this.lShoulderRidge = new ModelRenderer(this, 155, 25);
        this.lShoulderRidge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lShoulderRidge.addBox(3.5F, -1.2F, -3.0F, 2, 9, 5, 0.0F);
        this.setRotation(lShoulderRidge, 0.22759093446006054F, -0.18203784098300857F, 0.13962634015954636F);
        this.rShoulderRidge = new ModelRenderer(this, 172, 25);
        this.rShoulderRidge.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rShoulderRidge.addBox(-5.5F, -1.2F, -3.0F, 2, 9, 5, 0.0F);
        this.setRotation(rShoulderRidge, 0.22759093446006054F, 0.18203784098300857F, -0.13962634015954636F);
        this.jaw2 = new ModelRenderer(this, 52, 20);
        this.jaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jaw2.addBox(-1.5F, 5.0F, -8.4F, 3, 1, 5, 0.0F);
        this.setRotation(jaw2, 0.7740535232594852F, -0.0F, 0.0F);
        this.rHead = new ModelRenderer(this, 34, 0);
        this.rHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rHead.addBox(-2.51F, 4.1F, -6.7F, 3, 3, 5, 0.0F);
        this.setRotation(rHead, 0.5009094953223726F, -0.0F, 0.0F);
        this.rClaw2 = new ModelRenderer(this, 60, 38);
        this.rClaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rClaw2.addBox(-1.1F, 1.0F, -5.8F, 3, 0, 6, 0.0F);
        this.tailSpines2 = new ModelRenderer(this, 90, 93);
        this.tailSpines2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailSpines2.addBox(0.0F, -4.5F, 0.2F, 0, 8, 11, 0.0F);
        this.abdomen = new ModelRenderer(this, 0, 27);
        this.abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.abdomen.addBox(-3.5F, 0.5F, 8.0F, 7, 6, 12, 0.0F);
        this.headBase = new ModelRenderer(this, 0, 0);
        this.headBase.setRotationPoint(0.0F, 0.4F, -1.4F);
        this.headBase.addBox(-2.5F, -3.2F, -3.9F, 5, 10, 5, 0.0F);
        this.setRotation(headBase, -0.6017895260876448F, -0.0F, 0.0F);
        this.tailSpines3 = new ModelRenderer(this, 118, 94);
        this.tailSpines3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailSpines3.addBox(0.0F, -2.9F, 1.3F, 0, 6, 10, 0.0F);
        this.rThigh = new ModelRenderer(this, 59, 45);
        this.rThigh.setRotationPoint(-4.5F, 3.5F, 16.0F);
        this.rThigh.addBox(-1.5F, -2.0F, -1.0F, 4, 14, 5, 0.0F);
        this.setRotation(rThigh, -0.4923573819876004F, 0.2228785454796759F, 0.43022366061660217F);
        this.tail4 = new ModelRenderer(this, 149, 66);
        this.tail4.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 11, 0.0F);
        this.setRotation(tail4, 0.091106186954104F, -0.0F, 0.0F);
        this.lClaw1 = new ModelRenderer(this, 83, 24);
        this.lClaw1.setRotationPoint(0.0F, -0.1F, -10.3F);
        this.lClaw1.addBox(-0.9F, 0.0F, -3.1F, 2, 1, 3, 0.0F);
        this.setRotation(lClaw1, -1.0471975511965976F, 0.18203784098300857F, 0.22759093446006054F);
        this.tailSpines4 = new ModelRenderer(this, 149, 96);
        this.tailSpines4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailSpines4.addBox(0.0F, -2.1F, 1.1F, 0, 4, 10, 0.0F);
        this.lThigh = new ModelRenderer(this, 40, 45);
        this.lThigh.setRotationPoint(4.5F, 3.5F, 16.0F);
        this.lThigh.addBox(-2.5F, -2.0F, -1.0F, 4, 14, 5, 0.0F);
        this.setRotation(lThigh, -0.4923573819876004F, -0.2228785454796759F, -0.43022366061660217F);
        this.tail1 = new ModelRenderer(this, 50, 66);
        this.tail1.setRotationPoint(0.0F, 3.5F, 18.5F);
        this.tail1.addBox(-2.0F, -2.5F, 0.0F, 4, 4, 11, 0.0F);
        this.setRotation(tail1, -0.40142572795869574F, -0.0F, 0.0F);
        this.headTop = new ModelRenderer(this, 28, 10);
        this.headTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headTop.addBox(-2.5F, -11.8F, -4.7F, 5, 10, 5, 0.0F);
        this.setRotation(headTop, -0.31869712141416456F, -0.0F, 0.0F);
        this.rClaw1 = new ModelRenderer(this, 72, 24);
        this.rClaw1.setRotationPoint(0.0F, -0.1F, -10.3F);
        this.rClaw1.addBox(-1.1F, 0.0F, -3.1F, 2, 1, 3, 0.0F);
        this.setRotation(rClaw1, -1.0471975511965976F, -0.091106186954104F, -0.22759093446006054F);
        this.lFoot = new ModelRenderer(this, 110, 24);
        this.lFoot.setRotationPoint(1.0F, 8.2F, 0.0F);
        this.lFoot.addBox(-1.0F, -1.0F, -4.4F, 2, 2, 5, 0.0F);
        this.setRotation(lFoot, 0.5462880558742251F, 0.0F, 0.136659280431156F);
        this.lShin2 = new ModelRenderer(this, 113, 40);
        this.lShin2.setRotationPoint(-1.3F, 1.5F, 10.4F);
        this.lShin2.addBox(0.0F, -0.2F, -1.1F, 2, 9, 2, 0.0F);
        this.setRotation(lShin2, 0.0F, 0.0F, 0.36425021489121656F);
        this.lShin1 = new ModelRenderer(this, 79, 49);
        this.lShin1.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.lShin1.addBox(-2.0F, 0.0F, -0.5F, 3, 3, 12, 0.0F);
        this.lArm2 = new ModelRenderer(this, 71, 9);
        this.lArm2.setRotationPoint(0.9F, 12.9F, 0.6F);
        this.lArm2.addBox(-0.9F, -1.1F, -10.6F, 2, 2, 11, 0.0F);
        this.setRotation(lArm2, 0.9560913642424937F, -0.0F, -0.045553093477052F);
        this.tailSpines5 = new ModelRenderer(this, 177, 96);
        this.tailSpines5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailSpines5.addBox(0.0F, -0.9F, 0.4F, 0, 3, 11, 0.0F);
        this.jawLower = new ModelRenderer(this, 88, 1);
        this.jawLower.setRotationPoint(0.0F, 6.7F, 2.3F);
        this.jawLower.addBox(-1.5F, -0.5F, -5.2F, 3, 1, 5, 0.0F);
        this.setRotation(jawLower, 0.7740535232594852F, 0.0F, 0.0F);
        this.tailStabber = new ModelRenderer(this, 205, 66);
        this.tailStabber.setRotationPoint(0.0F, 0.3F, 10.2F);
        this.tailStabber.addBox(0.0F, -2.0F, 0.0F, 0, 5, 12, 0.0F);
        this.rFoot = new ModelRenderer(this, 95, 24);
        this.rFoot.setRotationPoint(0.0F, 8.2F, 0.0F);
        this.rFoot.addBox(-1.0F, -1.0F, -4.4F, 2, 2, 5, 0.0F);
        this.setRotation(rFoot, 0.5462880558742251F, 0.0F, -0.136659280431156F);
        this.backSpines1 = new ModelRenderer(this, 0, 67);
        this.backSpines1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.backSpines1.addBox(0.0F, -5.8F, 0.0F, 0, 6, 10, 0.0F);
        this.tail2 = new ModelRenderer(this, 85, 66);
        this.tail2.setRotationPoint(0.0F, 0.0F, 11.0F);
        this.tail2.addBox(-2.0F, -2.5F, -0.1F, 4, 4, 11, 0.0F);
        this.setRotation(tail2, 0.091106186954104F, -0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 0, 46);
        this.chest.setRotationPoint(0.0F, -1.5F, 0.0F);
        this.chest.addBox(-4.5F, 0.0F, 0.0F, 9, 8, 10, 0.0F);
        this.rShin1 = new ModelRenderer(this, 79, 33);
        this.rShin1.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.rShin1.addBox(-1.0F, 0.0F, -0.5F, 3, 3, 12, 0.0F);
        this.lClaw1.addChild(this.lClaw2);
        this.tail2.addChild(this.tail3);
        this.rArm1.addChild(this.rArm2);
        this.tail1.addChild(this.tailSpines1);
        this.tail4.addChild(this.tail5);
        this.chest.addChild(this.lArm1);
        this.chest.addChild(this.backSpines2);
        this.headBase.addChild(this.rCrest);
        this.rShin1.addChild(this.rShin2);
        this.chest.addChild(this.neck);
        this.chest.addChild(this.rArm1);
        this.headBase.addChild(this.lHead);
        this.headBase.addChild(this.lCrest);
        this.headBase.addChild(this.innerJaw);
        this.chest.addChild(this.lShoulderRidge);
        this.chest.addChild(this.rShoulderRidge);
        this.headBase.addChild(this.jaw2);
        this.headBase.addChild(this.rHead);
        this.rClaw1.addChild(this.rClaw2);
        this.tail2.addChild(this.tailSpines2);
        this.chest.addChild(this.abdomen);
        this.chest.addChild(this.headBase);
        this.tail3.addChild(this.tailSpines3);
        this.chest.addChild(this.rThigh);
        this.tail3.addChild(this.tail4);
        this.lArm2.addChild(this.lClaw1);
        this.tail4.addChild(this.tailSpines4);
        this.chest.addChild(this.lThigh);
        this.chest.addChild(this.tail1);
        this.headBase.addChild(this.headTop);
        this.rArm2.addChild(this.rClaw1);
        this.lShin2.addChild(this.lFoot);
        this.lShin1.addChild(this.lShin2);
        this.lThigh.addChild(this.lShin1);
        this.lArm1.addChild(this.lArm2);
        this.tail5.addChild(this.tailSpines5);
        this.headBase.addChild(this.jawLower);
        this.tail5.addChild(this.tailStabber);
        this.rShin2.addChild(this.rFoot);
        this.chest.addChild(this.backSpines1);
        this.tail1.addChild(this.tail2);
        this.rThigh.addChild(this.rShin1);
    }
    
    @Override
    public void render(Object obj)
    {
        draw(chest);
    }
}
