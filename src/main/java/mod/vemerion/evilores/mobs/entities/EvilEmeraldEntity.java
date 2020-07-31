package mod.vemerion.evilores.mobs.entities;

import java.util.EnumSet;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EvilEmeraldEntity extends CreatureEntity implements IMob {

	private int shakeHead;
	private int chargeDuration;

	public EvilEmeraldEntity(EntityType<? extends EvilEmeraldEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new EvilEmeraldEntity.ChargeGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.4, true));
		this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.3f));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public void tick() {
		super.tick();
		if (world.isRemote) {
			chargeDuration--;
			if (shakeHead-- < 0) {
				shakeHead = rand.nextInt(100) + 60;
			}
		}
	}

	private Vec3d getFacing() {
		return Vec3d.fromPitchYaw(this.getPitchYaw());
	}

	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_HORSE_BREATHE;
	}

	private void playerChargeStepSound() {
		playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, this.getSoundVolume(), this.getSoundPitch() * 0.1f);
	}

	private void playerChargeSound() {
		playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, this.getSoundVolume(), this.getSoundPitch() * 0.3f);
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundevent = this.getAmbientSound();
		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume() * 1.3f, this.getSoundPitch() * 0.1f);
		}
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		if (!isCharging()) {
			super.knockBack(entityIn, strength, xRatio, zRatio);
		}
	}

	@Override
	protected float getSoundVolume() {
		return 3.0F;
	}

	public boolean isShakingHead() {
		return shakeHead > 0 && shakeHead < 40;
	}

	public boolean isCharging() {
		return chargeDuration > 0;
	}

	public class ChargeGoal extends Goal {
		private EvilEmeraldEntity entity;
		private int chargeTimer = 0;
		private int totalChargeDuration = 60;
		private LivingEntity target;
		private Vec3d prevPos;

		public ChargeGoal(EvilEmeraldEntity entity) {
			this.entity = entity;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			LivingEntity target = entity.getAttackTarget();
			return target != null && target.isAlive() && target instanceof PlayerEntity
					&& entity.canEntityBeSeen(target) && target.getDistanceSq(entity) > 40
					&& Math.abs(target.getPosY() - entity.getPosY()) < 3;
		}

		@Override
		public void startExecuting() {
			playerChargeSound();
			target = entity.getAttackTarget();
			chargeTimer = totalChargeDuration;
			entity.chargeDuration = totalChargeDuration;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return target != null && target.isAlive() && target instanceof PlayerEntity && chargeTimer > 0;
		}

		@Override
		public void tick() {
			chargeTimer--;
			if (target != null) {
				if (chargeTimer > totalChargeDuration - 20) {
					entity.getLookController().setLookPosition(target.getPosX(), target.getPosYEye(), target.getPosZ());
					entity.faceEntity(target, 30.0F, 30.0F);
				} else {
					if (chargeTimer % 5 == 0) {
						playerChargeStepSound();
					}
					if (chargeTimer % 10 == 0 && prevPos != null
							&& prevPos.squareDistanceTo(entity.getPositionVec()) < 0.1) {
						explode();
					}
					prevPos = entity.getPositionVec();
					entity.getLookController().setLookPosition(target.getPosX(), target.getPosYEye(), target.getPosZ());
					entity.faceEntity(target, 5.0F, 5.0F);
					Vec3d vec3d = entity.getFacing().scale(0.4f);
					entity.setMotion(vec3d.x, 0, vec3d.z);
					if (entity.getBoundingBox().intersects(target.getBoundingBox())) {
						chargeTimer = -1;
						target.attackEntityFrom(DamageSource.causeMobDamage(entity), 10);
						target.knockBack(entity, 2, 0.5, 0.5);
					}
				}
			}
		}

		private void explode() {
			Vec3d forward = entity.getFacing();
			Vec3d left = forward.rotateYaw(90);
			Vec3d right = forward.rotateYaw(-90);

			world.createExplosion(entity, entity.getPosX() + forward.x * 2.5 + left.x * 1.5, entity.getPosY() + 1.5,
					entity.getPosZ() + forward.z * 2.5 + left.z * 1.5, 2.1F, Explosion.Mode.BREAK);
			world.createExplosion(entity, entity.getPosX() + forward.x * 2.5 + right.x * 1.5, entity.getPosY() + 1.5,
					entity.getPosZ() + forward.z * 2.5 + right.z * 1.5, 2.1F, Explosion.Mode.BREAK);
		}
	}
}
