package mod.vemerion.evilores.mobs.entities;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EvilRedstoneEntity extends CreatureEntity implements IMob {
	
	private boolean wasOnGround;

	public EvilRedstoneEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EvilRedstoneEntity.JumpGoal(this));
		this.goalSelector.addGoal(1, new EvilRedstoneEntity.FaceRandomGoal(this));
	}
	
	@Override
	public void onCollideWithPlayer(PlayerEntity player) {
		player.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}
	
	private Vec3d getFacing() {
		return Vec3d.fromPitchYaw(this.getPitchYaw());
	}
	
	@Override
	protected void jump() {
		Vec3d vec3d = this.getFacing().scale(0.2f);
		this.setMotion(vec3d.x, (double) this.getJumpUpwardsMotion(), vec3d.z);
		this.isAirBorne = true;
	}
	
	public void tick() {
		super.tick();
		if (this.onGround && !this.wasOnGround) {

			for (int j = 0; j < 8; ++j) {
				float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
				float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * 1f * 0.5F * f1;
				float f3 = MathHelper.cos(f) * 1f * 0.5F * f1;
				this.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.REDSTONE_ORE.getDefaultState()), this.getPosX() + (double) f2, this.getPosY() - 0.2f,
						this.getPosZ() + (double) f3, 0.0D, 0.0D, 0.0D);
			}

			this.playSound(SoundEvents.BLOCK_STONE_PLACE, this.getSoundVolume() * 0.7f, rand.nextFloat() * 0.5f + 2f);

		}
		this.wasOnGround = this.onGround;
	}
	
	public class FaceRandomGoal extends Goal {
		private EvilRedstoneEntity entity;
		private int cooldown;
		private float direction;
		
		public FaceRandomGoal(EvilRedstoneEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return entity.getAttackTarget() == null;
		}
		
		@Override
		public void tick() {
			Random rand = entity.getRNG();
			if (cooldown-- <= 0) {
				cooldown = entity.getRNG().nextInt(20) + 10;
				direction = rand.nextFloat() * 180 - 180;
			}
			Vec2f pitchYaw = entity.getPitchYaw();
			entity.setRotation((float)MathHelper.lerp(0.6, pitchYaw.y, direction), pitchYaw.x);
		}

	}
	
	public class JumpGoal extends Goal {
		private EvilRedstoneEntity entity;
		private int jumpCooldown;

		public JumpGoal(EvilRedstoneEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void startExecuting() {
		}

		@Override
		public void tick() {
			jumpCooldown--;
			if (jumpCooldown < 0) {
				entity.jump();
				jumpCooldown = 20;
			}
		}
	}
}
