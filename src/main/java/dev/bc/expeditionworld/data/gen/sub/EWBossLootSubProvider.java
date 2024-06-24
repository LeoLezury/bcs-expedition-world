package dev.bc.expeditionworld.data.gen.sub;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class EWBossLootSubProvider implements LootTableSubProvider {
	@Override
	public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
		consumer.accept(ExpeditionWorld.id("boss/frosty_moa"),
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
