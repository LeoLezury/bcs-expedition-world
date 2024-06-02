package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean canFreeze();

    @Inject(method = "canBeAffected", at = @At("TAIL"), cancellable = true)
    private void expeditionWorld$canBeAffected(MobEffectInstance instance, CallbackInfoReturnable<Boolean> cir) {
        if (instance.getEffect() == EWMobEffects.FROZEN.get() && !canFreeze()) {
            cir.setReturnValue(false);
        }
    }
}
