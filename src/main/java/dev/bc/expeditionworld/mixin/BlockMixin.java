package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.block.FetteredChestBlock;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private static void expeditionWorld$dropResources(BlockState state, Level level, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo ci) {
        if (level instanceof ServerLevel && state.getBlock() instanceof FetteredChestBlock && state.getValue(FetteredChestBlock.HALF) == DoubleBlockHalf.LOWER && level.getRandom().nextBoolean()) {
            ci.cancel();
            Mimichest mimichest = state.is(EWBlocks.FETTERED_CHEST.get()) ? new Mimichest(EWEntities.MIMICHEST.get(), level) : new Mimichest(EWEntities.MIMIPOT.get(), level);
            mimichest.setPos(pos.getCenter().add(0, -0.5, 0));
            mimichest.setFixedYRot(state.getValue(FetteredChestBlock.FACING).toYRot() + 90);
            level.addFreshEntity(mimichest);
            mimichest.triggerSpawn();
            if (entity instanceof ServerPlayer serverPlayer) {
                EWCriteriaTriggers.WAKE_MIMICHEST.trigger(serverPlayer);
            }
        }
    }
}
