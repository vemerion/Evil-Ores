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
public class EvilOreOriginModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer origin;
    private float ageInTicks;

    public EvilOreOriginModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.origin = new ModelRenderer(this, 0, 0);
        this.origin.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.origin.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	matrixStackIn.push();
    	matrixStackIn.translate(0, 0.5, 0);
    	matrixStackIn.scale(Helper.pingpong(0.1f, 0.6f, ageInTicks / 20), Helper.pingpong(0.1f, 0.6f, ageInTicks / 20), Helper.pingpong(0.1f, 0.6f, ageInTicks / 20));
    	matrixStackIn.translate(0, -0.5, 0);
    	ImmutableList.of(this.origin).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        matrixStackIn.pop();
    }

    @Override
    public void setRotationAngles(T entityIn, float unused1, float unused2, float ageInTicks, float unused3, float unused4) {
    	this.ageInTicks = ageInTicks;
    	setRotateAngle(origin, ageInTicks / 20 * (float)Math.PI * 2, ageInTicks / 20 * (float)Math.PI * 2, ageInTicks / 20 * (float)Math.PI * 2);
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
