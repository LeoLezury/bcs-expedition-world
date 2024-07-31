package dev.bc.expeditionworld.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class SmoothRotationControl extends BodyRotationControl {
	private final Mob mob;
	private int headStableTime;
	private float lastStableYHeadRot;

	public SmoothRotationControl(Mob mob) {
		super(mob);
		this.mob = mob;
	}

	@Override
	public void clientTick() {
		if (this.isMoving()) {
			this.mob.yBodyRot = this.mob.getYRot();
			this.rotateHeadIfNecessary();
			this.lastStableYHeadRot = this.mob.yHeadRot;
			this.headStableTime = 0;
		} else {
			if (this.notCarryingMobPassengers()) {
				if (Math.abs(this.mob.yHeadRot - this.lastStableYHeadRot) > 15.0F) {
					this.headStableTime = 0;
					this.lastStableYHeadRot = this.mob.yHeadRot;
					this.rotateBodyIfNecessary();
				} else {
					++this.headStableTime;
					if (this.headStableTime > 10) {
						// here
						float stepSize = Math.max(0, 1 - (headStableTime - 10) * 0.1f) / 2f;
						this.mob.yBodyRot = Mth.approachDegrees(this.mob.yBodyRot, this.mob.yHeadRot, stepSize);
						// this.rotateHeadTowardsFront();
					}
				}
			}

		}
	}

	private void rotateBodyIfNecessary() {
		this.mob.yBodyRot = Mth.rotateIfNecessary(this.mob.yBodyRot, this.mob.yHeadRot, (float) this.mob.getMaxHeadYRot());
	}

	private void rotateHeadIfNecessary() {
		this.mob.yHeadRot = Mth.rotateIfNecessary(this.mob.yHeadRot, this.mob.yBodyRot, (float) this.mob.getMaxHeadYRot());
	}

	private boolean notCarryingMobPassengers() {
		return !(this.mob.getFirstPassenger() instanceof Mob);
	}

	private boolean isMoving() {
		double xDiff = this.mob.getX() - this.mob.xo;
		double zDiff = this.mob.getZ() - this.mob.zo;
		return xDiff * xDiff + zDiff * zDiff > 2.500000277905201E-7;
	}
}
