package mod.vemerion.evilores;

import java.util.Random;

import mod.vemerion.evilores.mobs.entities.EvilCoalEntity;
import mod.vemerion.evilores.mobs.entities.EvilOreOriginEntity;
import mod.vemerion.evilores.mobs.entities.EvilGoldEntity;
import mod.vemerion.evilores.mobs.entities.EvilIronEntity;
import mod.vemerion.evilores.mobs.entities.EvilLapisEntity;
import mod.vemerion.evilores.mobs.entities.EvilQuartzEntity;
import mod.vemerion.evilores.mobs.entities.EvilRedstoneEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = EvilOres.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

	@SubscribeEvent
	public static void spawnEvilOres(BreakEvent event) {
		World world = event.getWorld().getWorld();
		PlayerEntity player = event.getPlayer();
		BlockPos blockPos = event.getPos();
		Vec3d position = new Vec3d(blockPos);
		Random rand = world.getRandom();
		if (rand.nextDouble() < 0.2) {
			if (Blocks.IRON_ORE.equals(event.getState().getBlock())) {
				Entity entity = new EvilIronEntity(EvilOres.EVIL_IRON_ENTITY, world);
				spawn(entity, world, position);
			} else if (Blocks.GOLD_ORE.equals(event.getState().getBlock())) {
				Entity entity = new EvilGoldEntity(EvilOres.EVIL_GOLD_ENTITY, world);
				spawn(entity, world, position);
			} else if (Blocks.DIAMOND_ORE.equals(event.getState().getBlock())) {
				Entity entity = new EvilOreOriginEntity(EvilOres.EVIL_ORE_ORIGIN_ENTITY, world, "evil_diamond_entity",
						"textures/entity/evil_diamond_origin.png", 3.5f);
				spawn(entity, world, position);
			} else if (Blocks.LAPIS_ORE.equals(event.getState().getBlock())) {
				EvilLapisEntity evilLapis = new EvilLapisEntity(EvilOres.EVIL_LAPIS_ENTITY, world, blockPos);
				evilLapis.setDirection(Direction.getFacingDirections(player)[0].getOpposite());
				spawn(evilLapis, world, position);
			} else if (Blocks.COAL_ORE.equals(event.getState().getBlock())) {
				Entity entity = new EvilCoalEntity(EvilOres.EVIL_COAL_ENTITY, world);
				spawn(entity, world, position);
			} else if (Blocks.NETHER_QUARTZ_ORE.equals(event.getState().getBlock())) {
				EvilQuartzEntity evilQuartz = new EvilQuartzEntity(EvilOres.EVIL_QUARTZ_ENTITY, world, blockPos);
				evilQuartz.setDirection(Direction.getFacingDirections(player)[0].getOpposite());
				spawn(evilQuartz, world, position);
			} else if (Blocks.EMERALD_ORE.equals(event.getState().getBlock())) {
				Entity entity = new EvilOreOriginEntity(EvilOres.EVIL_ORE_ORIGIN_ENTITY, world, "evil_emerald_entity",
						"textures/entity/evil_emerald_origin.png", 4.5f);
				spawn(entity, world, position);
			} else if (Blocks.REDSTONE_ORE.equals(event.getState().getBlock())) {
				for (int i = 0; i < rand.nextInt(5) + 4; i++) {
					Entity entity = new EvilRedstoneEntity(EvilOres.EVIL_REDSTONE_ENTITY, world);
					spawn(entity, world, position.add(rand.nextDouble() * 0.4 - 0.2, 0, rand.nextDouble() * 0.4 - 0.2));
				}
			}
		}
	}

	private static void spawn(Entity entity, World world, Vec3d position) {
		entity.setPosition(position.getX() + 0.5, position.getY(), position.getZ() + 0.5);
		world.addEntity(entity);
	}
}
