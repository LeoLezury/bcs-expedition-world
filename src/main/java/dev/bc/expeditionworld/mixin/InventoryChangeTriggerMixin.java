package dev.bc.expeditionworld.mixin;

import dev.bc.expeditionworld.advancement.EWCriteriaTriggers;
import dev.bc.expeditionworld.data.EWTrimMaterials;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(InventoryChangeTrigger.class)
public class InventoryChangeTriggerMixin {
    @Inject(method = "trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/item/ItemStack;)V", at = @At("TAIL"))
    private void expeditionWorld$trigger(ServerPlayer player, Inventory inventory, ItemStack stack, CallbackInfo ci) {
        AtomicBoolean shouldTrigger = new AtomicBoolean(true);
        for (ItemStack armor : player.getArmorSlots()) {
            ArmorTrim.getTrim(player.level().registryAccess(), armor).ifPresentOrElse((armorTrim -> {
                if (!armorTrim.material().is(EWTrimMaterials.TRAPPED_SOUL)) {
                    shouldTrigger.set(false);
                }
            }), () -> shouldTrigger.set(false));
        }
        if (shouldTrigger.get()) {
            EWCriteriaTriggers.FULL_ARMOR_SET_WITH_TRAPPED_SOUL_TRIM.trigger(player);
        }
    }
}
