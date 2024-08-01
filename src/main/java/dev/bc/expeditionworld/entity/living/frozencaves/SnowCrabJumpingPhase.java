package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWMathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SnowCrabJumpingPhase extends AttackPhase<SnowCrab> {
	public static final int ID = 3;

	public SnowCrabJumpingPhase() {
		super(ID, 1, 400, 0, SnowCrabLandPhase.ID);
	}

	@Override
	public boolean canStart(SnowCrab entity, boolean cooldownOver) {
		return false;
	}

	@Override
	public void onStart(SnowCrab entity) {

	}

	@Override
	public void tick(SnowCrab entity) {

	}

	@Override
	public boolean canContinue(SnowCrab entity) {
		return entity.getAttackTicks() < 5 || (!entity.onGround() && !entity.isInFluidType());
	}

	@Override
	public void onStop(SnowCrab entity) {
		entity.setDiscardFriction(false);
		entity.triggerAnim("Land", "Land");
		Level level = entity.level();
		float radius = entity.getBbWidth() / 2 + 3;
		for (LivingEntity living : level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox().inflate(3))) {
			if (living.distanceTo(entity) < radius + living.getBbWidth() / 2) {
				living.hurt(level.damageSources().mobAttack(entity), (float) (entity.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2));
			}
		}
		if (level instanceof ServerLevel serverLevel) {
			for (int i = 0; i < 360; i += 5) {
				Vec3 vec3 = EWMathUtil.rotationToPosition(entity.position().add(0, -0.1, 0), radius, 0, i);
				BlockPos particlePos = new BlockPos((int) vec3.x, (int) vec3.y, (int) vec3.z);
				BlockState state = level.getBlockState(particlePos.below());
				if (state.getRenderShape() != RenderShape.INVISIBLE) {
					serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), vec3.x, vec3.y + 0.6, vec3.z, 3, 0, 0, 0, 0.2);
				}
			}
		}
		entity.playSound(level.getBlockState(entity.blockPosition().below()).getSoundType(level, entity.blockPosition().below(), entity).getBreakSound());
	}
}
