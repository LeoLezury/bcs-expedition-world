package dev.bc.expeditionworld.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FrozenMobEffect extends MobEffect {
	public FrozenMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity living, int amplifier) {
		if (living.canFreeze()) {
			living.setIsInPowderSnow(true);
			living.setTicksFrozen(living.getTicksFrozen() + amplifier);
		}
		return true;
	}

	@Override
	public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
		super.onEffectAdded(livingEntity, amplifier);
		if (livingEntity.canFreeze()) {
			livingEntity.setTicksFrozen(130);
		}
	}
}
