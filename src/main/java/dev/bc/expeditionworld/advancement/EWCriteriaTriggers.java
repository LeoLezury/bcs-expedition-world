package dev.bc.expeditionworld.advancement;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class EWCriteriaTriggers {
    public static final PlayerTrigger STAND_ON_SCULK_SHRIEKER_WITH_CATWALK = new PlayerTrigger(new ResourceLocation(ExpeditionWorld.MOD_ID, "stand_on_sculk_shrieker_with_catwalk"));

    public static void register() {
        CriteriaTriggers.register(STAND_ON_SCULK_SHRIEKER_WITH_CATWALK);
    }
}
