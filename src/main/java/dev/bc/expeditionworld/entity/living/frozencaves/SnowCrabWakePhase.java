package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWEntityUtil;

public class SnowCrabWakePhase extends AttackPhase<SnowCrab> {
	public static final int ID = 6;

	public SnowCrabWakePhase() {
		super(ID, 1, 20, 200);
	}

	@Override
	public boolean canStart(SnowCrab entity, boolean cooldownOver) {
		return (cooldownOver || EWEntityUtil.targetValid(entity) || entity.getLastHurtByMob() != null) && entity.isHiding();
	}

	@Override
	public void onStart(SnowCrab entity) {

	}

	@Override
	public void tick(SnowCrab entity) {

	}

	@Override
	public boolean canContinue(SnowCrab entity) {
		return true;
	}

	@Override
	public void onStop(SnowCrab entity) {
		entity.setHiding(false);
		entity.getAttackManager().getCooldowns().put(SnowCrabHidePhase.ID, 250);
	}
}
