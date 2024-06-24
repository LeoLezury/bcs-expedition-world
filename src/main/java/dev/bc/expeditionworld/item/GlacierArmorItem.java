package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.armor.InnerGlacierArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterGlacierArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GlacierArmorItem extends ArmorItem {
	public GlacierArmorItem(ArmorMaterial material, Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return (slot == EquipmentSlot.LEGS ? ExpeditionWorld.id("textures/armor/glacier_inner.png") : ExpeditionWorld.id("textures/armor/glacier_outer.png")).toString();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
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
		});
	}
}
