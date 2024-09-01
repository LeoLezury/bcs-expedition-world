package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWMobEffects;
import dev.bc.expeditionworld.registry.EWParticles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MoaFeatherArrow extends AbstractArrow {
	private int duration = 160;

	public MoaFeatherArrow(EntityType<? extends MoaFeatherArrow> entityType, Level level) {
		super(entityType, level);
	}

	public MoaFeatherArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.MOA_FEATHER_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	public MoaFeatherArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.MOA_FEATHER_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide && !this.inGround) {
			this.level().addParticle(EWParticles.SNOWFLAKE.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
		}
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return EWItems.MOA_FEATHER_ARROW.get().getDefaultInstance();
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		MobEffectInstance instance = new MobEffectInstance(EWMobEffects.FROZEN, this.duration, 1);
		living.addEffect(instance, this.getEffectSource());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Duration")) {
			this.duration = tag.getInt("Duration");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Duration", this.duration);
	}
}