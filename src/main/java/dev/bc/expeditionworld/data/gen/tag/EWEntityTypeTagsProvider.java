package dev.bc.expeditionworld.data.gen.tag;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.EWEntityTags;
import dev.bc.expeditionworld.registry.EWEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EWEntityTypeTagsProvider extends EntityTypeTagsProvider {
	public EWEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, ExpeditionWorld.ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider) {
		tag(EWEntityTags.CAN_SPAWN_IN_FROZEN_CAVES).add(
			EWEntities.CHILLED.get(),
			EWEntities.ICE_CREEPER.get(),
			EWEntities.SNOW_CRAB.get(),
			EntityType.STRAY,
			EntityType.BAT
		);
		tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(
			EWEntities.MIMICHEST.get(),
			EWEntities.MIMIPOT.get()
		);
		tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(
			EWEntities.CHILLED.get(),
			EWEntities.ICE_CREEPER.get(),
			EWEntities.SNOW_CRAB.get()
		);
		tag(EntityTypeTags.UNDEAD).add(
			EWEntities.MIMICHEST.get(),
			EWEntities.MIMIPOT.get()
		);
		tag(EntityTypeTags.ZOMBIES).add(
			EWEntities.CHILLED.get()
		);
		tag(EntityTypeTags.ARTHROPOD).add(
			EWEntities.SNOW_CRAB.get()
		);
	}
}
