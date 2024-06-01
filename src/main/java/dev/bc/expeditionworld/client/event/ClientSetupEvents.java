package dev.bc.expeditionworld.client.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.entity.MimichestKnifeModel;
import dev.bc.expeditionworld.client.renderer.entity.FrostbiteTntRenderer;
import dev.bc.expeditionworld.client.renderer.entity.FrozenArrowRenderer;
import dev.bc.expeditionworld.client.renderer.entity.MimichestKnifeRenderer;
import dev.bc.expeditionworld.client.renderer.entity.MimichestRenderer;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.particle.EWParticles;
import net.minecraft.client.particle.SoulParticle;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ExpeditionWorld.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EWEntities.FROSTBITE_TNT.get(), FrostbiteTntRenderer::new);
        event.registerEntityRenderer(EWEntities.MIMICHEST_KNIFE.get(), MimichestKnifeRenderer::new);
        event.registerEntityRenderer(EWEntities.FROST_CHARGE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EWEntities.FROZEN_ARROW.get(), FrozenArrowRenderer::new);
        event.registerEntityRenderer(EWEntities.MIMICHEST.get(), context -> new MimichestRenderer<>(
                context, new DefaultedEntityGeoModel<>(EWEntities.MIMICHEST.getId(), true)));
        event.registerEntityRenderer(EWEntities.MIMIPOT.get(), context -> new MimichestRenderer<>(
                context, new DefaultedEntityGeoModel<>(EWEntities.MIMIPOT.getId(), true)));
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MimichestKnifeModel.LAYER_LOCATION, MimichestKnifeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(EWParticles.TRAPPED_SOUL.get(), SoulParticle.EmissiveProvider::new);
    }
}
