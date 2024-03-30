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

public class EWChineseLangProvider extends LanguageProvider {
    public EWChineseLangProvider(PackOutput output) {
        super(output, ExpeditionWorld.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("item_group." + ExpeditionWorld.MOD_ID, "BC的远征物语");
        add(EWItems.SCULK_MINT.get(), "幽匿薄荷");
        add(EWMobEffects.CATWALK.get(), "猫步");
        add(Items.POTION, EWPotions.CATWALK.get(), "猫步药水");
        add(Items.SPLASH_POTION, EWPotions.CATWALK.get(), "喷溅型猫步药水");
        add(Items.LINGERING_POTION, EWPotions.CATWALK.get(), "滞留型猫步药水");
        addAdvancement("root", "BC的远征物语", "BC的远征物语");
        addAdvancement("obtain_sculk_mint", "幽匿也为之垂涎欲滴", "获得幽匿薄荷");
        addAdvancement("stand_on_sculk_shrieker_with_catwalk", "刀尖舞者", "在同时拥有黑暗与猫步效果时下站在一个幽匿尖啸体上方");
    }

    public void add(Item item, Potion key, String name) {
        add(item.getDescriptionId() + ".effect." + ForgeRegistries.POTIONS.getKey(key).getPath(), name);
    }

    public void addAdvancement(String advancement, String name, String desc) {
        add("advancements." + ExpeditionWorld.MOD_ID + "." + advancement + ".title", name);
        add("advancements." + ExpeditionWorld.MOD_ID + "." + advancement + ".description", desc);
    }
}
