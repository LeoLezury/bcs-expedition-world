package dev.bc.expeditionworld.world.feature;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ExpeditionWorld.ID);

	public static final RegistryObject<IceSpikeFeature> ICE_SPIKE = FEATURES.register("ice_spike", () -> new IceSpikeFeature(NoneFeatureConfiguration.CODEC));
}
