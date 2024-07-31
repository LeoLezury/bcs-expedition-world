package dev.bc.expeditionworld.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.living.frozencaves.Chilled;
import dev.bc.expeditionworld.entity.living.frozencaves.IceCreeper;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import dev.bc.expeditionworld.registry.EWBlocks;
import dev.bc.expeditionworld.registry.EWEntities;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = ExpeditionWorld.ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {
	@SubscribeEvent
	private static void onSetup(FMLCommonSetupEvent event) {
		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(EWBlocks.ICE_FLOWER.getId(), EWBlocks.POTTED_ICE_FLOWER);
	}

	@SubscribeEvent
	private static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(EWEntities.MIMICHEST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mimichest::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EWEntities.MIMIPOT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mimichest::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EWEntities.CHILLED.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Chilled::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(EWEntities.ICE_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IceCreeper::checkAnyLightMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	@SubscribeEvent
	private static void onRegisterEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EWEntities.MIMICHEST.get(), Mimichest.createChestAttributes().build());
		event.put(EWEntities.MIMIPOT.get(), Mimichest.createPotAttributes().build());
		event.put(EWEntities.CHILLED.get(), Zombie.createAttributes().build());
		event.put(EWEntities.ICE_CREEPER.get(), Creeper.createAttributes().build());
	}
}
