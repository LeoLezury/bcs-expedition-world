package dev.bc.expeditionworld.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.potion.EWPotions;
import dev.bc.expeditionworld.potion.recipe.ExactBrewingRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ExpeditionWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.AWKWARD), Ingredient.of(EWItems.SCULK_MINT.get()), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.CATWALK.get())));
        BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.CATWALK.get()), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.MEDIUM_CATWALK.get())));
        BrewingRecipeRegistry.addRecipe(new ExactBrewingRecipe(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.MEDIUM_CATWALK.get()), Ingredient.of(Items.ECHO_SHARD), PotionUtils.setPotion(Items.POTION.getDefaultInstance(), EWPotions.LONG_CATWALK.get())));

        EWCriteriaTriggers.register();
    }
}
