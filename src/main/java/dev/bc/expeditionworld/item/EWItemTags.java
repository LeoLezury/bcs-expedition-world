package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EWItemTags {
	public static final TagKey<Item> REPAIRS_TOTEM_OF_ICE = create("repairs_totem_of_ice");
	public static final TagKey<Item> TRIMMABLE_ARMOR = create("trimmable_armor");

	private static TagKey<Item> create(String id) {
		return TagKey.create(Registries.ITEM, ExpeditionWorld.id(id));
	}
}
