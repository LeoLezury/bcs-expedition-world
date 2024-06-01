package dev.bc.expeditionworld.entity.misc;

import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.potion.EWMobEffects;
import dev.bc.expeditionworld.sound.EWSoundEvents;
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
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
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
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    BlockPos pos = blockPosition().offset(x, y, z);
                    if (level().getBlockState(pos).is(Blocks.WATER) && blockPosition().distSqr(pos) <= 10 * 10) {
                        level().setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
                    }
                    if (level().getBlockState(pos).is(Blocks.LAVA) && blockPosition().distSqr(pos) <= 5 * 5) {
                        level().setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
                    }
                }
            }
        }
    }

    private void spawnLingeringCloud() {
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        cloud.setRadius(2.5F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setDuration(cloud.getDuration() / 2);
        cloud.setRadiusPerTick(-cloud.getRadius() / (float) cloud.getDuration());
        cloud.addEffect(new MobEffectInstance(new MobEffectInstance(EWMobEffects.FROZEN.get(), 60)));
        this.level().addFreshEntity(cloud);
    }
}
