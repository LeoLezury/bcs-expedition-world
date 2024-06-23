package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.particle.EWParticles;
import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MoaFeatherArrow extends AbstractArrow {
    private int duration = 160;
    private ItemStack pickupStack = ItemStack.EMPTY;

    public MoaFeatherArrow(EntityType<? extends MoaFeatherArrow> type, Level level) {
        super(type, level);
    }

    public MoaFeatherArrow(Level level, LivingEntity owner) {
        super(EWEntities.MOA_FEATHER_ARROW.get(), owner, level);
    }

    public MoaFeatherArrow(Level level, double x, double y, double z) {
        super(EWEntities.MOA_FEATHER_ARROW.get(), x, y, z, level);
    }

    public void setPickupStack(ItemStack pickupStack) {
        this.pickupStack = pickupStack;
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(EWParticles.SNOWFLAKE.get(), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    public ItemStack getPickupItem() {
        return pickupStack.isEmpty() ? EWItems.MOA_FEATHER_ARROW.get().getDefaultInstance() : pickupStack;
    }

    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living);
        MobEffectInstance instance = new MobEffectInstance(EWMobEffects.FROZEN.get(), this.duration, 1);
        living.addEffect(instance, this.getEffectSource());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Duration")) {
            this.duration = tag.getInt("Duration");
        }
        if (tag.contains("PickupStack", CompoundTag.TAG_COMPOUND)) {
            this.pickupStack = ItemStack.of(tag.getCompound("PickupStack"));
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Duration", this.duration);
        if (!pickupStack.isEmpty()) {
            tag.put("PickupStack", pickupStack.save(new CompoundTag()));
        }
    }
}