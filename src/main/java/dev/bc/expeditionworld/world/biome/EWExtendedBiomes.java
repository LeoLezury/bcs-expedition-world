package dev.bc.expeditionworld.world.biome;

import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.data.EWBiomes;
import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.List;

public class EWExtendedBiomes {
    public static final List<ResourceKey<Biome>> POSSIBLE_BIOMES = List.of(EWBiomes.FROZEN_CAVES);

    public static Holder<Biome> replaceBiome(ExtendedBiomeSource biomeSource, Holder<Biome> original, int x, int y, int z, Climate.Sampler sampler) {
        Climate.TargetPoint targetPoint = sampler.sample(x, y, z);
        float temperature = Climate.unquantizeCoord(targetPoint.temperature());
        // float humidity = Climate.unquantizeCoord(targetPoint.humidity());
        float continentalness = Climate.unquantizeCoord(targetPoint.continentalness());
        // float erosion = Climate.unquantizeCoord(targetPoint.erosion());
        float depth = Climate.unquantizeCoord(targetPoint.depth());
        // float weirdness = Climate.unquantizeCoord(targetPoint.weirdness());
        BlockPos pos = new BlockPos(QuartPos.toBlock(x), QuartPos.toBlock(y), QuartPos.toBlock(z));
        if (!pos.closerThan(BlockPos.ZERO, 1000)
            && temperature >= -1 && temperature <= -0.45
            && continentalness >= 0.4 && continentalness <= 1
            && depth >= 0.3 && depth <= 1.5) {
            return biomeSource.getBiome(EWBiomes.FROZEN_CAVES);
        }
        return original;
    }

    private static SurfaceRules.RuleSource bedrockFloor() {
        SurfaceRules.RuleSource bedrock = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());
        SurfaceRules.ConditionSource bedrockCondition = SurfaceRules.verticalGradient("bedrock", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5));
        return SurfaceRules.ifTrue(bedrockCondition, bedrock);
    }

    private static SurfaceRules.RuleSource frozenCaves() {
        SurfaceRules.RuleSource ice = SurfaceRules.state(EWBlocks.FROZEN_STONE.get().defaultBlockState());
        SurfaceRules.RuleSource grass = SurfaceRules.state(EWBlocks.FROZEN_GRASS_BLOCK.get().defaultBlockState());
        SurfaceRules.RuleSource dirt = SurfaceRules.state(EWBlocks.FROZEN_DIRT.get().defaultBlockState());
        SurfaceRules.RuleSource checkedSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(0, 0), grass), dirt);
        SurfaceRules.RuleSource floor = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, checkedSurface), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, dirt));
        return SurfaceRules.sequence(bedrockFloor(), floor, ice);
    }

    public static SurfaceRules.RuleSource getSurfaceRules() {
        return SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(EWBiomes.FROZEN_CAVES), frozenCaves()));
    }
}
