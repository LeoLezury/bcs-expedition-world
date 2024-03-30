package dev.bc.expeditionworld.potion.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import org.jetbrains.annotations.NotNull;

public class ExactBrewingRecipe implements IBrewingRecipe {
    @NotNull
    private final ItemStack input;
    @NotNull private final Ingredient ingredient;
    @NotNull private final ItemStack output;

    public ExactBrewingRecipe(ItemStack input, Ingredient ingredient, ItemStack output) {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(@NotNull ItemStack stack) {
        return stack.is(input.getItem()) && PotionUtils.getPotion(stack) == PotionUtils.getPotion(input);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return this.ingredient.test(ingredient);
    }
}
