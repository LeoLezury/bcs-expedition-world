package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExpeditionWorld.ID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab", () -> CreativeModeTab
		.builder().title(Component.translatable("item_group." + ExpeditionWorld.ID))
		.icon(() -> EWItems.FROZEN_GRASS_BLOCK.get().getDefaultInstance())
		.displayItems((params, output) -> {
			EWItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
			EWPotions.POTIONS.getEntries().forEach(potion -> {
				ItemStack stack = Items.POTION.getDefaultInstance();
				stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
				output.accept(stack);
				stack = Items.SPLASH_POTION.getDefaultInstance();
				stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
				output.accept(stack);
				stack = Items.LINGERING_POTION.getDefaultInstance();
				stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
				output.accept(stack);
			});
		}).build());
}
