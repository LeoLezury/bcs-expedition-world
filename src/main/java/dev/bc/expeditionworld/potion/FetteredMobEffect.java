package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.registry.EWParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

public class FetteredMobEffect extends MobEffect {
	public FetteredMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public ParticleOptions createParticleOptions(MobEffectInstance effect) {
		return EWParticles.TRAPPED_SOUL.get();
	}
}
