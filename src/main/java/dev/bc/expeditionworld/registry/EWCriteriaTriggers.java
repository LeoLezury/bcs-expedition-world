package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWCriteriaTriggers {
	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, ExpeditionWorld.ID);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> STAND_ON_SCULK_SHRIEKER_WITH_CATWALK = TRIGGERS.register("stand_on_sculk_shrieker_with_catwalk", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> WAKE_MIMICHEST = TRIGGERS.register("wake_mimichest", PlayerTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, PlayerTrigger> FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM = TRIGGERS.register("full_armor_set_with_trapped_soul_trim", PlayerTrigger::new);
}
