package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.data.EWLootTables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class FrostyMoaLootBagItem extends Item {
	public FrostyMoaLootBagItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (level instanceof ServerLevel serverLevel) {
			LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(EWLootTables.BOSS_FROSTY_MOA);
			LootParams.Builder builder = new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, player.position());
			LootParams params = builder.create(LootContextParamSets.EMPTY);
			lootTable.getRandomItems(params, player.getLootTableSeed(), player::spawnAtLocation);
		}
		return InteractionResultHolder.consume(stack);
	}
}
