package dev.bc.expeditionworld.data.gen.loot;

import dev.bc.expeditionworld.data.EWBiomes;
import dev.bc.expeditionworld.data.EWLootTables;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class EWEntityModifiedLootSubProvider implements LootTableSubProvider {
	private final HolderLookup.Provider registries;

	public EWEntityModifiedLootSubProvider(HolderLookup.Provider registries) {
		this.registries = registries;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		HolderGetter<Biome> biomes = registries.lookupOrThrow(Registries.BIOME);

		consumer.accept(EWLootTables.ENTITY_WARDEN,
			LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(EWItems.SCULK_MINT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(5.0F, 7.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 2.0F))))));
		consumer.accept(EWLootTables.ENTITY_STRAY,
			LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(EWItems.FRIGID_BEAK.get()).when(LootItemRandomChanceCondition.randomChance(0.02f)).when(LocationCheck.checkLocation(LocationPredicate.Builder.inBiome(biomes.getOrThrow(EWBiomes.FROZEN_CAVES)))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F))))));
	}
}
