package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IceTotemItem extends Item {
    public static final ResourceLocation BROKEN = ExpeditionWorld.id("broken");

    public IceTotemItem(Properties properties) {
        super(properties);
    }

    public static boolean tryDamage(ItemStack stack) {
        if (stack.getDamageValue() < stack.getMaxDamage() - 1) {
            stack.setDamageValue(stack.getDamageValue() + 1);
            return true;
        }
        return false;
    }

    public static boolean isBroken(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack opposite = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
        if (stack.isDamaged() && opposite.is(EWItemTags.REPAIRS_TOTEM_OF_ICE)) {
            if (!player.getAbilities().instabuild) {
                opposite.shrink(1);
            }
            stack.setDamageValue(Math.max(stack.getDamageValue() - 20, 0));
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }
}
