package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Chilled extends Zombie {
	private int unfreezeTime;

	public Chilled(EntityType<? extends Zombie> type, Level level) {
		super(type, level);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean flag = super.doHurtTarget(entity);
		if (entity instanceof LivingEntity living && flag) {
			living.addEffect(new MobEffectInstance(EWMobEffects.FROZEN.get(), 40));
		}
		return flag;
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if ((level().isDay() && level().canSeeSky(BlockPos.containing(getX(), getEyeY(), getZ()))) || isInWaterRainOrBubble()) {
			unfreezeTime++;
			if (unfreezeTime > 300) {
				convertToZombieType(EntityType.ZOMBIE);
			}
			if (level().isClientSide) {
				level().addParticle(ParticleTypes.FALLING_WATER, getRandomX(0.7), getRandomY(), getRandomZ(0.7), (getRandom().nextDouble() - 0.5) * 0.8, -0.05, (getRandom().nextDouble() - 0.5) * 0.8);
			}
		} else {
			unfreezeTime = 0;
		}
	}

	@Override
	protected boolean convertsInWater() {
		return false;
	}

	@Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}

	@Override
	protected boolean isSunSensitive() {
		return false;
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("UnfreezeTime", unfreezeTime);
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.unfreezeTime = tag.getInt("UnfreezeTime");
	}
}
