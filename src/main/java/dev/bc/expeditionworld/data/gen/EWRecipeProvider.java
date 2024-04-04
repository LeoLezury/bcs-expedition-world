package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class EWRecipeProvider extends RecipeProvider {
    public EWRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.STONE_MIMICHEST_KNIFE.get())
                .pattern("N")
                .pattern("X")
                .pattern("S")
                .define('N', Items.IRON_NUGGET)
                .define('X', Items.STONE)
                .define('S', EWItems.TRAPPED_SOUL.get())
                .unlockedBy("has_item", has(EWItems.TRAPPED_SOUL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, EWItems.BRICK_MIMICHEST_KNIFE.get())
                .pattern("N")
                .pattern("X")
                .pattern("S")
                .define('N', Items.IRON_NUGGET)
                .define('X', Items.BRICKS)
                .define('S', EWItems.TRAPPED_SOUL.get())
                .unlockedBy("has_item", has(EWItems.TRAPPED_SOUL.get()))
                .save(consumer);
    }
}
