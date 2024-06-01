package dev.bc.expeditionworld.data;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.world.feature.EWFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

public class EWConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_CRYSTAL_ORE = create("ice_crystal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_SPIKE = create("ice_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_CAVES_FLOWERS = create("frozen_caves_flowers");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_CAVES_VEGETATION = create("frozen_caves_vegetation");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, ICE_CRYSTAL_ORE, Feature.ORE, new OreConfiguration(new BlockMatchTest(EWBlocks.FROZEN_STONE.get()), EWBlocks.ICE_CRYSTAL_ORE.get().defaultBlockState(), 8));
        FeatureUtils.register(context, ICE_SPIKE, EWFeatures.ICE_SPIKE.get(), new NoneFeatureConfiguration());
        FeatureUtils.register(context, FROZEN_CAVES_FLOWERS, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(EWBlocks.ICE_FLOWER.get().defaultBlockState(), 10).add(EWBlocks.FRIGID_GLADIOLUS.get().defaultBlockState(), 3).build()), 32));
        FeatureUtils.register(context, FROZEN_CAVES_VEGETATION, Feature.RANDOM_PATCH, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(EWBlocks.FROZEN_GRASS.get().defaultBlockState(), 10).build()), 128));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ExpeditionWorld.id(name));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }
}
