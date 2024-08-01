package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.data.EWBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Stray.class)
public abstract class StrayMixin {
	@Inject(method = "checkStraySpawnRules", at = @At("RETURN"), cancellable = true)
	private static void expeditionWorld$checkStraySpawnRules(EntityType<Stray> stray, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
		if (level.getBiome(pos).is(EWBiomes.FROZEN_CAVES) && Monster.checkAnyLightMonsterSpawnRules(stray, level, spawnType, pos, random)) {
			cir.setReturnValue(true);
		}
	}
}
