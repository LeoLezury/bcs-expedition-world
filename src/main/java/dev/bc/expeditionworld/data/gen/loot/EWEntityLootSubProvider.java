package dev.bc.expeditionworld.data.gen.loot;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class EWEntityLootSubProvider extends EntityLootSubProvider {
	public EWEntityLootSubProvider(HolderLookup.Provider registries) {
		super(FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	public void generate() {
		add(EWEntities.MIMICHEST.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0F, 1.0F))
				.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));

		add(EWEntities.MIMIPOT.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.BRICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))))
			.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0F, 1.0F))
				.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));

		add(EWEntities.CHILLED.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.ICE_CRYSTAL.get()).when(LootItemRandomChanceCondition.randomChance(0.2F))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.FRIGID_BEAK.get()).when(LootItemRandomChanceCondition.randomChance(0.02F))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.COLDPROOF_HAT.get()).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.75F, 0.85F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.COLDPROOF_COAT.get()).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.75F, 0.85F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.COLDPROOF_LEGGINGS.get()).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.75F, 0.85F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.COLDPROOF_BOOTS.get()).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.75F, 0.85F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));

		add(EWEntities.ICE_CREEPER.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.FROSTBITE_GUNPOWDER.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 2.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.FRIGID_BEAK.get()).when(LootItemRandomChanceCondition.randomChance(0.02F)).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 2.0F))))));

		add(EWEntities.SNOW_CRAB.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.ICE_CRYSTAL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 2.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.SHARP_ICICLE.get()).when(LootItemRandomChanceCondition.randomChance(0.3F)).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.FRIGID_BEAK.get()).when(LootItemRandomChanceCondition.randomChance(0.02F)).apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F))))));
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return BuiltInRegistries.ENTITY_TYPE.stream().filter(type -> BuiltInRegistries.ENTITY_TYPE.getKey(type).getNamespace().equals(ExpeditionWorld.ID));
	}
}
