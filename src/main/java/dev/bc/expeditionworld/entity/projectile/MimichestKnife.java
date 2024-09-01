package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.data.EWTrimMaterials;
import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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

	public MimichestKnife(EntityType<? extends MimichestKnife> entityType, Level level) {
		super(entityType, level);
	}

	public MimichestKnife(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.MIMICHEST_KNIFE.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	public MimichestKnife(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.MIMICHEST_KNIFE.get(), owner, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BRICK, false);
	}

	public boolean isBrick() {
		return this.entityData.get(BRICK);
	}

	public void setBrick(boolean brick) {
		this.entityData.set(BRICK, brick);
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		AtomicInteger loyaltyLevel = new AtomicInteger(0);
		if (entity instanceof LivingEntity living) {
			for (ItemStack stack : living.getArmorSlots()) {
				ArmorTrim trim = stack.get(DataComponents.TRIM);
				if (trim != null) {
					if (trim.material().is(EWTrimMaterials.TRAPPED_SOUL)) {
						loyaltyLevel.set(3);
					}
				}
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

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		float f = 5.0F;
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, entity1 == null ? this : entity1);
		if (this.level() instanceof ServerLevel serverlevel && getWeaponItem() != null) {
			f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, f);
		}

		this.dealtDamage = true;
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (this.level() instanceof ServerLevel serverlevel1) {
				EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, entity, damagesource, this.getWeaponItem());
			}

			if (entity instanceof LivingEntity livingentity) {
				this.doKnockback(livingentity, damagesource);
				this.doPostHurtEffects(livingentity);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
		this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
	}

	@Override
	protected boolean tryPickup(Player player) {
		return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return isBrick() ? EWItems.BRICK_MIMICHEST_KNIFE.get().getDefaultInstance() : EWItems.STONE_MIMICHEST_KNIFE.get().getDefaultInstance();
	}

	@Override
	public void playerTouch(Player player) {
		if (this.ownedBy(player) || this.getOwner() == null) {
			super.playerTouch(player);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		this.dealtDamage = compoundTag.getBoolean("DealtDamage");
		this.setBrick(compoundTag.getBoolean("Brick"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("DealtDamage", this.dealtDamage);
		compoundTag.putBoolean("Brick", this.isBrick());
	}

	@Override
	public void tickDespawn() {
		if (this.pickup != Pickup.ALLOWED) {
			super.tickDespawn();
		}
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

	@Override
	public boolean shouldRender(double d, double e, double f) {
		return true;
	}
}