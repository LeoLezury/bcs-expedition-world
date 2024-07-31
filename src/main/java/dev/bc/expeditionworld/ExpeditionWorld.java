package dev.bc.expeditionworld;

import com.mojang.logging.LogUtils;
import dev.bc.expeditionworld.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ExpeditionWorld.ID)
public class ExpeditionWorld {
	public static final String ID = "bcs_expedition_world";
	public static final Logger LOGGER = LogUtils.getLogger();

	public ExpeditionWorld(IEventBus modBus) {
		EWFeatures.FEATURES.register(modBus);
		EWEntities.ENTITY_TYPES.register(modBus);
		EWBlocks.BLOCKS.register(modBus);
		EWArmorMaterials.ARMOR_MATERIALS.register(modBus);
		EWItems.ITEMS.register(modBus);
		EWCreativeModeTabs.TABS.register(modBus);
		EWLootModifiers.LOOT_MODIFIERS.register(modBus);
		EWParticles.PARTICLE_TYPES.register(modBus);
		EWMobEffects.MOB_EFFECTS.register(modBus);
		EWPotions.POTIONS.register(modBus);
		EWSoundEvents.SOUND_EVENTS.register(modBus);
		EWCriteriaTriggers.TRIGGERS.register(modBus);
	}

	public static ResourceLocation id(String string) {
		return ResourceLocation.fromNamespaceAndPath(ID, string);
	}
}
