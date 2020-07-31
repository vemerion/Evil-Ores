package mod.vemerion.evilores.mobs.renderers;

import mod.vemerion.evilores.EvilOres;
import mod.vemerion.evilores.mobs.entities.EvilRedstoneEntity;
import mod.vemerion.evilores.mobs.models.EvilRedstoneModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EvilRedstoneRenderer extends MobRenderer<EvilRedstoneEntity, EvilRedstoneModel<EvilRedstoneEntity>> {
	private static final ResourceLocation EVIL_REDSTONE_TEXTURES = new ResourceLocation(EvilOres.MODID, "textures/entity/evil_redstone.png");

	public EvilRedstoneRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new EvilRedstoneModel<EvilRedstoneEntity>(), 0.2f);
	}

	@Override
	public ResourceLocation getEntityTexture(EvilRedstoneEntity entity) {
		return EVIL_REDSTONE_TEXTURES;
	}

}
