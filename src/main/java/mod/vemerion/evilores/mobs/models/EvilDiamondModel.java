package mod.vemerion.evilores.mobs.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.helper.Helper;
import mod.vemerion.evilores.mobs.entities.EvilDiamondEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * using Tabula 8.0.0
 */
public class EvilDiamondModel<T extends EvilDiamondEntity> extends EntityModel<T> {
	public ModelRenderer spin;
	public ModelRenderer head;
	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer body3;
	public ModelRenderer body4;
	public ModelRenderer leftArm;
	public ModelRenderer rightArm;

	public EvilDiamondModel() {
		this.textureWidth = 128;
        this.textureHeight = 64;
        this.body3 = new ModelRenderer(this, 64, 26);
        this.body3.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.body3.addBox(-6.0F, -3.0F, -6.0F, 12.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.spin = new ModelRenderer(this, 0, 0);
        this.spin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.spin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 0, 26);
        this.body2.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body2.addBox(-8.0F, -3.0F, -8.0F, 16.0F, 6.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.body4 = new ModelRenderer(this, 80, 0);
        this.body4.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.body4.addBox(-4.0F, -3.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 20, 0);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-10.0F, -3.0F, -10.0F, 20.0F, 6.0F, 20.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.head.addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, 0.7819074915776542F, 0.9382889765773795F, 0.5864306020384839F);
        this.rightArm = new ModelRenderer(this, 0, 0);
        this.rightArm.setRotationPoint(-10.0F, 0.0F, 0.0F);
        this.rightArm.setTextureOffset(56, 44).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.rightArm.setTextureOffset(44, 14).addBox(-7.0F, 8.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightArm.setTextureOffset(104, 0).addBox(-6.0F, 14.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.17453292519943295F);
        this.leftArm = new ModelRenderer(this, 0, 0);
        this.leftArm.setRotationPoint(10.0F, 0.0F, 0.0F);
        this.leftArm.setTextureOffset(56, 44).addBox(0.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.leftArm.setTextureOffset(100, 14).addBox(1.0F, 8.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leftArm.setTextureOffset(104, 0).addBox(2.0F, 14.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.17453292519943295F);
        this.spin.addChild(this.rightArm);
        this.spin.addChild(this.leftArm);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.spin, this.body3, this.body2, this.body4, this.head, this.body1)
				.forEach((modelRenderer) -> {
					modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
							alpha);
				});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {

		// Rotate head
		setRotateAngle(head, ageInTicks / 40 * (float) Math.PI * 2, ageInTicks / 35 * (float) Math.PI * 2,
				ageInTicks / 48 * (float) Math.PI * 2);

		// Rotate body
		setRotateAngle(body1, -0.05f, ageInTicks / 35 * (float) Math.PI * 2 + 0.6f, 0.25f);
		setRotateAngle(body2, 0.1f, ageInTicks / 35 * (float) Math.PI * 2 + 0.4f, 0.1f);
		setRotateAngle(body3, -0.1f, ageInTicks / 35 * (float) Math.PI * 2 + 0.2f, -0.1f);
		setRotateAngle(body4, 0.05f, ageInTicks / 35 * (float) Math.PI * 2, 0.07f);
		
		// Rotate arms
		setRotateAngle(rightArm, 0, 0, Helper.pingpong(-0.1f, 0.1f, ageInTicks / 10));
		setRotateAngle(leftArm, 0, 0, -Helper.pingpong(-0.1f, 0.1f, ageInTicks / 10));
		
		// Reset arm rotation
		setRotateAngle(spin, 0, 0, 0);
		
		// Shooting
		if (entityIn.isShooting()) {
			setRotateAngle(rightArm, -(float) Math.PI / 2, 0, 0);
		}

		// Spin attack
		if (entityIn.isSpinning()) {
			setRotateAngle(rightArm, 0, 0, (float) Math.PI / 2);
			setRotateAngle(leftArm, 0, 0, -(float) Math.PI / 2);
			setRotateAngle(spin, 0, ageInTicks / 10 * (float) Math.PI * 2, 0);
		}
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
