package dev.bc.expeditionworld.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.potion.recipe.ExactBrewingRecipe;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWPotions;
import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import dev.bc.expeditionworld.world.biome.EWExtendedBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.LevelStem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid = ExpeditionWorld.ID)
public class CommonEvents {
	@SubscribeEvent
	private static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, Potions.AWKWARD), Ingredient.of(EWItems.SCULK_MINT.get()), PotionContents.createItemStack(Items.POTION, EWPotions.CATWALK)));
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, EWPotions.CATWALK), Ingredient.of(Items.REDSTONE), PotionContents.createItemStack(Items.POTION, EWPotions.MEDIUM_CATWALK)));
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, EWPotions.MEDIUM_CATWALK), Ingredient.of(Items.ECHO_SHARD), PotionContents.createItemStack(Items.POTION, EWPotions.LONG_CATWALK)));

		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, Potions.MUNDANE), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionContents.createItemStack(Items.POTION, EWPotions.FETTERED)));
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, Potions.THICK), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionContents.createItemStack(Items.POTION, EWPotions.FETTERED)));
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, Potions.AWKWARD), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionContents.createItemStack(Items.POTION, EWPotions.FETTERED)));
		event.getBuilder().addRecipe(new ExactBrewingRecipe(PotionContents.createItemStack(Items.POTION, EWPotions.FETTERED), Ingredient.of(Items.REDSTONE), PotionContents.createItemStack(Items.POTION, EWPotions.LONG_FETTERED)));
	}

	@SubscribeEvent
	private static void onServerAboutToStart(ServerAboutToStartEvent event) {
		Registry<Biome> biomeRegistry = event.getServer().registryAccess().registryOrThrow(Registries.BIOME);
		Map<ResourceKey<Biome>, Holder<Biome>> biomes = new HashMap<>();
		for (ResourceKey<Biome> biomeKey : EWExtendedBiomes.POSSIBLE_BIOMES) {
			biomeRegistry.getHolder(biomeKey).ifPresent(holder -> {
				biomes.put(biomeKey, holder);
				ExpeditionWorld.LOGGER.info("Biome Extended: {}", biomeKey);
			});
		}
		Registry<LevelStem> levelStemRegistry = event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM);
		for (ResourceKey<LevelStem> levelStemResourceKey : levelStemRegistry.registryKeySet()) {
			Optional<Holder.Reference<LevelStem>> stem = levelStemRegistry.getHolder(levelStemResourceKey);
			if (stem.isPresent() && stem.get().isBound() && stem.get().value().type().is(BuiltinDimensionTypes.OVERWORLD) && stem.get().value().generator().getBiomeSource() instanceof ExtendedBiomeSource source) {
				ExpeditionWorld.LOGGER.info("Overworld Biome Extended");
				source.setBiomes(biomes);
			}
		}
	}
}
