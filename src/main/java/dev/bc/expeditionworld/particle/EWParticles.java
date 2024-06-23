package dev.bc.expeditionworld.particle;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ExpeditionWorld.ID);
    public static final RegistryObject<SimpleParticleType> TRAPPED_SOUL = PARTICLE_TYPES.register("trapped_soul", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SNOWFLAKE = PARTICLE_TYPES.register("snowflake", () -> new SimpleParticleType(false));
}
