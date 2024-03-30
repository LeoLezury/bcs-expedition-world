package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.data.gen.lang.EWChineseLangProvider;
import dev.bc.expeditionworld.data.gen.lang.EWEnglishLangProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ExpeditionWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new EWAdvancementProvider(output, lookupProvider, helper));
        generator.addProvider(event.includeServer(), new EWLootProvider(output));
        generator.addProvider(event.includeServer(), new EWLootModifierProvider(output));

        generator.addProvider(event.includeClient(), new EWItemModelProvider(output, helper));
        generator.addProvider(event.includeClient(), new EWEnglishLangProvider(output));
        generator.addProvider(event.includeClient(), new EWChineseLangProvider(output));
    }
}
