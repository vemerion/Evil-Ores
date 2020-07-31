package mod.vemerion.evilores.mobs.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.helper.Helper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Tabula 8.0.0
 */
public class EvilGoldModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer bottom;
	public ModelRenderer top;
	public ModelRenderer tooth1;
	public ModelRenderer tooth2;

	public EvilGoldModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.bottom = new ModelRenderer(this, 0, 0);
		this.bottom.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.bottom.addBox(-7.0F, 0.0F, -7.0F, 14.0F, 7.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.top = new ModelRenderer(this, 0, 21);
		this.top.setRotationPoint(0.0F, 17.0F, 7.0F);
		this.top.addBox(-7.0F, -7.0F, -14.0F, 14.0F, 7.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.top.addBox(0.0F, -1.0F, -15.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.top.addBox(5.0F, -1.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.top.addBox(-6.0F, -1.0F, -10.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.tooth2 = new ModelRenderer(this, 0, 0);
		this.tooth2.setRotationPoint(3.0F, 16.0F, -8.0F);
		this.tooth2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.tooth1 = new ModelRenderer(this, 0, 0);
		this.tooth1.setRotationPoint(-4.0F, 16.0F, -8.0F);
		this.tooth1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.bottom, this.tooth2, this.top, this.tooth1).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		setRotateAngle(top, -Helper.pingpong(0, (float) Math.PI / 4f, ageInTicks / 10), 0, 0);
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
