package dev.bc.expeditionworld.data;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class EWPlacedFeatures {
	public static final ResourceKey<PlacedFeature> ICE_CRYSTAL_ORE = create("ice_crystal_ore");
	public static final ResourceKey<PlacedFeature> ICE_SPIKE = create("ice_spike");
	public static final ResourceKey<PlacedFeature> HANGING_ICE_SPIKE = create("hanging_ice_spike");
	public static final ResourceKey<PlacedFeature> FROZEN_CAVES_FLOWERS = create("frozen_caves_flowers");
	public static final ResourceKey<PlacedFeature> FROZEN_CAVES_VEGETATION = create("frozen_caves_vegetation");

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, ICE_CRYSTAL_ORE, configuredFeatures.getOrThrow(EWConfiguredFeatures.ICE_CRYSTAL_ORE), commonOrePlacement(15, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
		PlacementUtils.register(context, ICE_SPIKE, configuredFeatures.getOrThrow(EWConfiguredFeatures.ICE_SPIKE), CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.hasSturdyFace(Direction.UP), BlockPredicate.ONLY_IN_AIR_PREDICATE, 32), BiomeFilter.biome());
		PlacementUtils.register(context, HANGING_ICE_SPIKE, configuredFeatures.getOrThrow(EWConfiguredFeatures.ICE_SPIKE), CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 32), BiomeFilter.biome());
		PlacementUtils.register(context, FROZEN_CAVES_FLOWERS, configuredFeatures.getOrThrow(EWConfiguredFeatures.FROZEN_CAVES_FLOWERS), CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.hasSturdyFace(Direction.UP), BlockPredicate.ONLY_IN_AIR_PREDICATE, 32), BiomeFilter.biome());
		PlacementUtils.register(context, FROZEN_CAVES_VEGETATION, configuredFeatures.getOrThrow(EWConfiguredFeatures.FROZEN_CAVES_VEGETATION), CountPlacement.of(250), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.hasSturdyFace(Direction.UP), BlockPredicate.ONLY_IN_AIR_PREDICATE, 32), BiomeFilter.biome());
	}

	private static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ExpeditionWorld.id(name));
	}

	public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
		return List.of(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
	}

	public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(count), modifier);
	}
}
