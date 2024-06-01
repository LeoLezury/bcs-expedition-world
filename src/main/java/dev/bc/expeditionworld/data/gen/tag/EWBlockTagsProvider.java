package dev.bc.expeditionworld.data.gen.tag;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlockTags;
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
        super(output, lookupProvider, ExpeditionWorld.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(EWBlockTags.PREVENTS_MELTING).add(
                EWBlocks.ICE_LANTERN.get(),
                EWBlocks.ICE_CRYSTAL_TORCH.get()
        );

        tag(BlockTags.DIRT).add(
                EWBlocks.FROZEN_DIRT.get(),
                EWBlocks.FROZEN_GRASS_BLOCK.get()
        );
        tag(BlockTags.BASE_STONE_OVERWORLD).add(
                EWBlocks.FROZEN_STONE.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                EWBlocks.FROZEN_STONE.get(),
                EWBlocks.FROZEN_STONE_SLAB.get(),
                EWBlocks.FROZEN_STONE_STAIRS.get(),
                EWBlocks.FROZEN_STONE_WALL.get(),
                EWBlocks.ICE_CRYSTAL_ORE.get(),
                EWBlocks.ICE_CRYSTAL_BLOCK.get(),
                EWBlocks.ICE_LANTERN.get()
        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                EWBlocks.FROZEN_DIRT.get(),
                EWBlocks.FROZEN_GRASS_BLOCK.get()
        );
        tag(BlockTags.MOSS_REPLACEABLE).add(
                EWBlocks.FROZEN_STONE.get()
        );
        tag(BlockTags.NEEDS_STONE_TOOL).add(
                EWBlocks.ICE_CRYSTAL_ORE.get(),
                EWBlocks.ICE_CRYSTAL_BLOCK.get()
        );
        tag(BlockTags.SLABS).add(
                EWBlocks.FROZEN_STONE_SLAB.get()
        );
        tag(BlockTags.STAIRS).add(
                EWBlocks.FROZEN_STONE_STAIRS.get()
        );
        tag(BlockTags.WALLS).add(
                EWBlocks.FROZEN_STONE_WALL.get()
        );
        tag(BlockTags.SMALL_FLOWERS).add(
                EWBlocks.ICE_FLOWER.get()
        );
        tag(BlockTags.TALL_FLOWERS).add(
                EWBlocks.FRIGID_GLADIOLUS.get()
        );
        tag(BlockTags.WALL_POST_OVERRIDE).add(
                EWBlocks.ICE_CRYSTAL_TORCH.get()
        );
        tag(BlockTags.ENDERMAN_HOLDABLE).add(
                EWBlocks.FROSTBITE_TNT.get()
        );
    }
}
