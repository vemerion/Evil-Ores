package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilCoalEntity;
import mod.vemerion.evilores.mobs.models.EvilCoalModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilCoalRenderer extends MobRenderer<EvilCoalEntity, EvilCoalModel<EvilCoalEntity>> {
	private static final ResourceLocation EVIL_COAL_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_coal.png");

	public EvilCoalRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilCoalModel<EvilCoalEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilCoalEntity entity) {
		return EVIL_COAL_TEXTURES;
	}

}
