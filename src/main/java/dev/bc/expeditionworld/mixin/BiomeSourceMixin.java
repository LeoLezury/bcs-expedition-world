package dev.bc.expeditionworld.mixin;

import com.google.common.base.Suppliers;
import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import org.spongepowered.asm.mixin.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(BiomeSource.class)
public abstract class BiomeSourceMixin implements ExtendedBiomeSource {
	@Shadow
	@Final
	@Mutable
	private Supplier<Set<Holder<Biome>>> possibleBiomes;
	@Unique
	private final Map<ResourceKey<Biome>, Holder<Biome>> expeditionWorld$biomesMap = new HashMap<>();

	@Override
	public void setBiomes(Map<ResourceKey<Biome>, Holder<Biome>> biomes) {
		expeditionWorld$biomesMap.clear();
		expeditionWorld$biomesMap.putAll(biomes);
		Set<Holder<Biome>> set = new HashSet<>(possibleBiomes.get());
		set.addAll(biomes.values());
		possibleBiomes = Suppliers.memoize(() -> set);
	}

	@Override
	public Map<ResourceKey<Biome>, Holder<Biome>> getBiomes() {
		return expeditionWorld$biomesMap;
	}

	@Override
	public Holder<Biome> getBiome(ResourceKey<Biome> key) {
		return expeditionWorld$biomesMap.get(key);
	}
}
