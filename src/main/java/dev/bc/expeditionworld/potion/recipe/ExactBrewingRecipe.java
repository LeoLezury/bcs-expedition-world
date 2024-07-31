package dev.bc.expeditionworld.potion.recipe;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import org.jetbrains.annotations.NotNull;

public class ExactBrewingRecipe implements IBrewingRecipe {
	@NotNull
	private final ItemStack input;
	@NotNull
	private final Ingredient ingredient;
	@NotNull
	private final ItemStack output;

	public ExactBrewingRecipe(ItemStack input, Ingredient ingredient, ItemStack output) {
		this.input = input;
		this.ingredient = ingredient;
		this.output = output;
	}

	@Override
	public boolean isInput(@NotNull ItemStack stack) {
		PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
		PotionContents inputContents = input.get(DataComponents.POTION_CONTENTS);
		boolean potionCheck;
		if (contents == null) {
			potionCheck = inputContents == null;
		} else {
			potionCheck = inputContents != null && inputContents.potion().isPresent() && contents.is(inputContents.potion().get());
		}
		return stack.is(input.getItem()) && potionCheck;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;
	}

	public ItemStack getOutput() {
		return output;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return this.ingredient.test(ingredient);
	}
}
