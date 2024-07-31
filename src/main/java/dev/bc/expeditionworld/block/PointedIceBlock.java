package dev.bc.expeditionworld.block;

import com.mojang.serialization.MapCodec;
import dev.bc.expeditionworld.registry.EWBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PointedIceBlock extends Block {
	public static final MapCodec<PointedIceBlock> CODEC = simpleCodec(PointedIceBlock::new);

	public static final DirectionProperty DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
	private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
	private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);

	public PointedIceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTION, Direction.UP));
	}

	@Override
	protected MapCodec<? extends Block> codec() {
		return CODEC;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(DIRECTION);
		BlockPos blockPos = pos.relative(direction.getOpposite());
		return level.getBlockState(blockPos).isFaceSturdy(level, blockPos, direction);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor level, BlockPos pos, BlockPos pos1) {
		if (!canSurvive(state, level, pos)) {
			return Blocks.AIR.defaultBlockState();
		}
		return super.updateShape(state, direction, state1, level, pos, pos1);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getNearestLookingVerticalDirection().getOpposite();
		return defaultBlockState().setValue(DIRECTION, direction);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!isProtectedByIceLantern(level, pos) && level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos)) {
			level.removeBlock(pos, false);
		}
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!isProtectedByIceLantern(level, pos) && level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos)) {
			Vec3 vec3 = pos.getCenter().add((random.nextDouble() - 0.5) * 0.2, (random.nextDouble() - 0.5) * 0.6, (random.nextDouble() - 0.5) * 0.2);
			level.addParticle(ParticleTypes.DRIPPING_WATER, vec3.x, vec3.y, vec3.z, 0, 0, 0);
		}
	}

	private boolean isProtectedByIceLantern(LevelReader level, BlockPos pos) {
		for (BlockPos blockPos : BlockPos.withinManhattan(pos, 7, 7, 7)) {
			if (level.getBlockState(blockPos).is(EWBlocks.ICE_LANTERN.get())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(DIRECTION) == Direction.UP ? TIP_SHAPE_UP : TIP_SHAPE_DOWN;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DIRECTION);
	}
}
