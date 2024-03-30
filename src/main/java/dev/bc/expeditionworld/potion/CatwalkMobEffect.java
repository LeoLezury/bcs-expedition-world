package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class CatwalkMobEffect extends MobEffect {
    public CatwalkMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living instanceof ServerPlayer serverPlayer && serverPlayer.hasEffect(MobEffects.DARKNESS) && (isValid(living.level().getBlockState(living.blockPosition().below())) || isValid(living.level().getBlockState(living.blockPosition())))) {
            EWCriteriaTriggers.STAND_ON_SCULK_SHRIEKER_WITH_CATWALK.trigger(serverPlayer);
        }
    }

    private boolean isValid(BlockState state) {
        return state.is(Blocks.SCULK_SHRIEKER) && state.getValue(BlockStateProperties.CAN_SUMMON);
    }
}
