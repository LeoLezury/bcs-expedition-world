package dev.bc.expeditionworld.entity.living.mimichest;

import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.entity.ai.AttackPhase;
import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import dev.bc.expeditionworld.util.EWEntityUtil;
import dev.bc.expeditionworld.util.EWMathUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MimichestRangedAttackPhase extends AttackPhase<Mimichest> {
    public static final int ID = 2;

    public MimichestRangedAttackPhase() {
        super(ID, 1, 30, 40);
    }

    @Override
    public boolean canStart(Mimichest entity, boolean coolDownOver) {
        return EWEntityUtil.targetValid(entity) && coolDownOver && !EWEntityUtil.canReachTarget(entity, 5);
    }

    @Override
    public void onStart(Mimichest entity) {
        entity.triggerAnim("RangedAttack", "RangedAttack");
    }

    @Override
    public void tick(Mimichest entity) {
        LivingEntity target = entity.getTarget();
        if (target != null) {
            entity.setAttackYRot(EWMathUtil.positionToYaw(entity.position(), target.position()));
            if (entity.getAttackTicks() == 10) {
                Vec3 basePos = target.position().add(0, target.getBbHeight() * 0.4, 0);
                float baseYaw = EWMathUtil.positionToYaw(entity.position(), basePos);
                float basePitch = EWMathUtil.positionToPitch(entity.position(), basePos);
                float baseRadius = (float) basePos.distanceTo(entity.position());
                shootAt(entity, basePos);
                shootAt(entity, EWMathUtil.rotationToPosition(entity.position(), baseRadius, basePitch, baseYaw + 15));
                shootAt(entity, EWMathUtil.rotationToPosition(entity.position(), baseRadius, basePitch, baseYaw - 15));
            }
        }
    }

    private void shootAt(Mimichest entity, Vec3 pos) {
        MimichestKnife knife = new MimichestKnife(entity, entity.level());
        if (entity.getType() == EWEntities.MIMIPOT.get()) {
            knife.setBricks(true);
        }
        knife.setPos(knife.getX(), entity.getY() + entity.getBbHeight() * 0.1, knife.getZ());
        double d0 = pos.x - entity.getX();
        double d1 = pos.y - knife.getY();
        double d2 = pos.z - entity.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        knife.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6f, 0.2f);
        entity.level().addFreshEntity(knife);
    }

    @Override
    public boolean canContinue(Mimichest entity) {
        return true;
    }

    @Override
    public void onStop(Mimichest entity) {

    }
}
