package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWEntityUtil;

public class SnowCrabMeleePhase extends AttackPhase<SnowCrab> {
	public static final int ID = 1;

	public SnowCrabMeleePhase() {
		super(ID, 1, 20, 20);
	}

	@Override
	public boolean canStart(SnowCrab entity, boolean cooldownOver) {
		return cooldownOver && EWEntityUtil.targetValid(entity) && !entity.isHiding() && EWEntityUtil.canReachTarget(entity, 2.5);
	}

	@Override
	public void onStart(SnowCrab entity) {
		entity.triggerAnim("MeleeAttack", "MeleeAttack");
	}

	@Override
	public void tick(SnowCrab entity) {
		if (entity.getAttackTicks() == 8) {
			EWEntityUtil.performMeleeAttack(entity, 2.5);
		}
	}

	@Override
	public boolean canContinue(SnowCrab entity) {
		return true;
	}

	@Override
	public void onStop(SnowCrab entity) {

	}
}
