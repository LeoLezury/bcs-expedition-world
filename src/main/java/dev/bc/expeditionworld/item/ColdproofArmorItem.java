package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.armor.InnerColdproofArmorModel;
import dev.bc.expeditionworld.client.model.armor.OuterColdproofArmorModel;
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

public class ColdproofArmorItem extends ArmorItem {
    public ColdproofArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return (slot == EquipmentSlot.LEGS ? ExpeditionWorld.id("textures/armor/coldproof_inner.png") : ExpeditionWorld.id("textures/armor/coldproof_outer.png")).toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
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
        });
    }
}
