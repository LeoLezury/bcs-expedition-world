package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.data.gen.sub.EWChestLootSubProvider;
import dev.bc.expeditionworld.data.gen.sub.EWEntityLootSubProvider;
import dev.bc.expeditionworld.data.gen.sub.EWEntityModifiedLootSubProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class EWLootProvider extends LootTableProvider {
    public EWLootProvider(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(EWEntityLootSubProvider::new, LootContextParamSets.ENTITY),
                new SubProviderEntry(EWEntityModifiedLootSubProvider::new, LootContextParamSets.ENTITY),
                new SubProviderEntry(EWChestLootSubProvider::new, LootContextParamSets.CHEST)
        ));
    }
}
