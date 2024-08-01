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

public class EWChineseLangProvider extends LanguageProvider {
	public EWChineseLangProvider(PackOutput output) {
		super(output, ExpeditionWorld.ID, "zh_cn");
	}

	@Override
	protected void addTranslations() {
		add("item_group." + ExpeditionWorld.ID, "BC的远征物语");

		add(EWBlocks.FETTERED_CHEST.get(), "桎梏箱");
		add(EWBlocks.FETTERED_POT.get(), "桎梏罐");
		add(EWBlocks.MOSSFLORA.get(), "苔花");
		add(EWBlocks.FROZEN_STONE.get(), "封冻石");
		add(EWBlocks.FROZEN_STONE_SLAB.get(), "封冻石台阶");
		add(EWBlocks.FROZEN_STONE_STAIRS.get(), "封冻石楼梯");
		add(EWBlocks.FROZEN_STONE_WALL.get(), "封冻石墙");
		add(EWBlocks.ICE_CRYSTAL_ORE.get(), "冰晶矿石");
		add(EWBlocks.ICE_CRYSTAL_BLOCK.get(), "冰晶块");
		add(EWBlocks.ICE_CRYSTAL_TORCH.get(), "冰石火把");
		add(EWBlocks.FROSTBITE_TNT.get(), "冻伤TNT");
		add(EWBlocks.FROZEN_DIRT.get(), "封冻土壤");
		add(EWBlocks.FROZEN_GRASS_BLOCK.get(), "封冻草方块");
		add(EWBlocks.ICE_LANTERN.get(), "冰灯");
		add(EWBlocks.POINTED_ICE.get(), "冰锥");
		add(EWBlocks.FROZEN_GRASS.get(), "霜冻草");
		add(EWBlocks.ICE_FLOWER.get(), "冰花");
		add(EWBlocks.POTTED_ICE_FLOWER.get(), "盆装冰花");
		add(EWBlocks.FRIGID_GLADIOLUS.get(), "凛剑兰");

		add(EWItems.MIMICHEST_SPAWN_EGG.get(), "匿箱灵刷怪蛋");
		add(EWItems.MIMIPOT_SPAWN_EGG.get(), "匿罐灵刷怪蛋");
		add(EWItems.CHILLED_SPAWN_EGG.get(), "羽绒僵尸刷怪蛋");
		add(EWItems.ICE_CREEPER_SPAWN_EGG.get(), "冰雪苦力怕刷怪蛋");
		add(EWItems.SNOW_CRAB_SPAWN_EGG.get(), "冰雪蟹刷怪蛋");

		add(EWItems.SCULK_MINT.get(), "幽匿薄荷");
		add(EWItems.TRAPPED_SOUL.get(), "受困灵魂");
		add(EWItems.STONE_MIMICHEST_KNIFE.get(), "箱灵飞刃");
		add(EWItems.BRICK_MIMICHEST_KNIFE.get(), "罐灵飞刃");
		add(EWItems.ICE_CRYSTAL.get(), "冰晶");
		add(EWItems.FROSTBITE_GUNPOWDER.get(), "冻伤火药");
		add(EWItems.SHARP_ICICLE.get(), "尖锐冰锥");
		add(EWItems.FROST_CHARGE.get(), "霜冻弹");
		add(EWItems.FROZEN_ARROW.get(), "冰封箭");
		add(EWItems.FRIGID_BEAK.get(), "严寒鸟喙");
		add(EWItems.FRIGID_BEAK.get().getDescriptionId() + ".desc", "其上散发着屡屡寒气");
		add(EWItems.FRIGID_BEAK.get().getDescriptionId() + ".fail", "这个空间不太合适");
		add(EWItems.FROSTY_MOA_LOOT_BAG.get(), "冰霜恐鸟战利品袋");
		add(EWItems.MOA_FEATHER.get(), "恐鸟羽毛");
		add(EWItems.MOA_SKULL.get(), "恐鸟头骨");
		add(EWItems.FROSTY_MOA_EGG.get(), "冰霜恐鸟蛋");
		add(EWItems.MOA_FEATHER_ARROW.get(), "恐羽箭");
		add(EWItems.TOTEM_OF_ICE.get(), "冰之图腾");
		add(EWItems.COLDPROOF_HAT.get(), "防寒帽");
		add(EWItems.COLDPROOF_COAT.get(), "防寒上衣");
		add(EWItems.COLDPROOF_LEGGINGS.get(), "防寒护腿");
		add(EWItems.COLDPROOF_BOOTS.get(), "防寒靴");
		add(EWItems.CRYO_SMITHING_TEMPLATE.get(), "锻造模板");
		add(Util.makeDescriptionId("upgrade", ExpeditionWorld.id("glacier_upgrade")), "冰川升级");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.applies_to")), "防寒装备");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.ingredients")), "恐鸟羽毛");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.base_slot_description")), "放入防寒装备或冰封匕首");
		add(Util.makeDescriptionId("item", ExpeditionWorld.id("smithing_template.glacier_upgrade.additions_slot_description")), "放入恐鸟羽毛");
		add(EWItems.GLACIER_HELMET.get(), "冰川头盔");
		add(EWItems.GLACIER_CHESTPLATE.get(), "冰川胸甲");
		add(EWItems.GLACIER_LEGGINGS.get(), "冰川护腿");
		add(EWItems.GLACIER_BOOTS.get(), "冰川靴子");
		add(EWItems.FROSTY_DAGGER.get(), "冰封匕首");
		add(EWItems.GLACIER_DAGGER.get(), "冰河匕首");
		add(EWItems.GLACIER_BOW.get(), "冰河弓");

		add("trim_material." + ExpeditionWorld.ID + ".trapped_soul", "受困灵魂材料");

		add(Items.POTION, EWPotions.CATWALK.get(), "猫步药水");
		add(Items.SPLASH_POTION, EWPotions.CATWALK.get(), "喷溅型猫步药水");
		add(Items.LINGERING_POTION, EWPotions.CATWALK.get(), "滞留型猫步药水");
		add(Items.TIPPED_ARROW, EWPotions.CATWALK.get(), "猫步之箭");

		add(Items.POTION, EWPotions.FETTERED.get(), "桎梏药水");
		add(Items.SPLASH_POTION, EWPotions.FETTERED.get(), "喷溅型桎梏药水");
		add(Items.LINGERING_POTION, EWPotions.FETTERED.get(), "滞留型桎梏药水");
		add(Items.TIPPED_ARROW, EWPotions.FETTERED.get(), "桎梏之箭");

		add(EWMobEffects.CATWALK.get(), "猫步");
		add(EWMobEffects.FETTERED.get(), "桎梏");
		add(EWMobEffects.FROZEN.get(), "冰封");

		add(EWEntities.FROSTBITE_TNT.get(), "冻伤TNT");
		add(EWEntities.MIMICHEST_KNIFE.get(), "箱灵飞刃");
		add(EWEntities.FROST_CHARGE.get(), "霜冻弹");
		add(EWEntities.FROZEN_ARROW.get(), "冰封箭");
		add(EWEntities.MOA_FEATHER_ARROW.get(), "恐羽箭");
		add(EWEntities.MIMICHEST.get(), "匿箱灵");
		add(EWEntities.MIMIPOT.get(), "匿罐灵");
		add(EWEntities.CHILLED.get(), "羽绒僵尸");
		add(EWEntities.ICE_CREEPER.get(), "冰雪苦力怕");
		add(EWEntities.SNOW_CRAB.get(), "冰雪蟹");

		add("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_coldproof", "防寒盔甲：摩擦");
		add("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_glacier", "冰川盔甲：摩擦");
		add("subtitles.entity." + ExpeditionWorld.ID + ".frostbite_tnt.explode", "冻伤TNT：爆炸");

		addAdvancement("root", "BC的远征物语", "BC的远征物语");
		addAdvancement("obtain_sculk_mint", "幽匿也为之垂涎欲滴", "获得幽匿薄荷");
		addAdvancement("stand_on_sculk_shrieker_with_catwalk", "刀尖舞者", "在同时拥有黑暗与猫步效果时站在一个幽匿尖啸体上");
		addAdvancement("wake_mimichest", "死者为大", "惊醒匿箱灵或匿罐灵");
		addAdvancement("full_armor_set_with_trapped_soul_trim", "身陷桎梏", "穿戴一整套使用受困灵魂作为盔甲纹饰材料的护甲");
	}

	public void add(Item item, Potion key, String name) {
		add(item.getDescriptionId() + ".effect." + Objects.requireNonNull(BuiltInRegistries.POTION.getKey(key)).getPath(), name);
	}

	public void addAdvancement(String advancement, String name, String desc) {
		add("advancements." + ExpeditionWorld.ID + "." + advancement + ".title", name);
		add("advancements." + ExpeditionWorld.ID + "." + advancement + ".description", desc);
	}
}
