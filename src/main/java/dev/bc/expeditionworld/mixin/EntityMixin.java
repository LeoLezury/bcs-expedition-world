package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "dampensVibrations", at = @At("TAIL"), cancellable = true)
    private void expeditionWorld$dampensVibrations(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof LivingEntity living && living.hasEffect(EWMobEffects.CATWALK.get())) {
            cir.setReturnValue(true);
        }
    }
}
