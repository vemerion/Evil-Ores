package mod.vemerion.evilores.mobs.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.evilores.mobs.entities.EvilOreOriginEntity;
import mod.vemerion.evilores.mobs.models.EvilOreOriginModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class EvilOreOriginRenderer extends EntityRenderer<EvilOreOriginEntity> {
	private final EvilOreOriginModel<EvilOreOriginEntity> model = new EvilOreOriginModel<>();

	public EvilOreOriginRenderer(EntityRendererManager rendererManager) {
		super(rendererManager);
	}

	@Override
	protected int getBlockLight(EvilOreOriginEntity entityIn, float partialTicks) {
		return 15;
	}

	@Override
	public void render(EvilOreOriginEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		model.setRotationAngles(entityIn, 0, 0, entityIn.ticksExisted + partialTicks, 0, 0);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(getEntityTexture(entityIn)));
		this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F,
				1.0F);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilOreOriginEntity entity) {
		return entity.getTexture();
	}

}