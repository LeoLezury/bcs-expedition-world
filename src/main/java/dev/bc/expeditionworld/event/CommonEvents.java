package dev.bc.expeditionworld.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.particle.EWParticles;
import dev.bc.expeditionworld.potion.EWMobEffects;
import dev.bc.expeditionworld.world.ExtendedBiomeSource;
import dev.bc.expeditionworld.world.biome.EWExtendedBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = ExpeditionWorld.MOD_ID)
public class CommonEvents {
    @SubscribeEvent
    public static void serverAboutToStart(ServerAboutToStartEvent event) {
        Registry<Biome> biomeRegistry = event.getServer().registryAccess().registryOrThrow(Registries.BIOME);
        Map<ResourceKey<Biome>, Holder<Biome>> biomes = new HashMap<>();
        for (ResourceKey<Biome> biomeKey : EWExtendedBiomes.POSSIBLE_BIOMES) {
            biomeRegistry.getHolder(biomeKey).ifPresent(holder -> {
                biomes.put(biomeKey, holder);
                ExpeditionWorld.LOGGER.info("Biome Extended: {}", biomeKey);
            });
        }
        Registry<LevelStem> levelStemRegistry = event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        for (ResourceKey<LevelStem> levelStemResourceKey : levelStemRegistry.registryKeySet()) {
            Optional<Holder.Reference<LevelStem>> stem = levelStemRegistry.getHolder(levelStemResourceKey);
            if (stem.isPresent() && stem.get().isBound() && stem.get().value().type().is(BuiltinDimensionTypes.OVERWORLD) && stem.get().value().generator().getBiomeSource() instanceof ExtendedBiomeSource source) {
                ExpeditionWorld.LOGGER.info("Overworld Biome Extended");
                source.setBiomes(biomes);
            }
        }
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living.hasEffect(EWMobEffects.FETTERED.get()) && living.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(EWParticles.TRAPPED_SOUL.get(), living.getRandomX(0.5D), living.getRandomY() - 0.25D, living.getRandomZ(0.5D), 1, 0.2, 0.2, 0.2, 0);
        }
    }
}
