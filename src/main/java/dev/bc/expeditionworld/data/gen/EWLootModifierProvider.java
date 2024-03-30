package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.loot.AppendLootModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class EWLootModifierProvider extends GlobalLootModifierProvider {
    public EWLootModifierProvider(PackOutput output) {
        super(output, ExpeditionWorld.MOD_ID);
    }

    @Override
    protected void start() {
        add("chest_ancient_city", new AppendLootModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build()}, new ResourceLocation(ExpeditionWorld.MOD_ID, "chests/ancient_city")));
        add("entity_warden", new AppendLootModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(new ResourceLocation("entities/warden")).build()}, new ResourceLocation(ExpeditionWorld.MOD_ID, "entities/warden")));
    }
}
