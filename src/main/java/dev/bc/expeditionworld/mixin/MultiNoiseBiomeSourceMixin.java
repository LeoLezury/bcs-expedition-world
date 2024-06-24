package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import dev.bc.expeditionworld.world.biome.EWExtendedBiomes;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MultiNoiseBiomeSourceMixin implements ExtendedBiomeSource {
	@Unique
	private final Map<ResourceKey<Biome>, Holder<Biome>> biomesMap = new HashMap<>();

	@Inject(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At("TAIL"), cancellable = true)
	private void expeditionWorld$getNoiseBiome(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
		Holder<Biome> original = cir.getReturnValue();
		Holder<Biome> replaced = EWExtendedBiomes.replaceBiome(this, original, x, y, z, sampler);
		if (original != replaced) {
			cir.setReturnValue(replaced);
		}
	}

	@Inject(method = "collectPossibleBiomes", at = @At("TAIL"), cancellable = true)
	private void expeditionWorld$collectPossibleBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir) {
		Stream<Holder<Biome>> stream = cir.getReturnValue();
		ArrayList<Holder<Biome>> list = new ArrayList<>(stream.toList());
		list.addAll(biomesMap.values());
		cir.setReturnValue(list.stream());
	}

	@Override
	public void setBiomes(Map<ResourceKey<Biome>, Holder<Biome>> biomes) {
		if (biomesMap.isEmpty()) {
			biomesMap.putAll(biomes);
		}
	}

	@Override
	public Map<ResourceKey<Biome>, Holder<Biome>> getBiomes() {
		return biomesMap;
	}

	@Override
	public Holder<Biome> getBiome(ResourceKey<Biome> key) {
		return biomesMap.get(key);
	}
}
