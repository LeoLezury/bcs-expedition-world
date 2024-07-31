package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.entity.ai.AttackPhase;

public class SnowCrabLandPhase extends AttackPhase<SnowCrab> {
	public static final int ID = 4;

	public SnowCrabLandPhase() {
		super(ID, 1, 8, 0);
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
		return true;
	}

	@Override
	public void onStop(SnowCrab entity) {

	}
}
