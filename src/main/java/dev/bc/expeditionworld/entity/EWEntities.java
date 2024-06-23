package dev.bc.expeditionworld.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import dev.bc.expeditionworld.entity.misc.FrostbiteTnt;
import dev.bc.expeditionworld.entity.projectile.FrostCharge;
import dev.bc.expeditionworld.entity.projectile.FrozenArrow;
import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExpeditionWorld.ID);

    // misc
    public static final RegistryObject<EntityType<FrostbiteTnt>> FROSTBITE_TNT = ENTITY_TYPES.register("frostbite_tnt",
            () -> EntityType.Builder.<FrostbiteTnt>of(FrostbiteTnt::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build("frostbite_tnt"));

    // projectiles
    public static final RegistryObject<EntityType<MimichestKnife>> MIMICHEST_KNIFE = ENTITY_TYPES.register("mimichest_knife",
            () -> EntityType.Builder.<MimichestKnife>of(MimichestKnife::new, MobCategory.MISC).sized(0.25f, 0.25f).build("mimichest_knife"));
    public static final RegistryObject<EntityType<FrostCharge>> FROST_CHARGE = ENTITY_TYPES.register("frost_charge",
            () -> EntityType.Builder.<FrostCharge>of(FrostCharge::new, MobCategory.MISC).sized(0.25f, 0.25f).build("frost_charge"));
    public static final RegistryObject<EntityType<FrozenArrow>> FROZEN_ARROW = ENTITY_TYPES.register("frozen_arrow",
            () -> EntityType.Builder.<FrozenArrow>of(FrozenArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("frozen_arrow"));
    public static final RegistryObject<EntityType<MoaFeatherArrow>> MOA_FEATHER_ARROW = ENTITY_TYPES.register("moa_feather_arrow",
            () -> EntityType.Builder.<MoaFeatherArrow>of(MoaFeatherArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("moa_feather_arrow"));

    // monsters
    public static final RegistryObject<EntityType<Mimichest>> MIMICHEST = ENTITY_TYPES.register("mimichest",
            () -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 1.5f).build("mimichest"));
    public static final RegistryObject<EntityType<Mimichest>> MIMIPOT = ENTITY_TYPES.register("mimipot",
            () -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 1.5f).build("mimipot"));
}
