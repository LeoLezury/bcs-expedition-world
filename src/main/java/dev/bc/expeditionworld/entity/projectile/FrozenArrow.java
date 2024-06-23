package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrozenArrow extends AbstractArrow {
    private int duration = 120;

    public FrozenArrow(EntityType<? extends FrozenArrow> type, Level level) {
        super(type, level);
    }

    public FrozenArrow(Level level, LivingEntity owner) {
        super(EWEntities.FROZEN_ARROW.get(), owner, level);
    }

    public FrozenArrow(Level level, double x, double y, double z) {
        super(EWEntities.FROZEN_ARROW.get(), x, y, z, level);
    }

    public ItemStack getPickupItem() {
        return EWItems.FROZEN_ARROW.get().getDefaultInstance();
    }

    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        MobEffectInstance instance = new MobEffectInstance(EWMobEffects.FROZEN.get(), this.duration, 0);
        living.addEffect(instance, this.getEffectSource());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Duration")) {
            this.duration = tag.getInt("Duration");
        }

    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Duration", this.duration);
    }
}