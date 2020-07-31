package mod.vemerion.evilores;

import mod.vemerion.evilores.mobs.renderers.EvilCoalRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilDiamondRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilEmeraldRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilGoldRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilIronRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilLapisRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilOreOriginRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilQuartzRenderer;
import mod.vemerion.evilores.mobs.renderers.EvilRedstoneRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = EvilOres.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
	@SubscribeEvent
	public static void onRegister(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_REDSTONE_ENTITY, (renderManager) -> new EvilRedstoneRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_IRON_ENTITY, (renderManager) -> new EvilIronRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_GOLD_ENTITY, (renderManager) -> new EvilGoldRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_COAL_ENTITY, (renderManager) -> new EvilCoalRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_DIAMOND_ENTITY, (renderManager) -> new EvilDiamondRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.ORE_ARROW_ENTITY, (renderManager) -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_ORE_ORIGIN_ENTITY, (renderManager) -> new EvilOreOriginRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_LAPIS_ENTITY, (renderManager) -> new EvilLapisRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_QUARTZ_ENTITY, (renderManager) -> new EvilQuartzRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EvilOres.EVIL_EMERALD_ENTITY, (renderManager) -> new EvilEmeraldRenderer(renderManager));
	}
}
