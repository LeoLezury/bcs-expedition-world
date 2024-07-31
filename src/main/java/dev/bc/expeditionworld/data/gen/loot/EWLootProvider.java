package dev.bc.expeditionworld.data.gen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EWLootProvider extends LootTableProvider {
	public EWLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, Set.of(), List.of(
			new SubProviderEntry(EWBlockLootSubProvider::new, LootContextParamSets.BLOCK),
			new SubProviderEntry(EWEntityLootSubProvider::new, LootContextParamSets.ENTITY),
			new SubProviderEntry(EWChestLootSubProvider::new, LootContextParamSets.CHEST),
			new SubProviderEntry(EWEntityModifiedLootSubProvider::new, LootContextParamSets.ENTITY),
			new SubProviderEntry(EWBossLootSubProvider::new, LootContextParamSets.EMPTY)
		), registries);
	}
}
