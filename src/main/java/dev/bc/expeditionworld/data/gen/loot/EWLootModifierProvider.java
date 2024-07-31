package dev.bc.expeditionworld.data.gen.loot;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.loot.AppendLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class EWLootModifierProvider extends GlobalLootModifierProvider {
	public EWLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, ExpeditionWorld.ID);
	}

	@Override
	protected void start() {
		add("chest_ancient_city", new AppendLootModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ancient_city")).build()}, ExpeditionWorld.id("chests/ancient_city")));
		add("entity_warden", new AppendLootModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/warden")).build()}, ExpeditionWorld.id("entities/warden")));
	}
}
