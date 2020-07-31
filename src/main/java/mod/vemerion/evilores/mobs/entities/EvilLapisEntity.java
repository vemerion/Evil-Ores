package mod.vemerion.evilores.mobs.entities;


import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EvilLapisEntity extends StationaryEntity {
	
	private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(StationaryEntity.class,
			DataSerializers.BOOLEAN);

	public EvilLapisEntity(EntityType<? extends EvilLapisEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public EvilLapisEntity(EntityType<? extends EvilLapisEntity> type, World worldIn, BlockPos pos) {
		super(type, worldIn, pos);
	}

	protected void registerData() {
		super.registerData();
		dataManager.register(ATTACKING, false);
	}

	public boolean isAttacking() {
		return dataManager.get(ATTACKING);
	}

	public void setAttacking(boolean value) {
		this.dataManager.set(ATTACKING, value);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EvilLapisEntity.AttackGoal(this));
	}

	public class AttackGoal extends Goal {
		private EvilLapisEntity entity;
		private int cooldown = 60;
		private int duration;

		public AttackGoal(EvilLapisEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void tick() {
			if (--duration > 0) {
				if (duration % 3 == 0)
					shoot();
			} else if (cooldown-- < 0) {
				playSound(EvilOres.SPIN_SOUND, 3,
						((entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.2F + 1.0F) / 0.8F);
				entity.setAttacking(true);
				duration = 20;
				cooldown = 60;
			} else {
				entity.setAttacking(false);
			}
		}

		public void shoot() {
			Vec3d dir = new Vec3d(entity.getDirection().getDirectionVec());
			Vec3d start = new Vec3d(getPosX() + dir.getX() * 0.2,
					getPosY() + dir.getY() * 0.2 + Math.abs(dir.getX() + dir.getZ()) * 0.2,
					getPosZ() + dir.getZ() * 0.2);
			OreArrowEntity abstractarrowentity = new OreArrowEntity(world, start.getX(), start.getY(), start.getZ(),
					Items.LAPIS_LAZULI, 3);
			abstractarrowentity.shoot(dir.getX() + entity.getRNG().nextDouble() * 0.4 - 0.2,
					dir.getY() + entity.getRNG().nextDouble() * 0.2,
					dir.getZ() + entity.getRNG().nextDouble() * 0.4 - 0.2, 1F, 0);
			entity.world.addEntity(abstractarrowentity);
		}
	}
}
