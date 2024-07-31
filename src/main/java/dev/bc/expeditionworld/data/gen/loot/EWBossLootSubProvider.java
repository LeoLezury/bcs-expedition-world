package dev.bc.expeditionworld.data.gen.loot;

import dev.bc.expeditionworld.data.EWLootTables;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class EWBossLootSubProvider implements LootTableSubProvider {
	public EWBossLootSubProvider(HolderLookup.Provider registries) {

	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		consumer.accept(EWLootTables.BOSS_FROSTY_MOA,
			LootTable.lootTable()
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(6, 8))
					.add(LootItem.lootTableItem(EWItems.MOA_FEATHER.get())))
				.withPool(LootPool.lootPool()
					.setRolls(UniformGenerator.between(1, 2))
					.add(LootItem.lootTableItem(EWItems.CRYO_SMITHING_TEMPLATE.get())))
				.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(EWItems.MOA_SKULL.get())))
				.withPool(LootPool.lootPool()
					.when(LootItemRandomChanceCondition.randomChance(0.4F))
					.add(LootItem.lootTableItem(EWItems.TOTEM_OF_ICE.get())))
				.withPool(LootPool.lootPool()
					.when(LootItemRandomChanceCondition.randomChance(0.2F))
					.add(LootItem.lootTableItem(EWItems.FROSTY_MOA_EGG.get()))));
	}
}
