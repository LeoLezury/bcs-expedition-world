package dev.bc.expeditionworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class IceLanternBlock extends Block {
    public IceLanternBlock(Properties properties) {
        super(properties);
    }

    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        for (Direction direction : Direction.values()) {
            BlockPos relative = pos.relative(direction);
            if (serverLevel.getBlockState(relative).is(Blocks.LAVA) && randomSource.nextInt(3) == 0) {
                serverLevel.setBlockAndUpdate(relative, Blocks.OBSIDIAN.defaultBlockState());
            }
        }
    }
}
