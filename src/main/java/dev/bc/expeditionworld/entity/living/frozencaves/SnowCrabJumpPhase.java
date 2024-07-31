package dev.bc.expeditionworld.entity.living.frozencaves;

import com.google.common.collect.Lists;
import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWEntityUtil;
import dev.bc.expeditionworld.util.EWMathUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.LongJumpUtil;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class SnowCrabJumpPhase extends AttackPhase<SnowCrab> {
	public static final int ID = 2;
	private static final ObjectArrayList<Integer> ALLOWED_ANGLES = new ObjectArrayList<>(Lists.newArrayList(40, 55, 60, 75, 80));

	public SnowCrabJumpPhase() {
		super(ID, 1, 13, 90, SnowCrabJumpingPhase.ID);
	}

	@Override
	public boolean canStart(SnowCrab entity, boolean cooldownOver) {
		return cooldownOver && !entity.isHiding() && EWEntityUtil.targetValid(entity) && !EWEntityUtil.canReachTarget(entity, 8) && (entity.onGround() || entity.isInFluidType());
	}

	@Override
	public void onStart(SnowCrab entity) {
		entity.triggerAnim("Jump", "Jump");
	}

	@Override
	public void tick(SnowCrab entity) {
		LivingEntity target = entity.getTarget();
		if (entity.getAttackTicks() == 11 && target != null) {
			calculateOptimalJumpVector(entity, entity.getRandom(), target.position()).ifPresent(vec3 -> {
				entity.setDeltaMovement(vec3);
				entity.setDiscardFriction(true);
				entity.setFixedYRot(EWMathUtil.positionToYaw(entity.position(), target.position()));
			});
		}
	}

	private static Optional<Vec3> calculateOptimalJumpVector(SnowCrab entity, RandomSource random, Vec3 target) {
		for (int i : Util.shuffledCopy(ALLOWED_ANGLES, random)) {
			Optional<Vec3> optional = LongJumpUtil.calculateJumpVectorForAngle(entity, target, 1.5f, i, false);
			if (optional.isPresent()) {
				return optional;
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean canContinue(SnowCrab entity) {
		return true;
	}

	@Override
	public void onStop(SnowCrab entity) {

	}
}
