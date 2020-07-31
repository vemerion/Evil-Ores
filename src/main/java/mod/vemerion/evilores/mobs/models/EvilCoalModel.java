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
public class EvilCoalModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer model;
	public ModelRenderer body;
	public ModelRenderer topRightWing;
	public ModelRenderer topLeftWing;
	public ModelRenderer bottomRightWing;
	public ModelRenderer bottomLeftWing;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer leftEye;
	public ModelRenderer rightEye;

	public EvilCoalModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.rightEye = new ModelRenderer(this, 30, 0);
		this.rightEye.setRotationPoint(0.0F, -2.0F, -5.0F);
		this.rightEye.addBox(-6.5F, -2.0F, -3.0F, 5.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightEye, 0.08726646259971647F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.leg3 = new ModelRenderer(this, 0, 0);
		this.leg3.setRotationPoint(1.0F, 5.0F, -2.0F);
		this.leg3.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.bottomRightWing = new ModelRenderer(this, 0, 20);
		this.bottomRightWing.mirror = true;
		this.bottomRightWing.setRotationPoint(-5.0F, 0.0F, 0.0F);
		this.bottomRightWing.addBox(-9.0F, 0.0F, -3.0F, 9.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(bottomRightWing, 0.0F, 0.0F, -0.47123889803846897F);
		this.bottomLeftWing = new ModelRenderer(this, 20, 20);
		this.bottomLeftWing.mirror = true;
		this.bottomLeftWing.setRotationPoint(5.0F, 0.0F, 0.0F);
		this.bottomLeftWing.addBox(0.0F, 0.0F, -3.0F, 9.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(bottomLeftWing, 0.0F, 0.0F, 0.47123889803846897F);
		this.leg2 = new ModelRenderer(this, 0, 0);
		this.leg2.setRotationPoint(-2.0F, 5.0F, 1.0F);
		this.leg2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.topRightWing = new ModelRenderer(this, 0, 20);
		this.topRightWing.mirror = true;
		this.topRightWing.setRotationPoint(-5.0F, -4.0F, 0.0F);
		this.topRightWing.addBox(-9.0F, 0.0F, -3.0F, 9.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(topRightWing, 0.0F, 0.0F, 0.47123889803846897F);
		this.model = new ModelRenderer(this, 64, 64);
		this.model.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.setRotateAngle(model, 0.17453292519943295F, 0.0F, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 0);
		this.leg1.setRotationPoint(1.0F, 5.0F, 1.0F);
		this.leg1.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.topLeftWing = new ModelRenderer(this, 20, 20);
		this.topLeftWing.mirror = true;
		this.topLeftWing.setRotationPoint(5.0F, -4.0F, 0.0F);
		this.topLeftWing.addBox(0.0F, 0.0F, -3.0F, 9.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(topLeftWing, 0.0F, 0.0F, -0.47123889803846897F);
		this.leftEye = new ModelRenderer(this, 30, 0);
		this.leftEye.setRotationPoint(0.0F, -2.0F, -5.0F);
		this.leftEye.addBox(1.5F, -2.0F, -3.0F, 5.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftEye, 0.08726646259971647F, 0.0F, 0.0F);
		this.leg4 = new ModelRenderer(this, 0, 0);
		this.leg4.setRotationPoint(-2.0F, 5.0F, -2.0F);
		this.leg4.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.rightEye);
		this.model.addChild(this.body);
		this.model.addChild(this.leg3);
		this.model.addChild(this.bottomRightWing);
		this.model.addChild(this.bottomLeftWing);
		this.model.addChild(this.leg2);
		this.model.addChild(this.topRightWing);
		this.model.addChild(this.leg1);
		this.model.addChild(this.topLeftWing);
		this.body.addChild(this.leftEye);
		this.model.addChild(this.leg4);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.model).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		
    	setRotateAngle(model, headPitch * ((float)Math.PI / 180F), netHeadYaw * ((float)Math.PI / 180F), 0);

		setRotateAngle(topRightWing, 0, 0, -Helper.pingpong(-(float) Math.PI / 4f, (float) Math.PI / 4f, ageInTicks / 3));
		setRotateAngle(topLeftWing, 0, 0, Helper.pingpong(-(float) Math.PI / 4f, (float) Math.PI / 4f, ageInTicks / 3));
		setRotateAngle(bottomLeftWing, 0, 0, Helper.pingpong(-(float) Math.PI / 4f, (float) Math.PI / 4f, ageInTicks / 3));
		setRotateAngle(bottomRightWing, 0, 0, -Helper.pingpong(-(float) Math.PI / 4f, (float) Math.PI / 4f, ageInTicks / 3));
		
		setRotateAngle(leg1, Helper.pingpong(-(float) Math.PI / 8f, (float) Math.PI / 8f, ageInTicks / 6), 0, 0);
		setRotateAngle(leg2, Helper.pingpong(-(float) Math.PI / 8f, (float) Math.PI / 8f, ageInTicks / 6 + 0.3f), 0, 0);
		setRotateAngle(leg3, Helper.pingpong(-(float) Math.PI / 8f, (float) Math.PI / 8f, ageInTicks / 6 + 0.55f), 0, 0);
		setRotateAngle(leg4, Helper.pingpong(-(float) Math.PI / 8f, (float) Math.PI / 8f, ageInTicks / 6 - 0.2f), 0, 0);
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
