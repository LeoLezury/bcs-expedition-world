package dev.bc.expeditionworld.entity.living.frozencaves;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.ai.AttackManager;
import dev.bc.expeditionworld.entity.ai.MoveToTargetGoal;
import dev.bc.expeditionworld.entity.ai.MultiPhaseAttacker;
import dev.bc.expeditionworld.registry.EWSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class SnowCrab extends Monster implements GeoEntity, MultiPhaseAttacker {
	public static final RawAnimation IDLE_HIDING = RawAnimation.begin().thenLoop("misc.idle_hiding");
	public static final RawAnimation JUMPING = RawAnimation.begin().thenLoop("move.jumping");
	public static final RawAnimation LAND = RawAnimation.begin().thenPlay("move.land");
	public static final RawAnimation HIDE = RawAnimation.begin().thenPlay("misc.hide");
	public static final RawAnimation WAKE = RawAnimation.begin().thenPlay("misc.wake");

	private static final AttributeModifier FOLLOW_RANGE_BONUS = new AttributeModifier(ExpeditionWorld.id("snow_crab.follow_range_bonus"), 16, AttributeModifier.Operation.ADD_VALUE);

	protected static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(SnowCrab.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(SnowCrab.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Float> FIXED_Y_ROT = SynchedEntityData.defineId(SnowCrab.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> HIDING = SynchedEntityData.defineId(SnowCrab.class, EntityDataSerializers.BOOLEAN);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	private final AttackManager<SnowCrab> attackManager = new AttackManager<>(this, List.of(
		new SnowCrabMeleePhase(),
		new SnowCrabJumpPhase(),
		new SnowCrabJumpingPhase(),
		new SnowCrabLandPhase(),
		new SnowCrabHidePhase(),
		new SnowCrabWakePhase()
	));

	public SnowCrab(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	public AttackManager<SnowCrab> getAttackManager() {
		return attackManager;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ATTACK_STATE, 0)
			.define(ATTACK_TICKS, 0)
			.define(FIXED_Y_ROT, 0f)
			.define(HIDING, false);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MoveToTargetGoal(this, 1f));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1f));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this, SnowCrab.class).setAlertOthers());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Spider.class, false));
	}

	@Override
	protected BodyRotationControl createBodyControl() {
		return new SnowCrabBodyRotationControl();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.25F)
			.add(Attributes.FOLLOW_RANGE, 12.0D)
			.add(Attributes.MAX_HEALTH, 30.0D)
			.add(Attributes.ATTACK_DAMAGE, 5.0D)
			.add(Attributes.ARMOR, 2);
	}

	@Override
	protected void customServerAiStep() {
		if (!isNoAi()) {
			this.attackManager.tick();
			AttributeInstance attributeInstance = getAttribute(Attributes.FOLLOW_RANGE);
			if (attributeInstance != null) {
				if (isHiding()) {
					attributeInstance.removeModifier(FOLLOW_RANGE_BONUS.id());
				} else {
					attributeInstance.addOrReplacePermanentModifier(FOLLOW_RANGE_BONUS);
				}
			}
			if (isHiding()) {
				heal(0.05f);
			}
			if (this.isHiding() || this.getAttackState() == SnowCrabJumpingPhase.ID || this.getAttackState() == SnowCrabLandPhase.ID) {
				float yRot = this.getFixedYRot();
				this.yBodyRot = Mth.wrapDegrees(yRot - 90f);
				this.yHeadRot = Mth.wrapDegrees(yRot - 90f);
				this.setYRot(Mth.wrapDegrees(yRot - 90f));
			}
		}
	}

	@Override
	public int getAttackState() {
		return this.entityData.get(ATTACK_STATE);
	}

	@Override
	public void setAttackState(int attackState) {
		this.entityData.set(ATTACK_STATE, attackState);
	}

	@Override
	public int getAttackTicks() {
		return this.entityData.get(ATTACK_TICKS);
	}

	@Override
	public void setAttackTicks(int attackTicks) {
		this.entityData.set(ATTACK_TICKS, attackTicks);
	}

	public float getFixedYRot() {
		return this.entityData.get(FIXED_Y_ROT);
	}

	public void setFixedYRot(float attackYRot) {
		this.entityData.set(FIXED_Y_ROT, attackYRot);
	}

	public boolean isHiding() {
		return this.entityData.get(HIDING);
	}

	public void setHiding(boolean hiding) {
		this.entityData.set(HIDING, hiding);
	}

	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);
		if (compoundTag.contains("Hiding", CompoundTag.TAG_BYTE)) {
			this.setHiding(compoundTag.getBoolean("Hiding"));
		}
	}

	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("Hiding", this.isHiding());
	}

	@Override
	public void setDeltaMovement(Vec3 vec3) {
		if (this.isHiding() || getAttackState() == SnowCrabMeleePhase.ID || getAttackState() == SnowCrabHidePhase.ID) {
			super.setDeltaMovement(new Vec3(0, Math.min(vec3.y, 0), 0));
		} else {
			super.setDeltaMovement(vec3);
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "MeleeAttack", state -> PlayState.STOP)
			.triggerableAnim("MeleeAttack", DefaultAnimations.ATTACK_SLAM).transitionLength(5));
		controllers.add(new AnimationController<>(this, "Jump", state -> PlayState.STOP)
			.triggerableAnim("Jump", DefaultAnimations.JUMP).transitionLength(5));
		controllers.add(new AnimationController<>(this, "Land", state -> PlayState.STOP)
			.triggerableAnim("Land", LAND).transitionLength(5));
		controllers.add(new AnimationController<>(this, "Jumping", state -> getAttackState() == SnowCrabJumpingPhase.ID ? state.setAndContinue(JUMPING) : PlayState.STOP));
		controllers.add(new AnimationController<>(this, "Status", state -> state.setAndContinue(isHiding() ? (getAttackState() == SnowCrabWakePhase.ID ? WAKE : IDLE_HIDING) : (getAttackState() == SnowCrabHidePhase.ID ? HIDE : DefaultAnimations.IDLE))));
		controllers.add(new AnimationController<>(this, "Walk", state -> state.isMoving() && getAttackState() != SnowCrabMeleePhase.ID && getAttackState() != SnowCrabHidePhase.ID ? state.setAndContinue(DefaultAnimations.WALK) : PlayState.STOP));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {

	}

	@Override
	public boolean isAlliedTo(Entity entity) {
		return super.isAlliedTo(entity) || entity instanceof SnowCrab;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return EWSoundEvents.SNOW_CRAB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return EWSoundEvents.SNOW_CRAB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		super.playStepSound(pos, state);
		this.playSound(EWSoundEvents.SNOW_CRAB_STEP.get(), 0.2f, 1);
	}

	private class SnowCrabBodyRotationControl extends BodyRotationControl {
		public SnowCrabBodyRotationControl() {
			super(SnowCrab.this);
		}

		@Override
		public void clientTick() {
			if (SnowCrab.this.isHiding() || SnowCrab.this.getAttackState() == SnowCrabJumpingPhase.ID || SnowCrab.this.getAttackState() == SnowCrabLandPhase.ID) {
				float yRot = SnowCrab.this.getFixedYRot();
				SnowCrab.this.yBodyRot = Mth.wrapDegrees(yRot - 90f);
				SnowCrab.this.yHeadRot = Mth.wrapDegrees(yRot - 90f);
				SnowCrab.this.setYRot(Mth.wrapDegrees(yRot - 90f));
			} else {
				super.clientTick();
			}
		}
	}
}
