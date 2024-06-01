package dev.bc.expeditionworld.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EWEntityTags {
    public static final TagKey<EntityType<?>> CAN_SPAWN_IN_FROZEN_CAVES = create("can_spawn_in_frozen_caves");

    private static TagKey<EntityType<?>> create(String id) {
        return TagKey.create(Registries.ENTITY_TYPE, ExpeditionWorld.id(id));
    }
}
