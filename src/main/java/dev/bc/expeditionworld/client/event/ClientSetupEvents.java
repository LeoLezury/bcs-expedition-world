package dev.bc.expeditionworld.client.event;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.armor.InnerColdproofArmorModel;
import dev.bc.expeditionworld.client.model.armor.InnerGlacierArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterColdproofArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterGlacierArmorModel;
import dev.bc.expeditionworld.client.model.entity.ChilledModel;
import dev.bc.expeditionworld.client.model.entity.MimichestKnifeModel;
import dev.bc.expeditionworld.client.particle.SnowflakeParticle;
import dev.bc.expeditionworld.client.renderer.entity.*;
import dev.bc.expeditionworld.entity.EWEntities;
import dev.bc.expeditionworld.item.EWItems;
import dev.bc.expeditionworld.item.IceTotemItem;
import dev.bc.expeditionworld.particle.EWParticles;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.SoulParticle;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ExpeditionWorld.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ItemProperties.register(EWItems.TOTEM_OF_ICE.get(), IceTotemItem.BROKEN, (stack, level, entity, i) -> IceTotemItem.isBroken(stack) ? 1 : 0);
		ItemProperties.register(EWItems.GLACIER_BOW.get(), new ResourceLocation("pull"), (stack, level, entity, i) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(EWItems.GLACIER_BOW.get(), new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
	}

	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EWEntities.FROSTBITE_TNT.get(), FrostbiteTntRenderer::new);
		event.registerEntityRenderer(EWEntities.MIMICHEST_KNIFE.get(), MimichestKnifeRenderer::new);
		event.registerEntityRenderer(EWEntities.FROST_CHARGE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(EWEntities.FROZEN_ARROW.get(), FrozenArrowRenderer::new);
		event.registerEntityRenderer(EWEntities.MOA_FEATHER_ARROW.get(), MoaFeatherArrowRenderer::new);
		event.registerEntityRenderer(EWEntities.MIMICHEST.get(), context -> new MimichestRenderer<>(
			context, new DefaultedEntityGeoModel<>(EWEntities.MIMICHEST.getId(), true)));
		event.registerEntityRenderer(EWEntities.MIMIPOT.get(), context -> new MimichestRenderer<>(
			context, new DefaultedEntityGeoModel<>(EWEntities.MIMIPOT.getId(), true)));
		event.registerEntityRenderer(EWEntities.CHILLED.get(), ChilledRenderer::new);
	}

	public static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
	public static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);


	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(OuterColdproofArmorModel.LAYER_LOCATION, OuterColdproofArmorModel::createBodyLayer);
		event.registerLayerDefinition(InnerColdproofArmorModel.LAYER_LOCATION, InnerColdproofArmorModel::createBodyLayer);
		event.registerLayerDefinition(OuterGlacierArmorModel.LAYER_LOCATION, OuterGlacierArmorModel::createBodyLayer);
		event.registerLayerDefinition(InnerGlacierArmorModel.LAYER_LOCATION, InnerGlacierArmorModel::createBodyLayer);
		event.registerLayerDefinition(MimichestKnifeModel.LAYER_LOCATION, MimichestKnifeModel::createBodyLayer);
		event.registerLayerDefinition(ChilledRenderer.MAIN_LAYER, () -> LayerDefinition.create(ChilledModel.createMesh(CubeDeformation.NONE), 64, 64));
		event.registerLayerDefinition(ChilledRenderer.INNER_ARMOR_LAYER, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(INNER_ARMOR_DEFORMATION), 64, 32));
		event.registerLayerDefinition(ChilledRenderer.OUTER_ARMOR_LAYER, () -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(OUTER_ARMOR_DEFORMATION), 64, 32));
	}

	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EWParticles.TRAPPED_SOUL.get(), SoulParticle.EmissiveProvider::new);
		event.registerSpriteSet(EWParticles.SNOWFLAKE.get(), SnowflakeParticle.Provider::new);
	}
}
