package dev.bc.expeditionworld.data;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class EWTrimMaterials {
	public static final ResourceKey<TrimMaterial> TRAPPED_SOUL = create("trapped_soul");

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, TRAPPED_SOUL, EWItems.TRAPPED_SOUL, 0xa1a1a1, 0.1f);
	}

	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Holder<Item> trimItem, int color, float itemModelIndex) {
		TrimMaterial material = new TrimMaterial(key.location().getPath(), trimItem, itemModelIndex, Map.of(), Component.translatable(Util.makeDescriptionId("trim_material", key.location())).withStyle(Style.EMPTY.withColor(color)));
		context.register(key, material);
	}

	private static ResourceKey<TrimMaterial> create(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, ExpeditionWorld.id(name));
	}
}
