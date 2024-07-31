package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.living.frozencaves.Chilled;
import dev.bc.expeditionworld.entity.living.frozencaves.IceCreeper;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import dev.bc.expeditionworld.entity.misc.FrostbiteTnt;
import dev.bc.expeditionworld.entity.projectile.FrostCharge;
import dev.bc.expeditionworld.entity.projectile.FrozenArrow;
import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ExpeditionWorld.ID);

	// misc
	public static final DeferredHolder<EntityType<?>, EntityType<FrostbiteTnt>> FROSTBITE_TNT = ENTITY_TYPES.register("frostbite_tnt",
		() -> EntityType.Builder.<FrostbiteTnt>of(FrostbiteTnt::new, MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build("frostbite_tnt"));

	// projectiles
	public static final DeferredHolder<EntityType<?>, EntityType<MimichestKnife>> MIMICHEST_KNIFE = ENTITY_TYPES.register("mimichest_knife",
		() -> EntityType.Builder.<MimichestKnife>of(MimichestKnife::new, MobCategory.MISC).sized(0.25f, 0.25f).build("mimichest_knife"));
	public static final DeferredHolder<EntityType<?>, EntityType<FrostCharge>> FROST_CHARGE = ENTITY_TYPES.register("frost_charge",
		() -> EntityType.Builder.<FrostCharge>of(FrostCharge::new, MobCategory.MISC).sized(0.25f, 0.25f).build("frost_charge"));
	public static final DeferredHolder<EntityType<?>, EntityType<FrozenArrow>> FROZEN_ARROW = ENTITY_TYPES.register("frozen_arrow",
		() -> EntityType.Builder.<FrozenArrow>of(FrozenArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("frozen_arrow"));
	public static final DeferredHolder<EntityType<?>, EntityType<MoaFeatherArrow>> MOA_FEATHER_ARROW = ENTITY_TYPES.register("moa_feather_arrow",
		() -> EntityType.Builder.<MoaFeatherArrow>of(MoaFeatherArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).build("moa_feather_arrow"));

	// monsters
	public static final DeferredHolder<EntityType<?>, EntityType<Mimichest>> MIMICHEST = ENTITY_TYPES.register("mimichest",
		() -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 1.5f).build("mimichest"));
	public static final DeferredHolder<EntityType<?>, EntityType<Mimichest>> MIMIPOT = ENTITY_TYPES.register("mimipot",
		() -> EntityType.Builder.of(Mimichest::new, MobCategory.MONSTER).sized(0.9f, 1.5f).build("mimipot"));
	public static final DeferredHolder<EntityType<?>, EntityType<Chilled>> CHILLED = ENTITY_TYPES.register("chilled",
		() -> EntityType.Builder.of(Chilled::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("chilled"));
	public static final DeferredHolder<EntityType<?>, EntityType<IceCreeper>> ICE_CREEPER = ENTITY_TYPES.register("ice_creeper",
		() -> EntityType.Builder.of(IceCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build("ice_creeper"));
}
