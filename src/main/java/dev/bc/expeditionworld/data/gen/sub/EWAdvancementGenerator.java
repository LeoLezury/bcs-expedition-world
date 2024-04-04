package dev.bc.expeditionworld.data.gen.sub;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import dev.bc.expeditionworld.item.EWItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.commands.CommandFunction;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class EWAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement().display(
                        Items.AMETHYST_SHARD,
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".root.title"),
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".root.description"),
                        new ResourceLocation("textures/block/amethyst_block.png"),
                        FrameType.TASK,
                        false, false, false)
                .requirements(RequirementsStrategy.OR)
                .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE))
                .save(saver, ExpeditionWorld.MOD_ID + ":root");
        
        Advancement obtainSculkMint = addItemObtain(saver, root, "obtain_sculk_mint", EWItems.SCULK_MINT.get());
        Advancement standOnSculkShriekerWithCatwalk = Advancement.Builder.advancement().parent(obtainSculkMint).display(
                        Items.SCULK_SHRIEKER,
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".stand_on_sculk_shrieker_with_catwalk.title"),
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".stand_on_sculk_shrieker_with_catwalk.description"),
                        null, FrameType.CHALLENGE, true, true, false)
                .addCriterion("stand_with_effect", new PlayerTrigger.TriggerInstance(EWCriteriaTriggers.STAND_ON_SCULK_SHRIEKER_WITH_CATWALK.getId(), ContextAwarePredicate.ANY))
                .rewards(new AdvancementRewards(20, new ResourceLocation[0], new ResourceLocation[0], CommandFunction.CacheableFunction.NONE))
                .save(saver, ExpeditionWorld.MOD_ID + ":stand_on_sculk_shrieker_with_catwalk");
        Advancement fullArmorSetWithTrappedSoulTrim = Advancement.Builder.advancement().parent(root).display(
                        EWItems.TRAPPED_SOUL.get(),
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".full_armor_set_with_trapped_soul_trim.title"),
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + ".full_armor_set_with_trapped_soul_trim.description"),
                        null, FrameType.GOAL, true, true, false)
                .addCriterion("armor_set", new PlayerTrigger.TriggerInstance(EWCriteriaTriggers.FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM.getId(), ContextAwarePredicate.ANY))
                .save(saver, ExpeditionWorld.MOD_ID + ":full_armor_set_with_trapped_soul_trim");
    }

    private static Advancement addItemObtain(Consumer<Advancement> consumer, Advancement parent, String id, Item item) {
        return Advancement.Builder.advancement().parent(parent).display(
                        item,
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + "." + id + ".title"),
                        Component.translatable("advancements." + ExpeditionWorld.MOD_ID + "." + id + ".description"),
                        null, FrameType.GOAL, true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(item))
                .save(consumer, ExpeditionWorld.MOD_ID + ":" + id);
    }
}
