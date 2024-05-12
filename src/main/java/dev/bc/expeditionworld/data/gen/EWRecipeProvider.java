package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

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

        addSingleConversion(consumer, Items.LIGHT_BLUE_DYE, EWItems.ICE_FLOWER.get());
    }

    protected final void addSingleConversion(Consumer<FinishedRecipe> recipeOutput, Item to, Item from) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, to)
                .requires(from)
                .unlockedBy("has_item", has(from))
                .save(recipeOutput, ExpeditionWorld.id("shapeless/" + name(to) + "_from_" + name(from)));
    }

    protected final String name(ItemLike item) {
        return key(item).getPath();
    }

    protected final ResourceLocation key(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem());
    }
}
