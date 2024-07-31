package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.registry.EWParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class EWParticleDescriptionProvider extends ParticleDescriptionProvider {
	public EWParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, fileHelper);
	}

	@Override
	protected void addDescriptions() {
		spriteSet(EWParticles.TRAPPED_SOUL.get(), loc("trapped_soul"), 11, false);
		spriteSet(EWParticles.SNOWFLAKE.get(), loc("snowflake"), 4, false);
	}

	private ResourceLocation loc(String s) {
		return ExpeditionWorld.id(s);
	}
}
