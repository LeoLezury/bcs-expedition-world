package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ExpeditionWorld.ID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_COLDPROOF = register("item.armor.equip_coldproof");
	public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_GLACIER = register("item.armor.equip_glacier");
	public static final DeferredHolder<SoundEvent, SoundEvent> FROSTBITE_TNT_EXPLODE = register("entity.frostbite_tnt.explode");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNOW_CRAB_HURT = register("entity.snow_crab.hurt");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNOW_CRAB_DEATH = register("entity.snow_crab.death");
	public static final DeferredHolder<SoundEvent, SoundEvent> SNOW_CRAB_STEP = register("entity.snow_crab.step");

	public static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ExpeditionWorld.id(name)));
	}
}
