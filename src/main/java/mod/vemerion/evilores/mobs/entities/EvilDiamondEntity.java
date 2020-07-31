package mod.vemerion.evilores.mobs.entities;

import java.util.Random;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EvilDiamondEntity extends CreatureEntity implements IMob {

	private static final DataParameter<Boolean> SPINNING = EntityDataManager.createKey(EvilDiamondEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SHOOTING = EntityDataManager.createKey(EvilDiamondEntity.class,
			DataSerializers.BOOLEAN);

	public EvilDiamondEntity(EntityType<? extends EvilDiamondEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerData() {
		super.registerData();
		dataManager.register(SPINNING, false);
		dataManager.register(SHOOTING, false);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EvilDiamondEntity.SpinGoal(this));
		this.goalSelector.addGoal(2, new EvilDiamondEntity.ShootGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.45, true));
		this.targetSelector.addGoal(1,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213811_1_) -> {
					return Math.abs(p_213811_1_.getPosY() - this.getPosY()) <= 7.0D;
				}));
	}

	@Override
	public void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		return false;
	}

	public boolean isSpinning() {
		return dataManager.get(SPINNING);
	}

	public void setSpinning(boolean value) {
		this.dataManager.set(SPINNING, value);
	}

	public boolean isShooting() {
		return dataManager.get(SHOOTING);
	}

	public void setShooting(boolean value) {
		this.dataManager.set(SHOOTING, value);
	}

	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		Vec3d start = new Vec3d(getPosX() + Math.cos(Math.toRadians(rotationYaw - 180)) * 1, getPosYEye() - 1,
				getPosZ() + Math.sin(Math.toRadians(rotationYaw - 180)) * 1);
		OreArrowEntity abstractarrowentity = new OreArrowEntity(world, start.getX(), start.getY(),
				start.getZ(), Items.DIAMOND, 6);
		double d0 = target.getPosX() - start.getX();
		double d1 = target.getPosYHeight(0.3333333333333333D) - start.getY();
		double d2 = target.getPosZ() - start.getZ();
		double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
		abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, 0);
		this.playSound(EvilOres.SHOOT_SOUND, 1.5F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.world.addEntity(abstractarrowentity);
	}

	public class SpinGoal extends Goal {
		private EvilDiamondEntity entity;
		private int duration;
		private int cooldown;

		public SpinGoal(EvilDiamondEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return entity.getAttackTarget() != null;
		}

		@Override
		public void resetTask() {
			entity.setSpinning(false);
		}

		@Override
		public void tick() {
			if (--duration > 0) {
				if (!entity.world.isRemote) {
					AxisAlignedBB attack = entity.getBoundingBox().grow(1, 0, 1);
					for (Entity e : entity.world.getEntitiesInAABBexcluding(entity, attack, null)) {
						if (e instanceof PlayerEntity) {
							e.attackEntityFrom(DamageSource.causeMobDamage(entity), 8);
						}
					}
				}
			} else if (cooldown-- < 0) {
				playSound(EvilOres.SPIN_SOUND, 3,
						((entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.2F + 1.0F) / 0.8F);
				entity.setSpinning(true);
				duration = 20;
				cooldown = entity.getRNG().nextInt(50) + 50;
			} else {
				entity.setSpinning(false);
			}
		}
	}

	public class ShootGoal extends Goal {
		private EvilDiamondEntity entity;
		private int cooldown;
		private double maxAttackDistance = 100;

		public ShootGoal(EvilDiamondEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			LivingEntity target = entity.getAttackTarget();
			return entity.getAttackTarget() != null
					&& entity.getDistanceSq(target.getPosX(), target.getPosY(), target.getPosZ()) > 10;
		}

		@Override
		public void tick() {
			LivingEntity target = this.entity.getAttackTarget();
			if (target != null) {
				double distance = this.entity.getDistanceSq(target.getPosX(), target.getPosY(), target.getPosZ());
				if (distance < maxAttackDistance) {
					if (cooldown-- > 0 && cooldown <= 20) {
						entity.setShooting(true);
					}

					if (cooldown < 0) {
						cooldown = 100;
						entity.setShooting(false);

						entity.attackEntityWithRangedAttack(target, 1);
					}
				} else {
					entity.setShooting(false);
				}
			}
		}
	}

	public class LookRandomlyGoal extends Goal {
		private EvilDiamondEntity entity;

		public LookRandomlyGoal(EvilDiamondEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return entity.getAttackTarget() == null && entity.getRNG().nextFloat() < 0.05f;
		}

		@Override
		public void tick() {
			Random rand = entity.getRNG();
			Vec3d target = entity.getPositionVec().add(rand.nextDouble() * 10 - 5, 0, rand.nextDouble() * 10 - 5);
			entity.getLookController().setLookPosition(target.x, target.y, target.z, 60, 60);
		}
	}
}
