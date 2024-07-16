package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.particle.EWParticles;
import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class IceCreeper extends Creeper implements GeoEntity {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public IceCreeper(EntityType<? extends Creeper> type, Level level) {
		super(type, level);
	}

	@Override
	public void spawnLingeringCloud() {
		super.spawnLingeringCloud();
		AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
		cloud.setRadius(3F);
		cloud.setRadiusOnUse(-0.5F);
		cloud.setWaitTime(10);
		cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());
		cloud.setParticle(EWParticles.SNOWFLAKE.get());
		cloud.addEffect(new MobEffectInstance(new MobEffectInstance(EWMobEffects.FROZEN.get(), 80)));
		this.level().addFreshEntity(cloud);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericIdleController(this));
		controllers.add(DefaultAnimations.genericWalkController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}
}
