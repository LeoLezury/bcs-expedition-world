package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.data.gen.sub.EWAdvancementGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EWAdvancementProvider extends ForgeAdvancementProvider {
	public EWAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
		super(output, registries, existingFileHelper, List.of(new EWAdvancementGenerator()));
	}
}
