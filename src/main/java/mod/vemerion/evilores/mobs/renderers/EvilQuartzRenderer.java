package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilQuartzEntity;
import mod.vemerion.evilores.mobs.models.EvilQuartzModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilQuartzRenderer extends MobRenderer<EvilQuartzEntity, EvilQuartzModel<EvilQuartzEntity>> {
	private static final ResourceLocation EVIL_QUARTZ_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_quartz.png");

	public EvilQuartzRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilQuartzModel<EvilQuartzEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilQuartzEntity entity) {
		return EVIL_QUARTZ_TEXTURES;
	}

}
