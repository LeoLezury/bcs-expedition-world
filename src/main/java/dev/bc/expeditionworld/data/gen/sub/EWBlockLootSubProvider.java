package dev.bc.expeditionworld.data.gen.sub;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.block.FetteredChestBlock;
import dev.bc.expeditionworld.block.MossfloraBlock;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EWBlockLootSubProvider extends BlockLootSubProvider {
    public EWBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(EWBlocks.FETTERED_CHEST.get(), (block) -> LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 12.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get())).when(LootItemRandomChanceCondition.randomChance(0.1F)).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.STONE_MIMICHEST_KNIFE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.MOSSFLORA.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER))));
        add(EWBlocks.FETTERED_POT.get(), (block) -> LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 12.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.BRICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 4.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.TRAPPED_SOUL.get())).when(LootItemRandomChanceCondition.randomChance(0.1F)).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.BRICK_MIMICHEST_KNIFE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(EWItems.MOSSFLORA.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).when(getDoubleBlockLootCondition(block, FetteredChestBlock.HALF, DoubleBlockHalf.LOWER))));
        add(EWBlocks.MOSSFLORA.get(), (block) ->
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(1, 2, 3), (integer) ->
                                SetItemCountFunction.setCount(ConstantValue.exactly(integer + 1)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MossfloraBlock.AGE, integer))))))));
    }

    protected <T extends Comparable<T> & StringRepresentable> LootItemCondition.Builder getDoubleBlockLootCondition(Block block, Property<T> property, T value) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, value));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(ExpeditionWorld.MOD_ID)).collect(Collectors.toList());
    }
}
