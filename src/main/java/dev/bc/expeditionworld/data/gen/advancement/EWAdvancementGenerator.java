package dev.bc.expeditionworld.data.gen.advancement;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWCriteriaTriggers;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class EWAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
	@Override
	public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
		AdvancementHolder root = Advancement.Builder.advancement().display(
				EWItems.FROZEN_GRASS_BLOCK.get(),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".root.title"),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".root.description"),
				ResourceLocation.withDefaultNamespace("textures/block/amethyst_block.png"),
				AdvancementType.TASK,
				false, false, false)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE))
			.save(consumer, ExpeditionWorld.ID + ":root");

		AdvancementHolder obtainSculkMint = addItemObtain(consumer, root, "obtain_sculk_mint", EWItems.SCULK_MINT.get());
		AdvancementHolder standOnSculkShriekerWithCatwalk = Advancement.Builder.advancement().parent(obtainSculkMint).display(
				Items.SCULK_SHRIEKER,
				Component.translatable("advancements." + ExpeditionWorld.ID + ".stand_on_sculk_shrieker_with_catwalk.title"),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".stand_on_sculk_shrieker_with_catwalk.description"),
				null, AdvancementType.CHALLENGE, true, true, false)
			.addCriterion("stand_with_effect", EWCriteriaTriggers.STAND_ON_SCULK_SHRIEKER_WITH_CATWALK.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
			.rewards(new AdvancementRewards(20, List.of(), List.of(), Optional.empty()))
			.save(consumer, ExpeditionWorld.ID + ":stand_on_sculk_shrieker_with_catwalk");
		AdvancementHolder wakeMimichest = Advancement.Builder.advancement().parent(root).display(
				EWItems.FETTERED_CHEST.get(),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".wake_mimichest.title"),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".wake_mimichest.description"),
				null, AdvancementType.GOAL, true, true, false)
			.addCriterion("wake", EWCriteriaTriggers.WAKE_MIMICHEST.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
			.save(consumer, ExpeditionWorld.ID + ":wake_mimichest");
		AdvancementHolder fullArmorSetWithTrappedSoulTrim = Advancement.Builder.advancement().parent(wakeMimichest).display(
				EWItems.TRAPPED_SOUL.get(),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".full_armor_set_with_trapped_soul_trim.title"),
				Component.translatable("advancements." + ExpeditionWorld.ID + ".full_armor_set_with_trapped_soul_trim.description"),
				null, AdvancementType.GOAL, true, true, false)
			.addCriterion("armor_set", EWCriteriaTriggers.FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
			.save(consumer, ExpeditionWorld.ID + ":full_armor_set_with_trapped_soul_trim");
	}

	private static AdvancementHolder addItemObtain(Consumer<AdvancementHolder> consumer, AdvancementHolder parent, String id, Item item) {
		return Advancement.Builder.advancement().parent(parent).display(
				item,
				Component.translatable("advancements." + ExpeditionWorld.ID + "." + id + ".title"),
				Component.translatable("advancements." + ExpeditionWorld.ID + "." + id + ".description"),
				null, AdvancementType.GOAL, true, true, false)
			.addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(item))
			.save(consumer, ExpeditionWorld.ID + ":" + id);
	}
}
