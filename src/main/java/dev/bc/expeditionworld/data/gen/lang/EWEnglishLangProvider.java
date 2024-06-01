package dev.bc.expeditionworld.data.gen.lang;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.potion.EWMobEffects;
import dev.bc.expeditionworld.potion.EWPotions;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

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

        add(EWItems.SCULK_MINT.get(), "Sculk Mint");
        add(EWItems.TRAPPED_SOUL.get(), "Trapped Soul");
        add(EWItems.STONE_MIMICHEST_KNIFE.get(), "Stone Mimichest Knife");
        add(EWItems.BRICK_MIMICHEST_KNIFE.get(), "Brick Mimichest Knife");
        add(EWItems.ICE_CRYSTAL.get(), "Ice Crystal");
        add(EWItems.FROSTBITE_GUNPOWDER.get(), "Frostbite Gunpowder");
        add(EWItems.SHARP_ICICLE.get(), "Sharp Icicle");
        add(EWItems.FROST_CHARGE.get(), "Frost Charge");
        add(EWItems.FROZEN_ARROW.get(), "Frozen Arrow");

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
        add(EWEntities.MIMICHEST.get(), "Mimichest");
        add(EWEntities.MIMIPOT.get(), "Mimipot");

        add("subtitles.entity." + ExpeditionWorld.ID + ".frostbite_tnt.explode", "Frostbite TNT Explodes");

        addAdvancement("root", "BC's Expedition World", "BC's Expedition World");
        addAdvancement("obtain_sculk_mint", "The sculk is salivating over it", "Obtain the sculk mint");
        addAdvancement("stand_on_sculk_shrieker_with_catwalk", "Dancer on the knife", "Stand on a sculk shrieker with catwalk and darkness effect");
        addAdvancement("wake_mimichest", "Disturbing the dead", "Waking a mimichest or mimipot");
        addAdvancement("full_armor_set_with_trapped_soul_trim", "Confined", "Wearing a full set of armor with trapped soul as the armor trim material");
    }

    public void add(Item item, Potion key, String name) {
        add(item.getDescriptionId() + ".effect." + ForgeRegistries.POTIONS.getKey(key).getPath(), name);
    }

    public void addAdvancement(String advancement, String name, String desc) {
        add("advancements." + ExpeditionWorld.ID + "." + advancement + ".title", name);
        add("advancements." + ExpeditionWorld.ID + "." + advancement + ".description", desc);
    }
}
