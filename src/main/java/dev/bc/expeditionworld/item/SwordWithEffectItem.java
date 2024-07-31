package dev.bc.expeditionworld.item;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SwordWithEffectItem extends SwordItem {
	private final Holder<MobEffect> effect;
	private final int duration, level;

	public SwordWithEffectItem(Tier tier, Properties properties, Holder<MobEffect> effect, int duration, int level) {
		super(tier, properties);
		this.effect = effect;
		this.duration = duration;
		this.level = level;
	}


	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addEffect(new MobEffectInstance(effect, duration, level));
		return super.hurtEnemy(stack, target, attacker);
	}
}
