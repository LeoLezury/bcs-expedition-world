package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin {
    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void expeditionWorld$melt(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        for (BlockPos blockPos : BlockPos.withinManhattan(pos, 7, 7, 7)) {
            if (level.getBlockState(blockPos).is(EWBlocks.ICE_LANTERN.get())) {
                ci.cancel();
            }
        }
    }
}
