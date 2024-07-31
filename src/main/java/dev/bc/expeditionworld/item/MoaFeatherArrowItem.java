package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MoaFeatherArrowItem extends ArrowItem {
	public MoaFeatherArrowItem(Properties properties) {
		super(properties);
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
		return new MoaFeatherArrow(level, shooter, ammo, weapon);
	}
}
