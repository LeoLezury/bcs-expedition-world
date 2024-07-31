package dev.bc.expeditionworld.data;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class EWLootTables {
	public static final ResourceKey<LootTable> BOSS_FROSTY_MOA = create("boss/frosty_moa");
	public static final ResourceKey<LootTable> CHEST_ANCIENT_CITY = create("chests/ancient_city");
	public static final ResourceKey<LootTable> ENTITY_WARDEN = create("entities/warden");

	private static ResourceKey<LootTable> create(String name) {
		return ResourceKey.create(Registries.LOOT_TABLE, ExpeditionWorld.id(name));
	}
}
