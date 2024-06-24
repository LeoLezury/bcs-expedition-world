package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraftforge.registries.ForgeRegistries;

public class GlacierBowItem extends BowItem {
	public GlacierBowItem(Properties properties) {
		super(properties);
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow) {
		ResourceLocation location = ForgeRegistries.ENTITY_TYPES.getKey(arrow.getType());
		if (location != null && location.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
			MoaFeatherArrow featherArrow = new MoaFeatherArrow(arrow.level(), arrow.getOwner() instanceof LivingEntity living ? living : null);
			featherArrow.setPickupStack(arrow.getPickupItem());
			featherArrow.setBaseDamage(featherArrow.getBaseDamage() + 1);
			return featherArrow;
		}
		return super.customArrow(arrow);
	}
}
