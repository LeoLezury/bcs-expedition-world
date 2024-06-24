package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.data.EWBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FrigidBeakItem extends Item {
	public FrigidBeakItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level.getBiome(player.blockPosition()).is(EWBiomes.FROZEN_CAVES)) {
			boolean canSummon = true;
			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 2; z++) {
					for (int y = 0; y <= 4; y++) {
						BlockPos pos = player.blockPosition().offset(x, y, z);
						if (!level.isEmptyBlock(pos) && !level.getBlockState(pos).canBeReplaced()) {
							canSummon = false;
						}
					}
				}
			}
			if (canSummon) {
				// todo: do the summon
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				return InteractionResultHolder.consume(stack);
			}
		}
		if (!level.isClientSide) {
			player.sendSystemMessage(Component.translatable(getDescriptionId() + ".fail").withStyle(Style.EMPTY.withColor(0x6b7c86)));
		}
		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, level, components, flag);
		components.add(Component.translatable(getDescriptionId() + ".desc").withStyle(Style.EMPTY.withColor(0x6b7c86)));
	}
}
