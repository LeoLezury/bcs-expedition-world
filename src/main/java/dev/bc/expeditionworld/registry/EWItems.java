package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class EWItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, ExpeditionWorld.ID);

	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
	private static final Component GLACIER_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ExpeditionWorld.id("glacier_upgrade"))).withStyle(TITLE_FORMAT);
	private static final Component GLACIER_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component GLACIER_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component GLACIER_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.base_slot_description")));
	private static final Component GLACIER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.additions_slot_description")));
	private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
	private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
	private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
	private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
	private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

	// spawn eggs
	public static final DeferredHolder<Item, DeferredSpawnEggItem> MIMICHEST_SPAWN_EGG = ITEMS.register("mimichest_spawn_egg", () -> new DeferredSpawnEggItem(EWEntities.MIMICHEST, 0x818181, 0x4b5e4a, new Item.Properties()));
	public static final DeferredHolder<Item, DeferredSpawnEggItem> MIMIPOT_SPAWN_EGG = ITEMS.register("mimipot_spawn_egg", () -> new DeferredSpawnEggItem(EWEntities.MIMIPOT, 0x8b4e40, 0x585e4a, new Item.Properties()));
	public static final DeferredHolder<Item, DeferredSpawnEggItem> CHILLED_SPAWN_EGG = ITEMS.register("chilled_spawn_egg", () -> new DeferredSpawnEggItem(EWEntities.CHILLED, 0x607791, 0x3e5945, new Item.Properties()));
	public static final DeferredHolder<Item, DeferredSpawnEggItem> ICE_CREEPER_SPAWN_EGG = ITEMS.register("ice_creeper_spawn_egg", () -> new DeferredSpawnEggItem(EWEntities.ICE_CREEPER, 0xf2f4f61, 0x84aefd, new Item.Properties()));
	public static final DeferredHolder<Item, DeferredSpawnEggItem> SNOW_CRAB_SPAWN_EGG = ITEMS.register("snow_crab_spawn_egg", () -> new DeferredSpawnEggItem(EWEntities.SNOW_CRAB, 0xe9f1f6, 0x95b9ff, new Item.Properties()));

	// sculk mint
	public static final DeferredHolder<Item, Item> SCULK_MINT = ITEMS.register("sculk_mint", () -> new Item(new Item.Properties()));

	// mimichest
	public static final DeferredHolder<Item, Item> TRAPPED_SOUL = ITEMS.register("trapped_soul", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, MimichestKnifeItem> STONE_MIMICHEST_KNIFE = ITEMS.register("stone_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
	public static final DeferredHolder<Item, MimichestKnifeItem> BRICK_MIMICHEST_KNIFE = ITEMS.register("brick_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
	public static final DeferredHolder<Item, BlockItem> FETTERED_CHEST = ITEMS.register("fettered_chest", () -> new BlockItem(EWBlocks.FETTERED_CHEST.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FETTERED_POT = ITEMS.register("fettered_pot", () -> new BlockItem(EWBlocks.FETTERED_POT.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> MOSSFLORA = ITEMS.register("mossflora", () -> new BlockItem(EWBlocks.MOSSFLORA.get(), new Item.Properties()));

	// frozen caves
	public static final DeferredHolder<Item, BlockItem> FROZEN_STONE = ITEMS.register("frozen_stone", () -> new BlockItem(EWBlocks.FROZEN_STONE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_STONE_SLAB = ITEMS.register("frozen_stone_slab", () -> new BlockItem(EWBlocks.FROZEN_STONE_SLAB.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_STONE_STAIRS = ITEMS.register("frozen_stone_stairs", () -> new BlockItem(EWBlocks.FROZEN_STONE_STAIRS.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_STONE_WALL = ITEMS.register("frozen_stone_wall", () -> new BlockItem(EWBlocks.FROZEN_STONE_WALL.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> ICE_CRYSTAL_ORE = ITEMS.register("ice_crystal_ore", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_ORE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> ICE_CRYSTAL_BLOCK = ITEMS.register("ice_crystal_block", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_BLOCK.get(), new Item.Properties()));
	public static final DeferredHolder<Item, StandingAndWallBlockItem> ICE_CRYSTAL_TORCH = ITEMS.register("ice_crystal_torch", () -> new StandingAndWallBlockItem(EWBlocks.ICE_CRYSTAL_TORCH.get(), EWBlocks.ICE_CRYSTAL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final DeferredHolder<Item, BlockItem> FROSTBITE_TNT = ITEMS.register("frostbite_tnt", () -> new BlockItem(EWBlocks.FROSTBITE_TNT.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_DIRT = ITEMS.register("frozen_dirt", () -> new BlockItem(EWBlocks.FROZEN_DIRT.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_GRASS_BLOCK = ITEMS.register("frozen_grass_block", () -> new BlockItem(EWBlocks.FROZEN_GRASS_BLOCK.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> ICE_LANTERN = ITEMS.register("ice_lantern", () -> new BlockItem(EWBlocks.ICE_LANTERN.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> POINTED_ICE = ITEMS.register("pointed_ice", () -> new BlockItem(EWBlocks.POINTED_ICE.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FROZEN_GRASS = ITEMS.register("frozen_grass", () -> new BlockItem(EWBlocks.FROZEN_GRASS.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> ICE_FLOWER = ITEMS.register("ice_flower", () -> new BlockItem(EWBlocks.ICE_FLOWER.get(), new Item.Properties()));
	public static final DeferredHolder<Item, BlockItem> FRIGID_GLADIOLUS = ITEMS.register("frigid_gladiolus", () -> new BlockItem(EWBlocks.FRIGID_GLADIOLUS.get(), new Item.Properties()));

	public static final DeferredHolder<Item, Item> ICE_CRYSTAL = ITEMS.register("ice_crystal", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> FROSTBITE_GUNPOWDER = ITEMS.register("frostbite_gunpowder", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> SHARP_ICICLE = ITEMS.register("sharp_icicle", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, FrostChargeItem> FROST_CHARGE = ITEMS.register("frost_charge", () -> new FrostChargeItem(new Item.Properties()));
	public static final DeferredHolder<Item, FrozenArrowItem> FROZEN_ARROW = ITEMS.register("frozen_arrow", () -> new FrozenArrowItem(new Item.Properties()));

	public static final DeferredHolder<Item, FrigidBeakItem> FRIGID_BEAK = ITEMS.register("frigid_beak", () -> new FrigidBeakItem(new Item.Properties()));
	public static final DeferredHolder<Item, FrostyMoaLootBagItem> FROSTY_MOA_LOOT_BAG = ITEMS.register("frosty_moa_loot_bag", () -> new FrostyMoaLootBagItem(new Item.Properties()));
	public static final DeferredHolder<Item, Item> MOA_FEATHER = ITEMS.register("moa_feather", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> MOA_SKULL = ITEMS.register("moa_skull", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, Item> FROSTY_MOA_EGG = ITEMS.register("frosty_moa_egg", () -> new Item(new Item.Properties()));
	public static final DeferredHolder<Item, MoaFeatherArrowItem> MOA_FEATHER_ARROW = ITEMS.register("moa_feather_arrow", () -> new MoaFeatherArrowItem(new Item.Properties()));
	public static final DeferredHolder<Item, IceTotemItem> TOTEM_OF_ICE = ITEMS.register("totem_of_ice", () -> new IceTotemItem(new Item.Properties().durability(400)));

	public static final DeferredHolder<Item, ColdproofArmorItem> COLDPROOF_HAT = ITEMS.register("coldproof_hat", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(14))));
	public static final DeferredHolder<Item, ColdproofArmorItem> COLDPROOF_COAT = ITEMS.register("coldproof_coat", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(14))));
	public static final DeferredHolder<Item, ColdproofArmorItem> COLDPROOF_LEGGINGS = ITEMS.register("coldproof_leggings", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(14))));
	public static final DeferredHolder<Item, ColdproofArmorItem> COLDPROOF_BOOTS = ITEMS.register("coldproof_boots", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(14))));

	public static final DeferredHolder<Item, SmithingTemplateItem> CRYO_SMITHING_TEMPLATE = ITEMS.register("cryo_smithing_template", () -> new SmithingTemplateItem(GLACIER_UPGRADE_APPLIES_TO, GLACIER_UPGRADE_INGREDIENTS, GLACIER_UPGRADE, GLACIER_UPGRADE_BASE_SLOT_DESCRIPTION, GLACIER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS), List.of(EMPTY_SLOT_INGOT)));

	public static final DeferredHolder<Item, GlacierArmorItem> GLACIER_HELMET = ITEMS.register("glacier_helmet", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(35))));
	public static final DeferredHolder<Item, GlacierArmorItem> GLACIER_CHESTPLATE = ITEMS.register("glacier_chestplate", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(35))));
	public static final DeferredHolder<Item, GlacierArmorItem> GLACIER_LEGGINGS = ITEMS.register("glacier_leggings", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(35))));
	public static final DeferredHolder<Item, GlacierArmorItem> GLACIER_BOOTS = ITEMS.register("glacier_boots", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(35))));

	public static final DeferredHolder<Item, SwordWithEffectItem> FROSTY_DAGGER = ITEMS.register("frosty_dagger", () -> new SwordWithEffectItem(EWItemTiers.SHARP_ICICLE, new Item.Properties().attributes(SwordItem.createAttributes(EWItemTiers.SHARP_ICICLE, 1, -2.2F)), EWMobEffects.FROZEN, 120, 0));
	public static final DeferredHolder<Item, SwordWithEffectItem> GLACIER_DAGGER = ITEMS.register("glacier_dagger", () -> new SwordWithEffectItem(EWItemTiers.GLACIER, new Item.Properties().attributes(SwordItem.createAttributes(EWItemTiers.GLACIER, 1, -2.2F)), EWMobEffects.FROZEN, 160, 1));

	public static final DeferredHolder<Item, GlacierBowItem> GLACIER_BOW = ITEMS.register("glacier_bow", () -> new GlacierBowItem(new Item.Properties().durability(860)));
}
