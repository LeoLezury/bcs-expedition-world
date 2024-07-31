package dev.bc.expeditionworld.block;

import com.mojang.serialization.MapCodec;
import dev.bc.expeditionworld.entity.misc.FrostbiteTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class FrostbiteTntBlock extends TntBlock {
	public static final MapCodec<FrostbiteTntBlock> CODEC = simpleCodec(FrostbiteTntBlock::new);

	public FrostbiteTntBlock(Properties properties) {
		super(properties);
	}

	public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
		if (!level.isClientSide) {
			FrostbiteTnt frostbiteTnt = new FrostbiteTnt(level, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, explosion.getIndirectSourceEntity());
			int i = frostbiteTnt.getFuse();
			frostbiteTnt.setFuse(level.random.nextInt(i / 4) + i / 8);
			level.addFreshEntity(frostbiteTnt);
		}
	}

	public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
		if (!level.isClientSide) {
			FrostbiteTnt frostbiteTnt = new FrostbiteTnt(level, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, igniter);
			level.addFreshEntity(frostbiteTnt);
			level.playSound(null, frostbiteTnt.getX(), frostbiteTnt.getY(), frostbiteTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
		}
	}
}
