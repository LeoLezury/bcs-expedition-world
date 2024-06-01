package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.data.EWBiomes;
import dev.bc.expeditionworld.data.EWConfiguredFeatures;
import dev.bc.expeditionworld.data.EWPlacedFeatures;
import dev.bc.expeditionworld.data.EWTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EWRegistryDataProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, EWBiomes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, EWConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, EWPlacedFeatures::bootstrap)
            .add(Registries.TRIM_MATERIAL, EWTrimMaterials::bootstrap);

    public EWRegistryDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ExpeditionWorld.ID, "minecraft"));
    }
}