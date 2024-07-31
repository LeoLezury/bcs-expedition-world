package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class EWArmorMaterials {
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, ExpeditionWorld.ID);
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COLDPROOF = ARMOR_MATERIALS.register("coldproof", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 5);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 9, EWSoundEvents.ARMOR_EQUIP_COLDPROOF, () -> Ingredient.of(ItemTags.WOOL), List.of(new ArmorMaterial.Layer(ExpeditionWorld.id("coldproof"))), 0F, 0F));
	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GLACIER = ARMOR_MATERIALS.register("glacier", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 9, EWSoundEvents.ARMOR_EQUIP_GLACIER, () -> Ingredient.of(EWItems.MOA_FEATHER.get()), List.of(new ArmorMaterial.Layer(ExpeditionWorld.id("glacier"))), 1F, 0F));
}
