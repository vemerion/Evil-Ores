package mod.vemerion.evilores.mobs.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.monster.IMob;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StationaryEntity extends CreatureEntity implements IMob {

	protected static final DataParameter<Direction> DIRECTION = EntityDataManager.createKey(StationaryEntity.class,
			DataSerializers.DIRECTION);

	private float fixedYaw;
	private float fixedPitch;
	private BlockPos fixedPos;

	protected StationaryEntity(EntityType<? extends StationaryEntity> type, World worldIn) {
		super(type, worldIn);
		this.setNoGravity(true);
	}

	public StationaryEntity(EntityType<? extends StationaryEntity> type, World worldIn, BlockPos pos) {
		this(type, worldIn);
		this.fixedPos = new BlockPos(pos);
		this.setPosition(fixedPos.getX() + 0.5, fixedPos.getY(), fixedPos.getZ() + 0.5);
	}

	protected BodyController createBodyController() {
		return new StationaryEntity.BodyHelperController(this);
	}

	protected void registerData() {
		super.registerData();
		dataManager.register(DIRECTION, Direction.WEST);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.dataManager.set(DIRECTION, Direction.byIndex(compound.getByte("Direction")));
		int[] pos = new int[3];
		if (compound.contains("FixedPos")) {
			pos = compound.getIntArray("FixedPos");
		} else {
			BlockPos position = this.getPosition();
			pos = new int[] { position.getX(), position.getY(), position.getZ() };
		}
		fixedPos = new BlockPos(pos[0], pos[1], pos[2]);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("Direction", (byte) this.dataManager.get(DIRECTION).getIndex());
		int[] pos = { fixedPos.getX(), fixedPos.getY(), fixedPos.getZ() };
		compound.putIntArray("FixedPos", pos);
	}

	public Direction getDirection() {
		return dataManager.get(DIRECTION);
	}

	public void setDirection(Direction value) {
		this.dataManager.set(DIRECTION, value);
	}

	private void rotationFromDirection(Direction direction) {
		if (direction == Direction.UP) {
			fixedYaw = 0;
			fixedPitch = -90;
		} else if (direction == Direction.DOWN) {
			fixedYaw = 0;
			fixedPitch = 90;
		} else if (direction == Direction.WEST) {
			fixedYaw = 90;
			fixedPitch = 0;
		} else if (direction == Direction.NORTH) {
			fixedYaw = 180;
			fixedPitch = 0;
		} else if (direction == Direction.EAST) {
			fixedYaw = -90;
			fixedPitch = 0;
		} else if (direction == Direction.SOUTH) {
			fixedYaw = 0;
			fixedPitch = 0;
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		this.setMotion(Vec3d.ZERO);
		rotationFromDirection(getDirection());
		this.renderYawOffset = fixedYaw;
		this.prevRenderYawOffset = fixedYaw;
		this.rotationYaw = fixedYaw;
		this.prevRotationYaw = fixedYaw;
		this.rotationYawHead = fixedYaw;
		this.prevRotationYawHead = fixedYaw;
		this.prevRotationPitch = fixedPitch;
		this.rotationPitch = fixedPitch;
		if (fixedPos != null)
			this.setLocationAndAngles((double) fixedPos.getX() + 0.5D, (double) fixedPos.getY(),
					(double) fixedPos.getZ() + 0.5D, fixedYaw, fixedPitch);
	}

	class BodyHelperController extends BodyController {

		public BodyHelperController(StationaryEntity entity) {
			super(entity);
		}

		public void updateRenderAngles() {
		}
	}
}
