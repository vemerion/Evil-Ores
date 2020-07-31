package mod.vemerion.evilores.mobs.entities;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EvilQuartzEntity extends StationaryEntity {

	public EvilQuartzEntity(EntityType<? extends EvilQuartzEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public EvilQuartzEntity(EntityType<? extends EvilQuartzEntity> type, World worldIn, BlockPos pos) {
		super(type, worldIn, pos);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EvilQuartzEntity.AttackGoal(this));
	}

	public class AttackGoal extends Goal {
		private EvilQuartzEntity entity;
		private int cooldown = 40;

		public AttackGoal(EvilQuartzEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void tick() {
			if (cooldown-- < 0) {
				playSound(EvilOres.EVIL_QUARTZ_SHOOT_SOUND, 1,
						((entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.2F + 1.0F) / 0.8F);
				shoot();
				cooldown = 40;
			}
		}

		public void shoot() {
			Vec3d dir = new Vec3d(entity.getDirection().getDirectionVec());
			Vec3d start = new Vec3d(getPosX() + dir.getX() * 0.5,
					getPosY() + dir.getY() * 0.5 + Math.abs(dir.getX() + dir.getZ()) * 0.6,
					getPosZ() + dir.getZ() * 0.5);
			dir = dir.scale(1000);
			FireballEntity fireballentity = new FireballEntity(world, entity, dir.getX(), dir.getY(), dir.getZ());
			fireballentity.explosionPower = 1;
			fireballentity.setPosition(start.getX(), start.getY(), start.getZ());
			world.addEntity(fireballentity);
		}

	}
}
