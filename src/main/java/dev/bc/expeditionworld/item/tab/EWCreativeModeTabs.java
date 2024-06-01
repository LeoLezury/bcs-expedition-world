package dev.bc.expeditionworld.item.tab;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.potion.EWPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EWCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExpeditionWorld.ID);
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("tab", () -> CreativeModeTab
            .builder().title(Component.translatable("item_group." + ExpeditionWorld.ID))
            .icon(() -> EWItems.FROZEN_GRASS_BLOCK.get().getDefaultInstance())
            .displayItems((params, output) -> {
                EWItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                EWPotions.POTIONS.getEntries().forEach(potion -> {
                    output.accept(PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potion.get()));
                    output.accept(PotionUtils.setPotion(Items.SPLASH_POTION.getDefaultInstance(), potion.get()));
                    output.accept(PotionUtils.setPotion(Items.LINGERING_POTION.getDefaultInstance(), potion.get()));
                });
            }).build());
}
