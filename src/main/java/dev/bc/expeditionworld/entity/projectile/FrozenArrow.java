package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FrozenArrow extends AbstractArrow {
	private int duration = 120;

	public FrozenArrow(EntityType<? extends FrozenArrow> entityType, Level level) {
		super(entityType, level);
	}

	public FrozenArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.FROZEN_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
	}

	public FrozenArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
		super(EWEntities.FROZEN_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return EWItems.FROZEN_ARROW.get().getDefaultInstance();
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		MobEffectInstance instance = new MobEffectInstance(EWMobEffects.FROZEN, this.duration, 0);
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