package dev.bc.expeditionworld.world;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.Map;

public interface ExtendedBiomeSource {
	void setBiomes(Map<ResourceKey<Biome>, Holder<Biome>> biomes);

	Map<ResourceKey<Biome>, Holder<Biome>> getBiomes();

	Holder<Biome> getBiome(ResourceKey<Biome> key);
}
