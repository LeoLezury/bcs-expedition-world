package dev.bc.expeditionworld.data.gen.loot;

import dev.bc.expeditionworld.data.EWLootTables;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class EWChestLootSubProvider implements LootTableSubProvider {
	public EWChestLootSubProvider(HolderLookup.Provider registries) {

	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		consumer.accept(EWLootTables.CHEST_ANCIENT_CITY,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(0, 1))
					.add(LootItem.lootTableItem(EWItems.SCULK_MINT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))));
	}
}
