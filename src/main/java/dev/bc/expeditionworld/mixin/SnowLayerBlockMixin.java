package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.block.EWBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowLayerBlock.class)
public class SnowLayerBlockMixin {
	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	private void expeditionWorld$randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
		for (BlockPos blockPos : BlockPos.withinManhattan(pos, 7, 7, 7)) {
			if (level.getBlockState(blockPos).is(EWBlockTags.PREVENTS_MELTING)) {
				ci.cancel();
			}
		}
	}
}
