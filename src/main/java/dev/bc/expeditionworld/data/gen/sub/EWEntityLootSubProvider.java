package dev.bc.expeditionworld.data.gen.sub;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class EWEntityLootSubProvider extends EntityLootSubProvider {
	public EWEntityLootSubProvider() {
		super(FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	public void generate() {
		add(EWEntities.MIMICHEST.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0F, 1.0F))
				.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));

		add(EWEntities.MIMIPOT.get(), LootTable.lootTable()
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))))
			.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(Items.BRICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))))
			.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0F, 1.0F))
				.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(type -> ForgeRegistries.ENTITY_TYPES.getKey(type).getNamespace().equals(ExpeditionWorld.ID));
	}
}
