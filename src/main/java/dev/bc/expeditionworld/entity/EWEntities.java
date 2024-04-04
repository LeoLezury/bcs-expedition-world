package dev.bc.expeditionworld.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExpeditionWorld.MOD_ID);

    // projectiles
    public static final RegistryObject<EntityType<MimichestKnife>> MIMICHEST_KNIFE = ENTITY_TYPES.register("mimichest_knife",
            () -> EntityType.Builder.<MimichestKnife>of(MimichestKnife::new, MobCategory.MISC).sized(0.25f, 0.25f).build("mimichest_knife"));

    // monsters
    public static final RegistryObject<EntityType<Mimichest>> MIMICHEST = ENTITY_TYPES.register("mimichest",
            () -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 0.7f).build("mimichest"));
    public static final RegistryObject<EntityType<Mimichest>> MIMIPOT = ENTITY_TYPES.register("mimipot",
            () -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 0.7f).build("mimipot"));
}
