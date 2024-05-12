package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.SnowGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowGolem.class)
public abstract class SnowGolemMixin {
    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/SnowGolem;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean expeditionWorld$hurt(SnowGolem instance, DamageSource damageSource, float amount) {
        if (isProtectedByIceLantern()) {
            return false;
        }
        return instance.hurt(damageSource, amount);
    }

    @Inject(method = "isSensitiveToWater", at = @At("TAIL"), cancellable = true)
    private void expeditionWorld$isSensitiveToWater(CallbackInfoReturnable<Boolean> cir) {
        if (isProtectedByIceLantern()) {
            cir.setReturnValue(false);
        }
    }

    @Unique
    private boolean isProtectedByIceLantern() {
        for (BlockPos blockPos : BlockPos.withinManhattan(((Entity) (Object) this).blockPosition(), 7, 7, 7)) {
            if (((Entity) (Object) this).level().getBlockState(blockPos).is(EWBlocks.ICE_LANTERN.get())) {
                return true;
            }
        }
        return false;
    }
}
