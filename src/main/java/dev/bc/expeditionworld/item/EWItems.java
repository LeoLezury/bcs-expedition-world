package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.potion.EWMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class EWItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExpeditionWorld.ID);

	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
	private static final Component GLACIER_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ExpeditionWorld.id("glacier_upgrade"))).withStyle(TITLE_FORMAT);
	private static final Component GLACIER_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component GLACIER_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component GLACIER_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.base_slot_description")));
	private static final Component GLACIER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.additions_slot_description")));
	private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
	private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
	private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
	private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
	private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");

	// spawn eggs
	public static final RegistryObject<ForgeSpawnEggItem> MIMICHEST_SPAWN_EGG = ITEMS.register("mimichest_spawn_egg", () -> new ForgeSpawnEggItem(EWEntities.MIMICHEST, 0x818181, 0x4b5e4a, new Item.Properties()));
	public static final RegistryObject<ForgeSpawnEggItem> MIMIPOT_SPAWN_EGG = ITEMS.register("mimipot_spawn_egg", () -> new ForgeSpawnEggItem(EWEntities.MIMIPOT, 0x8b4e40, 0x585e4a, new Item.Properties()));
	public static final RegistryObject<ForgeSpawnEggItem> CHILLED_SPAWN_EGG = ITEMS.register("chilled_spawn_egg", () -> new ForgeSpawnEggItem(EWEntities.CHILLED, 0x607791, 0x3e5945, new Item.Properties()));
	public static final RegistryObject<ForgeSpawnEggItem> ICE_CREEPER_SPAWN_EGG = ITEMS.register("ice_creeper_spawn_egg", () -> new ForgeSpawnEggItem(EWEntities.ICE_CREEPER, 0xf2f4f61, 0x84aefd, new Item.Properties()));

	// sculk mint
	public static final RegistryObject<Item> SCULK_MINT = ITEMS.register("sculk_mint", () -> new Item(new Item.Properties()));

	// mimichest
	public static final RegistryObject<Item> TRAPPED_SOUL = ITEMS.register("trapped_soul", () -> new Item(new Item.Properties()));
	public static final RegistryObject<MimichestKnifeItem> STONE_MIMICHEST_KNIFE = ITEMS.register("stone_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
	public static final RegistryObject<MimichestKnifeItem> BRICK_MIMICHEST_KNIFE = ITEMS.register("brick_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
	public static final RegistryObject<BlockItem> FETTERED_CHEST = ITEMS.register("fettered_chest", () -> new BlockItem(EWBlocks.FETTERED_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FETTERED_POT = ITEMS.register("fettered_pot", () -> new BlockItem(EWBlocks.FETTERED_POT.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> MOSSFLORA = ITEMS.register("mossflora", () -> new BlockItem(EWBlocks.MOSSFLORA.get(), new Item.Properties()));

	// frozen caves
	public static final RegistryObject<BlockItem> FROZEN_STONE = ITEMS.register("frozen_stone", () -> new BlockItem(EWBlocks.FROZEN_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_STONE_SLAB = ITEMS.register("frozen_stone_slab", () -> new BlockItem(EWBlocks.FROZEN_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_STONE_STAIRS = ITEMS.register("frozen_stone_stairs", () -> new BlockItem(EWBlocks.FROZEN_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_STONE_WALL = ITEMS.register("frozen_stone_wall", () -> new BlockItem(EWBlocks.FROZEN_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ICE_CRYSTAL_ORE = ITEMS.register("ice_crystal_ore", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_ORE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ICE_CRYSTAL_BLOCK = ITEMS.register("ice_crystal_block", () -> new BlockItem(EWBlocks.ICE_CRYSTAL_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<StandingAndWallBlockItem> ICE_CRYSTAL_TORCH = ITEMS.register("ice_crystal_torch", () -> new StandingAndWallBlockItem(EWBlocks.ICE_CRYSTAL_TORCH.get(), EWBlocks.ICE_CRYSTAL_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final RegistryObject<BlockItem> FROSTBITE_TNT = ITEMS.register("frostbite_tnt", () -> new BlockItem(EWBlocks.FROSTBITE_TNT.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_DIRT = ITEMS.register("frozen_dirt", () -> new BlockItem(EWBlocks.FROZEN_DIRT.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_GRASS_BLOCK = ITEMS.register("frozen_grass_block", () -> new BlockItem(EWBlocks.FROZEN_GRASS_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ICE_LANTERN = ITEMS.register("ice_lantern", () -> new BlockItem(EWBlocks.ICE_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> POINTED_ICE = ITEMS.register("pointed_ice", () -> new BlockItem(EWBlocks.POINTED_ICE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FROZEN_GRASS = ITEMS.register("frozen_grass", () -> new BlockItem(EWBlocks.FROZEN_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ICE_FLOWER = ITEMS.register("ice_flower", () -> new BlockItem(EWBlocks.ICE_FLOWER.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> FRIGID_GLADIOLUS = ITEMS.register("frigid_gladiolus", () -> new BlockItem(EWBlocks.FRIGID_GLADIOLUS.get(), new Item.Properties()));

	public static final RegistryObject<Item> ICE_CRYSTAL = ITEMS.register("ice_crystal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> FROSTBITE_GUNPOWDER = ITEMS.register("frostbite_gunpowder", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SHARP_ICICLE = ITEMS.register("sharp_icicle", () -> new Item(new Item.Properties()));
	public static final RegistryObject<FrostChargeItem> FROST_CHARGE = ITEMS.register("frost_charge", () -> new FrostChargeItem(new Item.Properties()));
	public static final RegistryObject<FrozenArrowItem> FROZEN_ARROW = ITEMS.register("frozen_arrow", () -> new FrozenArrowItem(new Item.Properties()));

	public static final RegistryObject<FrigidBeakItem> FRIGID_BEAK = ITEMS.register("frigid_beak", () -> new FrigidBeakItem(new Item.Properties()));
	public static final RegistryObject<FrostyMoaLootBagItem> FROSTY_MOA_LOOT_BAG = ITEMS.register("frosty_moa_loot_bag", () -> new FrostyMoaLootBagItem(new Item.Properties()));
	public static final RegistryObject<Item> MOA_FEATHER = ITEMS.register("moa_feather", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> MOA_SKULL = ITEMS.register("moa_skull", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> FROSTY_MOA_EGG = ITEMS.register("frosty_moa_egg", () -> new Item(new Item.Properties()));
	public static final RegistryObject<MoaFeatherArrowItem> MOA_FEATHER_ARROW = ITEMS.register("moa_feather_arrow", () -> new MoaFeatherArrowItem(new Item.Properties()));
	public static final RegistryObject<IceTotemItem> TOTEM_OF_ICE = ITEMS.register("totem_of_ice", () -> new IceTotemItem(new Item.Properties().durability(400)));

	public static final RegistryObject<ColdproofArmorItem> COLDPROOF_HAT = ITEMS.register("coldproof_hat", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<ColdproofArmorItem> COLDPROOF_COAT = ITEMS.register("coldproof_coat", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<ColdproofArmorItem> COLDPROOF_LEGGINGS = ITEMS.register("coldproof_leggings", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<ColdproofArmorItem> COLDPROOF_BOOTS = ITEMS.register("coldproof_boots", () -> new ColdproofArmorItem(EWArmorMaterials.COLDPROOF, ArmorItem.Type.BOOTS, new Item.Properties()));

	public static final RegistryObject<SmithingTemplateItem> CRYO_SMITHING_TEMPLATE = ITEMS.register("cryo_smithing_template", () -> new SmithingTemplateItem(GLACIER_UPGRADE_APPLIES_TO, GLACIER_UPGRADE_INGREDIENTS, GLACIER_UPGRADE, GLACIER_UPGRADE_BASE_SLOT_DESCRIPTION, GLACIER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS), List.of(EMPTY_SLOT_INGOT)));

	public static final RegistryObject<GlacierArmorItem> GLACIER_HELMET = ITEMS.register("glacier_helmet", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final RegistryObject<GlacierArmorItem> GLACIER_CHESTPLATE = ITEMS.register("glacier_chestplate", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final RegistryObject<GlacierArmorItem> GLACIER_LEGGINGS = ITEMS.register("glacier_leggings", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final RegistryObject<GlacierArmorItem> GLACIER_BOOTS = ITEMS.register("glacier_boots", () -> new GlacierArmorItem(EWArmorMaterials.GLACIER, ArmorItem.Type.BOOTS, new Item.Properties()));

	public static final RegistryObject<SwordWithEffectItem> FROSTY_DAGGER = ITEMS.register("frosty_dagger", () -> new SwordWithEffectItem(EWItemTiers.SHARP_ICICLE, 1, -2.2F, EWMobEffects.FROZEN.get(), 120, 0, new Item.Properties()));
	public static final RegistryObject<SwordWithEffectItem> GLACIER_DAGGER = ITEMS.register("glacier_dagger", () -> new SwordWithEffectItem(EWItemTiers.GLACIER, 1, -2.2F, EWMobEffects.FROZEN.get(), 160, 1, new Item.Properties()));

	public static final RegistryObject<GlacierBowItem> GLACIER_BOW = ITEMS.register("glacier_bow", () -> new GlacierBowItem(new Item.Properties().durability(860)));
}
