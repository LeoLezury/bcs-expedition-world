package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.block.EWBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrostedIceBlock.class)
public abstract class FrostedIceBlockMixin {
    @Inject(method = "slightlyMelt", at = @At("HEAD"), cancellable = true)
    private void expeditionWorld$melt(BlockState state, Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        for (BlockPos blockPos : BlockPos.withinManhattan(pos, 7, 7, 7)) {
            if (level.getBlockState(blockPos).is(EWBlockTags.PREVENTS_MELTING)) {
                cir.cancel();
                cir.setReturnValue(false);
            }
        }
    }
}
