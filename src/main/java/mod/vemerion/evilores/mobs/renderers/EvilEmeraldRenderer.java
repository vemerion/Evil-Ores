package mod.vemerion.evilores.mobs.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilEmeraldEntity;
import mod.vemerion.evilores.mobs.models.EvilEmeraldModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilEmeraldRenderer extends MobRenderer<EvilEmeraldEntity, EvilEmeraldModel> {
	private static final ResourceLocation EVIL_EMERALD_TEXTURES = new ResourceLocation(EvilOres.MODID,
			"textures/entity/evil_emerald.png");

	public EvilEmeraldRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilEmeraldModel(), 1f);

	}

	protected void preRenderCallback(EvilEmeraldEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
	}

	@Override
	public ResourceLocation getEntityTexture(EvilEmeraldEntity entity) {
		return EVIL_EMERALD_TEXTURES;
	}

}
