package mod.vemerion.evilores;

import mod.vemerion.evilores.mobs.entities.OreArrowEntity;
import mod.vemerion.evilores.mobs.entities.EvilCoalEntity;
import mod.vemerion.evilores.mobs.entities.EvilDiamondEntity;
import mod.vemerion.evilores.mobs.entities.EvilOreOriginEntity;
import mod.vemerion.evilores.mobs.entities.EvilEmeraldEntity;
import mod.vemerion.evilores.mobs.entities.EvilGoldEntity;
import mod.vemerion.evilores.mobs.entities.EvilIronEntity;
import mod.vemerion.evilores.mobs.entities.EvilLapisEntity;
import mod.vemerion.evilores.mobs.entities.EvilQuartzEntity;
import mod.vemerion.evilores.mobs.entities.EvilRedstoneEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = EvilOres.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	@SubscribeEvent
	public static void onRegisterEntity(RegistryEvent.Register<EntityType<?>> event) {
		EntityType<EvilRedstoneEntity> evilRedstone = EntityType.Builder.create(EvilRedstoneEntity::new, EntityClassification.CREATURE).size(0.5F, 0.9F).build("evil_redstone_entity");
		EntityType<EvilIronEntity> evilIron = EntityType.Builder.create(EvilIronEntity::new, EntityClassification.CREATURE).size(1, 1).build("evil_iron_entity");
		EntityType<EvilGoldEntity> evilGold = EntityType.Builder.create(EvilGoldEntity::new, EntityClassification.CREATURE).size(1, 1).build("evil_gold_entity");
		EntityType<EvilCoalEntity> evilCoal = EntityType.Builder.create(EvilCoalEntity::new, EntityClassification.CREATURE).size(1, 1).build("evil_coal_entity");
		EntityType<EvilDiamondEntity> evilDiamond = EntityType.Builder.create(EvilDiamondEntity::new, EntityClassification.CREATURE).size(1.5f, 2.5f).build("evil_diamond_entity");
		EntityType<OreArrowEntity> oreArrowEntity = EntityType.Builder.<OreArrowEntity>create(OreArrowEntity::new, EntityClassification.MISC).size(1, 1).build("ore_arrow_entity");
		EntityType<EvilOreOriginEntity> evilOreOriginEntity = EntityType.Builder.<EvilOreOriginEntity>create(EvilOreOriginEntity::new, EntityClassification.MISC).size(1, 1).build("evil_ore_origin_entity");
		EntityType<EvilLapisEntity> evilLapisEntity = EntityType.Builder.<EvilLapisEntity>create(EvilLapisEntity::new, EntityClassification.CREATURE).size(1, 1).build("evil_lapis_entity");
		EntityType<EvilQuartzEntity> evilQuartzEntity = EntityType.Builder.<EvilQuartzEntity>create(EvilQuartzEntity::new, EntityClassification.CREATURE).size(1, 1).build("evil_quartz_entity");
		EntityType<EvilEmeraldEntity> evilEmeraldEntity = EntityType.Builder.<EvilEmeraldEntity>create(EvilEmeraldEntity::new, EntityClassification.CREATURE).size(2.8f, 2.3f).build("evil_emerald_entity");

		
		event.getRegistry().register(setup(evilRedstone, "evil_redstone_entity"));
		event.getRegistry().register(setup(evilIron, "evil_iron_entity"));
		event.getRegistry().register(setup(evilGold, "evil_gold_entity"));
		event.getRegistry().register(setup(evilCoal, "evil_coal_entity"));
		event.getRegistry().register(setup(evilDiamond, "evil_diamond_entity"));
		event.getRegistry().register(setup(oreArrowEntity, "ore_arrow_entity"));
		event.getRegistry().register(setup(evilOreOriginEntity, "evil_ore_origin_entity"));
		event.getRegistry().register(setup(evilLapisEntity, "evil_lapis_entity"));
		event.getRegistry().register(setup(evilQuartzEntity, "evil_quartz_entity"));
		event.getRegistry().register(setup(evilEmeraldEntity, "evil_emerald_entity"));

	}
	
	@SubscribeEvent
	public static void onRegisterSound(RegistryEvent.Register<SoundEvent> event) {
		SoundEvent EvilIronMoveSound = new SoundEvent(new ResourceLocation(EvilOres.MODID, "evil_iron_move_sound"));
		SoundEvent shootSound = new SoundEvent(new ResourceLocation(EvilOres.MODID, "shoot_sound"));
		SoundEvent spinSound = new SoundEvent(new ResourceLocation(EvilOres.MODID, "spin_sound"));
		SoundEvent evilCoalSound = new SoundEvent(new ResourceLocation(EvilOres.MODID, "evil_coal_sound"));
		SoundEvent evilQuartzShootSound = new SoundEvent(new ResourceLocation(EvilOres.MODID, "evil_quartz_shoot_sound"));

		event.getRegistry().register(setup(EvilIronMoveSound, "evil_iron_move_sound"));
		event.getRegistry().register(setup(shootSound, "shoot_sound"));
		event.getRegistry().register(setup(spinSound, "spin_sound"));
		event.getRegistry().register(setup(evilCoalSound, "evil_coal_sound"));
		event.getRegistry().register(setup(evilQuartzShootSound, "evil_quartz_shoot_sound"));

	}
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(EvilOres.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
