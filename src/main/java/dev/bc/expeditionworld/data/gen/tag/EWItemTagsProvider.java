package dev.bc.expeditionworld.data.gen.tag;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItemTags;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EWItemTagsProvider extends ItemTagsProvider {
	public EWItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockLookup, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockLookup, ExpeditionWorld.ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider) {
		copy(BlockTags.SLABS, ItemTags.SLABS);
		copy(BlockTags.STAIRS, ItemTags.STAIRS);
		copy(BlockTags.WALLS, ItemTags.WALLS);
		copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
		tag(EWItemTags.REPAIRS_TOTEM_OF_ICE).add(
			Items.ICE,
			Items.PACKED_ICE,
			Items.BLUE_ICE
		);
		tag(ItemTags.TRIM_MATERIALS).add(
			EWItems.TRAPPED_SOUL.get()
		);
		tag(ItemTags.ARROWS).add(
			EWItems.FROZEN_ARROW.get(),
			EWItems.MOA_FEATHER_ARROW.get()
		);
		tag(ItemTags.SWORDS).add(
			EWItems.FROSTY_DAGGER.get(),
			EWItems.GLACIER_DAGGER.get()
		);
		tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(
			EWItems.COLDPROOF_HAT.get(),
			EWItems.COLDPROOF_COAT.get(),
			EWItems.COLDPROOF_LEGGINGS.get(),
			EWItems.COLDPROOF_BOOTS.get(),
			EWItems.GLACIER_HELMET.get(),
			EWItems.GLACIER_CHESTPLATE.get(),
			EWItems.GLACIER_LEGGINGS.get(),
			EWItems.GLACIER_BOOTS.get()
		);
		tag(ItemTags.SWORDS).add(
			EWItems.FROSTY_DAGGER.get(),
			EWItems.GLACIER_DAGGER.get()
		);
		tag(ItemTags.HEAD_ARMOR).add(
			EWItems.COLDPROOF_HAT.get(),
			EWItems.GLACIER_HELMET.get()
		);
		tag(ItemTags.CHEST_ARMOR).add(
			EWItems.COLDPROOF_COAT.get(),
			EWItems.GLACIER_CHESTPLATE.get()
		);
		tag(ItemTags.LEG_ARMOR).add(
			EWItems.COLDPROOF_LEGGINGS.get(),
			EWItems.GLACIER_LEGGINGS.get()
		);
		tag(ItemTags.FOOT_ARMOR).add(
			EWItems.COLDPROOF_BOOTS.get(),
			EWItems.GLACIER_BOOTS.get()
		);
		tag(ItemTags.DURABILITY_ENCHANTABLE).add(
			EWItems.GLACIER_BOW.get()
		);
		tag(ItemTags.BOW_ENCHANTABLE).add(
			EWItems.GLACIER_BOW.get()
		);
	}
}
