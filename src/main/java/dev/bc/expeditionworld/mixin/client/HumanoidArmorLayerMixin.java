package dev.bc.expeditionworld.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.item.EWItemTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {
	@Unique
	private ItemStack expeditionWorld$armorStack;

	@Inject(method = "renderArmorPiece", at = @At("HEAD"))
	private void renderArmorPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int i, A humanoidModel, CallbackInfo ci) {
		expeditionWorld$armorStack = livingEntity.getItemBySlot(equipmentSlot);
	}

	// no remap for neoforge method
	@Inject(method = "renderTrim(Lnet/minecraft/core/Holder;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/armortrim/ArmorTrim;Lnet/minecraft/client/model/Model;Z)V", at = @At("HEAD"), remap = false, cancellable = true)
	private void renderTrim(Holder<ArmorMaterial> materialHolder, PoseStack stack, MultiBufferSource buffer, int i, ArmorTrim trim, Model model, boolean bl, CallbackInfo ci) {
		if (BuiltInRegistries.ITEM.getKey(expeditionWorld$armorStack.getItem()).getNamespace().equals(ExpeditionWorld.ID) && !expeditionWorld$armorStack.is(EWItemTags.TRIMMABLE_ARMOR)) {
			ci.cancel();
		}
	}
}
