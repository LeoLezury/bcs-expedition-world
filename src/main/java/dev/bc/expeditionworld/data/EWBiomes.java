package dev.bc.expeditionworld.data;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Nullable;

public class EWBiomes {
	public static final ResourceKey<Biome> FROZEN_CAVES = create("frozen_caves");

	public static void bootstrap(BootstapContext<Biome> context) {
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);
		context.register(FROZEN_CAVES, frozenCaves(placedFeatures, carvers));
	}

	public static Biome frozenCaves(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
		MobSpawnSettings.Builder mobSpawns = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(mobSpawns);
		BiomeGenerationSettings.Builder genSettings = new BiomeGenerationSettings.Builder(placedFeatures, carvers);

		genSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
		genSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
		genSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
		BiomeDefaultFeatures.addDefaultCrystalFormations(genSettings);
		BiomeDefaultFeatures.addDefaultMonsterRoom(genSettings);
		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);
		genSettings.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);
		BiomeDefaultFeatures.addSurfaceFreezing(genSettings);

		genSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EWPlacedFeatures.ICE_CRYSTAL_ORE);

		genSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, EWPlacedFeatures.ICE_SPIKE);
		genSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, EWPlacedFeatures.HANGING_ICE_SPIKE);

		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EWPlacedFeatures.FROZEN_CAVES_FLOWERS);
		genSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EWPlacedFeatures.FROZEN_CAVES_VEGETATION);

		return biome(true, 0.0F, 0.5F, mobSpawns, genSettings, Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS));
	}

	protected static int calculateSkyColor(float temperature) {
		float f = temperature / 3.0F;
		f = Mth.clamp(f, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}

	private static Biome biome(boolean hasPercipitation, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music backgroundMusic) {
		return biome(hasPercipitation, temperature, downfall, 4159204, 329011, (Integer) null, (Integer) null, mobSpawnSettings, generationSettings, backgroundMusic);
	}

	private static Biome biome(boolean hasPrecipitation, float temperature, float downfall, int waterColor, int waterFogColor, @Nullable Integer grassColorOverride, @Nullable Integer foliageColorOverride, MobSpawnSettings.Builder mobSpawnSettings, BiomeGenerationSettings.Builder generationSettings, @Nullable Music backgroundMusic) {
		BiomeSpecialEffects.Builder biomespecialeffects$builder = (new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(backgroundMusic);
		if (grassColorOverride != null) {
			biomespecialeffects$builder.grassColorOverride(grassColorOverride);
		}

		if (foliageColorOverride != null) {
			biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);
		}

		return (new Biome.BiomeBuilder()).hasPrecipitation(hasPrecipitation).temperature(temperature).downfall(downfall).specialEffects(biomespecialeffects$builder.build()).mobSpawnSettings(mobSpawnSettings.build()).generationSettings(generationSettings.build()).build();
	}

	public static ResourceKey<Biome> create(String name) {
		return ResourceKey.create(Registries.BIOME, ExpeditionWorld.id(name));
	}
}
