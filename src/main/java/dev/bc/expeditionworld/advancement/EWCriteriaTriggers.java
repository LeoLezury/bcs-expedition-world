package dev.bc.expeditionworld.advancement;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class EWCriteriaTriggers {
    public static final PlayerTrigger STAND_ON_SCULK_SHRIEKER_WITH_CATWALK = new PlayerTrigger(new ResourceLocation(ExpeditionWorld.MOD_ID, "stand_on_sculk_shrieker_with_catwalk"));
    public static final PlayerTrigger WAKE_MIMICHEST = new PlayerTrigger(new ResourceLocation(ExpeditionWorld.MOD_ID, "wake_mimichest"));
    public static final PlayerTrigger FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM = new PlayerTrigger(new ResourceLocation(ExpeditionWorld.MOD_ID, "full_armor_set_with_trapped_soul_trim"));

    public static void register() {
        CriteriaTriggers.register(STAND_ON_SCULK_SHRIEKER_WITH_CATWALK);
        CriteriaTriggers.register(WAKE_MIMICHEST);
        CriteriaTriggers.register(FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM);
    }
}
