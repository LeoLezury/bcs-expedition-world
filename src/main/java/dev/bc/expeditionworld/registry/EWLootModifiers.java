package dev.bc.expeditionworld.registry;

import com.mojang.serialization.MapCodec;
import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.loot.AppendLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class EWLootModifiers {
	public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExpeditionWorld.ID);
	public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AppendLootModifier>> APPEND = LOOT_MODIFIERS.register("append", () -> AppendLootModifier.CODEC);
}
