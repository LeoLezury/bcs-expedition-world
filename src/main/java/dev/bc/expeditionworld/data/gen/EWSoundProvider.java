package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.sound.EWSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class EWSoundProvider extends SoundDefinitionsProvider {
    public EWSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, ExpeditionWorld.ID, helper);
    }

    private ResourceLocation mcLoc(String s) {
        return new ResourceLocation(s);
    }

    private ResourceLocation loc(String s) {
        return ExpeditionWorld.id(s);
    }

    @Override
    public void registerSounds() {
        add(EWSoundEvents.FROSTBITE_TNT_EXPLODE, definition().with(sound(loc("entity/frostbite_tnt/explode"))).subtitle("subtitles.entity." + ExpeditionWorld.ID + ".frostbite_tnt.explode"));
    }
}
