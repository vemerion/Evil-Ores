package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilLapisEntity;
import mod.vemerion.evilores.mobs.models.EvilLapisModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilLapisRenderer extends MobRenderer<EvilLapisEntity, EvilLapisModel<EvilLapisEntity>> {
	private static final ResourceLocation EVIL_LAPIS_MOUTH_CLOSED_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_lapis_mouth_closed.png");
	private static final ResourceLocation EVIL_LAPIS_MOUTH_OPEN_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_lapis_mouth_open.png");

	public EvilLapisRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilLapisModel<EvilLapisEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilLapisEntity entity) {
		return entity.isAttacking() ? EVIL_LAPIS_MOUTH_OPEN_TEXTURES : EVIL_LAPIS_MOUTH_CLOSED_TEXTURES;
	}

}
