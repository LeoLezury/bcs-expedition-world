package dev.bc.expeditionworld.entity.misc;

import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWMobEffects;
import dev.bc.expeditionworld.registry.EWParticles;
import dev.bc.expeditionworld.registry.EWSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FrostbiteTnt extends PrimedTnt {
	public FrostbiteTnt(EntityType<? extends PrimedTnt> type, Level level) {
		super(type, level);
	}

	public FrostbiteTnt(Level level, double x, double y, double z, LivingEntity igniter) {
		this(EWEntities.FROSTBITE_TNT.get(), level);
		this.setPos(x, y, z);
		double d0 = igniter.getRandom().nextDouble() * (Math.PI * 2F);
		this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
		this.setFuse(80);
		this.xo = x;
		this.yo = y;
		this.zo = z;
		this.owner = igniter;
	}

	@Override
	protected void explode() {
		super.explode();
		playSound(EWSoundEvents.FROSTBITE_TNT_EXPLODE.get());
		spawnLingeringCloud();
		for (int x = -4; x <= 4; x++) {
			for (int y = -4; y <= 4; y++) {
				for (int z = -4; z <= 4; z++) {
					BlockPos pos = blockPosition().offset(x, y, z);
					if (level().getBlockState(pos).is(Blocks.WATER) && blockPosition().distSqr(pos) <= 4 * 4) {
						level().setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
					}
					if (level().getBlockState(pos).is(Blocks.LAVA) && blockPosition().distSqr(pos) <= 2 * 2) {
						level().setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
					}
				}
			}
		}
	}

	private void spawnLingeringCloud() {
		AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
		cloud.setRadius(5F);
		cloud.setRadiusOnUse(-0.5F);
		cloud.setWaitTime(10);
		cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());
		cloud.setParticle(EWParticles.SNOWFLAKE.get());
		cloud.addEffect(new MobEffectInstance(new MobEffectInstance(EWMobEffects.FROZEN, 60)));
		this.level().addFreshEntity(cloud);
	}
}
