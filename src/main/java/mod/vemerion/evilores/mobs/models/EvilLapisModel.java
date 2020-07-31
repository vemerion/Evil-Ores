package mod.vemerion.evilores.mobs.models;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.helper.Helper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created using Tabula 8.0.0
 */
public class EvilLapisModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;
    public ModelRenderer leftPupil;
    public ModelRenderer rightPupil;

    public EvilLapisModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftPupil = new ModelRenderer(this, 0, 5);
        this.leftPupil.setRotationPoint(3.5F, -4.0F, 20.0F);
        this.leftPupil.addBox(0.0F, 0.0F, -29.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.leftEye = new ModelRenderer(this, 0, 0);
        this.leftEye.setRotationPoint(2.0F, -5.0F, -9.0F);
        this.leftEye.addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.rightEye = new ModelRenderer(this, 0, 0);
        this.rightEye.setRotationPoint(-6.0F, -5.0F, -9.0F);
        this.rightEye.addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.rightPupil = new ModelRenderer(this, 0, 5);
        this.rightPupil.setRotationPoint(-4.5F, -4.0F, 20.0F);
        this.rightPupil.addBox(0.0F, 0.0F, -29.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.leftPupil);
        this.body.addChild(this.leftEye);
        this.body.addChild(this.rightEye);
        this.body.addChild(this.rightPupil);      
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	setRotateAngle(body, headPitch * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
    	setRotateAngle(leftPupil, 0, Helper.pingpong(-0.04f, 0.04f, ageInTicks / 25), 0);
    	setRotateAngle(rightPupil, 0, Helper.pingpong(-0.04f, 0.04f, ageInTicks / 25), 0);

    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
