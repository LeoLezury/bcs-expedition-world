package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.data.gen.lang.EWChineseLangProvider;
import dev.bc.expeditionworld.data.gen.lang.EWEnglishLangProvider;
import dev.bc.expeditionworld.data.gen.tag.EWBlockTagsProvider;
import dev.bc.expeditionworld.data.gen.tag.EWEntityTypeTagsProvider;
import dev.bc.expeditionworld.data.gen.tag.EWItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ExpeditionWorld.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new EWAdvancementProvider(output, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new EWLootProvider(output));
		generator.addProvider(event.includeServer(), new EWRecipeProvider(output));
		generator.addProvider(event.includeServer(), new EWLootModifierProvider(output));

		EWBlockTagsProvider blockTagsProvider = new EWBlockTagsProvider(output, lookupProvider, helper);
		generator.addProvider(event.includeServer(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new EWItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), helper));
		generator.addProvider(event.includeServer(), new EWEntityTypeTagsProvider(output, lookupProvider, helper));

		DatapackBuiltinEntriesProvider dataProvider = new EWRegistryDataProvider(output, lookupProvider);
		CompletableFuture<HolderLookup.Provider> lookup = dataProvider.getRegistryProvider();
		generator.addProvider(event.includeServer(), dataProvider);

		generator.addProvider(event.includeClient(), new EWParticleDescriptionProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWBlockStateProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWItemModelProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWSoundProvider(output, helper));
		generator.addProvider(event.includeClient(), new EWEnglishLangProvider(output));
		generator.addProvider(event.includeClient(), new EWChineseLangProvider(output));
	}
}
