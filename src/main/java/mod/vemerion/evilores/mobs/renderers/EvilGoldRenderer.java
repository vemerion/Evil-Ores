package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilGoldEntity;
import mod.vemerion.evilores.mobs.models.EvilGoldModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilGoldRenderer extends MobRenderer<EvilGoldEntity, EvilGoldModel<EvilGoldEntity>> {
	private static final ResourceLocation EVIL_GOLD_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_gold.png");

	public EvilGoldRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilGoldModel<EvilGoldEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilGoldEntity entity) {
		return EVIL_GOLD_TEXTURES;
	}

}
