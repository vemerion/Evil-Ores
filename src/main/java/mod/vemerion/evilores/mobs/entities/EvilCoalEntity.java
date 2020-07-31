package mod.vemerion.evilores.mobs.entities;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EvilCoalEntity extends CreatureEntity implements IMob {

	public EvilCoalEntity(EntityType<? extends EvilCoalEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new FlyingMovementController(this, 90, false);
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return EvilOres.EVIL_COAL_SOUND;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(3, new EvilCoalEntity.WanderGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue((double) 1F);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.3F);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(13);
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
			public boolean canEntityStandOnPos(BlockPos pos) {
				return true;
			}
		};
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanSwim(false);
		flyingpathnavigator.setCanEnterDoors(true);
		return flyingpathnavigator;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	class EvilCoalNearestAttackableTargetGoal extends NearestAttackableTargetGoal<PlayerEntity> {

		public EvilCoalNearestAttackableTargetGoal(MobEntity goalOwnerIn) {
			super(goalOwnerIn, PlayerEntity.class, true);
		}

		@Override
		protected AxisAlignedBB getTargetableArea(double targetDistance) {
			return this.goalOwner.getBoundingBox().grow(targetDistance, targetDistance, targetDistance);
		}

	}

	class WanderGoal extends Goal {
		private EvilCoalEntity entity;

		WanderGoal(EvilCoalEntity entity) {
			this.entity = entity;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return entity.getNavigator().noPath() && entity.rand.nextInt(10) < 5 && entity.getAttackTarget() == null;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return !entity.getNavigator().noPath() && entity.getAttackTarget() == null;
		}

		@Override
		public void startExecuting() {
			Vec3d randomLocation = this.getRandomLocation();
			if (randomLocation != null) {
				entity.getNavigator().setPath(entity.getNavigator().getPathToPos(new BlockPos(randomLocation), 1),
						1.0D);
			}

		}
		
		@Override
		public void resetTask() {
			entity.getNavigator().clearPath();
		}

		@Nullable
		private Vec3d getRandomLocation() {
			Random rand = entity.getRNG();
			List<Vec3d> positions = nearbyAir();

			if (!positions.isEmpty())
				return positions.get(rand.nextInt(positions.size()));
			else
				return null;
		}

		private List<Vec3d> nearbyAir() {
			List<Vec3d> positions = new ArrayList<>();
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						BlockPos pos = entity.getPosition().add(x, y, z);
						if (entity.world.isAirBlock(pos)) {
							positions.add(new Vec3d(pos));
						}
					}
				}
			}
			return positions;
		}
	}
}
