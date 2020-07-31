package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilDiamondEntity;
import mod.vemerion.evilores.mobs.models.EvilDiamondModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilDiamondRenderer extends MobRenderer<EvilDiamondEntity, EvilDiamondModel<EvilDiamondEntity>> {
	private static final ResourceLocation EVIL_DIAMOND_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_diamond.png");

	public EvilDiamondRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilDiamondModel<EvilDiamondEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilDiamondEntity entity) {
		return EVIL_DIAMOND_TEXTURES;
	}

}
