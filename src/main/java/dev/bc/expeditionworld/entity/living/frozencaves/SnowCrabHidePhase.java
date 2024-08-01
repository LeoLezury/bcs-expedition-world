package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWEntityUtil;

public class SnowCrabHidePhase extends AttackPhase<SnowCrab> {
	public static final int ID = 5;

	public SnowCrabHidePhase() {
		super(ID, 1, 20, 200);
	}

	@Override
	public boolean canStart(SnowCrab entity, boolean cooldownOver) {
		return cooldownOver && !EWEntityUtil.targetValid(entity) && !entity.isHiding() && !entity.isInFluidType();
	}

	@Override
	public void onStart(SnowCrab entity) {

	}

	@Override
	public void tick(SnowCrab entity) {
		if (entity.getAttackTicks() == 18) {
			entity.setFixedYRot(entity.yBodyRot + 90);
			entity.setHiding(true);
			entity.getAttackManager().getCooldowns().put(SnowCrabWakePhase.ID, 250);
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
