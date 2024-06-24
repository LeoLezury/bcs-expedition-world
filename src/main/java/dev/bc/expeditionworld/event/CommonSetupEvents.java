package dev.bc.expeditionworld.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.potion.EWPotions;
import dev.bc.expeditionworld.potion.recipe.ExactBrewingRecipe;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ExpeditionWorld.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.AWKWARD), Ingredient.of(EWItems.SCULK_MINT.get()), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.CATWALK.get())));
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.CATWALK.get()), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.MEDIUM_CATWALK.get())));
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.MEDIUM_CATWALK.get()), Ingredient.of(Items.ECHO_SHARD), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.LONG_CATWALK.get())));

		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.MUNDANE), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.FETTERED.get())));
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.THICK), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.FETTERED.get())));
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.AWKWARD), Ingredient.of(EWItems.TRAPPED_SOUL.get()), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.FETTERED.get())));
		BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.FETTERED.get()), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.LONG_FETTERED.get())));

		EWCriteriaTriggers.register();

		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(EWBlocks.ICE_FLOWER.getId(), EWBlocks.POTTED_ICE_FLOWER);
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(EWEntities.MIMICHEST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mimichest::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
		event.register(EWEntities.MIMIPOT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mimichest::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EWEntities.MIMICHEST.get(), Mimichest.createChestAttributes().build());
		event.put(EWEntities.MIMIPOT.get(), Mimichest.createPotAttributes().build());
	}
}
