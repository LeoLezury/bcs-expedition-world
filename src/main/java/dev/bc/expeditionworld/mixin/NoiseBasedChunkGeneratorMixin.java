package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.world.biome.EWExtendedBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class NoiseBasedChunkGeneratorMixin {
	@Mutable
	@Shadow
	@Final
	private Holder<NoiseGeneratorSettings> settings;
	@Unique
	private boolean modifiedRules = false;

	@Inject(method = "buildSurface(Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/WorldGenerationContext;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/core/Registry;Lnet/minecraft/world/level/levelgen/blending/Blender;)V", at = @At("HEAD"))
	private void expeditionWorld$buildSurface(ChunkAccess chunk, WorldGenerationContext context, RandomState random, StructureManager structureManager, BiomeManager biomeManager, Registry<Biome> biomes, Blender blender, CallbackInfo ci) {
		if (!modifiedRules && settings.isBound()) {
			modifiedRules = true;
			SurfaceRules.RuleSource original = settings.value().surfaceRule();
			SurfaceRules.RuleSource rules = SurfaceRules.sequence(EWExtendedBiomes.getSurfaceRules(), original);
			settings = Holder.direct(new NoiseGeneratorSettings(
				settings.value().noiseSettings(),
				settings.value().defaultBlock(),
				settings.value().defaultFluid(),
				settings.value().noiseRouter(),
				rules,
				settings.value().spawnTarget(),
				settings.value().seaLevel(),
				settings.value().disableMobGeneration(),
				settings.value().aquifersEnabled(),
				settings.value().oreVeinsEnabled(),
				settings.value().useLegacyRandomSource()
			));
		}
	}
}
