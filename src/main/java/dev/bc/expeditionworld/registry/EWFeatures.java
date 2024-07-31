package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.world.feature.IceSpikeFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, ExpeditionWorld.ID);

	public static final DeferredHolder<Feature<?>, IceSpikeFeature> ICE_SPIKE = FEATURES.register("ice_spike", () -> new IceSpikeFeature(NoneFeatureConfiguration.CODEC));
}
