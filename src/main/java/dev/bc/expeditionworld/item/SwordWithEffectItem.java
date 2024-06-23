package dev.bc.expeditionworld.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SwordWithEffectItem extends SwordItem {
    private final MobEffect effect;
    private final int duration, level;

    public SwordWithEffectItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, MobEffect effect, int duration, int level, Item.Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
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
