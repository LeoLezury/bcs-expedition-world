package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class EWRecipeProvider extends RecipeProvider {
    public EWRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EWItems.ICE_LANTERN.get())
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, EWItems.FROZEN_ARROW.get(), 4)
                .requires(Items.ARROW, 4)
                .requires(EWItems.ICE_CRYSTAL.get())
                .unlockedBy("has_item", has(EWItems.ICE_CRYSTAL.get()))
                .save(recipeOutput);
    }

    protected final void addSingleConversion(Consumer<FinishedRecipe> recipeOutput, Item to, Item from) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, to)
                .requires(from)
                .unlockedBy("has_item", has(from))
                .save(recipeOutput, ExpeditionWorld.id("shapeless/" + name(to) + "_from_" + name(from)));
    }

    // stone
    protected final void addStoneCompress(Consumer<FinishedRecipe> recipeOutput, Block output, Block input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_item", has(input))
                .save(recipeOutput);
    }

    protected final void addSlab(Consumer<FinishedRecipe> recipeOutput, Block output, Block input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_item", has(input))
                .save(recipeOutput);
    }

    protected final void addStairs(Consumer<FinishedRecipe> recipeOutput, Block output, Block input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_item", has(input))
                .save(recipeOutput);
    }

    // vanilla copies
    protected void stonecutting(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        stonecutting(recipeOutput, category, output, input, 1);
    }

    protected void stonecutting(Consumer<FinishedRecipe> recipeOutput, RecipeCategory category, ItemLike output, ItemLike input, int count) {
        SingleItemRecipeBuilder builder = SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input));
        String name = getConversionRecipeName(output, input);
        builder.save(recipeOutput, ExpeditionWorld.id(name + "_stonecutting"));
    }

    protected void nineBlockStorageCustomPacking(Consumer<FinishedRecipe> recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String packName, String packGroup) {
        nineBlockStorage(recipeOutput, unpackCategory, unpacked, packCategory, packed, packName, packGroup, getSimpleRecipeName(unpacked), null);
    }

    protected void nineBlockStorageCustomUnpacking(Consumer<FinishedRecipe> recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String unpackName, String unpackGroup) {
        nineBlockStorage(recipeOutput, unpackCategory, unpacked, packCategory, packed, getSimpleRecipeName(packed), null, unpackName, unpackGroup);
    }

    protected void nineBlockStorage(Consumer<FinishedRecipe> recipeOutput, RecipeCategory unpackCategory, ItemLike unpacked, RecipeCategory packCategory, ItemLike packed, String packName, String packGroup, String unpackName, String unpackGroup) {
        ShapelessRecipeBuilder.shapeless(unpackCategory, unpacked, 9).requires(packed).group(unpackGroup).unlockedBy(getHasName(packed), has(packed)).save(recipeOutput, ExpeditionWorld.id(unpackName));
        ShapedRecipeBuilder.shaped(packCategory, packed).define('#', unpacked).pattern("###").pattern("###").pattern("###").group(packGroup).unlockedBy(getHasName(unpacked), has(unpacked)).save(recipeOutput, ExpeditionWorld.id(packName));
    }

    protected final String name(ItemLike item) {
        return key(item).getPath();
    }

    protected final ResourceLocation key(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem());
    }
}
