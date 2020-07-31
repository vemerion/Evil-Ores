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
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(EvilOres.MODID)
public class EvilOres {
	public static final String MODID = "evil-ores";
	
	@ObjectHolder(EvilOres.MODID + ":evil_redstone_entity")
	public static final EntityType<EvilRedstoneEntity> EVIL_REDSTONE_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_iron_entity")
	public static final EntityType<EvilIronEntity> EVIL_IRON_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_gold_entity")
	public static final EntityType<EvilGoldEntity> EVIL_GOLD_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_coal_entity")
	public static final EntityType<EvilCoalEntity> EVIL_COAL_ENTITY = null;

	@ObjectHolder(EvilOres.MODID + ":evil_diamond_entity")
	public static final EntityType<EvilDiamondEntity> EVIL_DIAMOND_ENTITY = null;

	@ObjectHolder(EvilOres.MODID + ":ore_arrow_entity")
	public static final EntityType<OreArrowEntity> ORE_ARROW_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_ore_origin_entity")
	public static final EntityType<EvilOreOriginEntity> EVIL_ORE_ORIGIN_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_lapis_entity")
	public static final EntityType<EvilLapisEntity> EVIL_LAPIS_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_quartz_entity")
	public static final EntityType<EvilQuartzEntity> EVIL_QUARTZ_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_emerald_entity")
	public static final EntityType<EvilEmeraldEntity> EVIL_EMERALD_ENTITY = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_iron_move_sound")
	public static final SoundEvent EVIL_IRON_MOVE_SOUND = null;
	
	@ObjectHolder(EvilOres.MODID + ":shoot_sound")
	public static final SoundEvent SHOOT_SOUND = null;
	
	@ObjectHolder(EvilOres.MODID + ":spin_sound")
	public static final SoundEvent SPIN_SOUND = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_coal_sound")
	public static final SoundEvent EVIL_COAL_SOUND = null;
	
	@ObjectHolder(EvilOres.MODID + ":evil_quartz_shoot_sound")
	public static final SoundEvent EVIL_QUARTZ_SHOOT_SOUND = null;
}
