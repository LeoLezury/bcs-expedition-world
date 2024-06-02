package dev.bc.expeditionworld.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class FrozenMobEffect extends MobEffect {
    public FrozenMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.canFreeze()) {
            living.setIsInPowderSnow(true);
            living.setTicksFrozen(living.getTicksFrozen() + amplifier);
        }
    }

    @Override
    public void addAttributeModifiers(LivingEntity living, AttributeMap map, int i) {
        super.addAttributeModifiers(living, map, i);
        if (living.canFreeze()) {
            living.setTicksFrozen(130);
        }
    }
}
