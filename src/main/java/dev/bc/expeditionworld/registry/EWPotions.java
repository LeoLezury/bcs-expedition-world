package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWPotions {
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, ExpeditionWorld.ID);
	public static final DeferredHolder<Potion, Potion> CATWALK = POTIONS.register("catwalk", () -> new Potion(new MobEffectInstance(EWMobEffects.CATWALK, 3600)));
	public static final DeferredHolder<Potion, Potion> MEDIUM_CATWALK = POTIONS.register("medium_catwalk", () -> new Potion("catwalk", new MobEffectInstance(EWMobEffects.CATWALK, 4800)));
	public static final DeferredHolder<Potion, Potion> LONG_CATWALK = POTIONS.register("long_catwalk", () -> new Potion("catwalk", new MobEffectInstance(EWMobEffects.CATWALK, 12000)));
	public static final DeferredHolder<Potion, Potion> FETTERED = POTIONS.register("fettered", () -> new Potion(new MobEffectInstance(EWMobEffects.FETTERED, 3600)));
	public static final DeferredHolder<Potion, Potion> LONG_FETTERED = POTIONS.register("long_fettered", () -> new Potion("fettered", new MobEffectInstance(EWMobEffects.FETTERED, 7200)));
}
