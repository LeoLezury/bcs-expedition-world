package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import dev.bc.expeditionworld.registry.EWItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MimichestKnifeItem extends Item {
	public MimichestKnifeItem(Properties properties) {
		super(properties);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int time) {
		MimichestKnife knife = new MimichestKnife(level, living, stack, stack);
		int usedTime = this.getUseDuration(stack, living) - time;
		knife.shootFromRotation(living, living.getXRot(), living.getYRot(), 0.0F, 3F * (Math.min(usedTime, 50F) / 50F), 1.0F);
		if (living instanceof Player player && player.getAbilities().instabuild) {
			knife.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		}
		if (stack.is(EWItems.BRICK_MIMICHEST_KNIFE.get())) {
			knife.setBrick(true);
		}
		level.addFreshEntity(knife);
		knife.playSound(SoundEvents.TRIDENT_THROW.value());
		if (!(living instanceof Player player && player.getAbilities().instabuild)) {
			stack.shrink(1);
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player living, InteractionHand interactionHand) {
		ItemStack itemStack = living.getItemInHand(interactionHand);
		living.startUsingItem(interactionHand);
		return InteractionResultHolder.consume(itemStack);
	}
}
