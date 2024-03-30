package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExpeditionWorld.MOD_ID);
    public static final RegistryObject<Item> SCULK_MINT = ITEMS.register("sculk_mint", () -> new Item(new Item.Properties()));
}
