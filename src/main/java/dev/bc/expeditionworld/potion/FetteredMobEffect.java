package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.particle.EWParticles;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FetteredMobEffect extends MobEffect {
    public FetteredMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.level().isClientSide) {
            living.level().addParticle(EWParticles.TRAPPED_SOUL.get(), living.getRandomX(0.5D), living.getRandomY() - 0.25D, living.getRandomZ(0.5D), (living.getRandom().nextDouble() - 0.5D) * 0.1D, -living.getRandom().nextDouble() * 0.1D, (living.getRandom().nextDouble() - 0.5D) * 0.1D);
        }
    }
}
