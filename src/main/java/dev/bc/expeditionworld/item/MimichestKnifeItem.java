package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        MimichestKnife knife = new MimichestKnife(living, level);
        int usedTime = this.getUseDuration(stack) - time;
        knife.shootFromRotation(living, living.getXRot(), living.getYRot(), 0.0F, 3F * ((float) usedTime / this.getUseDuration(stack)), 1.0F);
        if (living instanceof Player player && player.getAbilities().instabuild) {
            knife.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        if (stack.is(EWItems.BRICK_MIMICHEST_KNIFE.get())) {
            knife.setBricks(true);
        }
        level.addFreshEntity(knife);
        level.playSound(null, knife, SoundEvents.TRIDENT_THROW, living instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE, 1.0F, 1.0F);
        if (!(living instanceof Player player && player.getAbilities().instabuild)) {
            stack.shrink(1);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 100;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player living, InteractionHand interactionHand) {
        ItemStack itemStack = living.getItemInHand(interactionHand);
        living.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemStack);
    }
}
