package dev.bc.expeditionworld.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.armor.InnerColdproofArmorModel;
import dev.bc.expeditionworld.client.model.armor.InnerGlacierArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterColdproofArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterGlacierArmorModel;
import dev.bc.expeditionworld.client.model.entity.ChilledModel;
import dev.bc.expeditionworld.client.model.entity.MimichestKnifeModel;
import dev.bc.expeditionworld.client.particle.SnowflakeParticle;
import dev.bc.expeditionworld.client.renderer.entity.*;
import dev.bc.expeditionworld.entity.living.frozencaves.IceCreeper;
import dev.bc.expeditionworld.item.IceTotemItem;
import dev.bc.expeditionworld.registry.EWEntities;
import dev.bc.expeditionworld.registry.EWItems;
import dev.bc.expeditionworld.registry.EWParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.SoulParticle;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ExpeditionWorld.ID, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {
	@SubscribeEvent
	private static void onClientSetup(FMLClientSetupEvent event) {
		ItemProperties.register(EWItems.TOTEM_OF_ICE.get(), IceTotemItem.BROKEN, (stack, level, entity, i) -> IceTotemItem.isBroken(stack) ? 1 : 0);
		ItemProperties.register(EWItems.GLACIER_BOW.get(), ResourceLocation.withDefaultNamespace("pull"), (stack, level, entity, i) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
			}
		});
		ItemProperties.register(EWItems.GLACIER_BOW.get(), ResourceLocation.withDefaultNamespace("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
	}

	@SubscribeEvent
	private static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
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
		event.registerEntityRenderer(EWEntities.ICE_CREEPER.get(), context -> new GeoEntityRenderer<>(
			context, new DefaultedEntityGeoModel<>(EWEntities.ICE_CREEPER.getId(), true)) {
			@Override
			public void preRender(PoseStack poseStack, IceCreeper animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
				float swelling = animatable.getSwelling(partialTick);
				float factor = 1.0F + Mth.sin(swelling * 100.0F) * swelling * 0.01F;
				swelling = Mth.clamp(swelling, 0.0F, 1.0F);
				swelling *= swelling;
				swelling *= swelling;
				float xzScale = (1.0F + swelling * 0.4F) * factor;
				float yScale = (1.0F + swelling * 0.1F) / factor;
				withScale(xzScale, yScale);
				super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
			}

			@Override
			public int getPackedOverlay(IceCreeper animatable, float u, float partialTick) {
				float swelling = animatable.getSwelling(partialTick);
				return super.getPackedOverlay(animatable, (int) (swelling * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(swelling, 0.5F, 1.0F), partialTick);
			}
		});
		event.registerEntityRenderer(EWEntities.SNOW_CRAB.get(), context -> new GeoEntityRenderer<>(context, new DefaultedEntityGeoModel<>(EWEntities.SNOW_CRAB.getId(), false)));
	}

	private static final CubeDeformation OUTER_ARMOR_DEFORMATION = new CubeDeformation(1.0F);
	private static final CubeDeformation INNER_ARMOR_DEFORMATION = new CubeDeformation(0.5F);


	@SubscribeEvent
	private static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
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
	private static void onRegisterParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(EWParticles.TRAPPED_SOUL.get(), SoulParticle.EmissiveProvider::new);
		event.registerSpriteSet(EWParticles.SNOWFLAKE.get(), SnowflakeParticle.Provider::new);
	}

	@SubscribeEvent
	private static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			private InnerGlacierArmorModel<LivingEntity> innerModel;
			private OuterGlacierArmorModel<LivingEntity> outerModel;

			@Override
			public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
				if (innerModel == null || outerModel == null) {
					innerModel = new InnerGlacierArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(InnerGlacierArmorModel.LAYER_LOCATION));
					outerModel = new OuterGlacierArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(OuterGlacierArmorModel.LAYER_LOCATION));
				}

				if (itemStack.is(EWItems.GLACIER_HELMET.get()) || itemStack.is(EWItems.GLACIER_CHESTPLATE.get()) || itemStack.is(EWItems.GLACIER_BOOTS.get())) {
					return outerModel;
				} else if (itemStack.is(EWItems.GLACIER_LEGGINGS.get())) {
					return innerModel;
				}

				return IClientItemExtensions.super.getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
			}
		}, EWItems.GLACIER_HELMET.get(), EWItems.GLACIER_CHESTPLATE.get(), EWItems.GLACIER_LEGGINGS.get(), EWItems.GLACIER_BOOTS.get());
		event.registerItem(new IClientItemExtensions() {
			private InnerColdproofArmorModel<LivingEntity> innerModel;
			private OuterColdproofArmorModel<LivingEntity> outerModel;

			@Override
			public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
				if (innerModel == null || outerModel == null) {
					innerModel = new InnerColdproofArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(InnerColdproofArmorModel.LAYER_LOCATION));
					outerModel = new OuterColdproofArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(OuterColdproofArmorModel.LAYER_LOCATION));
				}

				if (itemStack.is(EWItems.COLDPROOF_HAT.get()) || itemStack.is(EWItems.COLDPROOF_COAT.get()) || itemStack.is(EWItems.COLDPROOF_BOOTS.get())) {
					return outerModel;
				} else if (itemStack.is(EWItems.COLDPROOF_LEGGINGS.get())) {
					return innerModel;
				}

				return IClientItemExtensions.super.getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
			}
		}, EWItems.COLDPROOF_HAT.get(), EWItems.COLDPROOF_COAT.get(), EWItems.COLDPROOF_LEGGINGS.get(), EWItems.COLDPROOF_BOOTS.get());
	}
}
