package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.particle.EWParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ParticleDescriptionProvider;

public class EWParticleDescriptionProvider extends ParticleDescriptionProvider {
    public EWParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(EWParticles.TRAPPED_SOUL.get(), loc("trapped_soul"), 11, false);
    }

    private ResourceLocation loc(String s) {
        return new ResourceLocation(ExpeditionWorld.MOD_ID, s);
    }

    private ResourceLocation mcLoc(String s) {
        return new ResourceLocation(s);
    }
}
