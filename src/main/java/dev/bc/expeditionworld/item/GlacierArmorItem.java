package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class GlacierArmorItem extends ArmorItem {
	public GlacierArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
		super(material, type, properties);
	}

	@Override
	public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
		return slot == EquipmentSlot.LEGS ? ExpeditionWorld.id("textures/armor/glacier_inner.png") : ExpeditionWorld.id("textures/armor/glacier_outer.png");
	}
}
