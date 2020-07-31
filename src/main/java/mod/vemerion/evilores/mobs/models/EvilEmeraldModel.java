package mod.vemerion.evilores.mobs.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.mobs.entities.EvilEmeraldEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class EvilEmeraldModel extends EntityModel<EvilEmeraldEntity> {
	public ModelRenderer frontBody;
	public ModelRenderer upperJaw;
	public ModelRenderer backBody;
	public ModelRenderer leftFrontUpperLeg;
	public ModelRenderer rightFrontUpperLeg;
	public ModelRenderer rightBackUpperLeg;
	public ModelRenderer leftBackUpperLeg;
	public ModelRenderer lowerJaw;
	public ModelRenderer lowerHorn;
	public ModelRenderer lowerHorn_1;
	public ModelRenderer upperHorn;
	public ModelRenderer upperHorn_1;
	public ModelRenderer leftFrontLowerLeg;
	public ModelRenderer leftFrontFoot;
	public ModelRenderer rightFrontLowerLeg;
	public ModelRenderer rightFrontFoot;
	public ModelRenderer rightBackLowerLeg;
	public ModelRenderer rightBackFoot;
	public ModelRenderer leftBackLowerLeg;
	public ModelRenderer leftBackFoot;

	private float startShake;
	private boolean prevShakeHead;

	public EvilEmeraldModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.frontBody = new ModelRenderer(this, 0, 0);
		this.frontBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frontBody.addBox(-12.0F, -12.0F, -16.0F, 24.0F, 24.0F, 32.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(frontBody, 0.35185837453889574F, 0.0F, 0.0F);
		this.backBody = new ModelRenderer(this, 24, 74);
		this.backBody.setRotationPoint(0.0F, 5.0F, 15.0F);
		this.backBody.addBox(-10.0F, -20.0F, 0.0F, 20.0F, 20.0F, 24.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(backBody, -0.1563815016444822F, 0.0F, 0.0F);
		this.leftFrontFoot = new ModelRenderer(this, 84, 56);
		this.leftFrontFoot.setRotationPoint(2.0F, 13.0F, 0.0F);
		this.leftFrontFoot.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftFrontFoot, -0.03909537541112055F, -0.23457224414434488F, 0.27366763203903305F);
		this.leftFrontUpperLeg = new ModelRenderer(this, 86, 0);
		this.leftFrontUpperLeg.setRotationPoint(12.0F, 0.0F, 0.0F);
		this.leftFrontUpperLeg.addBox(0.0F, 0.0F, -5.0F, 10.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftFrontUpperLeg, 0.5473352640780661F, 0.19547687289441354F, -0.3127630032889644F);
		this.leftBackUpperLeg = new ModelRenderer(this, 86, 0);
		this.leftBackUpperLeg.setRotationPoint(10.0F, 0.0F, 28.0F);
		this.leftBackUpperLeg.addBox(0.0F, 0.0F, -5.0F, 10.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftBackUpperLeg, 0.5473352640780661F, 0.19547687289441354F, -0.3127630032889644F);
		this.upperHorn = new ModelRenderer(this, 16, 0);
		this.upperHorn.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.upperHorn.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(upperHorn, -0.1563815016444822F, 0.0F, 0.0F);
		this.upperHorn_1 = new ModelRenderer(this, 16, 0);
		this.upperHorn_1.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.upperHorn_1.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(upperHorn_1, -0.1563815016444822F, 0.0F, 0.0F);
		this.upperJaw = new ModelRenderer(this, 0, 56);
		this.upperJaw.setRotationPoint(0.0F, 5.0F, -17.0F);
		this.upperJaw.addBox(-6.0F, -6.0F, -12.0F, 12.0F, 12.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(upperJaw, 0.11728612207217244F, 0.0F, 0.0F);
		this.rightFrontFoot = new ModelRenderer(this, 84, 56);
		this.rightFrontFoot.setRotationPoint(-3.0F, 13.0F, 0.0F);
		this.rightFrontFoot.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightFrontFoot, 0.0781907508222411F, 0.0781907508222411F, -0.1563815016444822F);
		this.leftFrontLowerLeg = new ModelRenderer(this, 0, 9);
		this.leftFrontLowerLeg.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.leftFrontLowerLeg.addBox(0.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftFrontLowerLeg, -0.5473352640780661F, 0.0F, 0.0F);
		this.rightBackFoot = new ModelRenderer(this, 84, 56);
		this.rightBackFoot.setRotationPoint(-3.0F, 13.0F, 0.0F);
		this.rightBackFoot.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightBackFoot, 0.0781907508222411F, 0.0781907508222411F, -0.1563815016444822F);
		this.rightBackUpperLeg = new ModelRenderer(this, 86, 0);
		this.rightBackUpperLeg.setRotationPoint(-10.0F, 0.0F, 28.0F);
		this.rightBackUpperLeg.addBox(-10.0F, 0.0F, -5.0F, 10.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightBackUpperLeg, 0.4300491170387584F, -0.11728612207217244F, 0.19547687289441354F);
		this.rightFrontLowerLeg = new ModelRenderer(this, 0, 9);
		this.rightFrontLowerLeg.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.rightFrontLowerLeg.addBox(-8.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightFrontLowerLeg, -0.5473352640780661F, 0.0F, 0.0F);
		this.leftBackLowerLeg = new ModelRenderer(this, 0, 9);
		this.leftBackLowerLeg.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.leftBackLowerLeg.addBox(0.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftBackLowerLeg, -0.5473352640780661F, 0.0F, 0.0F);
		this.leftBackFoot = new ModelRenderer(this, 84, 56);
		this.leftBackFoot.setRotationPoint(2.0F, 13.0F, 0.0F);
		this.leftBackFoot.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftBackFoot, -0.03909537541112055F, -0.23457224414434488F, 0.27366763203903305F);
		this.rightBackLowerLeg = new ModelRenderer(this, 0, 9);
		this.rightBackLowerLeg.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.rightBackLowerLeg.addBox(-8.0F, 0.0F, -4.0F, 8.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightBackLowerLeg, -0.5473352640780661F, 0.0F, 0.0F);
		this.lowerJaw = new ModelRenderer(this, 48, 56);
		this.lowerJaw.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.lowerJaw.addBox(-6.0F, 0.0F, -12.0F, 12.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lowerJaw, 0.4300491170387584F, 0.0F, 0.0F);
		this.lowerHorn = new ModelRenderer(this, 0, 0);
		this.lowerHorn.setRotationPoint(0.0F, -5.0F, -7.0F);
		this.lowerHorn.addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lowerHorn, 0.35185837453889574F, 0.0F, 0.0F);
		this.rightFrontUpperLeg = new ModelRenderer(this, 86, 0);
		this.rightFrontUpperLeg.setRotationPoint(-12.0F, 0.0F, 0.0F);
		this.rightFrontUpperLeg.addBox(-10.0F, 0.0F, -5.0F, 10.0F, 12.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightFrontUpperLeg, 0.4300491170387584F, -0.11728612207217244F, 0.19547687289441354F);
		this.lowerHorn_1 = new ModelRenderer(this, 0, 0);
		this.lowerHorn_1.setRotationPoint(0.0F, -1.0F, -12.0F);
		this.lowerHorn_1.addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lowerHorn_1, 0.8991936386169619F, 0.0F, 0.0F);
		this.leftFrontLowerLeg.addChild(this.leftFrontFoot);
		this.lowerHorn.addChild(this.upperHorn);
		this.lowerHorn_1.addChild(this.upperHorn_1);
		this.rightFrontLowerLeg.addChild(this.rightFrontFoot);
		this.leftFrontUpperLeg.addChild(this.leftFrontLowerLeg);
		this.rightBackLowerLeg.addChild(this.rightBackFoot);
		this.rightFrontUpperLeg.addChild(this.rightFrontLowerLeg);
		this.leftBackUpperLeg.addChild(this.leftBackLowerLeg);
		this.leftBackLowerLeg.addChild(this.leftBackFoot);
		this.rightBackUpperLeg.addChild(this.rightBackLowerLeg);
		this.upperJaw.addChild(this.lowerJaw);
		this.upperJaw.addChild(this.lowerHorn);
		this.upperJaw.addChild(this.lowerHorn_1);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.frontBody, this.backBody, this.leftFrontUpperLeg, this.leftBackUpperLeg, this.upperJaw,
				this.rightBackUpperLeg, this.rightFrontUpperLeg).forEach((modelRenderer) -> {
					modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
							alpha);
				});
	}

	@Override
	public void setRotationAngles(EvilEmeraldEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.leftBackUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.8f;
		this.rightFrontUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F
				* limbSwingAmount * 0.8f;
		this.leftFrontUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.8f;
		this.rightBackUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F
				* limbSwingAmount * 0.8f;
		
		// Shake head
		if (entityIn.isShakingHead()) {
			if (!prevShakeHead)
				startShake = ageInTicks;
			setRotateAngle(upperJaw, 0, 0, MathHelper.sin((ageInTicks - startShake) * (float)Math.PI * 0.1f) * 0.2f);
		} else if (Math.abs(upperJaw.rotateAngleZ) > 0.01f) {
			setRotateAngle(upperJaw, 0, 0, MathHelper.sin((ageInTicks - startShake) * (float)Math.PI * 0.1f) * 0.2f);

		}
		prevShakeHead = entityIn.isShakingHead();
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
