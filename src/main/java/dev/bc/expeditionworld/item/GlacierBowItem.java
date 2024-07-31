package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

public class GlacierBowItem extends BowItem {
	public GlacierBowItem(Properties properties) {
		super(properties);
	}

	@Override
	public AbstractArrow customArrow(AbstractArrow arrow, ItemStack projectileStack, ItemStack weaponStack) {
		ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.getKey(arrow.getType());
		if (location.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE) || location.getNamespace().equals(ExpeditionWorld.ID)) {
			MoaFeatherArrow featherArrow = new MoaFeatherArrow(arrow.level(), arrow.getOwner() instanceof LivingEntity living ? living : null, projectileStack, weaponStack);
			featherArrow.setBaseDamage(featherArrow.getBaseDamage() + 1);
			return featherArrow;
		}
		return super.customArrow(arrow, projectileStack, weaponStack);
	}
}
