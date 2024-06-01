package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.data.EWBiomes;
import dev.bc.expeditionworld.entity.EWEntityTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {
    @Inject(method = "isValidSpawn", at = @At("HEAD"), cancellable = true)
    private void expeditionWorld$isValidSpawn(BlockGetter getter, BlockPos pos, EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        // hack
        if (getter instanceof LevelAccessor level && level.getBiome(pos).is(EWBiomes.FROZEN_CAVES) && !type.is(EWEntityTags.CAN_SPAWN_IN_FROZEN_CAVES)) {
            cir.setReturnValue(false);
        }
    }
}
