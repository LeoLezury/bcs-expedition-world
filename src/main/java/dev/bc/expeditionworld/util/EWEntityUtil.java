package dev.bc.expeditionworld.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class EWEntityUtil {
    public static boolean canReachTarget(Mob mob, double range) {
        LivingEntity target = mob.getTarget();
        if (target == null) {
            return false;
        }
        return mob.distanceTo(target) <= range + mob.getBbWidth() / 2f + target.getBbWidth() / 2f;
    }

    public static void performMeleeAttack(Mob mob, double range) {
        LivingEntity target = mob.getTarget();
        if (target == null) {
            return;
        }
        for (LivingEntity livingEntity : mob.level().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, mob, mob.getBoundingBox().inflate(range))) {
            if (livingEntity.getUUID().equals(target.getUUID()) && canReachTarget(mob, range)) {
                mob.doHurtTarget(livingEntity);
            }
        }
    }

    public static boolean targetValid(Mob mob) {
        return mob.getTarget() != null && mob.getTarget().isAlive();
    }
}
