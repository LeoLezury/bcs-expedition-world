package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class EWSoundProvider extends SoundDefinitionsProvider {
	public EWSoundProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, ExpeditionWorld.ID, helper);
	}

	private ResourceLocation mcLoc(String s) {
		return ResourceLocation.withDefaultNamespace(s);
	}

	private ResourceLocation loc(String s) {
		return ExpeditionWorld.id(s);
	}

	@Override
	public void registerSounds() {
		add(EWSoundEvents.ARMOR_EQUIP_COLDPROOF, definition().with(sound(mcLoc("item/armor/equip_leather1")), sound(mcLoc("item/armor/equip_leather2")), sound(mcLoc("item/armor/equip_leather3")), sound(mcLoc("item/armor/equip_leather4")), sound(mcLoc("item/armor/equip_leather5")), sound(mcLoc("item/armor/equip_leather6"))).subtitle("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_coldproof"));
		add(EWSoundEvents.ARMOR_EQUIP_GLACIER, definition().with(sound(mcLoc("item/armor/equip_diamond1")), sound(mcLoc("item/armor/equip_diamond2")), sound(mcLoc("item/armor/equip_diamond3")), sound(mcLoc("item/armor/equip_diamond4")), sound(mcLoc("item/armor/equip_diamond5")), sound(mcLoc("item/armor/equip_diamond6"))).subtitle("subtitles.item.armor." + ExpeditionWorld.ID + ".equip_glacier"));
		add(EWSoundEvents.FROSTBITE_TNT_EXPLODE, definition().with(sound(loc("entity/frostbite_tnt/explode"))).subtitle("subtitles.entity." + ExpeditionWorld.ID + ".frostbite_tnt.explode"));
	}
}
