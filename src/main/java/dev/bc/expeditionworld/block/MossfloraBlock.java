package dev.bc.expeditionworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MossfloraBlock extends BushBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final VoxelShape BOUNDING_BOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);

    public MossfloraBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this) && blockstate.getValue(AGE) < 3) {
            return blockstate.setValue(AGE, blockstate.getValue(AGE) + 1);
        }
        return super.getStateForPlacement(context);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(AGE) < 3 || super.canBeReplaced(state, context);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return BOUNDING_BOX;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return super.mayPlaceOn(state, blockGetter, pos) || state.is(Blocks.MOSS_BLOCK);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        if (serverLevel.getBlockState(pos.below()).is(Blocks.MOSS_BLOCK) && state.getValue(AGE) < 3) {
            serverLevel.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
            Vec3 vec3 = pos.getCenter();
            for (int i = 0; i < 4; i++) {
                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, vec3.x + (serverLevel.getRandom().nextFloat() - 0.5), vec3.y + (serverLevel.getRandom().nextFloat() - 0.5), vec3.z + (serverLevel.getRandom().nextFloat() - 0.5), 1, 0, 0, 0, 0);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
