package dev.bc.expeditionworld.item.loot;

import com.mojang.serialization.Codec;
import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExpeditionWorld.ID);
    public static final RegistryObject<Codec<AppendLootModifier>> APPEND = LOOT_MODIFIERS.register("append", () -> AppendLootModifier.CODEC);
}
