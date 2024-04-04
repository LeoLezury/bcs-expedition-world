package dev.bc.expeditionworld.entity.living.mimichest;

import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.util.EWEntityUtil;
import dev.bc.expeditionworld.util.EWMathUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MimichestMeleePhase extends AttackPhase<Mimichest> {
    public static final int ID = 1;
    private Vec3 motion = Vec3.ZERO;

    public MimichestMeleePhase() {
        super(ID, 1, 80, 90);
    }

    @Override
    public boolean canStart(Mimichest entity, boolean coolDownOver) {
        return EWEntityUtil.targetValid(entity) && coolDownOver && EWEntityUtil.canReachTarget(entity, 5);
    }

    @Override
    public void onStart(Mimichest entity) {
        entity.triggerAnim("MeleeAttack", "MeleeAttack");
    }

    @Override
    public void tick(Mimichest entity) {
        LivingEntity target = entity.getTarget();
        if (target != null) {
            if (entity.getAttackTicks() == 8) {
                motion = target.position().subtract(entity.position()).normalize().scale(0.5);
                entity.setAttackYRot(EWMathUtil.positionToYaw(Vec3.ZERO, motion));
            }
        }
        if (entity.getAttackTicks() >= 8 && entity.getAttackTicks() <= 50) {
            entity.hurtMarked = true;
            entity.setDeltaMovement(motion);
        } else {
            entity.setDeltaMovement(Vec3.ZERO);
        }
        if (entity.getAttackTicks() >= 8 && entity.getAttackTicks() <= 54) {
            EWEntityUtil.performMeleeAttack(entity, 1);
        }
        entity.getNavigation().stop();
    }

    @Override
    public boolean canContinue(Mimichest entity) {
        return true;
    }

    @Override
    public void onStop(Mimichest entity) {

    }
}
