package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.item.IceTotemItem;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWMobEffects;
import dev.bc.expeditionworld.registry.EWParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow
	public abstract boolean canFreeze();

	@Shadow
	public abstract boolean isHolding(Item item);

	@Shadow
	public abstract ItemStack getMainHandItem();

	@Shadow
	public abstract ItemStack getOffhandItem();

	@Unique
	private int expeditionWorld$lastExtinguishSound;

	@Inject(method = "canBeAffected", at = @At("TAIL"), cancellable = true)
	private void expeditionWorld$canBeAffected(MobEffectInstance instance, CallbackInfoReturnable<Boolean> cir) {
		if (instance.getEffect() == EWMobEffects.FROZEN.get() && !canFreeze()) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/world/damagesource/DamageSource;F)V", shift = At.Shift.BEFORE), cancellable = true)
	private void expeditionWorld$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (isHolding(EWItems.TOTEM_OF_ICE.get()) && source.is(DamageTypeTags.IS_FIRE)) {
			ItemStack stack = getMainHandItem().is(EWItems.TOTEM_OF_ICE.get()) ? getMainHandItem() : getOffhandItem();
			if (IceTotemItem.tryDamage(stack)) {
				LivingEntity living = (LivingEntity) (Object) this;
				cir.setReturnValue(false);
				living.setRemainingFireTicks(0);
				if (living.tickCount - expeditionWorld$lastExtinguishSound > 20) {
					expeditionWorld$lastExtinguishSound = living.tickCount;
					living.level().playSound(null, living.blockPosition(), SoundEvents.FIRE_EXTINGUISH, living.getSoundSource());
				}
				if (living.level() instanceof ServerLevel serverLevel) {
					serverLevel.sendParticles(EWParticles.SNOWFLAKE.get(), living.getRandomX(0.5D), living.getRandomY(), living.getRandomZ(0.5D), 15, 0.2, 0.2, 0.2, 0);
				}
			}
		}
	}
}
