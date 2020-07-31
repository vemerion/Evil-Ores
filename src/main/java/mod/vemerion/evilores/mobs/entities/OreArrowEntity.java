package mod.vemerion.evilores.mobs.entities;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class OreArrowEntity extends ProjectileItemEntity {

	private int damage;

	public OreArrowEntity(World worldIn, double x, double y, double z, Item item, int damage) {
		super(EvilOres.ORE_ARROW_ENTITY, x, y, z, worldIn);
		this.setItem(new ItemStack(item));
		this.damage = damage;
	}

	public OreArrowEntity(World worldIn, LivingEntity throwerIn) {
		super(EvilOres.ORE_ARROW_ENTITY, throwerIn, worldIn);
	}

	public OreArrowEntity(EntityType<? extends OreArrowEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected Item getDefaultItem() {
		return Items.DIAMOND;
	}

	private IParticleData makeParticle() {
		return new ItemParticleData(ParticleTypes.ITEM, getItem());
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	public void handleStatusUpdate(byte id) {
		if (id == 3) {
			IParticleData iparticledata = this.makeParticle();

			for (int i = 0; i < 8; ++i) {
				this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult) result).getEntity();
			if (entity instanceof PlayerEntity && !this.world.isRemote) {
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
			}

			if (!this.world.isRemote && entity instanceof PlayerEntity) {
				this.world.setEntityState(this, (byte) 3);
				this.remove();
			}
		} else if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.remove();
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
