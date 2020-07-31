package mod.vemerion.evilores.mobs.entities;

import mod.vemerion.evilores.EvilOres;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class EvilOreOriginEntity extends Entity {

	private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(EvilOreOriginEntity.class,
			DataSerializers.STRING);
	
	private int fuse = 60;
	private float explosionRadius;
	private ResourceLocation entityType;
	private ResourceLocation texture;
	
	public EvilOreOriginEntity(EntityType<? extends EvilOreOriginEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public EvilOreOriginEntity(EntityType<? extends EvilOreOriginEntity> type, World worldIn, String entityName, String texturePath, float explosionRadius) {
		this(type, worldIn);
		this.entityType = new ResourceLocation(EvilOres.MODID, entityName);
		this.setTexture(texturePath);
		this.explosionRadius = explosionRadius;
	}
	
	public ResourceLocation getTexture() {
		if (texture == null)
			texture = new ResourceLocation(EvilOres.MODID, dataManager.get(TEXTURE));
		return texture;
	}
	
	public void setTexture(String textureName) {
		dataManager.set(TEXTURE, textureName);
	}

	@Override
	protected void registerData() {
		dataManager.register(TEXTURE, "textures/entity/evil_emerald_origin.png");
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		entityType = new ResourceLocation(EvilOres.MODID, compound.getString("entity"));
		setTexture(compound.getString("texture"));
		explosionRadius = compound.getFloat("explosionRadius");
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		compound.putString("entity", entityType.getPath());
		compound.putString("texture", getTexture().getPath());
		compound.putFloat("explosionRadius", explosionRadius);
	}

	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void tick() {
		if (fuse-- <= 0) {
			remove();
			if (!world.isRemote) {
				world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), explosionRadius,
						Explosion.Mode.BREAK);
				Entity entity = ForgeRegistries.ENTITIES.getValue(entityType).create(world);
				entity.setPosition(getPosX(), getPosY(), getPosZ());
				world.addEntity(entity);
			}
		}
	}

	@Override
	public boolean isBurning() {
		return false;
	}

	/**
	 * Gets how bright this entity is.
	 */
	@Override
	public float getBrightness() {
		return 1.0F;
	}
}
