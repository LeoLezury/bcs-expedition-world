package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWBlocks;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class EWRecipeProvider extends RecipeProvider {
	public EWRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.STONE_MIMICHEST_KNIFE.get())
			.pattern("N")
			.pattern("X")
			.pattern("S")
			.define('N', Items.IRON_NUGGET)
			.define('X', Items.STONE)
			.define('S', EWItems.TRAPPED_SOUL.get())
			.unlockedBy("has_item", has(EWItems.TRAPPED_SOUL.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.BRICK_MIMICHEST_KNIFE.get())
			.pattern("N")
			.pattern("X")
			.pattern("S")
			.define('N', Items.IRON_NUGGET)
			.define('X', Items.BRICKS)
			.define('S', EWItems.TRAPPED_SOUL.get())
			.unlockedBy("has_item", has(EWItems.TRAPPED_SOUL.get()))
			.save(recipeOutput);

		nineBlockStorageCustomUnpacking(recipeOutput, RecipeCategory.MISC, EWItems.ICE_CRYSTAL.get(), RecipeCategory.BUILDING_BLOCKS, EWItems.ICE_CRYSTAL_BLOCK.get(), "ice_crystal_from_ice_crystal_block", "ice_crystal");
		wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EWBlocks.FROZEN_STONE_WALL.get(), EWBlocks.FROZEN_STONE.get());
		stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EWBlocks.FROZEN_STONE_WALL.get(), EWBlocks.FROZEN_STONE.get());
		addStairs(recipeOutput, EWBlocks.FROZEN_STONE_STAIRS.get(), EWBlocks.FROZEN_STONE.get());
		stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EWBlocks.FROZEN_STONE_STAIRS.get(), EWBlocks.FROZEN_STONE.get());
		addSlab(recipeOutput, EWBlocks.FROZEN_STONE_SLAB.get(), EWBlocks.FROZEN_STONE.get());
		stonecutting(recipeOutput, RecipeCategory.BUILDING_BLOCKS, EWBlocks.FROZEN_STONE_SLAB.get(), EWBlocks.FROZEN_STONE.get(), 2);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EWItems.ICE_CRYSTAL_TORCH.get(), 4)
			.pattern("I")
			.pattern("S")
			.define('S', Items.STICK)
			.define('I', EWItems.ICE_CRYSTAL.get())
			.unlockedBy("has_item", has(EWItems.ICE_CRYSTAL.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, EWItems.FROSTBITE_TNT.get())
			.pattern("SGS")
			.pattern("GIG")
			.pattern("SGS")
			.define('S', Items.SAND)
			.define('G', EWItems.FROSTBITE_GUNPOWDER.get())
			.define('I', EWItems.ICE_CRYSTAL.get())
			.unlockedBy("has_item", has(EWItems.FROSTBITE_GUNPOWDER.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EWItems.ICE_LANTERN.get())
			.pattern(" I ")
			.pattern("IGI")
			.pattern(" I ")
			.define('G', Items.GLOWSTONE)
			.define('I', EWItems.ICE_CRYSTAL.get())
			.unlockedBy("has_item", has(EWItems.ICE_CRYSTAL.get()))
			.save(recipeOutput);
		addSingleConversion(recipeOutput, Items.LIGHT_BLUE_DYE, EWItems.ICE_FLOWER.get());
		addSingleConversion(recipeOutput, EWItems.ICE_CRYSTAL.get(), EWItems.FRIGID_GLADIOLUS.get());
		addSingleConversion(recipeOutput, EWItems.ICE_CRYSTAL.get(), EWItems.SHARP_ICICLE.get());
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, EWItems.FROST_CHARGE.get(), 3)
			.requires(Items.SNOWBALL, 2)
			.requires(EWItems.ICE_CRYSTAL.get())
			.requires(EWItems.SHARP_ICICLE.get())
			.unlockedBy("has_item", has(EWItems.SHARP_ICICLE.get()))
			.save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, EWItems.FROZEN_ARROW.get(), 4)
			.requires(Items.ARROW, 4)
			.requires(EWItems.ICE_CRYSTAL.get())
			.unlockedBy("has_item", has(EWItems.ICE_CRYSTAL.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EWItems.FRIGID_BEAK.get())
			.pattern(" FC")
			.pattern("ILF")
			.pattern("II ")
			.define('F', Items.FEATHER)
			.define('C', EWItems.FROST_CHARGE.get())
			.define('I', EWItems.SHARP_ICICLE.get())
			.define('L', EWItems.ICE_LANTERN.get())
			.unlockedBy("has_item", has(EWItems.ICE_LANTERN.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.MOA_FEATHER_ARROW.get(), 8)
			.pattern("AAA")
			.pattern("AFA")
			.pattern("AAA")
			.define('A', EWItems.FROZEN_ARROW.get())
			.define('F', EWItems.MOA_FEATHER.get())
			.unlockedBy("has_item", has(EWItems.MOA_FEATHER.get()))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.COLDPROOF_HAT.get())
			.pattern("WWW")
			.pattern("S S")
			.define('S', Items.STRING)
			.define('W', ItemTags.WOOL)
			.unlockedBy("has_item", has(ItemTags.WOOL))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.COLDPROOF_COAT.get())
			.pattern("W W")
			.pattern("WWW")
			.pattern("SSS")
			.define('S', Items.STRING)
			.define('W', ItemTags.WOOL)
			.unlockedBy("has_item", has(ItemTags.WOOL))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.COLDPROOF_LEGGINGS.get())
			.pattern("WWW")
			.pattern("S S")
			.pattern("W W")
			.define('S', Items.STRING)
			.define('W', ItemTags.WOOL)
			.unlockedBy("has_item", has(ItemTags.WOOL))
			.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.COLDPROOF_BOOTS.get())
			.pattern("S S")
			.pattern("W W")
			.define('S', Items.STRING)
			.define('W', ItemTags.WOOL)
			.unlockedBy("has_item", has(ItemTags.WOOL))
			.save(recipeOutput);
		copySmithingTemplate(recipeOutput, EWItems.CRYO_SMITHING_TEMPLATE.get(), EWItems.MOA_FEATHER.get(), EWItems.FROZEN_STONE.get());
		glacierSmithing(recipeOutput, EWItems.COLDPROOF_HAT.get(), RecipeCategory.COMBAT, EWItems.GLACIER_HELMET.get());
		glacierSmithing(recipeOutput, EWItems.COLDPROOF_COAT.get(), RecipeCategory.COMBAT, EWItems.GLACIER_CHESTPLATE.get());
		glacierSmithing(recipeOutput, EWItems.COLDPROOF_LEGGINGS.get(), RecipeCategory.COMBAT, EWItems.GLACIER_LEGGINGS.get());
		glacierSmithing(recipeOutput, EWItems.COLDPROOF_BOOTS.get(), RecipeCategory.COMBAT, EWItems.GLACIER_BOOTS.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.FROSTY_DAGGER.get())
			.pattern("I ")
			.pattern(" S")
			.define('I', EWItems.SHARP_ICICLE.get())
			.define('S', Items.STICK)
			.unlockedBy("has_item", has(EWItems.SHARP_ICICLE.get()))
			.save(recipeOutput);
		glacierSmithing(recipeOutput, EWItems.FROSTY_DAGGER.get(), RecipeCategory.COMBAT, EWItems.GLACIER_DAGGER.get());
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.GLACIER_BOW.get())
			.pattern(" F#")
			.pattern("S #")
			.pattern(" F#")
			.define('S', EWItems.MOA_SKULL.get())
			.define('F', EWItems.MOA_FEATHER.get())
			.define('#', Items.STRING)
			.unlockedBy("has_item", has(EWItems.MOA_SKULL.get()))
			.save(recipeOutput);
	}

	protected final void addSingleConversion(RecipeOutput recipeOutput, Item to, Item from) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, to)
			.requires(from)
			.unlockedBy("has_item", has(from))
			.save(recipeOutput, ExpeditionWorld.id("shapeless/" + name(to) + "_from_" + name(from)));
	}

	// stone
	protected final void addStoneCompress(RecipeOutput recipeOutput, Block output, Block input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
			.pattern("##")
			.pattern("##")
			.define('#', input)
			.unlockedBy("has_item", has(input))
			.save(recipeOutput);
	}

	protected final void addSlab(RecipeOutput recipeOutput, Block output, Block input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
			.pattern("###")
			.define('#', input)
			.unlockedBy("has_item", has(input))
			.save(recipeOutput);
	}

	protected final void addStairs(RecipeOutput recipeOutput, Block output, Block input) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', input)
			.unlockedBy("has_item", has(input))
			.save(recipeOutput);
	}

	// vanilla copies
	protected void stonecutting(RecipeOutput recipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
		stonecutting(recipeOutput, category, output, input, 1);
	}

	protected void stonecutting(RecipeOutput recipeOutput, RecipeCategory category, ItemLike output, ItemLike input, int count) {
		SingleItemRecipeBuilder builder = SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input));
		String name = getConversionRecipeName(output, input);
		builder.save(recipeOutput, ExpeditionWorld.id(name + "_stonecutting"));
	}

	protected void nineBlockStorageCustomPacking(RecipeOutput recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String packName, String packGroup) {
		nineBlockStorage(recipeOutput, unpackCategory, unpacked, packCategory, packed, packName, packGroup, getSimpleRecipeName(unpacked), null);
	}

	protected void nineBlockStorageCustomUnpacking(RecipeOutput recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String unpackName, String unpackGroup) {
		nineBlockStorage(recipeOutput, unpackCategory, unpacked, packCategory, packed, getSimpleRecipeName(packed), null, unpackName, unpackGroup);
	}

	protected void nineBlockStorage(RecipeOutput recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String packName, String packGroup, String unpackName, String unpackGroup) {
		ShapelessRecipeBuilder.shapeless(unpackCategory, unpacked, 9).requires(packed).group(unpackGroup).unlockedBy(getHasName(packed), has(packed)).save(recipeOutput, ExpeditionWorld.id(unpackName));
		ShapedRecipeBuilder.shaped(packCategory, packed).define('#', unpacked).pattern("###").pattern("###").pattern("###").group(packGroup).unlockedBy(getHasName(unpacked), has(unpacked)).save(recipeOutput, ExpeditionWorld.id(packName));
	}

	protected static void copySmithingTemplate(RecipeOutput recipeOutput, ItemLike template, ItemLike core, ItemLike others) {
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, template, 2).define('#', others).define('C', core).define('S', template).pattern("#S#").pattern("#C#").pattern("###").unlockedBy(getHasName(template), has(template)).save(recipeOutput);
	}

	protected static void glacierSmithing(RecipeOutput recipeOutput, Item input, RecipeCategory category, Item output) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(EWItems.CRYO_SMITHING_TEMPLATE.get()), Ingredient.of(input), Ingredient.of(EWItems.MOA_FEATHER.get()), category, output).unlocks("has_moa_feather", has(EWItems.MOA_FEATHER.get())).save(recipeOutput, ExpeditionWorld.id(getItemName(output) + "_smithing"));
	}

	protected final String name(ItemLike item) {
		return key(item).getPath();
	}

	protected final ResourceLocation key(ItemLike item) {
		return BuiltInRegistries.ITEM.getKey(item.asItem());
	}
}
