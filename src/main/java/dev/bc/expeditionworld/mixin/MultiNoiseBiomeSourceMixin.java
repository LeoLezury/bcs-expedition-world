package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import dev.bc.expeditionworld.world.biome.EWExtendedBiomes;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MultiNoiseBiomeSourceMixin implements ExtendedBiomeSource {
	@Inject(method = "getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;", at = @At("TAIL"), cancellable = true)
	private void expeditionWorld$getNoiseBiome(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
		Holder<Biome> original = cir.getReturnValue();
		Holder<Biome> replaced = EWExtendedBiomes.replaceBiome(this, original, x, y, z, sampler);
		if (original != replaced) {
			cir.setReturnValue(replaced);
		}
	}
}
