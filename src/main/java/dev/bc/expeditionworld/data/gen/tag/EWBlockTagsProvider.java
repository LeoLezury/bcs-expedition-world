package dev.bc.expeditionworld.data.gen.tag;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EWBlockTagsProvider extends BlockTagsProvider {
    public EWBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExpeditionWorld.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(BlockTags.DIRT).add(
                EWBlocks.FROZEN_DIRT.get(),
                EWBlocks.FROZEN_GRASS_BLOCK.get()
        );
        tag(BlockTags.BASE_STONE_OVERWORLD).add(
                EWBlocks.FROZEN_STONE.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                EWBlocks.FROZEN_STONE.get(),
                EWBlocks.ICE_LANTERN.get()
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                EWBlocks.FROZEN_DIRT.get(),
                EWBlocks.FROZEN_GRASS_BLOCK.get()
        );
        tag(BlockTags.MOSS_REPLACEABLE).add(
                EWBlocks.FROZEN_STONE.get()
        );
    }
}
