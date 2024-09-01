package dev.bc.expeditionworld.entity.projectile;

import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FrostCharge extends ThrowableItemProjectile {
	public FrostCharge(EntityType<? extends FrostCharge> type, Level level) {
		super(type, level);
	}

	public FrostCharge(Level level, LivingEntity owner) {
		super(EWEntities.FROST_CHARGE.get(), owner, level);
	}

	public FrostCharge(Level level, double x, double y, double z) {
		super(EWEntities.FROST_CHARGE.get(), x, y, z, level);
	}

	@Override
	protected Item getDefaultItem() {
		return EWItems.FROST_CHARGE.get();
	}

	private ParticleOptions getParticle() {
		ItemStack stack = this.getItem();
		return stack.isEmpty() ? new ItemParticleOption(ParticleTypes.ITEM, getDefaultItem().getDefaultInstance()) : new ItemParticleOption(ParticleTypes.ITEM, stack);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions particle = this.getParticle();
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
			}
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);
		Entity entity = hitResult.getEntity();
		entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0);
		if (entity instanceof LivingEntity living) {
			living.addEffect(new MobEffectInstance(EWMobEffects.FROZEN, 120));
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			for (int x = -3; x <= 3; x++) {
				for (int y = -3; y <= 3; y++) {
					for (int z = -3; z <= 3; z++) {
						BlockPos pos = blockPosition().offset(x, y, z);
						if (level().getBlockState(pos).is(Blocks.WATER) && blockPosition().distSqr(pos) <= 3 * 3) {
							level().setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
						}
					}
				}
			}
			this.discard();
		}
	}
}
