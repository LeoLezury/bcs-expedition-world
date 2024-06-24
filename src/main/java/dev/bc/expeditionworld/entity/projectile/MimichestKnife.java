package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.data.EWTrimMaterials;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class MimichestKnife extends AbstractArrow {
	protected static final EntityDataAccessor<Boolean> BRICK = SynchedEntityData.defineId(MimichestKnife.class, EntityDataSerializers.BOOLEAN);

	private boolean dealtDamage;
	public int clientSideReturnTickCount;

	public MimichestKnife(EntityType<? extends AbstractArrow> type, Level level) {
		super(type, level);
	}

	public MimichestKnife(LivingEntity living, Level level) {
		super(EWEntities.MIMICHEST_KNIFE.get(), living, level);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BRICK, false);
	}

	public boolean isBrick() {
		return this.entityData.get(BRICK);
	}

	public void setBrick(boolean brick) {
		this.entityData.set(BRICK, brick);
	}

	public void tick() {
		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		AtomicInteger loyaltyLevel = new AtomicInteger(0);
		if (entity instanceof LivingEntity living) {
			for (ItemStack stack : living.getArmorSlots()) {
				ArmorTrim.getTrim(level().registryAccess(), stack).ifPresent((armorTrim -> {
					if (armorTrim.material().is(EWTrimMaterials.TRAPPED_SOUL)) {
						loyaltyLevel.set(3);
					}
				}));
			}
		}
		if (loyaltyLevel.get() > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
			if (!(entity instanceof Player)) {
				this.discard();
			}

			if (!this.isAcceptableReturnOwner()) {
				if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED) {
					this.spawnAtLocation(this.getPickupItem(), 0.1F);
				}

				this.discard();
			} else {
				this.setNoPhysics(true);
				Vec3 vec3 = entity.getEyePosition().subtract(this.position());
				this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * (double) loyaltyLevel.get(), this.getZ());
				if (this.level().isClientSide) {
					this.yOld = this.getY();
				}

				double d = 0.05 * (double) loyaltyLevel.get();
				this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d)));
				if (this.clientSideReturnTickCount == 0) {
					this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
				}

				++this.clientSideReturnTickCount;
			}
		}

		super.tick();
	}

	private boolean isAcceptableReturnOwner() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayer) || !entity.isSpectator();
		} else {
			return false;
		}
	}

	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec3, Vec3 vec32) {
		return this.dealtDamage ? null : super.findHitEntity(vec3, vec32);
	}

	protected void onHitEntity(EntityHitResult entityHitResult) {
		Entity entity = entityHitResult.getEntity();
		float f = 5.0F;
		if (entity instanceof LivingEntity livingEntity) {
			f += EnchantmentHelper.getDamageBonus(this.getPickupItem()/*getPickupItemStackOrigin()*/, livingEntity.getMobType());
		}

		Entity entity2 = this.getOwner();
		DamageSource damageSource = level().damageSources().mobProjectile(this, entity2 instanceof LivingEntity livingEntity ? livingEntity : null);
		this.dealtDamage = true;
		if (entity.hurt(damageSource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity livingEntity2) {
				if (entity2 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingEntity2, entity2);
					EnchantmentHelper.doPostDamageEffects((LivingEntity) entity2, livingEntity2);
				}

				this.doPostHurtEffects(livingEntity2);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
		float g = 1.0F;
		this.playSound(SoundEvents.PLAYER_ATTACK_CRIT, g, 1.0F);
	}

	protected boolean tryPickup(Player player) {
		return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
	}

	@Override
	public ItemStack getPickupItem() {
		return isBrick() ? EWItems.BRICK_MIMICHEST_KNIFE.get().getDefaultInstance() : EWItems.STONE_MIMICHEST_KNIFE.get().getDefaultInstance();
	}

	public void playerTouch(Player player) {
		if (this.ownedBy(player) || this.getOwner() == null) {
			super.playerTouch(player);
		}
	}

	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		this.dealtDamage = compoundTag.getBoolean("DealtDamage");
		this.setBrick(compoundTag.getBoolean("Brick"));
	}

	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("DealtDamage", this.dealtDamage);
		compoundTag.putBoolean("Brick", this.isBrick());
	}

	public void tickDespawn() {
		if (this.pickup != Pickup.ALLOWED) {
			super.tickDespawn();
		}
	}

	protected float getWaterInertia() {
		return 0.99F;
	}

	public boolean shouldRender(double d, double e, double f) {
		return true;
	}
}