package dev.bc.expeditionworld;

import com.mojang.logging.LogUtils;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.item.loot.EWLootModifiers;
import dev.bc.expeditionworld.item.tab.EWCreativeModeTabs;
import dev.bc.expeditionworld.particle.EWParticles;
import dev.bc.expeditionworld.potion.EWMobEffects;
import dev.bc.expeditionworld.potion.EWPotions;
import dev.bc.expeditionworld.sound.EWSoundEvents;
import dev.bc.expeditionworld.world.feature.EWFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(ExpeditionWorld.ID)
public class ExpeditionWorld {
    public static final String ID = "bcs_expedition_world";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExpeditionWorld() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EWFeatures.FEATURES.register(modEventBus);
        EWEntities.ENTITY_TYPES.register(modEventBus);
        EWBlocks.BLOCKS.register(modEventBus);
        EWItems.ITEMS.register(modEventBus);
        EWCreativeModeTabs.TABS.register(modEventBus);
        EWLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        EWParticles.PARTICLE_TYPES.register(modEventBus);
        EWMobEffects.MOB_EFFECTS.register(modEventBus);
        EWPotions.POTIONS.register(modEventBus);
        EWSoundEvents.SOUND_EVENTS.register(modEventBus);

        GeckoLib.initialize();
    }

    public static ResourceLocation id(String string) {
        return new ResourceLocation(ID, string);
    }
}
