package dev.bc.expeditionworld.world.feature;

import com.mojang.serialization.Codec;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.block.PointedIceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class IceSpikeFeature extends Feature<NoneFeatureConfiguration> {
    public IceSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        Direction direction = level.isEmptyBlock(origin.above()) ? Direction.UP : Direction.DOWN;
        int length = random.nextInt(5, 9);
        int total = 0;
        for (int i = 0; i < length; i++) {
            BlockPos pos = origin.relative(direction, i);
            if (level.isEmptyBlock(pos)) {
                setBlock(level, pos, Blocks.ICE.defaultBlockState());
                total++;
            }
        }
        if (level.isEmptyBlock(origin) || level.getBlockState(origin).is(BlockTags.DIRT) || level.getBlockState(origin).is(BlockTags.BASE_STONE_OVERWORLD)) {
            setBlock(level, origin, EWBlocks.ICE_LANTERN.get().defaultBlockState());
        }
        List<Direction> directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
        for (Direction dir : directions) {
            BlockPos pos = origin.relative(dir);
            int len = random.nextInt(1, Math.min(length / 2 + 1, 4));
            for (int i = 0; i < len; i++) {
                BlockPos blockPos = pos.relative(direction, i);
                if ((level.isEmptyBlock(blockPos) || level.getBlockState(blockPos).is(BlockTags.DIRT) || level.getBlockState(blockPos).is(BlockTags.BASE_STONE_OVERWORLD)) && !level.isEmptyBlock(blockPos.relative(direction.getOpposite()))) {
                    setBlock(level, blockPos, Blocks.ICE.defaultBlockState());
                    total++;
                }
            }
        }
        boolean placeIceAround = random.nextInt(5) == 0;
        for (int x = -3; x <= 3; x++) {
            for (int y = -6; y <= 6; y++) {
                for (int z = -3; z <= 3; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    if (level.isEmptyBlock(pos)) {
                        boolean upSturdy = level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN) && level.getBlockState(pos.above()).is(Blocks.ICE);
                        boolean downSturdy = level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP) && level.getBlockState(pos.below()).is(Blocks.ICE);
                        if (upSturdy && downSturdy) {
                            if (random.nextInt(5) == 0) {
                                setBlock(level, pos, EWBlocks.POINTED_ICE.get().defaultBlockState().setValue(PointedIceBlock.DIRECTION, random.nextBoolean() ? Direction.UP : Direction.DOWN));
                            }
                            total++;
                        } else if (upSturdy) {
                            if (random.nextInt(5) == 0) {
                                setBlock(level, pos, EWBlocks.POINTED_ICE.get().defaultBlockState().setValue(PointedIceBlock.DIRECTION, Direction.DOWN));
                            }
                            total++;
                        } else if (downSturdy) {
                            if (random.nextInt(5) == 0) {
                                setBlock(level, pos, EWBlocks.POINTED_ICE.get().defaultBlockState().setValue(PointedIceBlock.DIRECTION, Direction.UP));
                            }
                            total++;
                        }
                    } else if (placeIceAround && (level.getBlockState(pos).is(BlockTags.DIRT) || level.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD)) && pos.closerThan(origin, 4)) {
                        setBlock(level, pos, random.nextInt(8) == 0 ? Blocks.BLUE_ICE.defaultBlockState() : Blocks.ICE.defaultBlockState());
                        total++;
                    }
                }
            }
        }
        return total > 0;
    }
}
