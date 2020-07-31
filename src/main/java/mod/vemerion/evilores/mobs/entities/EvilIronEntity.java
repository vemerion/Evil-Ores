package mod.vemerion.evilores.mobs.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EvilIronEntity extends CreatureEntity implements IMob {

	private static final BlockPos[] DIRECTIONS = { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 1, 0),
			new BlockPos(0, -1, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };

	public EvilIronEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setNoGravity(true);
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity player) {
		player.attackEntityFrom(DamageSource.causeMobDamage(this), 6);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean success = super.attackEntityFrom(source, amount);
		if (success) {
			createParticles(rand.nextInt(3) + 3);
			playSound(EvilOres.EVIL_IRON_MOVE_SOUND, getSoundVolume(),
					((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		}
		return success;
	}
	
	@Override
	public void tick() {
		super.tick();
		if (rand.nextDouble() < 0.05) {
			createParticles(rand.nextInt(1) + 1);
		}
	}

	private void createParticles(int count) {
		for (int j = 0; j < count; ++j) {
			float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
			float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
			float f2 = MathHelper.sin(f) * 1f * 0.5F * f1;
			float f3 = MathHelper.cos(f) * 1f * 0.5F * f1;
			if (world instanceof ServerWorld) {
				ServerWorld world = (ServerWorld)this.world;
				world.spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.IRON_ORE.getDefaultState()),
						this.getPosX() + (double) f2, this.getPosY(), this.getPosZ() + (double) f3, 0, 0.0D, 0.0D, 0.0D, 1);
			}
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new EvilIronEntity.MoveGoal(this));
	}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	public class MoveGoal extends Goal {
		private EvilIronEntity entity;
		private Random rand = new Random();
		private BlockPos target;
		private float cooldown = 0;

		public MoveGoal(EvilIronEntity entity) {
			this.entity = entity;
		}

		private void randomTarget() {
			List<BlockPos> targets = new ArrayList<>();
			for (BlockPos dir : EvilIronEntity.DIRECTIONS) {
				if (entity.world.isAirBlock(entity.getPosition().add(dir.getX(), dir.getY(), dir.getZ()))) {
					targets.add(dir.add(entity.getPosition()));
				}
			}
			if (targets.isEmpty()) {
				target = entity.getPosition();
			} else {
				target = targets.get(rand.nextInt(targets.size()));
			}
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void startExecuting() {
			randomTarget();
		}

		@Override
		public void tick() {
			if (cooldown-- < 0) {
				cooldown = 5;
				randomTarget();
				entity.moveToBlockPosAndAngles(target, 0, 0);
			}
		}
	}
}
