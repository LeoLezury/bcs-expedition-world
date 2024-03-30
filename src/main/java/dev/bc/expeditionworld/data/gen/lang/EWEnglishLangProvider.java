package dev.bc.expeditionworld.data.gen.lang;

import dev.bc.expeditionworld.ExpeditionWorld;
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
        super(output, ExpeditionWorld.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("item_group." + ExpeditionWorld.MOD_ID, "BC's Expedition World");
        add(EWItems.SCULK_MINT.get(), "Sculk Mint");
        add(EWMobEffects.CATWALK.get(), "Catwalk");
        add(Items.POTION, EWPotions.CATWALK.get(), "Potion of Catwalk");
        add(Items.SPLASH_POTION, EWPotions.CATWALK.get(), "Splash Potion of Catwalk");
        add(Items.LINGERING_POTION, EWPotions.CATWALK.get(), "Lingering Potion of Catwalk");
        addAdvancement("root", "BC's Expedition World", "BC's Expedition World");
        addAdvancement("obtain_sculk_mint", "The sculk is salivating over it", "Obtain the sculk mint");
        addAdvancement("stand_on_sculk_shrieker_with_catwalk", "Dancer on the knife", "Stand on a sculk shrieker with catwalk effect and darkness effect");
    }

    public void add(Item item, Potion key, String name) {
        add(item.getDescriptionId() + ".effect." + ForgeRegistries.POTIONS.getKey(key).getPath(), name);
    }

    public void addAdvancement(String advancement, String name, String desc) {
        add("advancements." + ExpeditionWorld.MOD_ID + "." + advancement + ".title", name);
        add("advancements." + ExpeditionWorld.MOD_ID + "." + advancement + ".description", desc);
    }
}
