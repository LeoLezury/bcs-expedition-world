package dev.bc.expeditionworld.item;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExpeditionWorld.MOD_ID);
    public static final RegistryObject<Item> SCULK_MINT = ITEMS.register("sculk_mint", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRAPPED_SOUL = ITEMS.register("trapped_soul", () -> new Item(new Item.Properties()));
    public static final RegistryObject<MimichestKnifeItem> STONE_MIMICHEST_KNIFE = ITEMS.register("stone_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<MimichestKnifeItem> BRICK_MIMICHEST_KNIFE = ITEMS.register("brick_mimichest_knife", () -> new MimichestKnifeItem(new Item.Properties().stacksTo(4)));
    public static final RegistryObject<BlockItem> FETTERED_CHEST = ITEMS.register("fettered_chest", () -> new BlockItem(EWBlocks.FETTERED_CHEST.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FETTERED_POT = ITEMS.register("fettered_pot", () -> new BlockItem(EWBlocks.FETTERED_POT.get(), new Item.Properties()));
}
