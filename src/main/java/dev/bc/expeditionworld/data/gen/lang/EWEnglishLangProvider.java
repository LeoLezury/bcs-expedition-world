package dev.bc.expeditionworld.data.gen.lang;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.*;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Objects;

public class EWEnglishLangProvider extends LanguageProvider {
	public EWEnglishLangProvider(PackOutput output) {
		super(output, ExpeditionWorld.ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("item_group." + ExpeditionWorld.ID, "BC's Expedition World");

		add(EWBlocks.FETTERED_CHEST.get(), "Fettered Chest");
		add(EWBlocks.FETTERED_POT.get(), "Fettered Pot");
		add(EWBlocks.MOSSFLORA.get(), "Mossflora");
		add(EWBlocks.FROZEN_STONE.get(), "Frozen Stone");
		add(EWBlocks.FROZEN_STONE_SLAB.get(), "Frozen Stone Slab");
		add(EWBlocks.FROZEN_STONE_STAIRS.get(), "Frozen Stone Stairs");
		add(EWBlocks.FROZEN_STONE_WALL.get(), "Frozen Stone Wall");
		add(EWBlocks.ICE_CRYSTAL_ORE.get(), "Ice Crystal Ore");
		add(EWBlocks.ICE_CRYSTAL_BLOCK.get(), "Ice Crystal Block");
		add(EWBlocks.ICE_CRYSTAL_TORCH.get(), "Ice Crystal Torch");
		add(EWBlocks.FROSTBITE_TNT.get(), "Frostbite TNT");
		add(EWBlocks.FROZEN_DIRT.get(), "Frozen Dirt");
		add(EWBlocks.FROZEN_GRASS_BLOCK.get(), "Frozen Grass Block");
		add(EWBlocks.ICE_LANTERN.get(), "Ice Lantern");
		add(EWBlocks.POINTED_ICE.get(), "Pointed Ice");
		add(EWBlocks.FROZEN_GRASS.get(), "Frozen Grass");
		add(EWBlocks.ICE_FLOWER.get(), "Ice Flower");
		add(EWBlocks.POTTED_ICE_FLOWER.get(), "Potted Ice Flower");
		add(EWBlocks.FRIGID_GLADIOLUS.get(), "Frigid Gladiolus");

		add(EWItems.MIMICHEST_SPAWN_EGG.get(), "Mimichest Spawn Egg");
		add(EWItems.MIMIPOT_SPAWN_EGG.get(), "Mimipot Spawn Egg");
		add(EWItems.CHILLED_SPAWN_EGG.get(), "Chilled Spawn Egg");
		add(EWItems.ICE_CREEPER_SPAWN_EGG.get(), "Ice Creeper Spawn Egg");
		add(EWItems.SNOW_CRAB_SPAWN_EGG.get(), "Snow Crab Spawn Egg");

		add(EWItems.SCULK_MINT.get(), "Sculk Mint");
		add(EWItems.TRAPPED_SOUL.get(), "Trapped Soul");
		add(EWItems.STONE_MIMICHEST_KNIFE.get(), "Stone Mimichest Knife");
		add(EWItems.BRICK_MIMICHEST_KNIFE.get(), "Brick Mimichest Knife");
		add(EWItems.ICE_CRYSTAL.get(), "Ice Crystal");
		add(EWItems.FROSTBITE_GUNPOWDER.get(), "Frostbite Gunpowder");
		add(EWItems.SHARP_ICICLE.get(), "Sharp Icicle");
		add(EWItems.FROST_CHARGE.get(), "Frost Charge");
		add(EWItems.FROZEN_ARROW.get(), "Frozen Arrow");
		add(EWItems.FRIGID_BEAK.get(), "Frigid Beak");
		add(EWItems.FRIGID_BEAK.get().getDescriptionId() + ".desc", "It's emitting cold air...");
		add(EWItems.FRIGID_BEAK.get().getDescriptionId() + ".fail", "It's not a very good location");
		add(EWItems.FROSTY_MOA_LOOT_BAG.get(), "Frosty Moa Loot Bag");
		add(EWItems.MOA_FEATHER.get(), "Moa Feather");
		add(EWItems.MOA_SKULL.get(), "Moa Skull");
		add(EWItems.FROSTY_MOA_EGG.get(), "Frosty Moa Egg");
		add(EWItems.MOA_FEATHER_ARROW.get(), "Moa Feather Arrow");
		add(EWItems.TOTEM_OF_ICE.get(), "Totem of Ice");
		add(EWItems.COLDPROOF_HAT.get(), "Coldproof Hat");
		add(EWItems.COLDPROOF_COAT.get(), "Coldproof Coat");
		add(EWItems.COLDPROOF_LEGGINGS.get(), "Coldproof Leggings");
		add(EWItems.COLDPROOF_BOOTS.get(), "Coldproof Boots");
		add(EWItems.CRYO_SMITHING_TEMPLATE.get(), "Smithing Template");
		add(Util.makeDescriptionId("upgrade", ExpeditionWorld.id("glacier_upgrade")), "Glacier Upgrade");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.applies_to")), "Coldproof Equipment");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.ingredients")), "Moa Feather");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.base_slot_description")), "Add coldproof armor or frosty dagger");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.additions_slot_description")), "Add Moa Feather");
		add(EWItems.GLACIER_HELMET.get(), "Glacier Helmet");
		add(EWItems.GLACIER_CHESTPLATE.get(), "Glacier Chestplate");
		add(EWItems.GLACIER_LEGGINGS.get(), "Glacier Leggings");
		add(EWItems.GLACIER_BOOTS.get(), "Glacier Boots");
		add(EWItems.FROSTY_DAGGER.get(), "Frosty Dagger");
		add(EWItems.GLACIER_DAGGER.get(), "Glacier Dagger");
		add(EWItems.GLACIER_BOW.get(), "Glacier Bow");

		add("trim_material." + ExpeditionWorld.ID + ".trapped_soul", "Trapped Soul Material");

		add(Items.POTION, EWPotions.CATWALK.get(), "Potion of Catwalk");
		add(Items.SPLASH_POTION, EWPotions.CATWALK.get(), "Splash Potion of Catwalk");
		add(Items.LINGERING_POTION, EWPotions.CATWALK.get(), "Lingering Potion of Catwalk");
		add(Items.TIPPED_ARROW, EWPotions.CATWALK.get(), "Arrow of Catwalk");

		add(Items.POTION, EWPotions.FETTERED.get(), "Potion of Fettered");
		add(Items.SPLASH_POTION, EWPotions.FETTERED.get(), "Splash Potion of Fettered");
		add(Items.LINGERING_POTION, EWPotions.FETTERED.get(), "Lingering Potion of Fettered");
		add(Items.TIPPED_ARROW, EWPotions.FETTERED.get(), "Arrow of Fettered");

		add(EWMobEffects.CATWALK.get(), "Catwalk");
		add(EWMobEffects.FETTERED.get(), "Fettered");
		add(EWMobEffects.FROZEN.get(), "Frozen");

		add(EWEntities.FROSTBITE_TNT.get(), "Frostbite TNT");
		add(EWEntities.MIMICHEST_KNIFE.get(), "Mimichest Knife");
		add(EWEntities.FROST_CHARGE.get(), "Frost Charge");
		add(EWEntities.FROZEN_ARROW.get(), "Frozen Arrow");
		add(EWEntities.MOA_FEATHER_ARROW.get(), "Moa Feather Arrow");
		add(EWEntities.MIMICHEST.get(), "Mimichest");
		add(EWEntities.MIMIPOT.get(), "Mimipot");
		add(EWEntities.CHILLED.get(), "Chilled");
		add(EWEntities.ICE_CREEPER.get(), "Ice Creeper");
		add(EWEntities.SNOW_CRAB.get(), "Snow Crab");

		add("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_coldproof", "Coldproof armor rustles");
		add("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_glacier", "Glacier armor clings");
		add("subtitles.entity." + ExpeditionWorld.ID + ".frostbite_tnt.explode", "Frostbite TNT explodes");

		addAdvancement("root", "BC's Expedition World", "BC's Expedition World");
		addAdvancement("obtain_sculk_mint", "The sculk is salivating over it", "Obtain the sculk mint");
		addAdvancement("stand_on_sculk_shrieker_with_catwalk", "Dancer on the knife", "Stand on a sculk shrieker with catwalk and darkness effect");
		addAdvancement("wake_mimichest", "Disturbing the dead", "Waking a mimichest or mimipot");
		addAdvancement("full_armor_set_with_trapped_soul_trim", "Confined", "Wearing a full set of armor with trapped soul as the armor trim material");
	}

	public void add(Item item, Potion key, String name) {
		add(item.getDescriptionId() + ".effect." + Objects.requireNonNull(BuiltInRegistries.POTION.getKey(key)).getPath(), name);
	}

	public void addAdvancement(String advancement, String name, String desc) {
		add("advancements." + ExpeditionWorld.ID + "." + advancement + ".title", name);
		add("advancements." + ExpeditionWorld.ID + "." + advancement + ".description", desc);
	}
}
