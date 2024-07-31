package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.data.gen.advancement.EWAdvancementProvider;
import dev.bc.expeditionworld.data.gen.lang.EWChineseLangProvider;
import dev.bc.expeditionworld.data.gen.lang.EWEnglishLangProvider;
import dev.bc.expeditionworld.data.gen.loot.EWLootModifierProvider;
import dev.bc.expeditionworld.data.gen.loot.EWLootProvider;
import dev.bc.expeditionworld.data.gen.model.EWBlockStateProvider;
import dev.bc.expeditionworld.data.gen.model.EWItemModelProvider;
import dev.bc.expeditionworld.data.gen.tag.EWBlockTagsProvider;
import dev.bc.expeditionworld.data.gen.tag.EWEntityTypeTagsProvider;
import dev.bc.expeditionworld.data.gen.tag.EWItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ExpeditionWorld.ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new EWAdvancementProvider(output, lookupProvider, helper));

		EWBlockTagsProvider blockTagsProvider = new EWBlockTagsProvider(output, lookupProvider, helper);
		generator.addProvider(event.includeServer(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new EWItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new EWEntityTypeTagsProvider(output, lookupProvider, helper));

		DatapackBuiltinEntriesProvider dataProvider = new EWRegistryDataProvider(output, lookupProvider);
		CompletableFuture<HolderLookup.Provider> lookup = dataProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), dataProvider);

		generator.addProvider(event.includeServer(), new EWLootProvider(output, lookup));
		generator.addProvider(event.includeServer(), new EWRecipeProvider(output, lookup));
		generator.addProvider(event.includeServer(), new EWLootModifierProvider(output, lookup));

		generator.addProvider(event.includeClient(), new EWParticleDescriptionProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWBlockStateProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWItemModelProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWSoundProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWEnglishLangProvider(output));
		generator.addProvider(event.includeClient(), new EWChineseLangProvider(output));
	}
}
