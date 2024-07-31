package dev.bc.expeditionworld.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmoothMoveControl extends MoveControl {
	public SmoothMoveControl(Mob mob) {
		super(mob);
	}

	private float smoothSpeed;
	private float maxRotationFactor;

	@Override
	public void tick() {
		float f9;
		if (this.operation == Operation.STRAFE) {
			float f = (float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
			float f1 = (float) this.speedModifier * f;
			float f2 = this.strafeForwards;
			float f3 = this.strafeRight;
			float f4 = Mth.sqrt(f2 * f2 + f3 * f3);
			if (f4 < 1.0F) {
				f4 = 1.0F;
			}

			f4 = f1 / f4;
			f2 *= f4;
			f3 *= f4;
			float f5 = Mth.sin(this.mob.getYRot() * 0.017453292F);
			float f6 = Mth.cos(this.mob.getYRot() * 0.017453292F);
			float f7 = f2 * f6 - f3 * f5;
			f9 = f3 * f6 + f2 * f5;
			if (!this.isWalkable(f7, f9)) {
				this.strafeForwards = 1.0F;
				this.strafeRight = 0.0F;
			}

			// here
			this.smoothSpeed = Mth.lerp(0.1f, this.smoothSpeed, f1);
			this.mob.setSpeed(this.smoothSpeed);
			this.mob.setZza(this.strafeForwards);
			this.mob.setXxa(this.strafeRight);
			this.operation = Operation.WAIT;
		} else if (this.operation == Operation.MOVE_TO) {
			this.operation = Operation.WAIT;
			double d0 = this.wantedX - this.mob.getX();
			double d1 = this.wantedZ - this.mob.getZ();
			double d2 = this.wantedY - this.mob.getY();
			double d3 = d0 * d0 + d2 * d2 + d1 * d1;
			if (d3 < 2.500000277905201E-7) {
				// here
				this.smoothSpeed = Mth.lerp(0.1f, this.smoothSpeed, 0);
				this.maxRotationFactor = Mth.lerp(0.1f, this.maxRotationFactor, 0);
				this.mob.setZza(this.smoothSpeed);
				return;
			}

			f9 = (float) (Mth.atan2(d1, d0) * 57.2957763671875) - 90.0F;
			this.maxRotationFactor = Mth.lerp(0.1f, maxRotationFactor, 1);
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, Mth.lerp(maxRotationFactor, 5, 12)));
			// here
			this.smoothSpeed = Mth.lerp(0.1f, this.smoothSpeed, (float) this.speedModifier * (float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
			this.mob.setSpeed(this.smoothSpeed);
			BlockPos blockpos = this.mob.blockPosition();
			BlockState blockstate = this.mob.level().getBlockState(blockpos);
			VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level(), blockpos);
			if (d2 > (double) this.mob.maxUpStep() && d0 * d0 + d1 * d1 < (double) Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double) blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES)) {
				this.mob.getJumpControl().jump();
				this.operation = Operation.JUMPING;
			}
		} else if (this.operation == Operation.JUMPING) {
			// here
			this.smoothSpeed = Mth.lerp(0.1f, this.smoothSpeed, (float) this.speedModifier * (float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
			this.mob.setSpeed(this.smoothSpeed);
			if (this.mob.onGround()) {
				this.operation = Operation.WAIT;
			}
		} else {
			// here
			this.smoothSpeed = Mth.lerp(0.1f, this.smoothSpeed, 0);
			this.maxRotationFactor = Mth.lerp(0.1f, this.maxRotationFactor, 0);
			this.mob.setZza(this.smoothSpeed);
		}
	}

	private boolean isWalkable(float relativeX, float relativeZ) {
		PathNavigation pathnavigation = this.mob.getNavigation();
		NodeEvaluator nodeevaluator = pathnavigation.getNodeEvaluator();
		return nodeevaluator.getPathType(new PathfindingContext(mob.level(), mob), Mth.floor(this.mob.getX() + (double) relativeX), this.mob.getBlockY(), Mth.floor(this.mob.getZ() + (double) relativeZ)) == PathType.WALKABLE;
	}
}
