package dev.bc.expeditionworld.entity.living.mimichest;

import dev.bc.expeditionworld.entity.ai.AttackManager;
import dev.bc.expeditionworld.entity.ai.MoveToTargetGoal;
import dev.bc.expeditionworld.entity.ai.MultiPhaseAttacker;
import dev.bc.expeditionworld.particle.EWParticles;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class Mimichest extends Monster implements GeoEntity, MultiPhaseAttacker {
    protected static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(Mimichest.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> ATTACK_TICKS = SynchedEntityData.defineId(Mimichest.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> ATTACK_Y_ROT = SynchedEntityData.defineId(Mimichest.class, EntityDataSerializers.FLOAT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final AttackManager<Mimichest> attackManager = new AttackManager<>(this, List.of(
            new MimichestMeleePhase(),
            new MimichestRangedAttackPhase()
    ));

    public Mimichest(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, false);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, 0);
        this.entityData.define(ATTACK_TICKS, 0);
        this.entityData.define(ATTACK_Y_ROT, 0f);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveToTargetGoal(this, 1.0));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this, Mimichest.class).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractIllager.class, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new MimichestBodyRotationControl();
    }

    public static AttributeSupplier.Builder createChestAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.FLYING_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 200.0D)
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    public static AttributeSupplier.Builder createPotAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.FLYING_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 200.0D)
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    @Override
    protected void customServerAiStep() {
        this.attackManager.tick();
    }

    public void aiStep() {
        if (this.level().isClientSide && this.getRandom().nextInt(4) == 0) {
            this.level().addParticle(EWParticles.TRAPPED_SOUL.get(), this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 0.1D, -this.random.nextDouble() * 0.1D, (this.random.nextDouble() - 0.5D) * 0.1D);
        }
        super.aiStep();
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

    public float getAttackYRot() {
        return this.entityData.get(ATTACK_Y_ROT);
    }

    public void setAttackYRot(float attackYRot) {
        this.entityData.set(ATTACK_Y_ROT, attackYRot);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericIdleController(this));
        controllers.add(new AnimationController<>(this, "MeleeAttack", state -> PlayState.STOP)
                .triggerableAnim("MeleeAttack", DefaultAnimations.ATTACK_SWING).transitionLength(5));
        controllers.add(new AnimationController<>(this, "RangedAttack", state -> PlayState.STOP)
                .triggerableAnim("RangedAttack", DefaultAnimations.ATTACK_THROW).transitionLength(5));
        controllers.add(new AnimationController<>(this, "Spawn", state -> PlayState.STOP)
                .triggerableAnim("Spawn", DefaultAnimations.SPAWN));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean isAlliedTo(Entity entity) {
        return super.isAlliedTo(entity) || entity instanceof Mimichest;
    }

    private class MimichestBodyRotationControl extends BodyRotationControl {
        public MimichestBodyRotationControl() {
            super(Mimichest.this);
        }

        @Override
        public void clientTick() {
            if (Mimichest.this.getAttackState() == MimichestMeleePhase.ID || Mimichest.this.getAttackState() == MimichestRangedAttackPhase.ID) {
                float yRot = Mimichest.this.getAttackYRot();
                Mimichest.this.yBodyRot = Mth.wrapDegrees(yRot - 90f);
                Mimichest.this.yHeadRot = Mth.wrapDegrees(yRot - 90f);
            } else {
                super.clientTick();
            }
        }
    }
}
