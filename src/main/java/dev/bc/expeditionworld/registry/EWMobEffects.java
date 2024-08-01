package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.potion.CatwalkMobEffect;
import dev.bc.expeditionworld.potion.FrozenMobEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ExpeditionWorld.ID);
	public static final DeferredHolder<MobEffect, CatwalkMobEffect> CATWALK = MOB_EFFECTS.register("catwalk", () -> new CatwalkMobEffect(MobEffectCategory.BENEFICIAL, 0x052a32));
	public static final DeferredHolder<MobEffect, MobEffect> FETTERED = MOB_EFFECTS.register("fettered", () -> new MobEffect(MobEffectCategory.NEUTRAL, 0x525252).addAttributeModifier(Attributes.MOVEMENT_SPEED, ExpeditionWorld.id("fettered.speed"), -0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
	public static final DeferredHolder<MobEffect, MobEffect> FROZEN = MOB_EFFECTS.register("frozen", () -> new FrozenMobEffect(MobEffectCategory.HARMFUL, 0x83a1d7).addAttributeModifier(Attributes.MOVEMENT_SPEED, ExpeditionWorld.id("frozen.speed"), -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
}
