package mod.vemerion.evilores.mobs.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.helper.Helper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class EvilQuartzModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer block;
    public ModelRenderer front;
    public ModelRenderer cannon1;
    public ModelRenderer cannon2;
    public ModelRenderer cannon3;
    public ModelRenderer cannonLeftLeg;
    public ModelRenderer cannonRightLeg;

    public EvilQuartzModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.cannonRightLeg = new ModelRenderer(this, 0, 0);
        this.cannonRightLeg.setRotationPoint(-6.0F, 1.0F, 1.0F);
        this.cannonRightLeg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.cannonLeftLeg = new ModelRenderer(this, 0, 0);
        this.cannonLeftLeg.setRotationPoint(4.0F, 1.0F, 1.0F);
        this.cannonLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.cannon2 = new ModelRenderer(this, 32, 32);
        this.cannon2.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.cannon2.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.block = new ModelRenderer(this, 0, 0);
        this.block.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.block.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.front = new ModelRenderer(this, 0, 16);
        this.front.setRotationPoint(0.0F, 8.0F, -8.0F);
        this.front.addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.cannon1 = new ModelRenderer(this, 0, 32);
        this.cannon1.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.cannon1.addBox(-4.0F, -4.0F, -3.0F, 8.0F, 8.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.cannon3 = new ModelRenderer(this, 48, 0);
        this.cannon3.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.cannon3.addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.block.addChild(this.cannonRightLeg);
        this.block.addChild(this.cannonLeftLeg);
        this.cannon1.addChild(this.cannon2);
        this.block.addChild(this.front);
        this.block.addChild(this.cannon1);
        this.cannon1.addChild(this.cannon3);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.block).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	setRotateAngle(block, headPitch * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);
    	setRotateAngle(front, Math.min((float)Math.PI / 2, MathHelper.lerp(ageInTicks / 20, 0, (float)Math.PI / 2)), 0, 0);
    	setRotateAngle(cannon1, Helper.pingpong(-0.2f, 0.05f, ageInTicks / 30), 0, 0);

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