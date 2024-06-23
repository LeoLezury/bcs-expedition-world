package dev.bc.expeditionworld.sound;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ExpeditionWorld.ID);
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_COLDPROOF = register("item.armor.equip_coldproof");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_GLACIER = register("item.armor.equip_glacier");
    public static final RegistryObject<SoundEvent> FROSTBITE_TNT_EXPLODE = register("entity.frostbite_tnt.explode");

    public static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ExpeditionWorld.id(name)));
    }
}
