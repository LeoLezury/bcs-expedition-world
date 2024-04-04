package dev.bc.expeditionworld;

import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.item.loot.EWLootModifiers;
import dev.bc.expeditionworld.item.tab.EWCreativeModeTabs;
import dev.bc.expeditionworld.particle.EWParticles;
import dev.bc.expeditionworld.potion.EWMobEffects;
import dev.bc.expeditionworld.potion.EWPotions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(ExpeditionWorld.MOD_ID)
public class ExpeditionWorld {
    public static final String MOD_ID = "bcs_expedition_world";

    public ExpeditionWorld() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EWEntities.ENTITY_TYPES.register(modEventBus);
        EWBlocks.BLOCKS.register(modEventBus);
        EWItems.ITEMS.register(modEventBus);
        EWCreativeModeTabs.TABS.register(modEventBus);
        EWLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        EWParticles.PARTICLE_TYPES.register(modEventBus);
        EWMobEffects.MOB_EFFECTS.register(modEventBus);
        EWPotions.POTIONS.register(modEventBus);

        GeckoLib.initialize();
    }
}
