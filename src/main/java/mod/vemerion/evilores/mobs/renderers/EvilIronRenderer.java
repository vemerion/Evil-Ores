package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilIronEntity;
import mod.vemerion.evilores.mobs.models.EvilIronModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilIronRenderer extends MobRenderer<EvilIronEntity, EvilIronModel<EvilIronEntity>> {
	private static final ResourceLocation EVIL_IRON_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_iron.png");

	public EvilIronRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilIronModel<EvilIronEntity>(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilIronEntity entity) {
		return EVIL_IRON_TEXTURES;
	}

}
